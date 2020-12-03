package EmplFront;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import main.Conexion;
import main.Main;

public class V_EmplActiList extends JInternalFrame {
	private V_EmplHome v;
	JPanel pnhorario;
	JLabel jlvisualizarhorario;
	JTable table;
	int tuplas, reservas;
	String[] nombreColumnas = { "AULA", "NOMBRE", "DIA", "HORA", "PLAZAS LIBRES"};
	String resultado = "";
	JScrollPane scroll;
	
	public V_EmplActiList(V_EmplHome vent) throws SQLException {
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		v=vent;
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
	
		
		jlvisualizarhorario = new JLabel("MIS CLASES", SwingConstants.CENTER);

		setBackground(new Color(137, 13, 84));
		
		jlvisualizarhorario.setFont(new Font("Verdana", Font.BOLD, 30));
		jlvisualizarhorario.setForeground(Color.WHITE);
		
		//Consulta Actividades por Empleado.
		String query1 = "SELECT Actividad.idAula, Actividad.nombre, Horario.Diasemana, Horario.Hora, Aulas.aforo FROM Actividad, Horario, Persona, Aulas WHERE Persona.DNI = Actividad.dni AND Actividad.idAula = Aulas.idAula AND Actividad.idActividad = Horario.IdActividad AND Persona.DNI ="+v.getDNI1();
		String query2 = "SELECT COUNT(*) FROM Actividad,Horario WHERE Actividad.idActividad = Horario.IdActividad AND Actividad.dni = "+v.getDNI1();
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
			datosCeldas[fila][0] = String.valueOf(r1.getInt("Actividad.idAula"));
			datosCeldas[fila][1] = r1.getString("Actividad.nombre");
			datosCeldas[fila][2] = r1.getString("Horario.Diasemana");
			datosCeldas[fila][3] = String.valueOf(r1.getString("Horario.Hora"));
			//Cantidad de reservas por Actividad
			String query3 = "SELECT COUNT(*) FROM Reserva,Horario,Actividad WHERE Reserva.idHora = Horario.IdHora AND Horario.IdActividad = Actividad.idActividad AND Reserva.idHora IN (SELECT Horario.IdHora FROM Horario WHERE Horario.Hora = '"+r1.getString("Horario.Hora")+"' AND Horario.Diasemana = '"+r1.getString("Horario.Diasemana")+"' AND Horario.IdActividad IN(SELECT Actividad.idActividad FROM Actividad WHERE Actividad.nombre = '"+r1.getString("Actividad.nombre")+"'))";
			ResultSet r3 = c.consulta(Main.con, query3); 
			while(r3.next()) {reservas = r3.getInt(1);}
			//-----------------------------------
			datosCeldas[fila][4] = String.valueOf(r1.getInt("Aulas.aforo")-reservas);
			fila++;
		}
		
		table = new JTable(datosCeldas, nombreColumnas) {
			public boolean editCellAt(int row, int colum, java.util.EventObject e) {
				return false;
			}
		};

		table.setDefaultRenderer(Object.class, new V_EmpleScheList_Renderer());
		Font font = new Font("Verdana", Font.PLAIN, 20);
		table.setFont(font);
		table.setRowHeight(table.getRowHeight() * 2);
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(true);

		table.getTableHeader().setBackground(new Color(65,65,65));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 20));
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
