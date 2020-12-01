package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


import AdminBack.Ac_AdminPayUs;

public class V_AdminPayUs extends JInternalFrame {

	private JPanel superior, tabla;
	private JComboBox mes, año;
	private JTextField dni;
	private JTable t;
	
	public V_AdminPayUs(){
		setLayout(new BorderLayout());
		instanciarElementos();
	}
	private void instanciarElementos() {
		superior=new JPanel();
		superior.setLayout(new GridLayout(1,5));
		
		superior.add(new JLabel("Pagos usuarios-empleados"));
		dni=new JTextField("Introduce el dni que buscas");
		dni.setForeground(Color.gray);
		dni.addFocusListener(new Ac_AdminPayUs(this));
		
		mes=new JComboBox();
		for (int i = 0; i < 12; i++) {
			Date d=new Date();
			Calendar c=Calendar.getInstance();
			c.set(2, i);
			mes.addItem(c.get(Calendar.MONTH));
		}
		
		superior.add(dni);
		superior.add(mes);
		
		tabla=new JPanel();
		
		t=new JTable();
		
		
		tabla.add(t);
		
		
		add(superior,BorderLayout.NORTH);
		add(superior,BorderLayout.CENTER);
		
	}
	public JComboBox getMes() {
		return mes;
	}
	public JComboBox getAño() {
		return año;
	}
	public JTextField getDni() {
		return dni;
	}
}
