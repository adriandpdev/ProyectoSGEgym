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
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import AdminBack.Ac_AdminScheAdd;
import main.Conexion;
import main.Main;

public class V_AdminScheAdd extends JInternalFrame{
	private JPanel principal, eleccion, tabla;
	private JComboBox dia, actividad;
	private JSpinner hora;
	private JButton subir;
	private	String[] semana= {"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};
	public V_AdminScheAdd() {
		
		try {
			AddElements();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void AddElements() throws SQLException {
		principal=new JPanel();
		principal.setLayout(new BorderLayout());
		
		eleccion=new JPanel();
		eleccion.setLayout(new GridLayout(4,3));
		dia=new JComboBox();
		for (int i = 0; i < semana.length; i++) {
			dia.addItem(semana[i]);
		}
		SpinnerDateModel model = modeloHora();
		hora = new JSpinner(model);
		JSpinner.DateEditor de = new JSpinner.DateEditor(hora, "hh:mm a");
		
		hora.setEditor(de);
		hora.addChangeListener(new Ac_AdminScheAdd(this));
		actividad=new JComboBox();
		rellenarActividades();
		
		eleccion.add(new JLabel("Elegir día"));
		eleccion.add(new JLabel());
		eleccion.add(dia);
		
		eleccion.add(new JLabel("Elegir hora"));
		eleccion.add(new JLabel());
		eleccion.add(hora);
		
		eleccion.add(new JLabel("Elegir clase"));
		eleccion.add(new JLabel());
		eleccion.add(actividad);
		
		eleccion.add(new JLabel());
		subir=new JButton("Añadir");
		eleccion.add(subir);
		eleccion.add(new JLabel());
		
		principal.add(eleccion, BorderLayout.NORTH);
		
		
		tabla=new JPanel();
		tabla.setLayout(new GridLayout(7,12));
		principal.add(tabla, BorderLayout.SOUTH);
		
		this.add(principal);
		
	}

	public JComboBox getDia() {
		return dia;
	}

	public JComboBox getActividad() {
		return actividad;
	}

	public JSpinner getHora() {
		return hora;
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
		Conexion con= new Conexion();
		ResultSet rs=con.consulta(Main.con, "select distinct(nombre) from Actividad");
		while (rs.next()) {
			actividad.addItem(rs.getString("nombre"));
		}
	}
}
