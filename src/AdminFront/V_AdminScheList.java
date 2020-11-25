package AdminFront;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//import jdk.internal.misc.FileSystemOption;
import main.Conexion;
import main.Main;

public class V_AdminScheList extends JInternalFrame {
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

	public V_AdminScheList() throws SQLException {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		pnhorario = new JPanel();
		setTitle("Visualizar Horario");
		jlvisualizarhorario = new JLabel("HORARIO DE CLASES", SwingConstants.CENTER);
		jlvisualizarhorario.setFont(new Font("Verdana", Font.BOLD, 20));
		int cantidadfilas=0;
		String query1="select count(*) as cuenta from Horario";
		ResultSet r1= c.consulta(Main.con,query1);
			if(r1.next())
			{
				cantidadfilas=r1.getInt("cuenta");
			}
			datosatratar = new String[cantidadfilas][3];
		int cont = 0;
		String query = "select diasemana, hora, "
				+ "concat ((SELECT nombre from Actividad where IdActividad = h.IdActividad),\" - A\" ,"
				+ "(SELECT idAula from Actividad where idActividad=h.IdActividad)) as nombre"
				+ " from Horario h";
				
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
				}
				else {
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
		table.setRowHeight(table.getRowHeight() * max);

		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(true);

		scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(750, 450));
		pnhorario.add(scroll);

		getContentPane().add(jlvisualizarhorario, BorderLayout.NORTH);
		getContentPane().add(pnhorario, BorderLayout.CENTER);
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
		cont1=0;
		for (int i = 0; i < datosatratar.length; i++) {
			if (horaok.equalsIgnoreCase(datosatratar[i][1]) && diasemana.equalsIgnoreCase(datosatratar[i][0])) {
				actividad += datosatratar[i][2] + "\n";
				cont1++;
				flag = true;
			}
		}
		if (control == 0) {
			max = cont1;
			control = 1;
		} else {
			if (max < cont1) {
				max = cont1;
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
