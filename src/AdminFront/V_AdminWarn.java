package AdminFront;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import AdminBack.Ac_AdminUserAdd;


public class V_AdminWarn extends JInternalFrame {

	
	 private JTextField txtdestinatario,txtasunto;
	 private JTextArea mensaje;
	 private JButton btnenviar;
	 private String usuario = "prueba1357246@gmail.com";
	        String clave = "123prueba123456";
	        String servidorSMTP = "smtp.gmail.com";
	        String puertoEnvio = "465";
	        
	public V_AdminWarn() {
		CreateForm();
	}
	
	private void CreateForm() {
		this.setTitle("AVISOS");
		 JPanel Titulo = new JPanel();
		  Titulo.add(new JLabel("ENVIAR AVISO"));
			
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
		  
		  btnenviar.addActionListener(new ActionListener(){
			  
			   public void actionPerformed(ActionEvent e) {
			    if(!txtdestinatario.getText().equalsIgnoreCase("") && !txtdestinatario.getText().equalsIgnoreCase(" ")){
			     Properties props = new Properties();
			     props.put("mail.smtp.user", usuario);
			     props.put("mail.smtp.host", servidorSMTP);
			     props.put("mail.smtp.port", puertoEnvio);
			     props.put("mail.smtp.starttls.enable", "true");
			     props.put("mail.smtp.auth", "true");
			     props.put("mail.smtp.socketFactory.port", puertoEnvio);
			     props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			     props.put("mail.smtp.socketFactory.fallback", "false");


			     try {
			      Authenticator auth = new autentificadorSMTP(usuario,clave);
			      Session session = Session.getInstance(props, auth);
			      MimeMessage msg = new MimeMessage(session);
			      msg.setText(mensaje.getText());
			      msg.setSubject(txtasunto.getText());
			      msg.setFrom(new InternetAddress(usuario));
			      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(txtdestinatario.getText()));
			      Transport.send(msg);
			     } catch (Exception mex) {
			      JOptionPane.showMessageDialog(null, "Error, El mensaje no se ha podido enviar.");
			     }
			    }else{
			     JOptionPane.showMessageDialog(null, "Error, se debe llenar el campo destinatario.");
			    }
			    }
			   
			  });
		  
			Container c = getContentPane();
			c.add(Titulo, BorderLayout.NORTH);
			c.add(Datos, BorderLayout.CENTER);
			c.add(Botones, BorderLayout.SOUTH);
	}
	
	 
	  
	 
}
