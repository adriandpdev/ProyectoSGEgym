package UserFront;

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

import AdminFront.V_AdminHome;
import AdminFront.V_AdminScheList_Renderer;
import main.Conexion;
import main.Main;

public class V_UserActiList extends JInternalFrame {
	private V_UserHome v;
	JPanel pnhorario;
	JLabel jlvisualizarhorario;
	JTable table;
	int tuplas, reservas;
	String[] nombreColumnas = { "NOMBRE ACTIVIDAD", "PROFESOR", "DNI", "AULA", "DIA", "HORA"};
	String resultado = "";
	JScrollPane scroll;
	
	public V_UserActiList(V_UserHome vent) throws SQLException {
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		v=vent;
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());

	
		jlvisualizarhorario = new JLabel("MIS RESERVAS", SwingConstants.CENTER);
		setBackground(new Color(137, 13, 84));
		
		jlvisualizarhorario.setFont(new Font("Verdana", Font.BOLD, 30));
		jlvisualizarhorario.setForeground(Color.WHITE);
		
		//Consulta Actividades por Empleado.
		String query1 = "SELECT Actividad.nombre, Persona.nombre, Reserva.DNI, Actividad.idAula, Horario.Diasemana, Horario.Hora FROM Actividad,Persona,Horario,Reserva WHERE Actividad.idActividad = Horario.IdActividad AND Horario.IdHora = Reserva.idHora AND Reserva.Dni = Persona.Dni AND Reserva.Dni = "+v.getDNI1();
		String query2 = "SELECT COUNT(*) FROM Reserva WHERE Reserva.dni = "+v.getDNI1();
		//CONECTO
		Conexion c = new Conexion();
		//Numero de Tuplas Totales.Total actividades del dni.
		ResultSet r2 = c.consulta(Main.con, query2);
		while(r2.next()) {tuplas = r2.getInt(1);}
		
		//Array ya definido.
		String [][] datosCeldas = new String[tuplas][nombreColumnas.length];
		
		
		//Rellenar tuplas
		ResultSet r1 = c.consulta(Main.con, query1);
		int fila = 0;
		while (r1.next()) {
			//-----------------------------------
			datosCeldas[fila][0] = r1.getString("Actividad.nombre");
			datosCeldas[fila][1] = r1.getString("Persona.nombre");
			datosCeldas[fila][2] = r1.getString("Reserva.DNI");
			datosCeldas[fila][3] = r1.getString("Actividad.idAula");
			datosCeldas[fila][4] = r1.getString("Horario.Diasemana");
			datosCeldas[fila][5] = r1.getString("Horario.Hora");
			fila++;
		}
		
		table = new JTable(datosCeldas, nombreColumnas) {
			public boolean editCellAt(int row, int colum, java.util.EventObject e) {
				return false;
			}
		};

		table.setDefaultRenderer(Object.class, new V_UserScheList_Renderer());
		Font font = new Font("Verdana", Font.PLAIN, 12);
		table.setFont(font);
		table.setRowHeight(table.getRowHeight() * 2);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(new Color(65,65,65));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 20));
		table.setShowGrid(true);
		scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
}
