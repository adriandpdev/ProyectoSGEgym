package AdminFront;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import AdminBack.Ac_AdminUserAdd;

public class V_AdminUserAdd extends JInternalFrame {

	private JLabel Titulo, Dni, Nombre, CCC, email, Fecha, Apellidos, Contraseña, Telefono;
	private JTextField txtDni, txtNombre, txtCCC, txtemail, txtApellidos, txtContraseña, txtTelefono;
	private JFormattedTextField txtfecha;
	private JButton Añadir, Limpiar;
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
 
	public static String ESPECIALES = "ñÑ@#$%&";
 
	
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

	public V_AdminUserAdd(String name) { // Añadir todos los datos necesarios
		CreateForm();
		FillFields();
	}

	private void CreateForm() {
		this.setTitle("ALTA USUARIO");
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

		JPanel Norte = new JPanel();
		Norte.add(Titulo = new JLabel("ALTA USUARIO"));

		JPanel Centro = new JPanel();
		Centro.setLayout(new GridLayout(4, 4, 10, 10));
		Centro.add(Nombre = new JLabel("Nombre"));
		Centro.add(txtNombre = new JTextField());
		Centro.add(Apellidos = new JLabel("Apellidos"));
		Centro.add(txtApellidos = new JTextField());
		Centro.add(Dni = new JLabel("DNI"));
		Centro.add(txtDni = new JTextField());
		Centro.add(CCC = new JLabel("CCC"));
		Centro.add(txtCCC = new JTextField());
		Centro.add(email = new JLabel("E-mail"));
		Centro.add(txtemail = new JTextField());
		date = new JDateChooser();
		date.setDateFormatString("dd-MM-yyyy");
		Centro.add(Fecha = new JLabel("Fecha de Nacimiento"));
		Centro.add(date);
		Centro.add(Contraseña = new JLabel("Contraseña"));
		Centro.add(txtContraseña = new JTextField());
		Centro.add(Telefono = new JLabel("Telefono"));
		Centro.add(txtTelefono = new JTextField());
		txtContraseña.setEnabled(false);
		txtContraseña.setText(getPassword());

		JPanel Sur = new JPanel();
		Sur.add(Añadir = new JButton("Añadir"));
		Añadir.addActionListener(new Ac_AdminUserAdd(this));
		Sur.add(Limpiar = new JButton("Limpiar"));
		Limpiar.addActionListener(new Ac_AdminUserAdd(this));

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

	public JFormattedTextField getTxtfecha() {
		return txtfecha;
	}

	public void setTxtfecha(JFormattedTextField txtfecha) {
		this.txtfecha = txtfecha;
	}

	private void FillFields() {
		// Metodo para rellenar los campos con la información de la BD
		// Acordarse de cambiar el texto en los botones

	}
}
