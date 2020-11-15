import java.io.IOException;
import java.sql.*;

import AdminFront.V_AdminHome;
import AdminFront.*;

public class Main {
	public static Connection con;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		try {
			Conexion c = new Conexion();
			con = c.conectar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// V_Login v = new V_Login();

		V_AdminHome v = new V_AdminHome();
		// V_UserHome v = new V_UserHome();
		// V_EmplHome v = new V_EmplHome();
	}
}
