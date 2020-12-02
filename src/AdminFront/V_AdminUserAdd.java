package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import AdminBack.Ac_AdminUserAdd;

public class V_AdminUserAdd extends JInternalFrame {

	private JLabel Titulo, Dni, Nombre, CCC, email, Fecha, Apellidos, Contrase�a, Telefono;
	private JTextField txtDni, txtNombre, txtCCC, txtemail, txtApellidos, txtContrase�a, txtTelefono;
	private JFormattedTextField txtfecha;
	private JButton A�adir, Limpiar;
	private JDateChooser date;
	public JDateChooser getDate() {
		return date;
	}

	public void setDate(JDateChooser date) {
		this.date = date;
	}

	public static String NUMEROS = "0123456789";
	 
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
 
	public static String ESPECIALES = "��@#$%&";
 
	
	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}
 
	public String getPassword() {
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
	
	
	public V_AdminUserAdd() {
		CreateForm();
	}

	public V_AdminUserAdd(String name) { // A�adir todos los datos necesarios
		CreateForm();
		FillFields();
	}

	private void CreateForm() {
		this.setTitle("ALTA USUARIO");
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

		JPanel Norte = new JPanel();
		Norte.add(Titulo = new JLabel("ALTA USUARIO"));
		Norte.setBackground(new Color(137, 13, 84));
		Titulo.setFont(new Font("Verdana",Font.BOLD,22));
		Titulo.setForeground(Color.WHITE);
		

		JPanel Centro = new JPanel();
		Centro.setLayout(new GridLayout(4, 4, 10, 10));
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
		date.setDateFormatString("dd-MM-yyyy");
		date.setFont(new Font("Verdana",Font.BOLD,20));
		Centro.add(Fecha = new JLabel("Fecha de Nacimiento"));
		Fecha.setFont(new Font("Verdana",Font.BOLD,20));
		Fecha.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(date);
		Centro.add(Contrase�a = new JLabel("Contrase�a"));
		Contrase�a.setFont(new Font("Verdana",Font.BOLD,20));
		Contrase�a.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(txtContrase�a = new JTextField());
		txtContrase�a.setFont(new Font("Verdana",Font.BOLD,20));
		Centro.add(Telefono = new JLabel("Telefono"));
		Telefono.setFont(new Font("Verdana",Font.BOLD,20));
		Telefono.setHorizontalAlignment(JTextField.CENTER);
		Centro.add(txtTelefono = new JTextField());
		txtTelefono.setFont(new Font("Verdana",Font.BOLD,20));
		txtContrase�a.setEnabled(false);
		txtContrase�a.setText(getPassword());

		JPanel Sur = new JPanel();
		Sur.setBackground(new Color(137, 13, 84));
		Sur.add(A�adir = new JButton("A�adir"));
		A�adir.addActionListener(new Ac_AdminUserAdd(this));
		A�adir.setFont(new Font("Verdana",Font.BOLD,20));
		Sur.add(Limpiar = new JButton("Limpiar"));
		Limpiar.addActionListener(new Ac_AdminUserAdd(this));
		Limpiar.setFont(new Font("Verdana",Font.BOLD,20));

		Container c = getContentPane();
		c.add(Norte, BorderLayout.NORTH);
		c.add(Centro, BorderLayout.CENTER);
		c.add(Sur, BorderLayout.SOUTH);
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

	public JTextField getTxtContrase�a() {
		return txtContrase�a;
	}

	public void setTxtContrase�a(JTextField txtContrase�a) {
		this.txtContrase�a = txtContrase�a;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JFormattedTextField getTxtfecha() {
		return txtfecha;
	}

	public void setTxtfecha(JFormattedTextField txtfecha) {
		this.txtfecha = txtfecha;
	}

	private void FillFields() {
		// Metodo para rellenar los campos con la informaci�n de la BD
		// Acordarse de cambiar el texto en los botones

	}
}
