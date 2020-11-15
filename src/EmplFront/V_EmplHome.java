package EmplFront;

import javax.swing.*;
import javax.swing.UIManager.*;

import EmplBack.Ac_MenuEmple;

public class V_EmplHome extends JFrame {
	private JMenuBar jmb;
	private JMenu[] me = new JMenu[3];
	private JMenuItem[] mi = new JMenuItem[6];

	V_EmplHome() {
		super("Gestión de gimnasio - Empleado");
		this.setSize(800, 600);
		this.setLocation(100, 100); // PONER AL CENTRO
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		jmb = new JMenuBar();
		this.setJMenuBar(jmb);

		me[0] = new JMenu("Horarios");
		jmb.add(me[0]);
		mi[0] = new JMenuItem("Mis clases");
		mi[0].addActionListener(new Ac_MenuEmple(this));
		me[0].add(mi[0]);
		mi[1] = new JMenuItem("Ver horario");
		mi[1].addActionListener(new Ac_MenuEmple(this));
		me[0].add(mi[1]);

		me[1] = new JMenu("Nominas");
		jmb.add(me[1]);
		mi[2] = new JMenuItem("Última nómina");
		mi[2].addActionListener(new Ac_MenuEmple(this));
		me[1].add(mi[2]);
		mi[3] = new JMenuItem("Historial");
		mi[3].addActionListener(new Ac_MenuEmple(this));
		me[1].add(mi[3]);

		me[2] = new JMenu("Opciones");
		jmb.add(me[2]);
		mi[4] = new JMenuItem("Cerrar ventana");
		mi[4].addActionListener(new Ac_MenuEmple(this));
		me[2].add(mi[4]);
		mi[5] = new JMenuItem("Cerrar sesión");
		mi[5].addActionListener(new Ac_MenuEmple(this));
		me[2].add(mi[5]);

		this.setVisible(true);
	}
}
