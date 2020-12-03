package AdminBack;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Conexion;
import main.Main;
import AdminFront.*;

public class Ac_AdminWarn implements ActionListener {
	private V_AdminWarn vent;
	 private String usuario = "sgegimnasio@gmail.com";
	 private String clave = "sgeproyecto1gimnasio";
	 private String servidorSMTP = "smtp.gmail.com";
	 private String puertoEnvio = "465";

	public Ac_AdminWarn(V_AdminWarn v) {
		vent = v;
	}
	
	public void limpiar() {
		vent.getTxtdestinatario().setText("");
		vent.getTxtasunto().setText("");
		vent.getMensaje().setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			HiloEnvio envio = new HiloEnvio();
			envio.start();
			JOptionPane.showMessageDialog(null, "Enviando...", "ATENCIÓN ADMINISTRADOR", JOptionPane.INFORMATION_MESSAGE );	
	}
	
	
	class HiloEnvio extends Thread{
	      public void run(){
	    	  	java.util.Date d = new java.util.Date();  
			  	java.sql.Date date2 = new java.sql.Date(d.getTime());
				if(vent.getTodos().isSelected() && !vent.getTxtasunto().getText().equalsIgnoreCase(" ") && !vent.getMensaje().getText().equalsIgnoreCase("") ) {
					try {
						Conexion c = new Conexion();
						String query = "SELECT Persona.correo FROM Persona WHERE Persona.rol = 'user'";
						ArrayList<String> correos = new ArrayList<>();
						ResultSet rs = c.consulta(Main.con, query);
						c.alta(Main.con, "INSERT INTO Avisos (asunto,mensaje,fecha)VALUES('"+vent.getTxtasunto().getText()+"','"+vent.getMensaje().getText()+"','"+ date2 + "')");
						while(rs.next()) {
							correos.add(rs.getString("Persona.correo"));
						}
					    Properties props = new Properties();
					    props.put("mail.smtp.host", servidorSMTP);  //El servidor SMTP de Google
						props.put("mail.smtp.user", usuario);		//Usuario que envia
						props.put("mail.smtp.clave", clave);    //La clave de la cuenta
						props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
						props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
						props.put("mail.smtp.port", 587);
						for (int i = 0; i < correos.size(); i++) {
							Session mailSession = Session.getInstance(props,null);
							Message msg = new MimeMessage(mailSession);
							msg.setFrom(new InternetAddress(usuario));
							msg.addRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(correos.get(i).toString()) });
							msg.setSubject(vent.getAsunto().getText().toString());
							msg.setText(vent.getMensaje().getText().toString());
							
							Transport transport = mailSession.getTransport("smtp");
							transport.connect("smtp.gmail.com", usuario, clave);
							transport.sendMessage(msg, msg.getAllRecipients());
							transport.close();
						}
						limpiar();
						JOptionPane.showMessageDialog(null, "Se ha enviado el aviso.", "ATENCIÓN ADMINISTRADOR", JOptionPane.INFORMATION_MESSAGE );
					}catch (Exception e2) {System.out.println(e2);}
				}else if(!vent.getTodos().isSelected() && !vent.getTxtdestinatario().getText().equalsIgnoreCase("") && !vent.getTxtasunto().getText().equalsIgnoreCase(" ") && !vent.getMensaje().getText().equalsIgnoreCase("") ){
					Conexion c = new Conexion();
					try {
			    		c.alta(Main.con, "INSERT INTO Avisos(asunto,mensaje,fecha)VALUES('"+vent.getTxtasunto().getText()+"','"+vent.getMensaje().getText()+"','"+ date2 + "')");
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
						msg.addRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(vent.getTxtdestinatario().getText().toString()) });
						msg.setSubject(vent.getAsunto().getText().toString());
						msg.setText(vent.getMensaje().getText().toString());
						
					    Transport transport = mailSession.getTransport("smtp");
					    transport.connect("smtp.gmail.com", usuario, clave);
					    transport.sendMessage(msg, msg.getAllRecipients());
					    transport.close();
                      limpiar();
					    JOptionPane.showMessageDialog(null, "Se ha enviado el Aviso.", "ATENCIÓN ADMINISTRADOR", JOptionPane.INFORMATION_MESSAGE );
					    
					}catch (Exception e2) {System.out.println(e2);
						JOptionPane.showMessageDialog(null, "Error, El mensaje no se ha podido enviar.", "ATENCIÓN ADMINISTRADOR", JOptionPane.ERROR_MESSAGE);} 
			    }else{
			    	JOptionPane.showMessageDialog(null, "Error, Se debe rellenar el campo destinatario.", "ATENCIÓN ADMINISTRADOR", JOptionPane.ERROR_MESSAGE);
			    }
	      }
	   };
	
}
