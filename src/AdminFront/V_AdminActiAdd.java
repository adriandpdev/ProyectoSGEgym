package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import AdminBack.Ac_AdminActiAdd;
import main.Conexion;
import main.Main;

public class V_AdminActiAdd  extends JInternalFrame{

	private JLabel lblTitulo, lblIdclase, lblNombreactividad, lblDniprofesor, lblIdaula;
	private JTextField txtIdclase, txtNombreactividad;
	private JComboBox cbDniprofesor, cbIdaula;
	private JButton btnAnadir, btnCancelar;
	private JPanel jpCentro, jpSur;
	
	Conexion c = new Conexion();

	public V_AdminActiAdd() {
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

		this.setLayout(new BorderLayout());

		// Parte norte del borderlayout
		lblTitulo = new JLabel("ALTA DE CLASES");
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 22));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		setBackground(new Color(137, 13, 84));
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);

		// Parte central del borderlayout
		jpCentro = new JPanel();
		jpCentro.setLayout(new GridBagLayout());

		
		lblIdclase = new JLabel("ID CLASE");
		lblIdclase.setFont(new Font("Verdana", Font.BOLD, 20));
		txtIdclase = new JTextField();
		txtIdclase.setFont(new Font("Verdana", Font.BOLD, 19));
		
		
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
		lblNombreactividad.setFont(new Font("Verdana", Font.BOLD, 20));
		txtNombreactividad = new JTextField();
		txtNombreactividad.setFont(new Font("Verdana", Font.BOLD, 19));
		lblDniprofesor = new JLabel("DNI PROFESOR");
		lblDniprofesor.setFont(new Font("Verdana", Font.BOLD, 20));
		cbDniprofesor = new JComboBox();
		cbDniprofesor.setFont(new Font("Verdana", Font.BOLD, 20));
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
		lblIdaula.setFont(new Font("Verdana", Font.BOLD, 20));
		cbIdaula = new JComboBox();
		cbIdaula.setFont(new Font("Verdana", Font.BOLD, 19));
		
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
		txtIdclase.setPreferredSize(new Dimension(200, 60));
		jpCentro.add(txtIdclase, c);

		c.gridx = 0;
		c.gridy = 2;
		jpCentro.add(lblNombreactividad, c);

		c.gridx = 1;
		c.gridy = 2;
		txtNombreactividad.setPreferredSize(new Dimension(200, 60));
		jpCentro.add(txtNombreactividad, c);

		c.gridx = 0;
		c.gridy = 3;
		jpCentro.add(lblDniprofesor, c);

		c.gridx = 1;
		c.gridy = 3;
		cbDniprofesor.setPreferredSize(new Dimension(200, 40));
		jpCentro.add(cbDniprofesor, c);

		c.gridx = 0;
		c.gridy = 4;
		jpCentro.add(lblIdaula, c);

		c.gridx = 1;
		c.gridy = 4;
		cbIdaula.setPreferredSize(new Dimension(200, 40));
		jpCentro.add(cbIdaula, c);

		this.getContentPane().add(jpCentro, BorderLayout.CENTER);

		// Parte sur del borderlayout
		jpSur = new JPanel();
		jpSur.setLayout(new GridLayout(1, 2));

		btnAnadir = new JButton("AÑADIR");
		btnAnadir.setBackground(new Color(137, 13, 84));
		btnAnadir.setFont(new Font("Verdana", Font.BOLD, 19));
		btnAnadir.setForeground(Color.WHITE);
		btnAnadir.setPreferredSize(new Dimension(0,50));
		
		btnCancelar = new JButton("LIMPIAR");
		btnCancelar.setBackground(new Color(137, 13, 84));
		btnCancelar.setFont(new Font("Verdana", Font.BOLD, 19));
		btnCancelar.setForeground(Color.WHITE);

		jpSur.add(btnAnadir);
		jpSur.add(btnCancelar);

		this.getContentPane().add(jpSur, BorderLayout.SOUTH);
		
		//Añadir escucha a los botones
		btnAnadir.addActionListener(new Ac_AdminActiAdd(this));
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
		return btnAnadir;
	}

	public void setBtnAñadir(JButton btnAñadir) {
		this.btnAnadir = btnAñadir;
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
