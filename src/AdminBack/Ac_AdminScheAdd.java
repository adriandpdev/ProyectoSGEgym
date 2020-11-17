package AdminBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import AdminFront.V_AdminScheAdd;
import main.Conexion;
import main.Main;

public class Ac_AdminScheAdd implements ChangeListener, ActionListener {

	V_AdminScheAdd v;

	public Ac_AdminScheAdd(V_AdminScheAdd x) {
		v = x;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == v.getHora()) {
			Date hora;
			hora = (Date) v.getHora().getValue();
			if (hora.getHours() == 23) {
				hora.setHours(8);
				v.getHora().setValue(hora);
			}
			if (hora.getHours() == 7) {
				hora.setHours(22);
				v.getHora().setValue(hora);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == v.getDiaDel()) {
			mostrarHoras();
		}
		if (e.getSource() == v.getSubir()) {
			try {
				if (!comprobarAula()) {

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private boolean comprobarAula() throws SQLException {
		boolean ocupado = false;
		Conexion con = new Conexion();
		int aula;
		ResultSet rs = con.consulta(Main.con,
				"Select * from Actividad , Horario  where Actividad.idActividad=Horario.idActividad and Horario.Diasemana like '"
						+ v.getDia().getSelectedItem().toString() + "' and Horario.hora like '"
						+ v.getHora().getValue().toString() + ":00'");
		ResultSet rs2 = con.consulta(Main.con, "select idaula from Actividad where nombre like '"
				+ v.getActividad().getSelectedItem().toString() + "'");
		rs2.next();
		aula = rs2.getInt("idaula");

		while (rs.next()) {

			if (rs.getInt("Actividad.idaula") == aula)
				ocupado = true;
			System.out.println(ocupado);
		}
		return ocupado;
	}

	private void mostrarHoras() {
		if (v.getDiaDel().getSelectedIndex() != -1) {
			v.getHoraDel().removeAllItems();
			try {
				rellenarHoraBorrado();
				mostrarClases();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void mostrarClases() {
		v.getActividadDel().removeAllItems();

		try {
			rellenarClaseBorrado();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

	private void rellenarHoraBorrado() throws SQLException {
		Conexion con = new Conexion();
		ResultSet rs = con.consulta(Main.con, "select distinct(hora) from Horario where diasemana like '"
				+ v.getDiaDel().getSelectedItem().toString() + "' order by hora asc");
		while (rs.next()) {
			v.getHoraDel().addItem(rs.getTime("hora"));
		}
		v.getHoraDel().setEnabled(true);
	}

	private void rellenarClaseBorrado() throws SQLException {
		Conexion con = new Conexion();
		ResultSet rs = con.consulta(Main.con,
				"select nombre from Actividad, Horario where Actividad.idActividad = Horario.idActividad and Diasemana like '"
						+ v.getDiaDel().getSelectedItem().toString() + "' and hora like '"
						+ v.getHoraDel().getSelectedItem().toString() + "'");
		while (rs.next()) {
			v.getActividadDel().addItem(rs.getString("nombre"));
		}
		v.getActividadDel().setEnabled(true);
	}
}
