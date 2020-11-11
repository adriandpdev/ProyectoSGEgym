import javax.swing.JFrame;
import javax.swing.UIManager;

public class V_Login extends JFrame{
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
		this.setLayout(null);
		
		this.setVisible(true);
	}
}
