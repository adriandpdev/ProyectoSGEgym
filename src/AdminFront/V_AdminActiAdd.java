package AdminFront;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import main.Conexion;
import main.Main;

public class V_AdminActiAdd  extends JInternalFrame{

	private JLabel lblTitulo, lblIdclase, lblNombreactividad, lblDniprofesor, lblIdaula;
	private JTextField txtIdclase, txtNombreactividad;
	private JComboBox cbDniprofesor, cbIdaula;
	private JButton btnAñadir, btnCancelar;
	private JPanel jpCentro, jpSur;
	
	Conexion c = new Conexion();

	public V_AdminActiAdd() {
		this.setTitle("Alta de Clases");
		this.setSize(500, 500);
		this.setLocation(20, 20);

		this.setLayout(new BorderLayout());

		// Parte norte del borderlayout
		lblTitulo = new JLabel("ALTA DE CLASES");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);

		// Parte central del borderlayout
		jpCentro = new JPanel();
		jpCentro.setLayout(new GridBagLayout());

		lblIdclase = new JLabel("ID CLASE");
		txtIdclase = new JTextField();
		
		int idAuto = 0;
		
		try {
			idAuto= c.nuevoID(Main.con, "idActividad", "Actividad");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		txtIdclase.setText(String.valueOf(idAuto));
		txtIdclase.setEditable(false);
		
		lblNombreactividad = new JLabel("NOMBRE DE LA ACTIVIDAD");
		txtNombreactividad = new JTextField();
		lblDniprofesor = new JLabel("DNI PROFESOR");
		cbDniprofesor = new JComboBox();
		
			String qu = "SELECT DNI FROM Persona WHERE rol LIKE 'empl'";
			String xu = "dni";
			
			cbDniprofesor.removeAllItems();
			ArrayList<String> lista = new ArrayList<String>();
			try {
				lista = c.llenarCombo(Main.con, qu, xu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i <lista.size();i++)
			{
				cbDniprofesor.addItem(lista.get(i));
			}
		
		lblIdaula = new JLabel("ID AULA");
		cbIdaula = new JComboBox();
		
		String q = "SELECT * FROM Aulas";
		String x = "idAula";
		
			cbIdaula.removeAllItems();
			ArrayList<String> lista2 = new ArrayList<String>();
			try {
				lista2 = c.llenarCombo(Main.con, q, x);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i <lista2.size();i++)
			{
				cbIdaula.addItem(lista2.get(i));
			}
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		jpCentro.add(lblIdclase, c);

		c.gridx = 1;
		c.gridy = 0;
		txtIdclase.setPreferredSize(new Dimension(200, 20));
		jpCentro.add(txtIdclase, c);

		c.gridx = 0;
		c.gridy = 2;
		jpCentro.add(lblNombreactividad, c);

		c.gridx = 1;
		c.gridy = 2;
		txtNombreactividad.setPreferredSize(new Dimension(200, 20));
		jpCentro.add(txtNombreactividad, c);

		c.gridx = 0;
		c.gridy = 3;
		jpCentro.add(lblDniprofesor, c);

		c.gridx = 1;
		c.gridy = 3;
		cbDniprofesor.setPreferredSize(new Dimension(200, 20));
		jpCentro.add(cbDniprofesor, c);

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
		btnCancelar = new JButton("LIMPIAR");

		jpSur.add(btnAñadir);
		jpSur.add(btnCancelar);

		this.getContentPane().add(jpSur, BorderLayout.SOUTH);
		
		//Añadir escucha a los botones
		btnAñadir.addActionListener(new Ac_AdminActiAdd(this));
		btnCancelar.addActionListener(new Ac_AdminActiAdd(this));

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

	public JComboBox getCbDniprofesor() {
		return cbDniprofesor;
	}

	public void setCbDniprofesor(JComboBox cbDniprofesor) {
		this.cbDniprofesor = cbDniprofesor;
	}

	public JComboBox getCbIdaula() {
		return cbIdaula;
	}

	public void setCbIdaula(JComboBox cbIdaula) {
		this.cbIdaula = cbIdaula;
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
