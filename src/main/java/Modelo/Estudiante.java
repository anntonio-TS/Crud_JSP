package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

        

public class Estudiante{
    private String codigo;
    private int id_puesto;
    Conexion cn;
    public Estudiante() {}

    public Estudiante(String carne, String nombre, String apellido, String direccion, String telefono, String correo, String Sangre, String nacimiento) {
        super(carne, nombre, apellido, direccion, telefono, correo, sangre, nacimiento);
        this.codigo = codigo;
        this.id_puesto = id_puesto;
    }

    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }
 public DefaultTableModel leer(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrir_conexion();
      String query = "SELECT e.id_estudiante as id,e.carne,e.nombre,e.apellido,e.direccion,e.telefono,e.sangre,p.nacimiento FROM estudiantes;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id","carne","nombre","apellido","direccion","telefono","correo","sangre","nacimiento"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[9];
      while (consulta.next()){
          datos[0] = consulta.getString("carne");
          datos[1] = consulta.getString("nombre");
          datos[2] = consulta.getString("apellido");
          datos[3] = consulta.getString("direccion");
          datos[4] = consulta.getString("telefono");
          datos[5] = consulta.getString("correo");
          datos[6] = consulta.getString("fecha_nacimiento");
          datos[7] = consulta.getString("sangre");
          datos[8] = consulta.getString("nacimiento");
          tabla.addRow(datos);
      
      }
      
     cn.cerrar_conexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
 return tabla;
 }
    @Override
    public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into estudiantes(carne,nombre,apellido,direccion,telefono,correo,sangre,nacimiento) values(?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getCodigo());
            parametro.setString(2,getNombre());
            parametro.setString(3,getApellido());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getNacimiento());
            parametro.setInt(7, getId_puesto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    @Override
    public int modificar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update estudiantes set carne = ?,nombre= ?,apellido= ?,direccion= ?,telefono= ?,correo= ?,sangre= ?,nacimiento= ? where id_estudiantes = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getCarne());
            parametro.setString(2,getNombre());
            parametro.setString(3,getApellido());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getNacimiento());
            parametro.setInt(7, getId_puesto());
            parametro.setInt(8, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    @Override
    public int eliminar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from estudiantes  where id_estudiantes = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
   
}
