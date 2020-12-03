package AdminBack;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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

	public Ac_AdminWarn(V_AdminWarn v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Conexion c = new Conexion();
		if (!vent.getTxtdestinatario().getText().equalsIgnoreCase("")
				&& !vent.getTxtdestinatario().getText().equalsIgnoreCase(" ")) {
			try {
				c.alta(Main.con, "INSERT INTO Avisos(asunto,mensaje,fecha)VALUES('" + vent.getTxtasunto().getText()
						+ "','" + vent.getMensaje().getText() + "','" + vent.getDate2() + "')");
				Properties props = new Properties();
				props.put("mail.smtp.host", vent.getServidorSMTP()); // El servidor SMTP de Google
				props.put("mail.smtp.user", vent.getUsuario()); // Usuario que envia
				props.put("mail.smtp.clave", vent.getClave()); // La clave de la cuenta
				props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
				props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al SMTP
				props.put("mail.smtp.port", 587);
				Session mailSession = Session.getInstance(props, null);
				Message msg = new MimeMessage(mailSession);
				msg.setFrom(new InternetAddress(vent.getUsuario()));
				msg.addRecipients(Message.RecipientType.TO,
						new InternetAddress[] { new InternetAddress(vent.getTxtdestinatario().getText().toString()) });
				msg.setSubject(vent.getTxtasunto().getText().toString());
				msg.setText(vent.getMensaje().getText().toString());
				Transport transport = mailSession.getTransport("smtp");
				transport.connect("smtp.gmail.com", vent.getUsuario(), vent.getClave());
				transport.sendMessage(msg, msg.getAllRecipients());
				transport.close();

				JOptionPane.showMessageDialog(null, "Se ha enviado el Aviso.");

				JOptionPane.showMessageDialog(null, "Se ha enviado el Aviso.", "ATENCIÓN ADMINISTRADOR", JOptionPane.INFORMATION_MESSAGE );

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error, El mensaje no se ha podido enviar.", "ATENCIÓN ADMINISTRADOR", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error, Se debe rellenar el campo destinatario.", "ATENCIÓN ADMINISTRADOR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
