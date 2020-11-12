import javax.swing.*;

public class V_AdminHome extends JFrame{
	private JMenuBar jmb;
	private JMenu me;
	private JMenuItem[] mi = new JMenuItem[3];
	private JSeparator sep;
	
	V_AdminHome() {
		super("Gestión de gimnasio - Administrador");
		this.setSize(800, 600);
		this.setLocation(100, 100); // PONER AL CENTRO
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
		this.setResizable(false); 
		this.setLayout(null);
		
		jmb = new JMenuBar();
		this.getContentPane().add(jmb);
		me = new JMenu("MENU DE OPCIONES");
		jmb.add(me);
		mi[0]= new JMenuItem("Piedra, papel o Tijera");
		mi[0].addActionListener(new Ac_Menu(this));
		me.add(mi[0]);
		mi[1]= new JMenuItem("Números");
		mi[1].addActionListener(new Ac_Menu(this));
		me.add(mi[1]);
		sep = new JSeparator();
		me.add(sep);
		mi[2]= new JMenuItem("Salir");
		mi[2].addActionListener(new Ac_Menu(this));
		me.add(mi[2]);
		
		this.setVisible(true);
	}
}
