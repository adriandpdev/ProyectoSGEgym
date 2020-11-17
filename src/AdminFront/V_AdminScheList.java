package AdminFront;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

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

		jlvisualizarhorario = new JLabel("HORARIO DE CLASES");
		jlvisualizarhorario.setFont(new Font("Verdana", Font.BOLD, 20));

		for (int i = 0; i < horas.length; i++) {
			for (int j = 0; j < diasemana.length; j++) {
				if (j == 0) {
					actividades[i][0] = horas[i];
				} else {
					String query = "select a.nombre  as nombre from" + " Horario h, Actividad a"
							+ " where h.IdActividad=a.idActividad" + " and h.hora like '" + horas[i] + ":00' "
							+ "and h.Diasemana like '" + diasemana[j] + "'";

					ResultSet r = c.consulta(Main.con, query);
					while (r.next()) {
						resultado = resultado + "\n" + r.getString("nombre");
					}
					actividades[i][j] = resultado;
					resultado = "";
				}
			}
		}

		JTable table = new JTable(actividades, diasemana);
		Font font = new Font("Verdana", Font.PLAIN, 12);
		table.setPreferredSize(new Dimension(700, 500));
		table.setFont(font);
		table.setRowHeight(30);
		table.setShowGrid(true);

		scroll = new JScrollPane(table);
		pnhorario.add(scroll);

		getContentPane().add(jlvisualizarhorario, BorderLayout.NORTH);

		getContentPane().add(pnhorario, BorderLayout.CENTER);

	}

}
