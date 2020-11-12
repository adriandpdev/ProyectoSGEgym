import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class V_Login extends JFrame{
	private JTextField txt[] = new JTextField[2];
	private JLabel lbl[] = new JLabel[3];
	private JCheckBox ch;
	private JButton btn;
	
	V_Login() {
		super("Inicio de sesión");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			System.out.println(e);
		}
		this.setSize(500, 600);
		this.setLocation(100, 100); // PONER AL CENTRO
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
		this.setResizable(false); 
		this.setLayout(new GridLayout(4,2,50,50));
		
		txt[0] = new JTextField();
		txt[1] = new JTextField();
		lbl[0] = new JLabel("DNI");
		lbl[1] = new JLabel("Contraseña");
		lbl[2] = new JLabel("He olvidado mi contraseña");
		btn = new JButton("Iniciar sesión");
		btn.addActionListener(new Ac_Login(this));
		ch = new JCheckBox("Recordar DNI");
		
		this.getContentPane().add(lbl[0]);
		this.getContentPane().add(txt[0]);
		this.getContentPane().add(lbl[1]);
		this.getContentPane().add(txt[1]);
		this.getContentPane().add(ch);
		this.getContentPane().add(btn);
		this.getContentPane().add(lbl[2]);
		
		
		this.setVisible(true);
	}
}
