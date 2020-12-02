package Login;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import main.Conexion;
import main.Main;

public class Ac_ForgetPass implements MouseListener {
	public V_Login vent;
	private String mensaje, nclave;
	private String usuario = "sgegimnasio@gmail.com";
	private String clave = "sgeproyecto1gimnasio";
	private String servidorSMTP = "smtp.gmail.com";
	private String puertoEnvio = "587";

	Ac_ForgetPass(V_Login v) {
		vent = v;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		String correo = JOptionPane.showInputDialog(vent, "Introduce el correo registrado",
				JOptionPane.QUESTION_MESSAGE);
		Conexion c = new Conexion();
		if (!correo.equals("")) {
			try {
				ResultSet rs = c.consulta(Main.con, "SELECT * FROM Persona WHERE correo LIKE '" + correo + "'");
				rs.next();
				if (rs.first()) {
					System.out.println("HAY CORREO");
					Properties props = new Properties();
					props.put("mail.smtp.host", servidorSMTP); // El servidor SMTP de Google
					props.put("mail.smtp.user", usuario); // Usuario que envia
					props.put("mail.smtp.clave", clave); // La clave de la cuenta
					props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
					props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al SMTP
					props.put("mail.smtp.port", puertoEnvio);
					Session mailSession = Session.getInstance(props, null);
					Message msg = new MimeMessage(mailSession);
					msg.setFrom(new InternetAddress(usuario));
					msg.addRecipients(Message.RecipientType.TO, new InternetAddress[] { new InternetAddress(correo) });
					msg.setSubject("Nueva contraseña de proyectoSGEgym");

					// GENERAR UNA NUEVA CLAVE Y MODIFICARLA EN LA BASE DE DATOS
					nclave = "NULL";
					mensaje = "Tu nueva contraseña es " + nclave;
					msg.setText(mensaje);
					Transport transport = mailSession.getTransport("smtp");
					transport.connect("smtp.gmail.com", usuario, clave);
					transport.sendMessage(msg, msg.getAllRecipients());
					transport.close();
					JOptionPane.showMessageDialog(null, "Se ha enviado una nueva contraseña al correo.");
				} else {
					JOptionPane.showMessageDialog(null, "Introduce un correo valido.");
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error, El mensaje no se ha podido enviar.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Introduce un correo valido.");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}