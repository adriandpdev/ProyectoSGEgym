package Login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class V_Login extends JFrame {
	private JTextField txt[] = new JTextField[2];
	private JLabel lbl[] = new JLabel[5];
	private JCheckBox ch;
	private JButton btn;
	private JPanel pn[] = new JPanel[3];
	private BufferedImage logo;

	public V_Login() throws IOException {
		super("Inicio de sesión");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			System.out.println(e);
		}
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setLayout(new BorderLayout());
		pn[2] = new JPanel();
		pn[2].setLayout(new BorderLayout());
		lbl[3] = new JLabel("<html><center>Gestión del<br>gimnasio<html>", SwingConstants.CENTER);
		lbl[3].setFont(new Font("Century", Font.BOLD, 40));
		pn[2].add(lbl[3], BorderLayout.NORTH);
		logo = ImageIO.read(new File("logo.png"));
		lbl[4] = new JLabel(new ImageIcon(logo));
		pn[2].add(lbl[4], BorderLayout.CENTER);
		pn[0] = new JPanel();
		pn[0].setSize(500, 150);
		pn[0].setLayout(new GridLayout(2, 2, 10, 10));
		pn[1] = new JPanel();
		pn[1].setLayout(new BorderLayout());

		txt[0] = new JTextField();
		txt[0].setSize(300, 30);
		txt[1] = new JTextField();
		txt[1].setSize(300, 30);
		lbl[0] = new JLabel("DNI");
		lbl[1] = new JLabel("Contraseña");
		lbl[2] = new JLabel("He olvidado mi contraseña", SwingConstants.CENTER);
		btn = new JButton("Iniciar sesión");
		btn.setMargin(new Insets(10, 10, 10, 10));
		btn.addActionListener(new Ac_Login(this));
		ch = new JCheckBox("Recordar DNI");
		ch.setHorizontalAlignment(JLabel.CENTER);
		
		pn[0].add(lbl[0]);
		pn[0].add(txt[0]);
		pn[0].add(lbl[1]);
		pn[0].add(txt[1]);
		pn[1].add(ch, BorderLayout.NORTH);
		pn[1].add(btn, BorderLayout.CENTER);
		pn[1].add(lbl[2], BorderLayout.SOUTH);
		
		this.getContentPane().add(pn[2], BorderLayout.NORTH);
		this.getContentPane().add(pn[0], BorderLayout.CENTER);
		this.getContentPane().add(pn[1], BorderLayout.SOUTH);
		
		this.setVisible(true);
		trycache();
	}

	public JTextField[] gettxt() {
		return txt;
	}
	private void trycache() throws IOException {
		File f = new File ("cache.txt");
		if(f.exists()){	
			FileReader fr= new FileReader (f);
			BufferedReader BRF = new BufferedReader (fr);
			String linea;
			linea=BRF.readLine();
			while (linea!=null) {
				txt[0].setText(linea);
				ch.setSelected(true);
				linea=BRF.readLine();
			}
			BRF.close();
			fr.close();
			txt[1].requestFocus();
		} 
	}
	public JCheckBox getch() {
		return ch;
	}
	
}
