package listado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListarPersona {

	//Metodo para crear cada tupla del jtable instanciando un objeto de la clase persona para cada una de ella y agregandolas a un arraylist
	public ArrayList<Persona> Listar_Persona(){
        ArrayList<Persona> list = new ArrayList<Persona>();
        // AQUI CLASE CONEXION --> Conectar conex = new Conectar();
        String sql = "SELECT * FROM Persona;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            //ps = conex.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Persona per = new Persona();
                per.setDni(rs.getString(1));
                per.setNombre(rs.getString(2));
                per.setApellido(rs.getString(3));
                per.setCuentabanc(rs.getString(4));
                per.setPass(rs.getString(5));
                per.setFechanac(rs.getString(6));
                per.setTelefono(rs.getInt(7));
                per.setCorreo(rs.getString(8));
                per.setRol(rs.getString(9));
                list.add(per);
                
                
                
                list.add(per);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                rs.close();
                //conex.desconectar();
            }catch(Exception ex){}
        }
        return list;
    }
}
