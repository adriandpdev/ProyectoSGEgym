package UserFront;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import AdminBack.Ac_AdminScheAdd;
import AdminFront.V_AdminHome;
import Login.V_Login;
import UserBack.Ac_UserActiAdd;
import main.Conexion;
import main.Fecha;
import main.Main;

public class V_UserActiAdd extends JInternalFrame {
	private V_UserHome v1;
	private JLabel lblTitulo, lblDniuser, lblIdaula, lblverdniuser, lblhora, lbldiasemana,lblaforo,lblmostraraforo;
	private JTextField txtIdclase, txtNombreactividad;
	private JComboBox cbaula, cbhora, diasemana, diaAdd,cbaforo;
	private JButton btnAñadir, btnCancelar;
	private JPanel jpCentro, jpSur, panel_titulo, panel_dni_lbl, panel_dni_mostrar, panel_actividad_lbl, panel_actividad_combo, panel_hora_lbl, panel_hora_combo, 
	panel_dia_lbl, panel_dia_combo, panel_aforo_lbl, panel_aforo_mostrar;
	public V_Login vent;
	private String DNI;
	private String[] semana = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" };
	Conexion c = new Conexion();
	private JDateChooser date;
	public JDateChooser getDate() {
		return date;
	}

	public void setDate(JDateChooser date) {
		this.date = date;
	}
	

	public V_UserActiAdd(V_UserHome venti){
		v1 = venti;

		this.setTitle("Alta de Clases");
		this.setLayout(new BorderLayout());

		// Parte norte del borderlayout

		lblTitulo = new JLabel("Reserva de Clases:");
		lblTitulo.setFont(new Font("Verdana",Font.PLAIN,50));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_titulo = new JPanel();
		panel_titulo.setBackground(new Color(137, 13, 84));
		panel_titulo.add(lblTitulo);
		
		this.getContentPane().add(panel_titulo, BorderLayout.NORTH);

		// Parte central del borderlayout

		jpCentro = new JPanel();
		jpCentro.setLayout(new GridLayout(8,2));
		
		panel_dni_lbl = new JPanel();
		lblDniuser = new JLabel("Tu DNI:");
		lblDniuser.setBackground(new Color(137, 13, 84));
		lblDniuser.setFont(new Font("Verdana",Font.PLAIN,50));
		panel_dni_lbl.add(lblDniuser);
		jpCentro.add(panel_dni_lbl);
		
		panel_dni_mostrar = new JPanel();
		lblverdniuser = new JLabel();
		lblverdniuser.setText(v1.getDNI1());
		lblverdniuser.setBackground(new Color(137, 13, 84));
		lblverdniuser.setFont(new Font("Verdana",Font.PLAIN,50));
		panel_dni_mostrar.add(lblverdniuser);
		jpCentro.add(panel_dni_mostrar);
		
		panel_actividad_lbl = new JPanel();
		lblIdaula = new JLabel("Selecciona la actividad:");
		lblIdaula.setFont(new Font("Verdana",Font.PLAIN,50));
		panel_actividad_lbl.add(lblIdaula);
		
		panel_actividad_combo = new JPanel();
		panel_actividad_combo.setLayout(new FlowLayout());
		cbaula = new JComboBox();
		cbaula.setFont(new Font("Verdana",Font.PLAIN,50));
		panel_actividad_combo.add(cbaula);

		String q = "select distinct(nombre) from Actividad";
		String x = "nombre";

		cbaula.removeAllItems();
		ArrayList<String> lista = new ArrayList<String>();
		try {
			lista = c.llenarCombo(Main.con, q, x);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < lista.size(); i++) {
			cbaula.addItem(lista.get(i));

		}
		
		panel_hora_lbl = new JPanel();
		lblhora = new JLabel("Elige una hora:");
		lblhora.setFont(new Font("Verdana",Font.PLAIN,50));
		panel_hora_lbl.add(lblhora);
		
		panel_hora_combo = new JPanel();
		cbhora = new JComboBox();
		cbhora.setFont(new Font("Verdana",Font.PLAIN,50));
		cbhora.addActionListener(new Ac_UserActiAdd(this));
		panel_hora_combo.add(cbhora);
		
		panel_dia_lbl = new JPanel();
		lbldiasemana = new JLabel("Elige un día:");
		lbldiasemana.setFont(new Font("Verdana",Font.PLAIN,50));
		
		
		diasemana = new JComboBox();
		diasemana.setFont(new Font("Verdana",Font.PLAIN,50));
		
		for (int i = 0; i < semana.length; i++) {
			diasemana.addItem(semana[i]);
		}
		
		
		
		
		
		lblmostraraforo= new JLabel();
		lblmostraraforo.setFont(new Font("Verdana",Font.PLAIN,50));
		lblaforo = new JLabel("Aforo Total:");
		lblaforo.setFont(new Font("Verdana",Font.PLAIN,50));

		

	

		diasemana.addActionListener(new Ac_UserActiAdd(this));

		jpCentro.add(lblIdaula);
		jpCentro.add(cbaula);
		jpCentro.add(lblhora);
		jpCentro.add(cbhora);
		jpCentro.add(lbldiasemana);
		jpCentro.add(diasemana);
		jpCentro.add(lblaforo);
		jpCentro.add(lblmostraraforo);
		
		
		this.getContentPane().add(jpCentro, BorderLayout.CENTER);

		// Parte sur del borderlayout
		jpSur = new JPanel();
		jpSur.setLayout(new GridLayout(1, 2));

		btnAñadir = new JButton("Añadir");
		btnAñadir.setFont(new Font("Verdana",Font.PLAIN,50));
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Verdana",Font.PLAIN,50));

		final class addbutton implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null,
						
						"Tu usuario con el dni: " + v1.getDNI1() + " se ha apuntado a la clase: "
								+ cbaula.getSelectedItem() + " el " + diasemana.getSelectedItem()+" a las "+cbhora.getSelectedItem());
				int id=0;
				try {
					id = c.nuevoID(Main.con, "idReserva", "Reserva");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Fecha f = new Fecha();
			
				String q5 = "select idHora from Horario where idactividad in(select idactividad from Actividad where nombre like '"
						+ getCbaula().getSelectedItem() + "') and Diasemana like '" + getDiasemana().getSelectedItem() + "'";
				try {
					c.alta(Main.con, "INSERT INTO Reserva VALUES ("+id+","+vasafuncionar(q5)+","+ v1.getDNI1()+", '"+f.fechaActual() +"')");
					JOptionPane.showMessageDialog(null, "¡Se ha agregado correctamente!", "Creado correctamente",
							JOptionPane.INFORMATION_MESSAGE);
				
				} catch (Exception h) {
					JOptionPane.showMessageDialog(null, "No se ha podido agregar", "¡Advertencia!",
							JOptionPane.ERROR_MESSAGE);
				h.printStackTrace();
				}
			}
			
			public String vasafuncionar(String query) throws SQLException {
				ResultSet rs = c.consulta(Main.con, query);
				rs.next();
				return rs.getString("idhora");
				
			}
			
		}
	
		
		
		addbutton elListener = new addbutton();
		btnAñadir.addActionListener(elListener);
	
		
		jpSur.add(btnAñadir);
		jpSur.add(btnCancelar);

		this.getContentPane().add(jpSur, BorderLayout.SOUTH);

		this.setVisible(true);

	}
	


	public JLabel getLblmostraraforo() {
		return lblmostraraforo;
	}



	public void setLblmostraraforo(JLabel lblmostraraforo) {
		this.lblmostraraforo = lblmostraraforo;
	}



	public JLabel getLblaforo() {
		return lblaforo;
	}



	public void setLblaforo(JLabel lblaforo) {
		this.lblaforo = lblaforo;
	}



	public JTextField getTxtIdclase() {
		return txtIdclase;
	}

	public void setTxtIdclase(JTextField txtIdclase) {
		this.txtIdclase = txtIdclase;
	}

	public JTextField getTxtNombreactividad() {
		return txtNombreactividad;
	}

	public void setTxtNombreactividad(JTextField txtNombreactividad) {
		this.txtNombreactividad = txtNombreactividad;
	}

	public JButton getBtnAñadir() {
		return btnAñadir;
	}

	public void setBtnAñadir(JButton btnAñadir) {
		this.btnAñadir = btnAñadir;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JPanel getJpCentro() {
		return jpCentro;
	}

	public void setJpCentro(JPanel jpCentro) {
		this.jpCentro = jpCentro;
	}

	public JPanel getJpSur() {
		return jpSur;
	}

	public void setJpSur(JPanel jpSur) {
		this.jpSur = jpSur;
	}



	public JComboBox getActividadDel() {
		return cbaula;
	}

	public JComboBox getDia() {
		return diaAdd;
	}

	public JComboBox getCbaula() {
		return cbaula;
	}

	public void setCbaula(JComboBox cbaula) {
		this.cbaula = cbaula;
	}

	public JComboBox getCbhora() {
		return cbhora;
	}

	public void setCbhora(JComboBox cbhora) {
		this.cbhora = cbhora;
	}

	public JComboBox getDiasemana() {
		return diasemana;
	}

	public void setDiasemana(JComboBox diasemana) {
		this.diasemana = diasemana;
	}
	

}