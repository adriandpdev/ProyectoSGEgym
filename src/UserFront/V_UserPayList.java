package UserFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import AdminFront.V_AdminScheList_Renderer;
import main.Conexion;
import main.Fecha;
import main.Main;

public class V_UserPayList extends JInternalFrame {

	private Conexion c = new Conexion();
	private Fecha f = new Fecha();
	private JPanel jpanel;
	private V_UserHome vent;
	private JTable table;
	private JLabel jlnombre;
	private JScrollPane scroll;


	public V_UserPayList(V_UserHome vent) throws SQLException
	{((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.vent=vent;
	

		setLayout(new BorderLayout());
		setBackground(new Color(137, 13, 84));
		jlnombre= new JLabel("ÚLTIMOS PAGOS REALIZADOS", SwingConstants.CENTER);
		jlnombre.setFont(new Font("Verdana", Font.BOLD, 30));
		jlnombre.setForeground(Color.WHITE);
		
		add(jlnombre, BorderLayout.NORTH);
		consulta();
	
		
	
	
	}
	
	public void consulta() throws SQLException
	{
		String query=" select date as fecha, cantidad as cantidad from Transacciones"
				+ " where dniUsuario like '"+ vent.getDNI1() +"' "
						+ "and Pagado=1 and descripción like 'cobro'"
						+ " and date<'"+f.fechaActual()+"'order by  date asc";

		ResultSet r= c.consulta(Main.con,query);
		
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
		        	columnNames.add("FECHA");
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
