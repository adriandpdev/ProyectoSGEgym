package UserFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mysql.cj.xdevapi.Statement;

import main.Conexion;

public class V_UserAvList extends JInternalFrame {
		private JPanel jcontentpane=null;
		private JScrollPane jscrollpane=null;
		private JTable jtable=null;
		private avisosJtableModel model2 = new avisosJtableModel();
		private JLabel Titulo;
		private Connection con;
		private String asunto,mensaje,fecha;
 public V_UserAvList() throws ClassNotFoundException, SQLException {
	// TODO Auto-generated constructor stub
	 ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
	 this.setSize(700,500);
	 this.setLayout(new BorderLayout());
		JPanel Norte = new JPanel();
		 Norte.add(Titulo = new JLabel("Ultimos Avisos"));
		 Norte.setBackground(new Color(137, 13, 84));
		 Titulo.setFont(new Font("Verdana",Font.BOLD,22));
		 Titulo.setForeground(Color.WHITE);
		 this.add(Norte,BorderLayout.NORTH);
		 this.add(getJContentPane());
		 this.setVisible(true);
	}
private JPanel getJContentPane() throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	
	if(jcontentpane==null) {
	jcontentpane=new JPanel();
	jcontentpane.setLayout(null);
	jcontentpane.add(getJscrollPane());
	}
	return jcontentpane;
}


private JScrollPane getJscrollPane() throws ClassNotFoundException, SQLException {
	if(jscrollpane==null) {
	jscrollpane=new JScrollPane(jtable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	jscrollpane.setBounds( 0,0,1350,600);
	jscrollpane.setViewportView(getJTable());
	}
	return jscrollpane;
	
}
private JTable getJTable() throws ClassNotFoundException, SQLException {
	recuperardatos();
	if(jtable==null) {
	jtable = new JTable();
	jtable.setModel(model2);
	JTableHeader head = jtable.getTableHeader();
	TableColumnModel tcm = head.getColumnModel();
	TableColumn tabCM = tcm.getColumn(0);
	TableColumn tabCM2 = tcm.getColumn(1);
	TableColumn tabCM3 = tcm.getColumn(2);
	tabCM.setHeaderValue("Asunto");
	tabCM2.setHeaderValue("Mensaje");
	tabCM3.setHeaderValue("Fecha");
	 jtable.getTableHeader().setReorderingAllowed(false); 
	 jtable.setShowGrid(true);
	 jtable.getTableHeader().setBackground(new Color(65,65,65));
	 jtable.getTableHeader().setForeground(Color.white);
	 jtable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 20));
	 jtable.setRowHeight(jtable.getRowHeight() * 5);
	  Font font = new Font("Verdana", Font.PLAIN, 12); 
	  jtable.setFont(font);
	 jtable.repaint();
	}

	return jtable;
}


private void recuperardatos() throws ClassNotFoundException, SQLException {
Conexion c = new Conexion();
con = c.conectar();


try {

	java.sql.Statement stmt = con.createStatement();	
	ResultSet result = stmt.executeQuery("SELECT * FROM Avisos");
	// O SELECT * FROM usuarios;	            
	while (result.next()) {	               

		String[] registro= {
		asunto = result.getString("asunto"),
		mensaje = result.getString("mensaje"),	 
	    fecha = result.getString("fecha")               
		};
	model2.avisos.add(registro);
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

