package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import AdminBack.Ac_AdminScheAdd;
import main.Conexion;
import main.Main;


public class V_AdminScheAdd extends JInternalFrame {
	private JPanel principal, eleccion, eliminar,espacio1, espacio2;
	private JComboBox diaAdd, diaDel, actividadAdd, actividadDel, horaDel;
	private JLabel dAdd,dDel,aAdd,aDel,hAdd,hDel,titl,spc1,spc2;
	private JSpinner horaAdd;
	private JButton subir, quitar;
	private String[] semana = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };
	private Font fuente;


	public V_AdminScheAdd() {
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		fuente=new Font("Verdana",Font.BOLD,20);
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
		JPanel espacio1= new JPanel();
		JPanel espacio2= new JPanel();
		espacio1.add(spc1=new JLabel());
		espacio1.setBackground(new Color(137, 13, 84));
		espacio2.add(spc2=new JLabel());
		espacio2.setBackground(new Color(137, 13, 84));
		eliminar.setLayout(new GridLayout(5, 3));
		

		dDel=new JLabel("Elegir día");
		dDel.setFont(fuente);
		dDel.setHorizontalAlignment(JTextField.CENTER);

		diaDel = new JComboBox();
		for (int i = 0; i < semana.length; i++) {
			diaDel.addItem(semana[i]);
		}
		diaDel.addActionListener(new Ac_AdminScheAdd(this));
		diaDel.setSelectedIndex(-1);
		diaDel.setFont(fuente);

		hDel=new JLabel("Elegir hora");
		hDel.setFont(fuente);
		hDel.setHorizontalAlignment(JTextField.CENTER);
		
		horaDel = new JComboBox();
		horaDel.setEnabled(false);
		horaDel.addActionListener(new Ac_AdminScheAdd(this));
		horaDel.setFont(fuente);

		aDel=new JLabel("Elegir clase");
		aDel.setFont(fuente);
		aDel.setHorizontalAlignment(JTextField.CENTER);
		
		actividadDel = new JComboBox();
		actividadDel.setEnabled(false);
		actividadDel.setFont(fuente);

		quitar = new JButton("ELIMINAR");
		quitar.setBackground(Color.WHITE);
		quitar.addActionListener(new Ac_AdminScheAdd(this));
		quitar.setFont(fuente);

		
		añadirTitulo("ELIMINAR CLASES", eliminar);
		eliminar.add(dDel);
		eliminar.add(new JLabel());
		eliminar.add(diaDel);

		eliminar.add(hDel);
		eliminar.add(new JLabel());
		eliminar.add(horaDel);

		eliminar.add(aDel);
		eliminar.add(new JLabel());
		eliminar.add(actividadDel);

		eliminar.add(espacio1);
		eliminar.add(quitar);
		eliminar.add(espacio2);
	}

	private void establecerAñadir() throws SQLException {
		eleccion = new JPanel();
		JPanel espacio1= new JPanel();
		JPanel espacio2= new JPanel();
		espacio1.add(spc1=new JLabel());
		espacio1.setBackground(new Color(137, 13, 84));
		espacio2.add(spc2=new JLabel());
		espacio2.setBackground(new Color(137, 13, 84));
		eleccion.setLayout(new GridLayout(5, 3));
		
		
		dAdd=new JLabel("Elegir día");
		dAdd.setFont(fuente);
		dAdd.setHorizontalAlignment(JTextField.CENTER);
		
		
		diaAdd = new JComboBox();
		for (int i = 0; i < semana.length; i++) {
			diaAdd.addItem(semana[i]);
		}
		diaAdd.setFont(fuente);
		
		hAdd=new JLabel("Elegir hora");
		hAdd.setFont(fuente);
		hAdd.setHorizontalAlignment(JTextField.CENTER);
		
		
		SpinnerDateModel model = modeloHora();
		horaAdd = new JSpinner(model);
		JSpinner.DateEditor de = new JSpinner.DateEditor(horaAdd, "HH:mm");
		de.getTextField().setEditable(false);
		horaAdd.setEditor(de);
		horaAdd.addChangeListener(new Ac_AdminScheAdd(this));
		horaAdd.setFont(fuente);
		
		aAdd=new JLabel("Elegir actividad");
		aAdd.setFont(fuente);
		aAdd.setHorizontalAlignment(JTextField.CENTER);
		
		
		actividadAdd = new JComboBox();
		rellenarActividades();
		actividadAdd.setFont(fuente);

		subir = new JButton("AÑADIR");
		subir.addActionListener(new Ac_AdminScheAdd(this));
		subir.setFont(fuente);
		subir.setBackground(Color.WHITE);
		
		

		añadirTitulo("AÑADIR CLASES", eleccion);
		
		eleccion.add(dAdd);
		eleccion.add(new JLabel());
		eleccion.add(diaAdd);

		eleccion.add(hAdd);
		eleccion.add(new JLabel());
		eleccion.add(horaAdd);

		eleccion.add(aAdd);
		eleccion.add(new JLabel());
		eleccion.add(actividadAdd);

		eleccion.add(espacio1);
		eleccion.add(subir);
		eleccion.add(espacio2);
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
		JPanel titulo= new JPanel();
		JPanel espacio1= new JPanel();
		JPanel espacio2= new JPanel();
		titulo.add(titl= new JLabel(t));
		titl.setFont(new Font("Verdana",Font.BOLD,22));
		titl.setForeground(Color.WHITE);
		titulo.setBackground(new Color(137, 13, 84));
		espacio1.add(spc1=new JLabel());
		espacio1.setBackground(new Color(137, 13, 84));
		espacio2.add(spc2=new JLabel());
		espacio2.setBackground(new Color(137, 13, 84));
		p.add(espacio1);
		p.add(titulo);
		p.add(espacio2);
	}
}
