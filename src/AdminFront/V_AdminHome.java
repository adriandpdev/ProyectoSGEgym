package AdminFront;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.UIManager.*;

import AdminBack.Ac_AdminScheExp;
import AdminBack.Ac_MenuAdmin;

public class V_AdminHome extends JFrame {
	private JMenuBar jmb;
	private JMenu[] me = new JMenu[9];
	private JMenuItem[] mi = new JMenuItem[19];
	private JSeparator sep;
	private String DNI1;

	public V_AdminHome(String DNI) {
		super("Gestión de gimnasio - Administrador");
		DNI1 = DNI;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setSize(screenSize);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(1000, 600));
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

		jmb = new JMenuBar();
		this.setJMenuBar(jmb);

		me[0] = new JMenu("Usuarios");
		jmb.add(me[0]);
		mi[0] = new JMenuItem("Alta usuario");
		mi[0].addActionListener(new Ac_MenuAdmin(this));
		me[0].add(mi[0]);
		mi[1] = new JMenuItem("Lista usuarios");
		mi[1].addActionListener(new Ac_MenuAdmin(this));
		me[0].add(mi[1]);
		mi[2] = new JMenuItem("Pendiente de pago");
		mi[2].addActionListener(new Ac_MenuAdmin(this));
		me[0].add(mi[2]);

		me[1] = new JMenu("Profesores");
		jmb.add(me[1]);
		mi[3] = new JMenuItem("Alta profesor");
		mi[3].addActionListener(new Ac_MenuAdmin(this));
		me[1].add(mi[3]);
		mi[4] = new JMenuItem("Lista profesores");
		mi[4].addActionListener(new Ac_MenuAdmin(this));
		me[1].add(mi[4]);

		me[2] = new JMenu("Actividades");
		jmb.add(me[2]);
		mi[5] = new JMenuItem("Alta actividad");
		mi[5].addActionListener(new Ac_MenuAdmin(this));
		me[2].add(mi[5]);
		mi[6] = new JMenuItem("Lista actividades");
		mi[6].addActionListener(new Ac_MenuAdmin(this));
		me[2].add(mi[6]);

		me[3] = new JMenu("Horario");
		jmb.add(me[3]);
		mi[7] = new JMenuItem("Añadir hora");
		mi[7].addActionListener(new Ac_MenuAdmin(this));
		me[3].add(mi[7]);
		mi[8] = new JMenuItem("Visualizar horario");
		mi[8].addActionListener(new Ac_MenuAdmin(this));
		me[3].add(mi[8]);
		mi[9] = new JMenuItem("Exportar PDF");
		mi[9].addActionListener(new Ac_AdminScheExp());
		me[3].add(mi[9]);

		me[4] = new JMenu("Newsletter");
		jmb.add(me[4]);
		mi[10] = new JMenuItem("Promociones");
		mi[10].addActionListener(new Ac_MenuAdmin(this));
		me[4].add(mi[10]);
		mi[11] = new JMenuItem("Avisos");
		mi[11].addActionListener(new Ac_MenuAdmin(this));
		me[4].add(mi[11]);

		me[5] = new JMenu("Facturas");
		jmb.add(me[5]);
		mi[12] = new JMenuItem("Pagos usuarios");
		mi[12].addActionListener(new Ac_MenuAdmin(this));
		me[5].add(mi[12]);
		mi[13] = new JMenuItem("Nominas profesores");
		mi[13].addActionListener(new Ac_MenuAdmin(this));
		me[5].add(mi[13]);

		me[6] = new JMenu("Estadisticas");
		jmb.add(me[6]);

		mi[15] = new JMenuItem("De Actividades");
		mi[15].addActionListener(new Ac_MenuAdmin(this));
		me[6].add(mi[15]);

		me[7] = new JMenu("Perfil");
		jmb.add(me[7]);

		mi[16] = new JMenuItem("Mi Perfil");
		mi[16].addActionListener(new Ac_MenuAdmin(this));
		me[7].add(mi[16]);

		mi[18] = new JMenuItem("Cerrar sesión");
		mi[18].addActionListener(new Ac_MenuAdmin(this));
		me[7].add(mi[18]);

		me[8] = new JMenu("Opciones");
		jmb.add(me[8]);

		mi[17] = new JMenuItem("Cerrar ventana");
		mi[17].addActionListener(new Ac_MenuAdmin(this));
		me[8].add(mi[17]);

		this.setVisible(true);
	}

	public String getDNI1() {
		return DNI1;
	}

	public void setDNI1(String dNI1) {
		DNI1 = dNI1;
	}

}
