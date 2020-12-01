package AdminFront;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class V_AdminTransacciones extends JFrame {
	
	private JLabel lblTitulo, lblidTrans, lblDni, lblCantidad, lblDesc, lblFecha, lblPago;
	private JTextField txtidTrans, txtCant, txtDesc;
	private JComboBox cbDni;
	private JButton btnUpdate, btnCancelar;
	private JPanel jpCentro;
	private JRadioButton rbSi, rbNo;
	
	V_AdminTransacciones()
	{
		this.setTitle("Lista de Usuarios");
		this.setSize(700, 300);
		this.setLocation(20, 20);
		
		this.setLayout(new BorderLayout());
		
		//Parte norte del BorderLayout
		lblTitulo = new JLabel("PAGOS Y COBROS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);
		
		//Parte Central del BorderLayout
		jpCentro = new JPanel();
		jpCentro.setLayout(new GridLayout(3, 4, 10, 10));
		
		lblidTrans = new JLabel("Id Transaccion");
		txtidTrans = new JTextField();
		lblDni = new JLabel("DNI: ");
		cbDni = new JComboBox();
		lblCantidad = new JLabel("Cantidad:");
		txtCant = new JTextField();
		lblDesc = new JLabel("Descripcion");
		txtDesc = new JTextField();
		lblFecha = new JLabel("Fecha: ");
		//AQUI VA EL DATE PICKER
		
		
		
		
		this.setVisible(true);
	}	
	
}
