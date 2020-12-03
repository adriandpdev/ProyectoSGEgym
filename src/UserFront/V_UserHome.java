package UserFront;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.*;

import AdminBack.Ac_AdminScheExp;
import AdminBack.Ac_MenuAdmin;
import UserBack.Ac_MenuUser;

public class V_UserHome extends JFrame {
	private JMenuBar jmb;
	private JMenu[] me = new JMenu[6];
	private JMenuItem[] mi = new JMenuItem[10];
	private String DNI1;
	private JLabel lbl;
	private BufferedImage logo;
	
	public V_UserHome(String DNI) throws IOException {
		super("Gestión de gimnasio - Usuario");
		DNI1 = DNI;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setSize(screenSize);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(1000, 600));
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("./images/icono.png");
        setIconImage(icon.getImage());
        
		jmb = new JMenuBar();
		this.setJMenuBar(jmb);

		me[0] = new JMenu("Horarios");
		jmb.add(me[0]);
		mi[0] = new JMenuItem("Ver Horario");
		mi[0].addActionListener(new Ac_MenuUser(this));
		me[0].add(mi[0]);
		mi[1] = new JMenuItem("Exportar pdf");
		mi[1].addActionListener(new Ac_AdminScheExp());
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
		mi[3] = new JMenuItem("Información");
		mi[3].addActionListener(new Ac_MenuUser(this));
		me[2].add(mi[3]);
		mi[4] = new JMenuItem("Últimos pagos");
		mi[4].addActionListener(new Ac_MenuUser(this));
		me[2].add(mi[4]);

		me[3] = new JMenu("Avisos y promociones");
		jmb.add(me[3]);
		mi[5] = new JMenuItem("Últimos avisos");
		mi[5].addActionListener(new Ac_MenuUser(this));
		me[3].add(mi[5]);
		mi[6] = new JMenuItem("Promociones activas");
		mi[6].addActionListener(new Ac_MenuUser(this));
		me[3].add(mi[6]);

		me[4] = new JMenu("Perfil");
		jmb.add(me[4]);

		mi[7] = new JMenuItem("Mi Perfil");
		mi[7].addActionListener(new Ac_MenuUser(this));
		me[4].add(mi[7]);

		mi[9] = new JMenuItem("Cerrar sesión");
		mi[9].addActionListener(new Ac_MenuUser(this));
		me[4].add(mi[9]);

		me[5] = new JMenu("Opciones");
		jmb.add(me[5]);

		mi[8] = new JMenuItem("Cerrar ventana");
		mi[8].addActionListener(new Ac_MenuUser(this));
		me[5].add(mi[8]);
		setfoto();

		this.setVisible(true);
	}
	public void setfoto() throws IOException {
		logo = ImageIO.read(new File("images/fondomenu.png"));
		lbl = new JLabel(new ImageIcon(logo));
		this.add(lbl, BorderLayout.CENTER);
	}

	public String getDNI1() {
		return DNI1;
	}
}
