package AdminFront;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
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

public class V_AdminHome extends JFrame {
	private JMenuBar jmb;
	private JMenu[] me = new JMenu[10];
	private JMenuItem[] mi = new JMenuItem[21];
	private JSeparator sep;
	private String DNI1;
	private JLabel lbl;
	private BufferedImage logo;


	public V_AdminHome(String DNI) throws IOException {

		super("Gestión de gimnasio - Administrador");
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
		
		me[9] = new JMenu("Listados");
		jmb.add(me[9]);
		mi[18] = new JMenuItem("Listado general");
		mi[18].addActionListener(new Ac_MenuAdmin(this));
		me[9].add(mi[18]);
		mi[19] = new JMenuItem("Lista usuarios");
		mi[19].addActionListener(new Ac_MenuAdmin(this));
		me[9].add(mi[19]);
		mi[20] = new JMenuItem("Lista profesores");
		mi[20].addActionListener(new Ac_MenuAdmin(this));
		me[9].add(mi[20]);
		
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
		mi[12] = new JMenuItem("Pagos y Nominas");
		mi[12].addActionListener(new Ac_MenuAdmin(this));
		me[5].add(mi[12]);

		me[6] = new JMenu("Estadisticas");
		jmb.add(me[6]);

		mi[14] = new JMenuItem("De Actividades");
		mi[14].addActionListener(new Ac_MenuAdmin(this));
		me[6].add(mi[14]);

		me[7] = new JMenu("Perfil");
		jmb.add(me[7]);

		mi[15] = new JMenuItem("Mi Perfil");
		mi[15].addActionListener(new Ac_MenuAdmin(this));
		me[7].add(mi[15]);

		mi[16] = new JMenuItem("Cerrar sesión");
		mi[16].addActionListener(new Ac_MenuAdmin(this));
		me[7].add(mi[16]);

		me[8] = new JMenu("Opciones");
		jmb.add(me[8]);

		mi[17] = new JMenuItem("Cerrar ventana");
		mi[17].addActionListener(new Ac_MenuAdmin(this));
		me[8].add(mi[17]);
		
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

	public void setDNI1(String dNI1) {
		DNI1 = dNI1;
	}

}
