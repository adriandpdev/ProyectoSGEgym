package AdminFront;

import java.awt.BorderLayout;
import java.awt.Font;
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
	private JLabel dAdd,dDel,aAdd,aDel,hAdd,hDel;
	private JSpinner horaAdd;
	private JButton subir, quitar;
	private String[] semana = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };
	private Font fuente;


	public V_AdminScheAdd() {
		fuente=new Font("Verdana",Font.PLAIN,15);
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

		dDel=new JLabel("Elegir día");
		dDel.setFont(fuente);

		diaDel = new JComboBox();
		for (int i = 0; i < semana.length; i++) {
			diaDel.addItem(semana[i]);
		}
		diaDel.addActionListener(new Ac_AdminScheAdd(this));
		diaDel.setSelectedIndex(-1);
		diaDel.setFont(fuente);

		hDel=new JLabel("Elegir hora");
		hDel.setFont(fuente);
		
		horaDel = new JComboBox();
		horaDel.setEnabled(false);
		horaDel.addActionListener(new Ac_AdminScheAdd(this));
		horaDel.setFont(fuente);

		aDel=new JLabel("Elegir clase");
		aDel.setFont(fuente);
		
		actividadDel = new JComboBox();
		actividadDel.setEnabled(false);
		actividadDel.setFont(fuente);

		quitar = new JButton("Eliminar");
		quitar.addActionListener(new Ac_AdminScheAdd(this));
		quitar.setFont(fuente);

		eliminar.add(new JSeparator());
		eliminar.add(new JSeparator());
		eliminar.add(new JSeparator());

		añadirTitulo("Eliminar clases", eliminar);
		eliminar.add(dDel);
		eliminar.add(new JLabel());
		eliminar.add(diaDel);

		eliminar.add(hDel);
		eliminar.add(new JLabel());
		eliminar.add(horaDel);

		eliminar.add(aDel);
		eliminar.add(new JLabel());
		eliminar.add(actividadDel);

		eliminar.add(new JLabel());
		eliminar.add(quitar);
		eliminar.add(new JLabel());
	}

	private void establecerAñadir() throws SQLException {
		eleccion = new JPanel();
		eleccion.setLayout(new GridLayout(5, 3));
		
		dAdd=new JLabel("Elegir día");
		dAdd.setFont(fuente);
		
		diaAdd = new JComboBox();
		for (int i = 0; i < semana.length; i++) {
			diaAdd.addItem(semana[i]);
		}
		diaAdd.setFont(fuente);
		
		hAdd=new JLabel("Elegir hora");
		hAdd.setFont(fuente);
		
		SpinnerDateModel model = modeloHora();
		horaAdd = new JSpinner(model);
		JSpinner.DateEditor de = new JSpinner.DateEditor(horaAdd, "HH:mm");
		de.getTextField().setEditable(false);
		horaAdd.setEditor(de);
		horaAdd.addChangeListener(new Ac_AdminScheAdd(this));
		horaAdd.setFont(fuente);
		
		aAdd=new JLabel("Elegir actividad");
		aAdd.setFont(fuente);
		
		actividadAdd = new JComboBox();
		rellenarActividades();
		actividadAdd.setFont(fuente);

		subir = new JButton("Añadir");
		subir.addActionListener(new Ac_AdminScheAdd(this));
		subir.setFont(fuente);

		añadirTitulo("Añadir clases", eleccion);

		eleccion.add(dAdd);
		eleccion.add(new JLabel());
		eleccion.add(diaAdd);

		eleccion.add(hAdd);
		eleccion.add(new JLabel());
		eleccion.add(horaAdd);

		eleccion.add(aAdd);
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
		titulo.setFont(fuente);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		p.add(new JLabel());
		p.add(titulo);
		p.add(new JLabel());
	}
}
