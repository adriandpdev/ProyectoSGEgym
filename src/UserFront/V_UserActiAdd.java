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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import AdminFront.V_AdminHome;
import Login.V_Login;
import main.Conexion;
import main.Main;

public class V_UserActiAdd extends JInternalFrame {
	private V_UserHome v1;
	private JLabel lblTitulo, lblDniuser, lblIdaula, lblverdniuser, lblhora, lbldiasemana;
	private JTextField txtIdclase, txtNombreactividad;
	private JComboBox cbIdaula;
	private JButton btnA�adir, btnCancelar;
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

		lblIdaula = new JLabel("SELECCIONA EL ID HORA:");
		cbIdaula = new JComboBox();

		String q = "SELECT idhora FROM Horario";
		String x = "idHora";

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
		lblhora = new JLabel("AQUI VA LA HORA");
		lbldiasemana = new JLabel("DIA DE LA SEMANA");

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

		this.getContentPane().add(jpCentro, BorderLayout.CENTER);

		// Parte sur del borderlayout
		jpSur = new JPanel();
		jpSur.setLayout(new GridLayout(1, 2));

		btnA�adir = new JButton("A�ADIR");
		btnCancelar = new JButton("CANCELAR");

		final class addbutton implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Tu usuario con el dni: " + v1.getDNI1()
						+ " se ha apuntado a la clase: " + cbIdaula.getSelectedItem());

			}
		}
		addbutton elListener = new addbutton();
		btnA�adir.addActionListener(elListener);
		
//		final class botonelegir implements ActionListener {
//			public void actionPerformed(ActionEvent e) {
//			
//			lbldiasemana.setText(c.fechayhora(Main.con, "Select Diasemana FROM Horario WHERE idHora = '"+cbIdaula.getSelectedItem()+"'","Diasemana"));
//			}
//		}
//		addbutton elListen = new addbutton();
//		cbIdaula.addActionListener(elListen);
		
		

		jpSur.add(btnA�adir);
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

	public JButton getBtnA�adir() {
		return btnA�adir;
	}

	public void setBtnA�adir(JButton btnA�adir) {
		this.btnA�adir = btnA�adir;
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
