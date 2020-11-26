package UserFront;

import java.awt.BorderLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.Conexion;

public class V_UserPromList extends JInternalFrame {

	 String[][] data = {{"Importante", "Me debes pasta", "20-01-2020"},
			    {"Rapido", "Aprueba", "22-04-2019"}};
			    String[] cols = {"Asunto", "Mensaje", "Fecha"};

			    public JTable table = new JTable(data, cols);
 public V_UserPromList() {
	// TODO Auto-generated constructor stub
	 JScrollPane scroll = new JScrollPane(table);
     getContentPane().add(scroll);
     setTitle("Employees");
     setResizable(true);
     setClosable(true);
     setMaximizable(true);
     setIconifiable(true);
     pack();
     setVisible(true);
 }

	
	}

