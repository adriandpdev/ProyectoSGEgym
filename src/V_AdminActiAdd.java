import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class V_AdminActiAdd  extends JInternalFrame{
	private JLabel lblTitulo, lblIdclase, lblNombreactividad, lblDniprofesor, lblIdaula;
	private JTextField txtIdclase, txtNombreactividad;
	private JComboBox cbDniprofesor, cbIdaula;
	private JButton btnAñadir, btnCancelar;
	private JPanel jpCentro, jpSur;

	V_AdminActiAdd(){
		this.setTitle("Alta de Clases");
		this.setSize(500, 500);
		this.setLocation(20,20);
		
		this.setLayout(new BorderLayout());
		
		//Parte norte del borderlayout
		lblTitulo = new JLabel("ALTA DE CLASES");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);
		
		
		//Parte central del borderlayout
		jpCentro = new JPanel();
		jpCentro.setLayout(new GridBagLayout());
		
		lblIdclase = new JLabel("ID CLASE");
		txtIdclase = new JTextField();
		lblNombreactividad = new JLabel("NOMBRE DE LA ACTIVIDAD");
		txtNombreactividad = new JTextField();
		lblDniprofesor = new JLabel("DNI PROFESOR");
		cbDniprofesor = new JComboBox();
		lblIdaula = new JLabel("ID AULA");
		cbIdaula = new JComboBox();
		
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
	    
	    //Parte sur del borderlayout
	    jpSur = new JPanel();
	    jpSur.setLayout(new GridLayout(1,2));
	    
	    btnAñadir = new JButton("AÑADIR");
	    btnCancelar = new JButton("CANCELAR");
	    
	    jpSur.add(btnAñadir);
	    jpSur.add(btnCancelar);
	    
	    this.getContentPane().add(jpSur, BorderLayout.SOUTH);
	    
	    this.setVisible(true);
	}
}
