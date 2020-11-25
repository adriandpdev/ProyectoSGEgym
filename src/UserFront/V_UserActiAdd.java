package UserFront;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import AdminBack.Ac_AdminScheAdd;
import AdminFront.V_AdminHome;
import Login.V_Login;
import UserBack.Ac_UserActiAdd;
import main.Conexion;
import main.Main;

public class V_UserActiAdd extends JInternalFrame {
	private V_UserHome v1;
	private JLabel lblTitulo, lblDniuser, lblIdaula, lblverdniuser, lblhora, lbldiasemana;
	private JTextField txtIdclase, txtNombreactividad;
	private JComboBox cbIdaula,horaDel,actividadDel,diaAdd,horaAdd;
	private JButton btnAñadir, btnCancelar;
	private JPanel jpCentro, jpSur;
	public V_Login vent;
	private String DNI;

	Conexion c = new Conexion();

	public V_UserActiAdd(V_UserHome venti) {
		v1 = venti;

		this.setTitle("Alta de Clases");
		this.setSize(500, 500);
		this.setLocation(20, 20);
		this.setLayout(new BorderLayout());

		// Parte norte del borderlayout
		
		lblTitulo = new JLabel("RESERVA DE CLASES");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);

		// Parte central del borderlayout
		
		jpCentro = new JPanel();
		jpCentro.setLayout(new GridBagLayout());
		lblDniuser = new JLabel("Tu DNI:");
		lblverdniuser = new JLabel();
		lblverdniuser.setText(v1.getDNI1());
		lblIdaula = new JLabel("SELECCIONA LA ACTVIDAD A LA QUE QUIERES APUNTARTE:");
		cbIdaula = new JComboBox();
		lblhora = new JLabel("AQUI VA LA HORA");
		lbldiasemana = new JLabel("DIA DE LA SEMANA");
		horaDel = new JComboBox();
		horaDel.setEnabled(false);
		horaDel.addActionListener(new Ac_UserActiAdd(this));

		actividadDel = new JComboBox();
		actividadDel.setEnabled(false);
		
		String q = "select distinct(nombre) from Actividad";
		String x = "nombre";

		cbIdaula.removeAllItems();
		ArrayList<String> lista = new ArrayList<String>();
		try {
			lista = c.llenarCombo(Main.con, q, x);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < lista.size(); i++) {
			cbIdaula.addItem(lista.get(i));

		}
		


		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 3;
		jpCentro.add(lblDniuser, c);

		c.gridx = 1;
		c.gridy = 3;
		lblverdniuser.setPreferredSize(new Dimension(200, 20));
		jpCentro.add(lblverdniuser, c);

		c.gridx = 0;
		c.gridy = 4;
		jpCentro.add(lblIdaula, c);

		c.gridx = 1;
		c.gridy = 4;
		cbIdaula.setPreferredSize(new Dimension(200, 20));
		jpCentro.add(cbIdaula, c);

		c.gridx = 5;
		c.gridy = 7;
		jpCentro.add(lblhora, c);

		c.gridx = 0;
		c.gridy = 7;
		jpCentro.add(lbldiasemana, c);
		
		c.gridx = 0;
		c.gridy = 8;
		jpCentro.add(actividadDel, c);
		c.gridx = 5;
		c.gridy = 8;
		jpCentro.add(horaDel, c);

		this.getContentPane().add(jpCentro, BorderLayout.CENTER);

		// Parte sur del borderlayout
		jpSur = new JPanel();
		jpSur.setLayout(new GridLayout(1, 2));

		btnAñadir = new JButton("AÑADIR");
		btnCancelar = new JButton("CANCELAR");

		final class addbutton implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Tu usuario con el dni: " + v1.getDNI1()
						+ " se ha apuntado a la clase: " + cbIdaula.getSelectedItem());
			}
		}
		addbutton elListener = new addbutton();
		btnAñadir.addActionListener(elListener);
		
		jpSur.add(btnAñadir);
		jpSur.add(btnCancelar);

		this.getContentPane().add(jpSur, BorderLayout.SOUTH);

		this.setVisible(true);

	}

	public JTextField getTxtIdclase() {
		return txtIdclase;
	}

	public void setTxtIdclase(JTextField txtIdclase) {
		this.txtIdclase = txtIdclase;
	}

	public JTextField getTxtNombreactividad() {
		return txtNombreactividad;
	}

	public void setTxtNombreactividad(JTextField txtNombreactividad) {
		this.txtNombreactividad = txtNombreactividad;
	}

	public JButton getBtnAñadir() {
		return btnAñadir;
	}

	public void setBtnAñadir(JButton btnAñadir) {
		this.btnAñadir = btnAñadir;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JPanel getJpCentro() {
		return jpCentro;
	}

	public void setJpCentro(JPanel jpCentro) {
		this.jpCentro = jpCentro;
	}
	
	public JPanel getJpSur() {
		return jpSur;
	}

	public void setJpSur(JPanel jpSur) {
		this.jpSur = jpSur;
	}
	public JComboBox getDiaDel() {
		return diaAdd;
	}
	public JComboBox getHoraDel() {
		return horaAdd;
	}

}
