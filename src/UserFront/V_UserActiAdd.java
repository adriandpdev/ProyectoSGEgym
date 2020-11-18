package UserFront;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Login.V_Login;
import main.Conexion;
import main.Main;

public class V_UserActiAdd extends JInternalFrame {
	private JLabel lblTitulo,  lblDniuser, lblIdaula, lblverdniuser;
	private JTextField txtIdclase, txtNombreactividad;
	private JComboBox cbIdaula;
	private JButton btnAñadir, btnCancelar;
	private JPanel jpCentro, jpSur;
	public V_Login vent;

	

	Conexion c = new Conexion();

	public V_UserActiAdd() {

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
		lblverdniuser = new JLabel("AQUÍ VA SU DNI: ");

		

		lblIdaula = new JLabel("SELECCIONA EL AULA:");
		cbIdaula = new JComboBox();

		String q = "SELECT * FROM Aulas";
		String x = "idAula";

		cbIdaula.removeAllItems();
		ArrayList<String> lista = new ArrayList<String>();
		try {
			lista = c.llenarCombo(Main.con, q, x);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		this.getContentPane().add(jpCentro, BorderLayout.CENTER);

		// Parte sur del borderlayout
		jpSur = new JPanel();
		jpSur.setLayout(new GridLayout(1, 2));

		btnAñadir = new JButton("AÑADIR");
		btnCancelar = new JButton("CANCELAR");

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

}
