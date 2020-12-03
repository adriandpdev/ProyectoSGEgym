package AdminFront;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import EmplFront.V_EmplHome;
import UserFront.V_UserHome;
import main.Conexion;
import main.Main;

public class V_AdminEstActi extends JInternalFrame {

	private JLabel lbl;
	private JTable table;
	private JScrollPane scroll;

	public V_AdminEstActi() throws SQLException {
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setBackground(new Color(137, 13, 84));
		lbl = new JLabel("Estadísticas de actividades", SwingConstants.CENTER);
		lbl.setFont(new Font("Verdana", Font.BOLD, 40));
		lbl.setForeground(Color.WHITE);


		// ATRIBUTOS DEL PANEL PRINCIPAL
		setLayout(new BorderLayout());
		add(lbl, BorderLayout.NORTH);
		setVisible(true);
		rellenar();
	}

	public void rellenar() throws SQLException {
		String sql = "select Actividad.nombre,Reserva.idHora, COUNT(*) FROM Actividad , Reserva WHERE Actividad.idActividad=Reserva.idHora "
				+ "GROUP BY idHora ORDER BY `COUNT(*)` DESC";
		
		Conexion c = new Conexion();
		ResultSet rs = c.consulta(Main.con, sql);
		 table= new JTable (buildTableModel(rs)) {
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
		        	columnNames.add("ACTIVIDAD");
		        }
		        else if(column==2)
		        {
		        	columnNames.add("ID HORA");
		        }
		        else
		        {
		        	columnNames.add("Nº DE PERSONAS");
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



