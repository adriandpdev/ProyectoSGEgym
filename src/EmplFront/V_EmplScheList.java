package EmplFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import AdminFront.V_AdminScheList_Renderer;
import main.Conexion;
import main.Main;

public class V_EmplScheList extends JInternalFrame {

	public V_EmplHome vent;
	JPanel pnhorario;
	JLabel jlvisualizarhorario;
	JTable table;
	String[] diasemana = { "HORA", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO" };
	String[] horas = { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
			"19:00", "20:00", "21:00", "22:00" };
	String[][] datosatratar;
	String[][] actividades = new String[horas.length][diasemana.length];
	Conexion c = new Conexion();
	String resultado = "";
	JScrollPane scroll;
	int cont1 = 0, max = 0, control = 0;

	public V_EmplScheList(V_EmplHome vent) throws SQLException {
		// TODO Auto-generated constructor stub
		this.vent = vent;
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setLayout(new BorderLayout());
		jlvisualizarhorario = new JLabel("HORARIO DE CLASES", SwingConstants.CENTER);
		setBackground(new Color(137, 13, 84));
		jlvisualizarhorario.setFont(new Font("Verdana", Font.BOLD, 22));
		jlvisualizarhorario.setForeground(Color.WHITE);
		int cantidadfilas = 0;
		String query1 = "select count(*) as cuenta from Horario";
		ResultSet r1 = c.consulta(Main.con, query1);
		if (r1.next()) {
			cantidadfilas = r1.getInt("cuenta");
		}
		datosatratar = new String[cantidadfilas][3];
		int cont = 0;
		String query = "select diasemana, hora, concat ((SELECT nombre from Actividad where IdActividad = h.IdActividad),\" - Aula \" ,"
				+ "(SELECT idAula from Actividad where idActividad=h.IdActividad)) as nombre from Horario h, Actividad a"
				+ " where h.IdActividad = a.IdActividad and a.dni like '" + vent.getDNI1() + "'";
		ResultSet r = c.consulta(Main.con, query);
		while (r.next()) {
			datosatratar[cont][0] = r.getString("diasemana");
			datosatratar[cont][1] = r.getString("hora");
			datosatratar[cont][2] = r.getString("nombre");
			cont += 1;
		}
		for (int i = 0; i < horas.length; i++) {
			for (int j = 0; j < diasemana.length; j++) {
				if (j == 0) {
					actividades[i][0] = horas[i];
				} else {
					cont = 0;
					actividades[i][j] = recorrerarrayaux(diasemana[j], horas[i]);
					cont += 1;
				}
			}
		}
		table = new JTable(actividades, diasemana) {
			public boolean editCellAt(int row, int colum, java.util.EventObject e) {
				return false;
			}
		};
		// set TableCellRenderer into a specified JTable column class
		table.setDefaultRenderer(Object.class, new V_AdminScheList_Renderer());
		Font font = new Font("Verdana", Font.PLAIN, 12);
		table.setFont(font);
		table.setRowHeight(table.getRowHeight() * 2);
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(true);
		table.getTableHeader().setBackground(new Color(65, 65, 65));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 20));
		scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(750, 450));
		getContentPane().add(jlvisualizarhorario, BorderLayout.NORTH);
		getContentPane().add(scroll, BorderLayout.CENTER);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public String recorrerarrayaux(String diasemana, String hora) {
		String horaok = hora + ":00";
		String actividad = "";
		boolean flag = false;
		cont1 = 0;
		for (int i = 0; i < datosatratar.length; i++) {
			if (horaok.equalsIgnoreCase(datosatratar[i][1]) && diasemana.equalsIgnoreCase(datosatratar[i][0])) {
				actividad += datosatratar[i][2] + "\n";
				cont1++;
				flag = true;
			}
		}
		if (flag) {
			return actividad;
		} else {
			return "";
		}
	}

	public String[] getDiasemana() {
		return diasemana;
	}

	public String[][] getActividades() {
		return actividades;
	}
}
