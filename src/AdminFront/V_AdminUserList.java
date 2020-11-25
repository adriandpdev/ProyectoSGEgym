package AdminFront;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class V_AdminUserList extends JInternalFrame {
	
	private JLabel lblTitulo, lblRol, lblMes, lblNombre, lblApell, lblCuenta, lblNac, lblTlf, lblCorreo;
	private JTextField txtDni, txtNombre, txtApell, txtCuenta, txtNac, txtTlf, txtCoreo;
	private JComboBox cbRol, cbMes, cbDni;
	private JButton btnBuscar, btnUpdate, btnCancelar, btnMostrar;
	private JPanel jpTop, jpTopIzq, jpTopDer, jpMid, jpBot, jpCentro, jpSur;
	

	V_AdminUserList()
	{
		this.setTitle("Lista de Usuarios");
		this.setSize(700, 300);
		this.setLocation(20, 20);
		
		this.setLayout(new BorderLayout());
		
		//Parte norte del BorderLayout
		lblTitulo = new JLabel("ALTA DE CLASES");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);
		
		//Parte central del BorderLayout
		
		//JPanel top con las opciones de busqueda
		
		jpTop = new JPanel();
		jpTop.setLayout(new GridLayout(1,2,10,10));
		
		jpTopIzq = new JPanel();
		jpTopIzq.setLayout(new GridLayout(2,1,10,10));
		
		cbRol = new JComboBox();
			cbRol.addItem("Usuario");
			cbRol.addItem("Empleado");
		
		cbMes = new JComboBox();
			cbMes.addItem(1);
			cbMes.addItem(2);
			cbMes.addItem(3);
			cbMes.addItem(4);
			cbMes.addItem(5);
			cbMes.addItem(6);
			cbMes.addItem(7);
			cbMes.addItem(8);
			cbMes.addItem(9);
			cbMes.addItem(10);
			cbMes.addItem(11);
			cbMes.addItem(12);
		
		jpTopIzq.add(cbRol);
		jpTopIzq.add(cbMes);
		
		btnBuscar = new JButton("BUSCAR");
		
		jpTop.add(jpTopIzq);
		jpTop.add(btnBuscar);
		
		this.getContentPane().add(jpTop, BorderLayout.NORTH);
		
		//JPanel medio con la opcion de mostrar o introducir dni 
		
		jpCentro = new JPanel();
		
		jpMid = new JPanel();
		jpMid.setLayout(new GridLayout(1,3,10,10));
		
		cbDni = new JComboBox<>();
		txtDni = new JTextField();
		btnMostrar = new JButton("MOSTRAR RESULTADOS");
		
		jpMid.add(cbDni);
		jpMid.add(txtDni);
		jpMid.add(btnMostrar);
		
		jpBot = new JPanel();
		jpBot.setLayout(new GridLayout(3,4,10,10));
		
		lblNombre = new JLabel("NOMBRE:");
		txtNombre = new JTextField();
		lblApell = new JLabel("APELLIDOS:");
		txtApell = new JTextField();
		lblCuenta = new JLabel("CUENTA BANCARIA:");
		txtCuenta = new JTextField();
		lblNac = new JLabel("FECHA NACIMIENTO:");
		txtNac = new JTextField();
		lblTlf = new JLabel("TELEFONO:");
		txtTlf = new JTextField();
		lblCorreo = new JLabel("CORREO ELETRONICO");
		txtCoreo = new JTextField();
		
		jpBot.add(lblNombre);
		jpBot.add(txtNombre);
		jpBot.add(lblApell);
		jpBot.add(txtApell);
		jpBot.add(lblCuenta);
		jpBot.add(txtCuenta);
		jpBot.add(lblNac);
		jpBot.add(txtNac);
		jpBot.add(lblTlf);
		jpBot.add(txtTlf);
		jpBot.add(lblCorreo);
		jpBot.add(txtCoreo);
		
		jpCentro.add(jpMid);
		jpCentro.add(jpBot);
		
		this.getContentPane().add(jpCentro, BorderLayout.CENTER);
		
		//Zona sur del BorderLayout con los botones de confirmacion y cancelar
		
		jpSur = new JPanel();
		jpSur.setLayout(new GridLayout(1,2,10,10));
		
		btnUpdate = new JButton("ACTUALIZAR");
		btnCancelar = new JButton("CANCELAR");
		
		jpSur.add(btnUpdate);
		jpSur.add(btnCancelar);
		
		this.getContentPane().add(jpSur, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
}
