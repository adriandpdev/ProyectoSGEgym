package main;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Conexion {
	public Connection con;

	public Connection conectar() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://80.211.5.118:3306/SGE-Gimnasio", "SGE-DAM-2020", "SGE2020root#");
		return con;
	}

	public int nuevoID(Connection con, String campo, String tabla) throws SQLException {
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("SELECT max(" + campo + ") from " + tabla);
		rs.next();
		return rs.getInt("max(" + campo + ")") + 1;
	}

	public void alta(Connection con,String query) throws SQLException {
		Statement stm = con.createStatement();
		stm.executeUpdate(query);
	}
	
	public ResultSet consulta(Connection con, String query) throws SQLException {
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		return rs;
	}
	
	public ArrayList<String> llenarCombo(Connection con, String q, String x) throws SQLException
	{
		Statement stm = con.createStatement();
		ResultSet rs = null;
		
		ArrayList<String> lista = new ArrayList<String>();
		
		try {
			rs = stm.executeQuery(q);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			while(rs.next())
			{
				lista.add(rs.getString(x));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}
	
	public boolean comprobarId(Connection con, String id) throws SQLException
	{
		boolean flag = false;
			
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("SELECT idActividad FROM Actividad");
		
		while(rs.next())
		{
			if(rs.getString("idActividad").equals(id))
			{
				flag = true;
			}
		}
		
		return flag;
	}
	
}