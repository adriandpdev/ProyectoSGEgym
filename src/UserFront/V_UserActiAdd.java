package UserFront;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	private JLabel lblTitulo, lblDniuser, lblIdaula, lblverdniuser, lblhora, lbldiasemana, lblaforo, lblmostraraforo;
	private JTextField txtIdclase, txtNombreactividad;
	private JComboBox cbaula, cbhora, diasemana, diaAdd, cbaforo;
	private JButton btnAñadir, btnCancelar;
	private JPanel jpCentro, jpSur;
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

	public V_UserActiAdd(V_UserHome venti) {
		v1 = venti;

		this.setTitle("Alta de Clases");
		this.setSize(500, 500);
		this.setLocation(20, 20);
		this.setLayout(new BorderLayout());

		// Parte norte del borderlayout

		lblTitulo = new JLabel("RESERVA DE CLASES");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblTitulo, BorderLayout.NORTH);

		// Parte central del borderlayout

		jpCentro = new JPanel();
		jpCentro.setLayout(new GridBagLayout());
		lblDniuser = new JLabel("Tu DNI:");
		lblverdniuser = new JLabel();
		lblverdniuser.setText(v1.getDNI1());
		lblIdaula = new JLabel("SELECCIONA LA ACTVIDAD A LA QUE QUIERES APUNTARTE:");
		cbaula = new JComboBox();

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

		lblhora = new JLabel("ELIGE HORA:");
		lbldiasemana = new JLabel("ELIGE DÍA DE LA SEMANA:");
		cbhora = new JComboBox();
		cbhora.addActionListener(new Ac_UserActiAdd(this));
		diasemana = new JComboBox();

		for (int i = 0; i < semana.length; i++) {
			diasemana.addItem(semana[i]);
		}

		lblaforo = new JLabel("AFORO TOTAL: ");

		lblmostraraforo = new JLabel();

		diasemana.addActionListener(new Ac_UserActiAdd(this));

		GridBagConstraints g = new GridBagConstraints();

		g.gridwidth = 1;
		g.weightx = 1;
		g.weighty = 1;

		g.gridx = 0;
		g.gridy = 3;
		jpCentro.add(lblDniuser, g);

		g.gridx = 1;
		g.gridy = 3;
		lblverdniuser.setPreferredSize(new Dimension(200, 20));
		jpCentro.add(lblverdniuser, g);

		g.gridx = 0;
		g.gridy = 4;
		jpCentro.add(lblIdaula, g);

		g.gridx = 1;
		g.gridy = 4;
		cbaula.setPreferredSize(new Dimension(200, 20));
		jpCentro.add(cbaula, g);

		g.gridx = 5;
		g.gridy = 7;
		jpCentro.add(lblhora, g);

		g.gridx = 0;
		g.gridy = 7;
		jpCentro.add(lbldiasemana, g);

		g.gridx = 0;
		g.gridy = 8;
		jpCentro.add(diasemana, g);
		g.gridx = 5;
		g.gridy = 8;
		jpCentro.add(cbhora, g);

		g.gridwidth = 1;
		g.weightx = 1;
		g.weighty = 1;

		g.gridx = 0;
		g.gridy = 9;
		jpCentro.add(lblaforo, g);

		g.gridx = 1;
		g.gridy = 9;
		jpCentro.add(lblmostraraforo, g);

		this.getContentPane().add(jpCentro, BorderLayout.CENTER);

		// Parte sur del borderlayout
		jpSur = new JPanel();
		jpSur.setLayout(new GridLayout(1, 2));

		btnAñadir = new JButton("AÑADIR");
		btnCancelar = new JButton("CANCELAR");

		final class addbutton implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null,

						"Tu usuario con el dni: " + v1.getDNI1() + " se ha apuntado a la clase: "
								+ cbaula.getSelectedItem() + " el " + diasemana.getSelectedItem() + " a las "
								+ cbhora.getSelectedItem());
				int id = 0;
				try {
					id = c.nuevoID(Main.con, "idReserva", "Reserva");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Fecha f = new Fecha();

				String queryIdHora = "select idHora from Horario where idactividad in(select idactividad from Actividad where nombre like '"
						+ getCbaula().getSelectedItem() + "') and Diasemana like '" + getDiasemana().getSelectedItem()
						+ "'";

				int idHora = 0;
				int MaxAforo = 0;
				int reservas = 0;
				try {
					ResultSet rs = c.consulta(Main.con, queryIdHora);
					while (rs.next()) {
						idHora = rs.getInt(0);
					}
					String queryAforoMax = "SELECT Aulas.aforo FROM Aulas,Actividad,Horario WHERE Aulas.idAula = Actividad.idAula AND Actividad.idActividad = Horario.IdActividad AND Horario.IdHora = "
							+ idHora;
					rs = c.consulta(Main.con, queryAforoMax);
					while (rs.next()) {
						MaxAforo = rs.getInt(0);
					}
					String queryCantidadReservas = "SELECT Actividad.nombre,Reserva.idHora, COUNT(*) FROM Actividad , Reserva,Horario WHERE Actividad.idActividad=Horario.IdActividad AND Horario.IdHora=Reserva.idHora AND Reserva.idHora = "
							+ idHora + " GROUP BY idHora";
					rs = c.consulta(Main.con, queryCantidadReservas);
					while (rs.next()) {
						reservas++;
					}
				} catch (Exception e2) {
				}

				if (reservas < MaxAforo) {
					try {
						c.alta(Main.con, "INSERT INTO Reserva VALUES (" + id + "," + vasafuncionar(queryIdHora) + ","
								+ v1.getDNI1() + ", '" + f.fechaActual() + "')");
						JOptionPane.showMessageDialog(null, "¡Se ha agregado correctamente!", "Creado correctamente",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception h) {
						JOptionPane.showMessageDialog(null, "No se ha podido agregar", "¡Advertencia!",
								JOptionPane.ERROR_MESSAGE);
						h.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se ha podido agregar", "¡Aforo Maximo!",
							JOptionPane.ERROR_MESSAGE);
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
