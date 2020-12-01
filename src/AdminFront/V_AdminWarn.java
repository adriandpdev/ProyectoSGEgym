package AdminFront;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import AdminBack.*;

public class V_AdminWarn extends JInternalFrame {
	private JTextField txtdestinatario, txtasunto;
	private JTextArea mensaje;
	private JButton btnenviar;
	private String usuario = "sgegimnasio@gmail.com";
	private String clave = "sgeproyecto1gimnasio";
	private String servidorSMTP = "smtp.gmail.com";
	private String puertoEnvio = "465";
	private Date Date2;
	public V_AdminWarn() {
		CreateForm();
	}

	private void CreateForm() {
		this.setTitle("AVISOS");
		JPanel Titulo = new JPanel();
		Titulo.add(new JLabel("ENVIAR AVISO"));

		JPanel Datos = new JPanel();
		Datos.setLayout(new GridLayout(3, 3));
		Datos.add(new JLabel("Destinatario: "));
		txtdestinatario = new JTextField();
		Datos.add(txtdestinatario);
		Datos.add(new JLabel("Asunto: "));
		txtasunto = new JTextField();
		Datos.add(txtasunto);
		Datos.add(new JLabel("Mensaje: "));
		mensaje = new JTextArea();
		mensaje.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(mensaje);
		Datos.add(scroll);

		JPanel Botones = new JPanel();
		btnenviar = new JButton("Enviar");
		Botones.add(btnenviar);

		java.util.Date d = new java.util.Date();
		setDate2(new java.sql.Date(d.getTime()));

		btnenviar.addActionListener(new Ac_AdminWarn(this));
		Container c = getContentPane();
		c.add(Titulo, BorderLayout.NORTH);
		c.add(Datos, BorderLayout.CENTER);
		c.add(Botones, BorderLayout.SOUTH);
	}

	public JTextField getTxtdestinatario() {
		return txtdestinatario;
	}

	public void setTxtdestinatario(JTextField txtdestinatario) {
		this.txtdestinatario = txtdestinatario;
	}

	public JTextField getTxtasunto() {
		return txtasunto;
	}

	public void setTxtasunto(JTextField txtasunto) {
		this.txtasunto = txtasunto;
	}

	public JTextArea getMensaje() {
		return mensaje;
	}

	public void setMensaje(JTextArea mensaje) {
		this.mensaje = mensaje;
	}

	public JButton getBtnenviar() {
		return btnenviar;
	}

	public void setBtnenviar(JButton btnenviar) {
		this.btnenviar = btnenviar;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getServidorSMTP() {
		return servidorSMTP;
	}

	public void setServidorSMTP(String servidorSMTP) {
		this.servidorSMTP = servidorSMTP;
	}

	public String getPuertoEnvio() {
		return puertoEnvio;
	}

	public void setPuertoEnvio(String puertoEnvio) {
		this.puertoEnvio = puertoEnvio;
	}

	public Date getDate2() {
		return Date2;
	}

	public void setDate2(Date date2) {
		Date2 = date2;
	}
}
