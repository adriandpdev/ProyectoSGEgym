package AdminFront;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import AdminBack.Ac_AdminScheAdd;
import main.Conexion;
import main.Main;

public class V_AdminScheAdd extends JInternalFrame {
	private JPanel principal, eleccion, eliminar;
	private JComboBox diaAdd, diaDel, actividadAdd, actividadDel, horaDel;

	public JComboBox getDiaDel() {
		return diaDel;
	}

	public JComboBox getActividadDel() {
		return actividadDel;
	}

	public JComboBox getHoraDel() {
		return horaDel;
	}

	public JButton getQuitar() {
		return quitar;
	}

	private JSpinner horaAdd;
	private JButton subir, quitar;
	private String[] semana = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };

	public V_AdminScheAdd() {

		try {
			AddElements();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void AddElements() throws SQLException {
		principal = new JPanel();
		principal.setLayout(new GridLayout(2, 1));

		establecerAñadir();

		establecerEliminar();

		principal.add(eleccion);
		principal.add(eliminar);

		this.add(principal);

	}

	private void establecerEliminar() {
		eliminar = new JPanel();
		eliminar.setLayout(new GridLayout(6, 3));

		diaDel = new JComboBox();
		for (int i = 0; i < semana.length; i++) {
			diaDel.addItem(semana[i]);
		}
		diaDel.addActionListener(new Ac_AdminScheAdd(this));
		diaDel.setSelectedIndex(-1);

		horaDel = new JComboBox();
		horaDel.setEnabled(false);
		horaDel.addActionListener(new Ac_AdminScheAdd(this));

		actividadDel = new JComboBox();
		actividadDel.setEnabled(false);

		quitar = new JButton("Eliminar");
		quitar.addActionListener(new Ac_AdminScheAdd(this));

		eliminar.add(new JSeparator());
		eliminar.add(new JSeparator());
		eliminar.add(new JSeparator());

		añadirTitulo("Eliminar clases", eliminar);

		eliminar.add(new JLabel("Elegir día"));
		eliminar.add(new JLabel());
		eliminar.add(diaDel);

		eliminar.add(new JLabel("Elegir hora"));
		eliminar.add(new JLabel());
		eliminar.add(horaDel);

		eliminar.add(new JLabel("Elegir clase"));
		eliminar.add(new JLabel());
		eliminar.add(actividadDel);

		eliminar.add(new JLabel());
		eliminar.add(quitar);
		eliminar.add(new JLabel());
	}

	private void establecerAñadir() throws SQLException {
		eleccion = new JPanel();
		eleccion.setLayout(new GridLayout(5, 3));
		diaAdd = new JComboBox();
		for (int i = 0; i < semana.length; i++) {
			diaAdd.addItem(semana[i]);
		}
		SpinnerDateModel model = modeloHora();
		horaAdd = new JSpinner(model);
		JSpinner.DateEditor de = new JSpinner.DateEditor(horaAdd, "HH:mm");

		horaAdd.setEditor(de);
		horaAdd.addChangeListener(new Ac_AdminScheAdd(this));
		actividadAdd = new JComboBox();
		rellenarActividades();

		subir = new JButton("Añadir");
		subir.addActionListener(new Ac_AdminScheAdd(this));

		añadirTitulo("Añadir clases", eleccion);

		eleccion.add(new JLabel("Elegir día"));
		eleccion.add(new JLabel());
		eleccion.add(diaAdd);

		eleccion.add(new JLabel("Elegir hora"));
		eleccion.add(new JLabel());
		eleccion.add(horaAdd);

		eleccion.add(new JLabel("Elegir clase"));
		eleccion.add(new JLabel());
		eleccion.add(actividadAdd);

		eleccion.add(new JLabel());
		eleccion.add(subir);
		eleccion.add(new JLabel());
	}

	public JComboBox getDia() {
		return diaAdd;
	}

	public JComboBox getActividad() {
		return actividadAdd;
	}

	public JSpinner getHora() {
		return horaAdd;
	}

	public JButton getSubir() {
		return subir;
	}

	private SpinnerDateModel modeloHora() {

		Calendar inicio = Calendar.getInstance();

		inicio.set(Calendar.HOUR_OF_DAY, 12);
		inicio.set(Calendar.MINUTE, 0);
		inicio.set(Calendar.SECOND, 0);

		SpinnerDateModel model = new SpinnerDateModel(inicio.getTime(), null, null, Calendar.HOUR);

		return model;
	}

	private void rellenarActividades() throws SQLException {
		Conexion con = new Conexion();
		ResultSet rs = con.consulta(Main.con, "select distinct(nombre) from Actividad");
		while (rs.next()) {
			actividadAdd.addItem(rs.getString("nombre"));
		}
	}

	private void añadirTitulo(String t, JPanel p) {
		JLabel titulo = new JLabel(t);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		p.add(new JLabel());
		p.add(titulo);
		p.add(new JLabel());
	}
}
