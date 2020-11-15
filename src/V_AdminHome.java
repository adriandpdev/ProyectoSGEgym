import javax.swing.*;
import javax.swing.UIManager.*;

public class V_AdminHome extends JFrame{
	private JMenuBar jmb;
	private JMenu[] me = new JMenu[7];
	private JMenuItem[] mi = new JMenuItem[16];
	private JSeparator sep;
	
	V_AdminHome() {
		super("Gestión de gimnasio - Administrador");
		this.setSize(800, 600);
		this.setLocation(100, 100); // PONER AL CENTRO
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
		this.setResizable(false); 
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			/*for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }*/
		} catch (Exception e) {
			System.out.println(e);
		}
		jmb = new JMenuBar();
		this.setJMenuBar(jmb);
		
		me[0] = new JMenu("Usuarios");
		jmb.add(me[0]);
		mi[0]= new JMenuItem("Alta usuario");
		mi[0].addActionListener(new Ac_MenuAdmin(this));
		me[0].add(mi[0]);
		mi[1]= new JMenuItem("Lista usuarios");
		mi[1].addActionListener(new Ac_MenuAdmin(this));
		me[0].add(mi[1]);
		mi[2]= new JMenuItem("Pendiente de pago");
		mi[2].addActionListener(new Ac_MenuAdmin(this));
		me[0].add(mi[2]);
		
		me[1] = new JMenu("Profesores");
		jmb.add(me[1]);
		mi[3]= new JMenuItem("Alta profesor");
		mi[3].addActionListener(new Ac_MenuAdmin(this));
		me[1].add(mi[3]);
		mi[4]= new JMenuItem("Lista profesores");
		mi[4].addActionListener(new Ac_MenuAdmin(this));
		me[1].add(mi[4]);
	
		me[2] = new JMenu("Actividades");
		jmb.add(me[2]);
		mi[5]= new JMenuItem("Alta actividad");
		mi[5].addActionListener(new Ac_MenuAdmin(this));
		me[2].add(mi[5]);
		mi[6]= new JMenuItem("Lista actividades");
		mi[6].addActionListener(new Ac_MenuAdmin(this));
		me[2].add(mi[6]);

		
		me[3] = new JMenu("Horario");
		jmb.add(me[3]);
		mi[7]= new JMenuItem("Añadir hora");
		mi[7].addActionListener(new Ac_MenuAdmin(this));
		me[3].add(mi[7]);
		mi[8]= new JMenuItem("Visualizar horario");
		mi[8].addActionListener(new Ac_MenuAdmin(this));
		me[3].add(mi[8]);
		mi[9]= new JMenuItem("Exportar PDF");
		mi[9].addActionListener(new Ac_MenuAdmin(this));
		me[3].add(mi[9]);
		
		me[4] = new JMenu("Newsletter");
		jmb.add(me[4]);
		mi[10]= new JMenuItem("Promociones");
		mi[10].addActionListener(new Ac_MenuAdmin(this));
		me[4].add(mi[10]);
		mi[11]= new JMenuItem("Avisos");
		mi[11].addActionListener(new Ac_MenuAdmin(this));
		me[4].add(mi[11]);
		
		me[5] = new JMenu("Facturas");
		jmb.add(me[5]);
		mi[12]= new JMenuItem("Pagos usuarios");
		mi[12].addActionListener(new Ac_MenuAdmin(this));
		me[5].add(mi[12]);
		mi[13]= new JMenuItem("Nominas profesores");
		mi[13].addActionListener(new Ac_MenuAdmin(this));
		me[5].add(mi[13]);
		
		me[6] = new JMenu("Opciones");
		jmb.add(me[6]);
		mi[14]= new JMenuItem("Cerrar ventana");
		mi[14].addActionListener(new Ac_MenuAdmin(this));
		me[6].add(mi[14]);
		mi[15]= new JMenuItem("Cerrar sesión");
		mi[15].addActionListener(new Ac_MenuAdmin(this));
		me[6].add(mi[15]);
		
		this.setVisible(true);
	}
}








