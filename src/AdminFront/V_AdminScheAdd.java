package AdminFront;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class V_AdminScheAdd extends JInternalFrame{
	private JPanel principal, eleccion, tabla;
	private JComboBox dia, actividad;
	private JSpinner hora;
	private	String[] semana= {"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"};
	public V_AdminScheAdd() {
		
		AddElements();
	}

	private void AddElements() {
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
		
		eleccion.add(new JLabel("Elegir día"));
		eleccion.add(new JLabel());
		eleccion.add(dia);
		
		eleccion.add(new JLabel("Elegir hora"));
		eleccion.add(new JLabel());
		eleccion.add(hora);
		principal.add(eleccion, BorderLayout.NORTH);
		
		tabla=new JPanel();
		tabla.setLayout(new GridLayout(7,12));
		principal.add(tabla, BorderLayout.SOUTH);
		
		this.add(principal);
		
	}

	private SpinnerDateModel modeloHora() {
		Calendar inicio = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		
		inicio.set(Calendar.HOUR_OF_DAY, 7);
		inicio.set(Calendar.MINUTE, 0);
		inicio.set(Calendar.SECOND, 0);
		
		fin.set(Calendar.HOUR_OF_DAY, 23);
		fin.set(Calendar.MINUTE, 0);
		fin.set(Calendar.SECOND, 0);

		SpinnerDateModel model = new SpinnerDateModel(inicio.getTime(), inicio.getTime(), fin.getTime(), Calendar.HOUR);
		
		return model;
	}

}
