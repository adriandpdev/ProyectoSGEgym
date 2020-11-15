
import javax.swing.*;
import javax.swing.UIManager.*;

public class V_UserHome extends JFrame {
	private JMenuBar jmb;
	private JMenu[] me = new JMenu[7];
	private JMenuItem[] mi = new JMenuItem[16];

	V_UserHome() {
		super("Gesti�n de gimnasio - Usuario");
		this.setSize(800, 600);
		this.setLocation(100, 100); // PONER AL CENTRO
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		jmb = new JMenuBar();
		this.setJMenuBar(jmb);

		me[0] = new JMenu("Horarios");
		jmb.add(me[0]);
		mi[0] = new JMenuItem("Ver Horario");
		mi[0].addActionListener(new Ac_MenuUser(this));
		me[0].add(mi[0]);
		mi[1] = new JMenuItem("");
		mi[1].addActionListener(new Ac_MenuUser(this));
		me[0].add(mi[1]);

		me[1] = new JMenu("Actividades");
		jmb.add(me[1]);
		mi[2] = new JMenuItem("Reserva");
		mi[2].addActionListener(new Ac_MenuUser(this));
		me[1].add(mi[2]);
		mi[2] = new JMenuItem("Mis reservas");
		mi[2].addActionListener(new Ac_MenuUser(this));
		me[1].add(mi[2]);

		me[2] = new JMenu("Pagos");
		jmb.add(me[2]);
		mi[3] = new JMenuItem("Informaci�n");
		mi[3].addActionListener(new Ac_MenuUser(this));
		me[2].add(mi[3]);
		mi[4] = new JMenuItem("�ltimos pagos");
		mi[4].addActionListener(new Ac_MenuUser(this));
		me[2].add(mi[4]);

		me[3] = new JMenu("Avisos y promociones");
		jmb.add(me[3]);
		mi[5] = new JMenuItem("�ltimos avisos");
		mi[5].addActionListener(new Ac_MenuUser(this));
		me[3].add(mi[5]);
		mi[6] = new JMenuItem("Promociones activas");
		mi[6].addActionListener(new Ac_MenuUser(this));
		me[3].add(mi[6]);

		me[4] = new JMenu("Opciones");
		jmb.add(me[4]);
		mi[7] = new JMenuItem("Cerrar ventana");
		mi[7].addActionListener(new Ac_MenuUser(this));
		me[4].add(mi[7]);
		mi[8] = new JMenuItem("Cerrar sesi�n");
		mi[8].addActionListener(new Ac_MenuUser(this));
		me[4].add(mi[8]);

		this.setVisible(true);
	}
}
