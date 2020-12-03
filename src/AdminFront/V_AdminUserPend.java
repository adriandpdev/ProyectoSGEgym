package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import main.Conexion;
import main.Main;

public class V_AdminUserPend extends JInternalFrame {
	
	Conexion c = new Conexion();
	private JLabel lblTitulo;

	private JTable table;
	private JScrollPane scroll;
	
	public V_AdminUserPend() throws SQLException
	{((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
	setLayout(new BorderLayout());
	setBackground(new Color(137, 13, 84));
	lblTitulo= new JLabel("PENDIENTES DE PAGO", SwingConstants.CENTER);
	lblTitulo.setFont(new Font("Verdana", Font.BOLD, 30));
	lblTitulo.setForeground(Color.WHITE);
	
	add(lblTitulo, BorderLayout.NORTH);

		String query = "Select Persona.nombre, Persona.dni, Transacciones.cantidad From Persona, Transacciones Where Persona.DNI=Transacciones.dniUsuario and  Transacciones.Pagado Like 0";
		ResultSet r = c.consulta(Main.con, query);
		
		 table= new JTable (buildTableModel(r)) {
				public boolean editCellAt(int row, int colum, java.util.EventObject e) {
					return false;
				}
			};
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setBackground(new Color(65,65,65));
			table.getTableHeader().setForeground(Color.white);
			table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 20));
			table.setDefaultRenderer(Object.class, new V_AdminScheList_Renderer());
			table.setRowHeight(30);
			table.setFont(new Font("Arial",Font.PLAIN,20));
			
			
			scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setPreferredSize(new Dimension(750, 450));
		
			add(scroll, BorderLayout.CENTER);
		

	}
	public DefaultTableModel  buildTableModel(ResultSet r) throws SQLException
	{
		  ResultSetMetaData metaData = (ResultSetMetaData) r.getMetaData();

		    // names of columns
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        if(column==1)
		        {
		        	columnNames.add("PERSONA");
		        }
		        else if(column==2)
		        {
		        	columnNames.add("DNI");
		        }
		        else
		        {
		         	columnNames.add("CANTIDAD");
		        }
		    }
		 //  columnNames.add(metaData.getColumnName(column));
		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (r.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(r.getObject(columnIndex));
		        }
		        data.add(vector);
		    }

		    return new DefaultTableModel(data, columnNames);
	}

}
