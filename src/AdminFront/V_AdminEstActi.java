package AdminFront;

import java.awt.*;
import javax.swing.*;

public class V_AdminEstActi extends JInternalFrame {

	private JLabel[] lbl = new JLabel[3];

	public V_AdminEstActi() {
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		
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
}
