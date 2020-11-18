package AdminFront;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import AdminBack.Ac_AdminEmplAdd;
import AdminBack.Ac_AdminUserAdd;

public class V_AdminEmplAdd extends JInternalFrame {

	private JLabel Titulo, Dni, Nombre, CCC, email, Fecha, Apellidos, Contraseña, Telefono;
	private JTextField txtDni, txtNombre, txtCCC, txtemail, txtApellidos, txtContraseña, txtTelefono;
	private JFormattedTextField txtfecha;
	private JButton Añadir, Limpiar;
	private JDateChooser date;
	public V_AdminEmplAdd() {
		CreateForm();
	}

	public V_AdminEmplAdd(String name) { // Añadir todos los datos necesarios
		CreateForm();
		FillFields();
	}

	private void CreateForm() {
		this.setTitle("ALTA PROFESOR");
		this.setLocation(100, 100);
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

		JPanel Norte = new JPanel();
		Norte.add(Titulo = new JLabel("ALTA EMPLEADO"));

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
		Centro.add(Fecha = new JLabel("Fecha"));
		Centro.add(date);
		System.out.println(date);
		Centro.add(Contraseña = new JLabel("Contraseña"));
		Centro.add(txtContraseña = new JTextField());
		Centro.add(Telefono = new JLabel("Telefono"));
		Centro.add(txtTelefono = new JTextField());
		txtContraseña.setEnabled(true);
		JPanel Sur = new JPanel();
		Sur.add(Añadir = new JButton("Añadir"));
		Añadir.addActionListener(new Ac_AdminEmplAdd(this));
		Sur.add(Limpiar = new JButton("Limpiar"));
		Limpiar.addActionListener(new Ac_AdminEmplAdd(this));

		Container c = getContentPane();
		c.add(Norte, BorderLayout.NORTH);
		c.add(Centro, BorderLayout.CENTER);
		c.add(Sur, BorderLayout.SOUTH);

	}

	public JDateChooser getDate() {
		return date;
	}

	public void setDate(JDateChooser date) {
		this.date = date;
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