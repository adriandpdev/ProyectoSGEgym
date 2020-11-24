package AdminFront;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class V_AdminProm extends JInternalFrame {

	 private JTextField txtdestinatario,txtasunto;
	 private JTextArea mensaje;
	 private JButton btnenviar;
	 private String usuario = "alvaro@gmail.com";
	        String clave = "******";
	        String servidorSMTP = "smtp.gmail.com";
	        String puertoEnvio = "465";
	
	public V_AdminProm() {
		CreateForm();
	}
	
	private void CreateForm() {
		this.setTitle("PROMOCIONES");
		 JPanel Titulo = new JPanel();
		  Titulo.add(new JLabel("ENVIAR PROMOCION"));
			
		  JPanel Datos=new JPanel();
		  Datos.setLayout(new GridLayout(3,3));
		  Datos.add(new JLabel("Destinatario: "));
		  txtdestinatario=new JTextField();
		  Datos.add(txtdestinatario);
		  Datos.add(new JLabel("Asunto: "));
		  txtasunto=new JTextField();
		  Datos.add(txtasunto);
		  Datos.add(new JLabel("Mensaje: "));
		  mensaje=new JTextArea();
		  mensaje.setLineWrap(true);
		  JScrollPane scroll=new JScrollPane(mensaje);
		  Datos.add(scroll);
		  
		  JPanel Botones=new JPanel();
		  btnenviar=new JButton("Enviar");
		  Botones.add(btnenviar);
		  
			Container c = getContentPane();
			c.add(Titulo, BorderLayout.NORTH);
			c.add(Datos, BorderLayout.CENTER);
			c.add(Botones, BorderLayout.SOUTH);
	}
	 
}
