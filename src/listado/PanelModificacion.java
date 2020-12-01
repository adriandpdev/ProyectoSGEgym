package listado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import com.toedter.calendar.JDateChooser;

public class PanelModificacion extends JFrame implements ActionListener{

	private JLabel Titulo, Dni, Nombre, CCC, email, Fecha, Apellidos, Contraseña, Telefono, Rol;
	private JTextField txtDni, txtNombre, txtCCC, txtemail, txtApellidos, txtContraseña, txtTelefono, txtRol;
	private JFormattedTextField txtfecha;
	private JPanel panel_sur;
	private JButton Modificar;
	private JDateChooser Calendario;
	private String DNI;
	
	private Conexion cp;
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
	
	public PanelModificacion(String dni, String nombre, String apellido, String cuentabanc, String pass, String fechanac, String telefono, String correo, String rol) throws ParseException {
		
		this.DNI = dni;
		CreateForm();
		FillFields(dni, nombre, apellido, cuentabanc, pass, fechanac, telefono, correo, rol);
	}

	private void CreateForm() {
		this.setBounds(700, 200, 600, 550);
		this.setTitle("PANEL DE MODIFICACIÓN");
		

		UIManager.put("OptionPane.background",new ColorUIResource(new Color(137, 13, 84)));
		UIManager.put("OptionPane.messageForeground",new ColorUIResource(Color.WHITE));
		UIManager.put("Panel.background",new ColorUIResource(new Color(137, 13, 84)));
		
		JPanel Norte = new JPanel();
		Norte.add(Titulo = new JLabel("MODIFICADOR DE DATOS:"));
		Titulo.setForeground(Color.WHITE);
		Titulo.setFont(new Font("Verdana",Font.BOLD,23));

		JPanel Centro = new JPanel();
		Centro.setLayout(new GridLayout(10, 2, 10, 10));
		
		Centro.add(Dni = new JLabel("DNI:"));
		Dni.setForeground(Color.WHITE);
		Dni.setFont(new Font("Verdana",Font.PLAIN,20));
		Centro.add(txtDni = new JTextField());
		txtDni.setFont(new Font("Verdana",Font.PLAIN,20));
		
		Centro.add(Nombre = new JLabel("Nombre:"));
		Nombre.setForeground(Color.WHITE);
		Nombre.setFont(new Font("Verdana",Font.PLAIN,20));
		Centro.add(txtNombre = new JTextField());
		txtNombre.setFont(new Font("Verdana",Font.PLAIN,20));
		
		Centro.add(Apellidos = new JLabel("Apellido:"));
		Apellidos.setForeground(Color.WHITE);
		Apellidos.setFont(new Font("Verdana",Font.PLAIN,20));
		Centro.add(txtApellidos = new JTextField());
		txtApellidos.setFont(new Font("Verdana",Font.PLAIN,20));
		
		Centro.add(CCC = new JLabel("Número de Cuenta:"));
		CCC.setForeground(Color.WHITE);
		CCC.setFont(new Font("Verdana",Font.PLAIN,20));
		Centro.add(txtCCC = new JTextField());
		txtCCC.setFont(new Font("Verdana",Font.PLAIN,20));
		
		Centro.add(email = new JLabel("Correo Electrónico:"));
		email.setForeground(Color.WHITE);
		email.setFont(new Font("Verdana",Font.PLAIN,20));
		Centro.add(txtemail = new JTextField());
		txtemail.setFont(new Font("Verdana",Font.PLAIN,20));
		
		Centro.add(Fecha = new JLabel("Fecha de Nacimiento:"));
		Fecha.setForeground(Color.WHITE);
		Fecha.setFont(new Font("Verdana",Font.PLAIN,20));
		Calendario = new JDateChooser();
		Calendario.setFont(new Font("Verdana",Font.PLAIN,20));
		Centro.add(Calendario);
		
		Centro.add(Contraseña = new JLabel("Contraseña:"));
		Contraseña.setForeground(Color.WHITE);
		Contraseña.setFont(new Font("Verdana",Font.PLAIN,20));
		Centro.add(txtContraseña = new JTextField());
		txtContraseña.setFont(new Font("Verdana",Font.PLAIN,20));
		
		Centro.add(Telefono = new JLabel("Telefono:"));
		Telefono.setForeground(Color.WHITE);
		Telefono.setFont(new Font("Verdana",Font.PLAIN,20));
		Centro.add(txtTelefono = new JTextField());
		txtTelefono.setFont(new Font("Verdana",Font.PLAIN,20));
		
		Centro.add(Rol = new JLabel("Rol:"));
		Rol.setForeground(Color.WHITE);
		Rol.setFont(new Font("Verdana",Font.PLAIN,20));
		Centro.add(txtRol = new JTextField());
		txtRol.setFont(new Font("Verdana",Font.PLAIN,20));
		
		txtContraseña.setEnabled(false);
		txtContraseña.setText(getPassword());
		
		panel_sur = new JPanel();
		panel_sur.setLayout(new GridLayout());
		panel_sur.add(Modificar = new JButton("Modificar"));
		Modificar.setFont(new Font("Verdana",Font.PLAIN,25));
		Modificar.addActionListener(this);
	
		this.getContentPane().add(Norte, BorderLayout.NORTH);
		this.getContentPane().add(Centro, BorderLayout.CENTER);
		this.getContentPane().add(panel_sur, BorderLayout.SOUTH);
		this.setResizable(false);
		this.setVisible(true);

	}

	public JDateChooser getDate() {
		return Calendario;
	}

	public void setDate(JDateChooser date) {
		this.Calendario = date;
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

	private void FillFields(String dni, String nombre, String apellido, String cuentabanc, String pass, String fechanac, String telefono, String correo, String rol) throws ParseException {
		// Metodo para rellenar los campos con la información de la BD
		
		java.util.Date fech_parse = new SimpleDateFormat("dd-MM-yyyy").parse(fechanac);
		
		txtDni.setText(dni);
		txtNombre.setText(nombre);
		txtApellidos.setText(apellido);
		txtCCC.setText(cuentabanc);
		txtContraseña.setText(pass);
		Calendario.setDate(fech_parse);
		txtTelefono.setText(telefono);
		txtemail.setText(correo);
		txtRol.setText(rol);
		
		

	}
	
	public void modificarDatos() {
		
		//Cogemos los nuevos datos del panel de modificaciones
		
		String mod_dni = txtDni.getText().toString();
		String mod_nombre = txtNombre.getText().toString();
		String mod_apellido = txtApellidos.getText().toString();
		String mod_cuentabanc = txtCCC.getText().toString();
		String tele = txtTelefono.getText().toString();
		String mod_correo = txtemail.getText().toString();
		String mod_rol = txtRol.getText().toString();
		
		
		//Parseamos el teléfono a un integer para que no dé fallo al modificar la BBDD
		int mod_telefono = Integer.valueOf(tele);
		
		//Parseamos la fecha en formato date a string dd-MM-yyyy
		String pattern = "dd-MM-yyyy";
		DateFormat df = new SimpleDateFormat(pattern);    
		String mod_fechanac = df.format(Calendario.getDate());
		
		
		System.out.println(DNI);
		System.out.println(mod_dni);
		System.out.println(mod_nombre);
		System.out.println(mod_apellido);
		System.out.println(mod_cuentabanc);
		System.out.println(mod_fechanac);
		System.out.println(mod_telefono);
		System.out.println(mod_correo);
		System.out.println(mod_rol);
		
		String sql_modificar = "UPDATE Persona SET DNI='"+mod_dni+"', nombre='"+mod_nombre+"', apellido='"+mod_apellido+"',cuentabanc='"+mod_cuentabanc+"', fechanac='"+mod_fechanac+"',"
			+ "telefono="+mod_telefono+", correo='"+mod_correo+"', rol='"+mod_rol+"' WHERE DNI='"+DNI+"'";

		
		//UPDATE `persona` SET `fechanac`= "18-01-1980" WHERE 1
				
		cp = new Conexion();
		
		try {
			conn = cp.conectar();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cp.consulta(conn, sql_modificar);
			JOptionPane.showMessageDialog(null, "El registro ha sido modificado correctamente");
			dispose();
			
			listado list = new listado();
			list.refrescarTabla();
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se han podido realizar los cambios");
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Pulsado modificar");
		if(e.getSource() == Modificar) {
			modificarDatos();
		}
		
	}

}
