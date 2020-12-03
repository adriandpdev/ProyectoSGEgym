package AdminFront;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import EmplFront.V_EmplHome;
import UserFront.V_UserHome;
import main.Conexion;
import main.Main;

public class V_AdminEstActi extends JInternalFrame {

	private JLabel[] lbl = new JLabel[3];

	public V_AdminEstActi() {
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

		lbl[0] = new JLabel("Estadisticas de actividades", SwingConstants.CENTER);
		lbl[0].setFont(new Font("Century", Font.BOLD, 40));
		lbl[1] = new JLabel("ALGO", SwingConstants.CENTER);
		lbl[2] = new JLabel("OTRO ALGO", SwingConstants.CENTER);

		// ATRIBUTOS DEL PANEL PRINCIPAL
		setLayout(new BorderLayout());
		add(lbl[0], BorderLayout.NORTH);
		add(lbl[1], BorderLayout.CENTER);
		add(lbl[2], BorderLayout.SOUTH);
		setVisible(true);
	}

	public void rellenar() throws SQLException {
		String sql = "select Actividad.nombre,Reserva.idHora, COUNT(*) FROM Actividad , Reserva WHERE Actividad.idActividad=Reserva.idHora "
				+ "GROUP BY idHora ORDER BY `COUNT(*)` DESC";
		Conexion c = new Conexion();
		ResultSet rs = c.consulta(Main.con, sql);
		while (rs.next()) {
			
		}
	}
}
