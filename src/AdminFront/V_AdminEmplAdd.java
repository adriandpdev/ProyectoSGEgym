package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import AdminBack.Ac_AdminEmplAdd;
import AdminBack.Ac_AdminUserAdd;

public class V_AdminEmplAdd extends JInternalFrame {

	private JLabel Titulo, Dni, Nombre, CCC, email, Fecha, Apellidos, Contraseña, Telefono, minimo;
	private JTextField txtDni, txtNombre, txtCCC, txtemail, txtApellidos, txtContraseña, txtTelefono;
	private JButton Añadir, Limpiar;
	private JDateChooser date;
	
	public static String NUMEROS = "0123456789";
	 
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
 
	public static String ESPECIALES = "ñÑ@#$%&";
 

	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}
 
	public static String getPassword() {
		return getPassword(8);
	}
 
	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}
 
	public static String getPassword(String key, int length) {
		String pswd = "";
 
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
 
		return pswd;
	}

	public V_AdminEmplAdd() {
		CreateForm();
	}

	public V_AdminEmplAdd(String name) { // Añadir todos los datos necesarios
		CreateForm();
		FillFields();
	}

	private void CreateForm() {
		this.setLocation(100, 100);
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

		JPanel Norte = new JPanel();
		Norte.add(Titulo = new JLabel("ALTA PROFESOR"));
		Norte.setBackground(new Color(137, 13, 84));
		Titulo.setFont(new Font("Verdana",Font.BOLD,22));
		Titulo.setForeground(Color.WHITE);
		

		JPanel Centro = new JPanel();
		Centro.setLayout(new GridLayout(4, 4, 10, 10));
		Centro.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));
		Centro.add(Nombre = new JLabel("Nombre"));
		Nombre.setFont(new Font("Verdana",Font.BOLD,20));
		Nombre.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(txtNombre = new JTextField());
		txtNombre.setFont(new Font("Verdana",Font.BOLD,20));
		Centro.add(Apellidos = new JLabel("Apellidos"));
		Apellidos.setFont(new Font("Verdana",Font.BOLD,20));
		Apellidos.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(txtApellidos = new JTextField());
		txtApellidos.setFont(new Font("Verdana",Font.BOLD,20));
		Centro.add(Dni = new JLabel("DNI"));
		Dni.setFont(new Font("Verdana",Font.BOLD,20));
		Dni.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(txtDni = new JTextField());
		txtDni.setFont(new Font("Verdana",Font.BOLD,20));
		Centro.add(CCC = new JLabel("CCC"));
		CCC.setFont(new Font("Verdana",Font.BOLD,20));
		CCC.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(txtCCC = new JTextField());
		txtCCC.setFont(new Font("Verdana",Font.BOLD,20));
		Centro.add(email = new JLabel("E-mail"));
		email.setFont(new Font("Verdana",Font.BOLD,20));
		email.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(txtemail = new JTextField());
		txtemail.setFont(new Font("Verdana",Font.BOLD,20));
		date = new JDateChooser();
		date.setFont(new Font("Verdana",Font.BOLD,20));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -200);
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.YEAR, -16);
		Date min = cal.getTime();
		Date max = cal2.getTime();
		date.setSelectableDateRange(min, max);
		date.setDate(max);

		JPanel Minimo = new JPanel();
		Minimo.setLayout(new GridLayout(2,1));
		Minimo.add(Fecha = new JLabel("Fecha de Nacimiento"));
		Fecha.setFont(new Font("Verdana",Font.BOLD,20));
		Fecha.setHorizontalAlignment(JTextField.CENTER);
		Minimo.add(minimo= new JLabel("(Minimo 16 años)"));
		minimo.setFont(new Font("Verdana",Font.BOLD,20));
		minimo.setHorizontalAlignment(JTextField.CENTER);
		
		Centro.add(Minimo);
		Centro.add(date);
		Centro.add(Contraseña = new JLabel("Contraseña"));
		Contraseña.setFont(new Font("Verdana",Font.BOLD,20));
		Contraseña.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(txtContraseña = new JTextField());
		txtContraseña.setFont(new Font("Verdana",Font.BOLD,20));
		Centro.add(Telefono = new JLabel("Telefono"));
		Telefono.setFont(new Font("Verdana",Font.BOLD,20));
		Telefono.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(txtTelefono = new JTextField());
		txtTelefono.setFont(new Font("Verdana",Font.BOLD,20));
		txtContraseña.setEnabled(false);
		txtContraseña.setText(getPassword());
		
		JPanel Sur = new JPanel();
		Sur.setBackground(new Color(137, 13, 84));
		Sur.add(Añadir = new JButton("Añadir"));
		Añadir.addActionListener(new Ac_AdminEmplAdd(this));
		Añadir.setFont(new Font("Verdana",Font.BOLD,20));
		Sur.add(Limpiar = new JButton("Limpiar"));
		Limpiar.addActionListener(new Ac_AdminEmplAdd(this));
		Limpiar.setFont(new Font("Verdana",Font.BOLD,20));

		Container c = getContentPane();
		c.add(Norte, BorderLayout.NORTH);
		c.add(Centro, BorderLayout.CENTER);
		c.add(Sur, BorderLayout.SOUTH);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

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

	public JTextField getTxtCCC() {
		return txtCCC;
	}

	public void setTxtCCC(JTextField txtCCC) {
		this.txtCCC = txtCCC;
	}

	public JTextField getTxtemail() {
		return txtemail;
	}

	public void setTxtemail(JTextField txtemail) {
		this.txtemail = txtemail;
	}

	public JTextField getTxtApellidos() {
		return txtApellidos;
	}

	public void setTxtApellidos(JTextField txtApellidos) {
		this.txtApellidos = txtApellidos;
	}

	public JTextField getTxtContraseña() {
		return txtContraseña;
	}

	public void setTxtContraseña(JTextField txtContraseña) {
		this.txtContraseña = txtContraseña;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}
	
	public JDateChooser getDate() {
		return date;
	}

	public void setDate(JDateChooser date) {
		this.date = date;
	}

	private void FillFields() {
		// Metodo para rellenar los campos con la información de la BD
		// Acordarse de cambiar el texto en los botones

	}
}