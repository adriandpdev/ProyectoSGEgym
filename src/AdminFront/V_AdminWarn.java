package AdminFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import AdminBack.*;

public class V_AdminWarn extends JInternalFrame {
	private JLabel titulo, destinatario, asunto, mens;
	private JTextField txtdestinatario, txtasunto;
	private JTextArea mensaje;
	private JButton btnenviar;
	private JCheckBox todos;
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
		Titulo.add(titulo =new JLabel("ENVIAR AVISO"));
		Titulo.setBackground(new Color(137, 13, 84));
		titulo.setFont(new Font("Verdana",Font.BOLD,22));
		titulo.setForeground(Color.WHITE);

		JPanel Datos = new JPanel();
		Datos.setLayout(new GridLayout(3, 3));
		Datos.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));
		Datos.add(destinatario= new JLabel("Destinatario: "));
		destinatario.setFont(new Font("Verdana",Font.BOLD,20));
		destinatario.setHorizontalAlignment(JTextField.CENTER);
		txtdestinatario = new JTextField();
		txtdestinatario.setFont(new Font("Verdana",Font.BOLD,20));
		Datos.add(txtdestinatario);
		Datos.add(asunto= new JLabel("Asunto: "));
		asunto.setFont(new Font("Verdana",Font.BOLD,20));
		asunto.setHorizontalAlignment(JTextField.CENTER);
		txtasunto = new JTextField();
		txtasunto.setFont(new Font("Verdana",Font.BOLD,20));
		Datos.add(txtasunto);
		Datos.add(mens=new JLabel("Mensaje: "));
		mens.setFont(new Font("Verdana",Font.BOLD,20));
		mens.setHorizontalAlignment(JTextField.CENTER);
		mensaje = new JTextArea();
		mensaje.setFont(new Font("Verdana",Font.BOLD,20));
		mensaje.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(mensaje);
		Datos.add(scroll);

		JPanel Botones = new JPanel();
		Botones.setBackground(new Color(137, 13, 84));
		todos = new JCheckBox("Enviar a todos");
		todos.setBackground(new Color(137, 13, 84));
		todos.setFont(new Font("Verdana",Font.BOLD,20));
		btnenviar = new JButton("Enviar");
		btnenviar.setFont(new Font("Verdana",Font.BOLD,20));
		Botones.add(btnenviar);
		Botones.add(todos);
		
		todos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(todos.isSelected()) {txtdestinatario.setEnabled(false);
				}else {txtdestinatario.setEnabled(true);}
			}
		  });

		btnenviar.addActionListener(new Ac_AdminWarn(this));
		Container c = getContentPane();
		c.add(Titulo, BorderLayout.NORTH);
		c.add(Datos, BorderLayout.CENTER);
		c.add(Botones, BorderLayout.SOUTH);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
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

	public JLabel getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(JLabel destinatario) {
		this.destinatario = destinatario;
	}

	public JLabel getAsunto() {
		return asunto;
	}

	public void setAsunto(JLabel asunto) {
		this.asunto = asunto;
	}

	public JLabel getMens() {
		return mens;
	}

	public void setMens(JLabel mens) {
		this.mens = mens;
	}

	public JCheckBox getTodos() {
		return todos;
	}

	public void setTodos(JCheckBox todos) {
		this.todos = todos;
	}
	
}
