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
		private JPanel jcontentpane;
		private JScrollPane jscrollpane;
		private JTable jtable;
		private promocionesJtableModel model= new promocionesJtableModel();
		private Connection con;

 public V_UserPromList() {
	// TODO Auto-generated constructor stub

	 this.setSize(400,200);
	 this.setContentPane(getJContentPane());
	
	}
private JPanel getJContentPane() {
	// TODO Auto-generated method stub
	jcontentpane=new JPanel();
	jcontentpane.setLayout(null);
	return jcontentpane;
}


private JScrollPane getJscrollPane() throws ClassNotFoundException, SQLException {
	
	jscrollpane=new JScrollPane();
	jscrollpane.setBounds(new Rectangle( 18,17,252,180));
	jscrollpane.setViewportView(getJTable());
	return jscrollpane;
	
}
private JTable getJTable() throws ClassNotFoundException, SQLException {
	jtable = new JTable();
	jtable.setModel(model);
	recuperardatos();
	return jtable;
}


private void recuperardatos() throws ClassNotFoundException, SQLException {
String sql = "Select * from Promociones";
Conexion c = new Conexion();
con = c.conectar();


try {

	 
}catch (Exception e) {


}

}


}


