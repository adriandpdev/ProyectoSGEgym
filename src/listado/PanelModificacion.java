package listado;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

public class PanelModificacion extends JFrame{

	private JLabel Titulo, Dni, Nombre, CCC, email, Fecha, Apellidos, Contraseña, Telefono, Rol;
	private JTextField txtDni, txtNombre, txtCCC, txtemail, txtApellidos, txtContraseña, txtTelefono, txtRol;
	private JFormattedTextField txtfecha;
	private JButton Añadir, Limpiar;
	private JDateChooser date;
	
	private Conexion_pruebas cp;
	private Connection conn;
	private ResultSet rs;
	
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


	//Construtor sobrecargado con todos los datos de la BBDD
	
	public PanelModificacion(String dni, String nombre, String apellido, String cuentabanc, String pass, String fechanac, String telefono, String correo, String rol) {
		CreateForm();
		FillFields(dni, nombre, apellido, cuentabanc, pass, fechanac, telefono, correo, rol);
	}

	private void CreateForm() {
		this.setBounds(500, 100, 700, 500);
		

		JPanel Norte = new JPanel();
		Norte.add(Titulo = new JLabel("ALTA EMPLEADO"));

		JPanel Centro = new JPanel();
		Centro.setLayout(new GridLayout(10, 2, 10, 10));
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
		Centro.add(Rol = new JLabel("Rol"));
		Centro.add(txtRol = new JTextField());
		
		txtContraseña.setEnabled(false);
		txtContraseña.setText(getPassword());
		JPanel Sur = new JPanel();
		Sur.add(Añadir = new JButton("Modificar"));
		//Añadir.addActionListener(new Ac_AdminEmplAdd(this));
		

		this.getContentPane().add(Norte, BorderLayout.NORTH);
		this.getContentPane().add(Centro, BorderLayout.CENTER);
		this.getContentPane().add(Sur, BorderLayout.SOUTH);
		this.setResizable(false);
		this.setVisible(true);

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

	private void FillFields(String dni, String nombre, String apellido, String cuentabanc, String pass, String fechanac, String telefono, String correo, String rol) {
		// Metodo para rellenar los campos con la información de la BD
		
		//Date fech = new SimpleDateFormat("dd-MM-yyyy").parse(fechanac);
		
		txtDni.setText(dni);
		txtNombre.setText(nombre);
		txtApellidos.setText(apellido);
		txtCCC.setText(cuentabanc);
		txtContraseña.setText(pass);
		//date.setDate(fech);
		txtTelefono.setText(telefono);
		txtemail.setText(correo);
		txtRol.setText(rol);
		
		

	}
	
	public int modificarDatos(String dni, String nombre, String apellido, String cuentabanc, String pass, String fechanac, String telefono, String correo, String rol) {
		
		int tele = Integer.valueOf(telefono);
		
		String sql_modificar = "UPDATE persona SET dni='"+dni+"', apellido= '"+apellido+"',cuentabanc='"+cuentabanc+"',pass='"+pass+"', fechanac='"+fechanac+"',"
				+ "telefono=" +tele+ " correo='"+correo+"', rol='"+rol+"' WHERE dni='"+dni+"'";
		
		int res = 0;
		
		cp = new Conexion_pruebas();
		
		
		try {
			conn = cp.conectar();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = cp.consulta(conn, sql_modificar);
			
			if (res > 0) {
				JOptionPane.showConfirmDialog(null, "El registro ha sido modificado correctamente");
			}
			
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "Error al modificar el registro");
		}
		
		return res;
	}
}
