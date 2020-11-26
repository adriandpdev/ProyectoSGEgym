package AdminFront;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class V_AdminUserList extends JFrame {
	
	private JLabel lblTitulo, lblRol, lblMes, lblNombre, lblApell, lblCuenta, lblNac, lblTlf, lblCorreo, lblPagado;
	private JTextField txtDni, txtNombre, txtApell, txtCuenta, txtNac, txtTlf, txtCoreo;
	private JComboBox cbRol, cbMes, cbDni;
	private JButton btnBuscar, btnUpdate, btnCancelar, btnMostrar;
	private JPanel jpTop, jpTopIzq, jpTopDer, jpMid, jpBot, jpCentro, jpSur,jpPagar;
	private JRadioButton rbSi, rbNo;
	
	

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
		btnBuscar.addActionListener(new Ac_AdminUserList(this));
		
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
		
		jpPagar = new JPanel();
		jpPagar.setLayout(new GridLayout(1,3,10,10));
		
		lblPagado = new JLabel("Pagado");
		rbSi = new JRadioButton("Si");
		rbNo = new JRadioButton("No");
		
		jpPagar.add(lblPagado);
		jpPagar.add(rbSi);
		jpPagar.add(rbNo);
		
		jpPagar = new JPanel();
		lblPagado = new JLabel("Pagado");
		rbSi = new JRadioButton("Si");
		rbNo = new JRadioButton("No");
		rbNo.setSelected(true);
		
		
		jpCentro.add(jpMid);
		jpCentro.add(jpBot);
		jpCentro.add(jpPagar);
		
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


	public JRadioButton getRbSi() {
		return rbSi;
	}


	public void setRbSi(JRadioButton rbSi) {
		this.rbSi = rbSi;
	}


	public JRadioButton getRbNo() {
		return rbNo;
	}


	public void setRbNo(JRadioButton rbNo) {
		this.rbNo = rbNo;
	}


	public JTextField getTxtDni() {
		return txtDni;
	}


	public void setTxtDni(JTextField txtDni) {
		this.txtDni = txtDni;
	}


	public JTextField getTxtNombre() {
		return txtNombre;
	}


	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}


	public JTextField getTxtApell() {
		return txtApell;
	}


	public void setTxtApell(JTextField txtApell) {
		this.txtApell = txtApell;
	}


	public JTextField getTxtCuenta() {
		return txtCuenta;
	}


	public void setTxtCuenta(JTextField txtCuenta) {
		this.txtCuenta = txtCuenta;
	}


	public JTextField getTxtNac() {
		return txtNac;
	}


	public void setTxtNac(JTextField txtNac) {
		this.txtNac = txtNac;
	}


	public JTextField getTxtTlf() {
		return txtTlf;
	}


	public void setTxtTlf(JTextField txtTlf) {
		this.txtTlf = txtTlf;
	}


	public JTextField getTxtCoreo() {
		return txtCoreo;
	}


	public void setTxtCoreo(JTextField txtCoreo) {
		this.txtCoreo = txtCoreo;
	}


	public JComboBox getCbRol() {
		return cbRol;
	}


	public void setCbRol(JComboBox cbRol) {
		this.cbRol = cbRol;
	}


	public JComboBox getCbMes() {
		return cbMes;
	}


	public void setCbMes(JComboBox cbMes) {
		this.cbMes = cbMes;
	}


	public JComboBox getCbDni() {
		return cbDni;
	}


	public void setCbDni(JComboBox cbDni) {
		this.cbDni = cbDni;
	}
	
	
	
}
