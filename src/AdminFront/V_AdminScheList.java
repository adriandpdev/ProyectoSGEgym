package AdminFront;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import main.Conexion;
import main.Main;

public class V_AdminScheList extends JInternalFrame {
	JPanel pnhorario;
	JLabel jlvisualizarhorario;
	JTable jttabla;
	String[] diasemana = { "HORA", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO" };
	String[] horas = { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
			"19:00", "20:00", "21:00", "22:00" };
	String[][] actividades = new String[15][8];
	Conexion c = new Conexion();
	String resultado = "";
	JScrollPane scroll;

	public V_AdminScheList() throws SQLException {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		pnhorario = new JPanel();
		setTitle("Visualizar Horario");
		
		jlvisualizarhorario = new JLabel("HORARIO DE CLASES",SwingConstants.CENTER);
		jlvisualizarhorario.setFont(new Font("Verdana", Font.BOLD, 20));
		int cont, max=0, control=0;
		for (int i = 0; i < horas.length; i++) {
			for (int j = 0; j < diasemana.length; j++) {
				if (j == 0) {
					actividades[i][0] = horas[i];
				} else {
					cont = 0;
					String query = "select a.nombre  as nombre from" + " Horario h, Actividad a"
							+ " where h.IdActividad=a.idActividad" + " and h.hora like '" + horas[i] + ":00' "
							+ "and h.Diasemana like '" + diasemana[j] + "'";

					ResultSet r = c.consulta(Main.con, query);
					while (r.next()) {
						resultado = resultado + r.getString("nombre")+"\n";
						cont += 1;
					}
					actividades[i][j] = resultado;
					resultado = "";
					if(control==0) {
						max = cont;
						control = 1;
					}else {
						if(max<cont) {
							max = cont;
						}
					}
				}
			}
		}

		JTable table = new JTable(actividades, diasemana) {
			public boolean editCellAt(int row, int colum, java.util.EventObject e) {
				return false;
			}
		};
		
	    //set TableCellRenderer into a specified JTable column class
		table.setDefaultRenderer(Object.class, new V_AdminScheList_Renderer());
		Font font = new Font("Verdana", Font.PLAIN, 12);
		table.setFont(font);
	    table.setRowHeight(table.getRowHeight() * max);
	    table.getTableHeader().setReorderingAllowed(false);
	    table.setShowGrid(true);

		scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(750,450));
		pnhorario.add(scroll);

		getContentPane().add(jlvisualizarhorario, BorderLayout.NORTH);

		getContentPane().add(pnhorario, BorderLayout.CENTER);

	}

}
