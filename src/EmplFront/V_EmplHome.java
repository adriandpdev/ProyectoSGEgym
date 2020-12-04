package EmplFront;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.*;

import EmplBack.Ac_MenuEmple;
import UserBack.Ac_MenuUser;

public class V_EmplHome extends JFrame {
	private JMenuBar jmb;
	private JMenu[] me = new JMenu[4];
	private JMenuItem[] mi = new JMenuItem[7];
	private String DNI1;
	private JLabel lbl;
	private BufferedImage logo;
	
	public V_EmplHome(String DNI) throws IOException {
		super("Gestión de gimnasio - Empleado");
		DNI1 = DNI;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setSize(screenSize);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(1000, 600));
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/icono.png"));
        setIconImage(icon.getImage());

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
		
		me[2] = new JMenu("Perfil");
		jmb.add(me[2]);
		mi[4] = new JMenuItem("Mi Perfil");
		mi[4].addActionListener(new Ac_MenuEmple(this));
		me[2].add(mi[4]);
		mi[6] = new JMenuItem("Cerrar sesión");
		mi[6].addActionListener(new Ac_MenuEmple(this));
		me[2].add(mi[6]);

		me[3] = new JMenu("Opciones");
		jmb.add(me[3]);
		mi[5] = new JMenuItem("Cerrar ventana");
		mi[5].addActionListener(new Ac_MenuEmple(this));
		me[3].add(mi[5]);
		setfoto();
		
		this.setVisible(true);
	}
	public void setfoto() throws IOException {
		logo = ImageIO.read(getClass().getResource("/images/fondomenu.png"));
		lbl = new JLabel(new ImageIcon(logo));
		this.add(lbl, BorderLayout.CENTER);
	}

	public String getDNI1() {
		return DNI1;
	}

	public void setDNI1(String dNI1) {
		DNI1 = dNI1;
	}
	
}
