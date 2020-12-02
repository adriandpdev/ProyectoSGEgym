package UserFront;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import main.Conexion;

public class V_UserPromList extends JInternalFrame {
		private JPanel jcontentpane=null;
		private JScrollPane jscrollpane=null;
		private JTable jtable=null;
		private promocionesJtableModel model= new promocionesJtableModel();
		private Connection con;
		private String asunto,mensaje,fecha;
 public V_UserPromList() throws ClassNotFoundException, SQLException {
	// TODO Auto-generated constructor stub

	 this.setSize(700,500);
	 this.setContentPane(getJContentPane());
	 this.setVisible(true);
	}
private JPanel getJContentPane() throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	
	if(jcontentpane==null) {
	jcontentpane=new JPanel();
	jcontentpane.setLayout(null);
	jcontentpane.add(getJscrollPane(),null);
	}
	return jcontentpane;
}


private JScrollPane getJscrollPane() throws ClassNotFoundException, SQLException {
	if(jscrollpane==null) {
	jscrollpane=new JScrollPane();
	jscrollpane.setBounds(new Rectangle( 18,17,552,580));
	jscrollpane.setViewportView(getJTable());
	}
	return jscrollpane;
	
}
private JTable getJTable() throws ClassNotFoundException, SQLException {
	
	if(jtable==null) {
		
	
	jtable = new JTable();
	jtable.setModel(model);
	recuperardatos();
	}
	return jtable;
}


private void recuperardatos() throws ClassNotFoundException, SQLException {
String sql = "Select * from Promociones";
Conexion c = new Conexion();
con = c.conectar();


try {

	java.sql.Statement stmt = con.createStatement();	
	ResultSet result = stmt.executeQuery("SELECT * FROM Promociones");
	// O SELECT * FROM usuarios;	            
	while (result.next()) {	               

		String[] registro= {
		asunto = result.getString("asunto"),
		mensaje = result.getString("mensaje"),	 
	    fecha = result.getString("fecha")               
		};
		 System.out.println(asunto + " " + mensaje + " " + fecha);
	model.promociones.add(registro);
	}
}catch (Exception e) {
	 con = null ;	           
	 e.printStackTrace () ;	           
	 System.out.println(" SQLException : " + e.getMessage() );	           
	 System.out.println(" SQLState : " + ((SQLException) e).getSQLState () ) ;	      
	 System.out.println(" VendorError : " + ((SQLException) e).getErrorCode () );

}

}


}


