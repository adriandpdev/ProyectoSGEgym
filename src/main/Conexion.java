package main;

import java.sql.*;

public class Conexion {
	public Connection con;

	public Connection conectar() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://80.211.5.118:3306/SGE-Gimnasio", "SGE-DAM-2020",
				"SGE2020root#");
		return con;
	}

	public int nuevoID(Connection con, String campo, String tabla) throws SQLException {
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("SELECT max(" + campo + ") from " + tabla);
		rs.next();
		return rs.getInt("max(" + campo + ")") + 1;
	}

	public void alta(Connection con, String query) throws SQLException {
		Statement stm = con.createStatement();
		stm.executeUpdate(query);
	}

	public ResultSet consulta(Connection con, String query) throws SQLException {
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		return rs;
	}
}