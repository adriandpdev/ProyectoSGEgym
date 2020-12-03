package AdminFront;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.Conexion;
import main.Main;

public class V_AdminUserPend extends JInternalFrame {
	
	Conexion c = new Conexion();
	private JLabel lblTitulo;
	private JList lista;
	
	public V_AdminUserPend()
	{
		this.setLayout(new BorderLayout());
		
		lblTitulo = new JLabel("LISTADO DE MOROSOS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);
        
		lista = new JList();
		DefaultListModel modelo = new DefaultListModel();
		
		//modelo.addElement("Hola");
		
		String query = "Select Persona.nombre, Persona.dni, Transacciones.cantidad From Persona, Transacciones Where Persona.DNI=Transacciones.dniUsuario and  Transacciones.Pagado Like 0";
		ResultSet listado = null;
		try {
			listado = c.consulta(Main.con, query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(listado.next())
			{
				modelo.addElement("NOMBRE: "+listado.getString("nombre")+", DNI: "+listado.getString("dni")+", CANTIDAD QUE DEBE: "+listado.getString("cantidad")+"€");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lista.setModel(modelo);
		
		this.getContentPane().add(lista, BorderLayout.CENTER);
		
        this.setVisible(true);
	}

}
