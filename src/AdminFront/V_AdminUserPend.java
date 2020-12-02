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
	private JButton btnUpdate;
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
		
		lista.setModel(modelo);
		
		this.getContentPane().add(lista, BorderLayout.CENTER);

		btnUpdate = new JButton("ACTUALIZAR");

		this.getContentPane().add(btnUpdate, BorderLayout.SOUTH);
		
        this.setVisible(true);
	}

}
