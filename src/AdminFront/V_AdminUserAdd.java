package AdminFront;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class V_AdminUserAdd extends JInternalFrame{

	private JLabel Titulo, Dni, Nombre, CCC, email, Fecha, Apellidos, Contrase�a, Telefono;
	private JTextField txtDni, txtNombre, txtCCC, txtemail, txtfecha, txtApellidos, txtContrase�a, txtTelefono;
	private JButton A�adir, Limpiar;
	
	public V_AdminUserAdd(){
		this.setTitle("ALTA USUARIO");
		this.setResizable(false); 
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
		
		JPanel Norte= new JPanel();
		Norte.add(Titulo= new JLabel("ALTA USUARIO"));
		
		
		JPanel Centro= new JPanel();
		Centro.setLayout(new GridLayout(4,4,10,10));
		Centro.add(Nombre= new JLabel("Nombre"));
		Centro.add(txtNombre= new JTextField());
		Centro.add(Apellidos= new JLabel("Apellidos"));
		Centro.add(txtApellidos= new JTextField());
		Centro.add(Dni= new JLabel("DNI"));
		Centro.add(txtDni= new JTextField());
		Centro.add(CCC= new JLabel("CCC"));
		Centro.add(txtCCC= new JTextField());
		Centro.add(email= new JLabel("E-mail"));
		Centro.add(txtemail= new JTextField());
		JFormattedTextField txtfecha = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		txtfecha.setValue(new java.util.Date());
		Centro.add(Fecha= new JLabel("Fecha"));
		Centro.add(txtfecha);
		Centro.add(Contrase�a= new JLabel("Contrase�a"));
		Centro.add(txtContrase�a= new JTextField());
		Centro.add(Telefono= new JLabel("Telefono"));
		Centro.add(txtTelefono= new JTextField());
		txtContrase�a.setEnabled(false);
		
		
	    
		
		JPanel Sur= new JPanel();
		Sur.add(A�adir= new JButton("A�adir"));
		Sur.add(Limpiar= new JButton("Limpiar"));
		
		Container c= getContentPane();
		c.add(Norte,BorderLayout.NORTH);
		c.add(Centro,BorderLayout.CENTER);
		c.add(Sur,BorderLayout.SOUTH);
	
		}
	
}
