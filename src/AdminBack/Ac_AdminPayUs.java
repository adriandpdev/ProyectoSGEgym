package AdminBack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AdminFront.V_AdminPayUs;
import AdminFront.V_AdminScheList_Renderer;
import main.Conexion;
import main.Main;

public class Ac_AdminPayUs implements FocusListener, ActionListener {
	private V_AdminPayUs v;
	
	public Ac_AdminPayUs(V_AdminPayUs x) {
		
		v=x;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		if (v.getDni().getText().isEmpty() || v.getDni().getText().equals("Introduce el dni que buscas")) {
			v.getDni().setText("");
			v.getDni().setForeground(Color.BLACK);
        }
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (v.getDni().getText().isEmpty()) {
			v.getDni().setText("Introduce el dni que buscas");
			v.getDni().setForeground(Color.gray);
        }
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(v.getDni().getText().isEmpty() || v.getDni().getText().length()==0 || v.getDni().getText().equals("Introduce el dni que buscas")) {
			if (v.getMes().getSelectedIndex()==-1) {
				String query="SELECT * from Transacciones where year(date)="+v.getAño().getValue();
				
				String query1="select count(*) as cuenta from Transacciones where year(date)="+v.getAño().getValue();
				try {
					rellenarTabla(query, query1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}
	private void rellenarTabla(String query, String query1) throws SQLException {
		Conexion c = new Conexion();
		v.getTabla().remove(v.getScroll());
		String[][] nuevaTabla;
		int cantidadfilas=0;
		ResultSet r1= c.consulta(Main.con,query1);
			if(r1.next())
			{
				cantidadfilas=r1.getInt("cuenta");
			}
		nuevaTabla=new String[cantidadfilas][v.getColumnas().length];
		ResultSet rs;
		rs=c.consulta(Main.con, query);
		if (rs.next()) {
			for (int i = 0; i < v.getPagos().length; i++) {
				ResultSetMetaData metaData = rs.getMetaData();
				for (int j = 1; j <= v.getPagos()[i].length; j++) {
					String columnName = metaData.getColumnName(j);
					if(!columnName.equals("Pagado"))
						v.getPagos()[i][j-1]=rs.getString(columnName);
					else {
						if(rs.getString(columnName).equals("1")) {
							v.getPagos()[i][j-1]="SI";
						}
						else {
							v.getPagos()[i][j-1]="NO";
						}
					}
				}
				rs.next();
			}
		}
		v.setPagos(nuevaTabla);
		v.setT(new JTable(new DefaultTableModel(v.getPagos(), v.getColumnas())));
		v.getT().setDefaultRenderer(Object.class, new V_AdminScheList_Renderer());
		Font font = new Font("Verdana", Font.PLAIN, 12);
		v.getT().setFont(font);
		v.getT().setRowHeight(v.getT().getRowHeight() * 5);
		
		v.getT().getTableHeader().setReorderingAllowed(false);
		v.getT().setShowGrid(true);
		v.getT().getTableHeader().setBackground(new Color(65,65,65));
		v.getT().getTableHeader().setForeground(Color.white);
		v.getT().getTableHeader().setFont(new Font("Verdana", Font.BOLD, 20));
		v.setScroll((new JScrollPane(v.getT(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS)) );
		v.getScroll().setPreferredSize(new Dimension(800, 450));
		v.getTabla().add(v.getScroll());

	}

}
