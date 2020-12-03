package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Conexion;
import main.Main;

public class V_AdminProm extends JInternalFrame {

	 private JLabel titulo, destinatario, asunto, mens;
	 private JTextField txtdestinatario,txtasunto;
	 private JTextArea mensaje;
	 private JButton btnenviar;
	 private String usuario = "sgegimnasio@gmail.com";
	 private String clave = "sgeproyecto1gimnasio";
	 private String servidorSMTP = "smtp.gmail.com";
	 private String puertoEnvio = "465";
	        
	public V_AdminProm() {
		CreateForm();
	}
	
	private void CreateForm() {
		this.setTitle("PROMOCIONES");
		 JPanel Titulo = new JPanel();
		  Titulo.add(titulo= new JLabel("ENVIAR PROMOCION"));
		  Titulo.setBackground(new Color(137, 13, 84));
		  titulo.setFont(new Font("Verdana",Font.BOLD,22));
		  titulo.setForeground(Color.WHITE);
			
		  JPanel Datos=new JPanel();
		  Datos.setLayout(new GridLayout(3,3));
		  Datos.add(destinatario= new JLabel("Destinatario: "));
		  destinatario.setFont(new Font("Verdana",Font.BOLD,20));
		  destinatario.setHorizontalAlignment(JTextField.CENTER);
		  txtdestinatario=new JTextField();
		  txtdestinatario.setFont(new Font("Verdana",Font.BOLD,20));
		  Datos.add(txtdestinatario);
		  Datos.add(asunto= new JLabel("Asunto: "));
		  asunto.setFont(new Font("Verdana",Font.BOLD,20));
		  asunto.setHorizontalAlignment(JTextField.CENTER);
		  txtasunto=new JTextField();
		  txtasunto.setFont(new Font("Verdana",Font.BOLD,20));
		  Datos.add(txtasunto);
		  Datos.add(mens= new JLabel("Mensaje: "));
		  mens.setFont(new Font("Verdana",Font.BOLD,20));
		  mens.setHorizontalAlignment(JTextField.CENTER);
		  mensaje=new JTextArea();
		  mensaje.setFont(new Font("Verdana",Font.BOLD,20));
		  mensaje.setLineWrap(true);
		  JScrollPane scroll=new JScrollPane(mensaje);
		  Datos.add(scroll);
		  
		  JPanel Botones=new JPanel();
		  Botones.setBackground(new Color(137, 13, 84));
		  btnenviar=new JButton("Enviar");
		  btnenviar.setFont(new Font("Verdana",Font.BOLD,20));
		  Botones.add(btnenviar);
		  
		  java.util.Date d = new java.util.Date();  
		  java.sql.Date date2 = new java.sql.Date(d.getTime());
		  
		  btnenviar.addActionListener(new ActionListener(){
			  
			   public void actionPerformed(ActionEvent e) {
				   Conexion c = new Conexion();
			    if(!txtdestinatario.getText().equalsIgnoreCase("") && !txtdestinatario.getText().equalsIgnoreCase(" ")){
			    	try {
			    		c.alta(Main.con, "INSERT INTO Promociones(asunto,mensaje,fecha)VALUES('"+txtasunto.getText()+"','"+mensaje.getText()+"','"+ date2 + "')");
				    	Properties props = new Properties();
				    	props.put("mail.smtp.host", servidorSMTP);  //El servidor SMTP de Google
						props.put("mail.smtp.user", usuario);		//Usuario que envia
						props.put("mail.smtp.clave", clave);    //La clave de la cuenta
						props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
						props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
						props.put("mail.smtp.port", 587);
						
						Session mailSession = Session.getInstance(props,null);
						
						Message msg = new MimeMessage(mailSession);
						msg.setFrom(new InternetAddress(usuario));
						msg.addRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(txtdestinatario.getText().toString()) });
						msg.setSubject(txtasunto.getText().toString());
						msg.setText(mensaje.getText().toString());
						
					    Transport transport = mailSession.getTransport("smtp");
					    transport.connect("smtp.gmail.com", usuario, clave);
					    transport.sendMessage(msg, msg.getAllRecipients());
					    transport.close();
					    JOptionPane.showMessageDialog(null, "Se ha enviado el Aviso.", "ATENCIÓN ADMINISTRADOR", JOptionPane.INFORMATION_MESSAGE );
					    
					}catch (Exception e2) {JOptionPane.showMessageDialog(null, "Error, El mensaje no se ha podido enviar.", "ATENCIÓN ADMINISTRADOR", JOptionPane.ERROR_MESSAGE);} 
			    }else{
			    	JOptionPane.showMessageDialog(null, "Error, Se debe rellenar el campo destinatario.", "ATENCIÓN ADMINISTRADOR", JOptionPane.ERROR_MESSAGE);}
			    }

			  });
		  
			Container c = getContentPane();
			c.add(Titulo, BorderLayout.NORTH);
			c.add(Datos, BorderLayout.CENTER);
			c.add(Botones, BorderLayout.SOUTH);
	}
	
	 
}
