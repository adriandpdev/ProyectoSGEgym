package listado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class listado extends JInternalFrame{
	
	private JLabel lbl_titulo;
	private JPanel panel_norte, panel_sur, panel_central, panel_norte_busqueda, panel_central_listado;
	private String [] nombre_columnas = {"Dni", "Nombre", "Apellido", "Cuenta Bancaria", "Fecha de Nacimiento", "Teléfono", "Correo Electrónico", "Rol"};
	

	public listado() {
		
		this.setLayout(new BorderLayout());
		
		
		//Paneles del primer BorderLayout
		panel_norte = new JPanel();
		panel_central = new JPanel();
		panel_sur = new JPanel();
		
		//Paneles del segundo BorderLayout
		panel_norte_busqueda = new JPanel();
		panel_central_listado = new JPanel();
		
		//Color de fondo personalizado del título
		panel_norte.setBackground(new Color(137, 13, 84));
		
		//Título personalizado 
		lbl_titulo = new JLabel("LISTADO DE USUARIOS/PROFESORES");
		lbl_titulo.setFont(new Font("Verdana",Font.BOLD,22));
		lbl_titulo.setForeground(Color.WHITE);
		
		panel_central.setLayout(new BorderLayout());
		
		//Panel con los filtros para la busqueda
		panel_norte_busqueda = new JPanel();
		
		//Panel con el JTable
		panel_central_listado = new JPanel();
		
		//JTable
		
		
		panel_central.add(panel_norte_busqueda, BorderLayout.NORTH);
		panel_central.add(panel_central_listado, BorderLayout.CENTER);
		panel_norte.add(lbl_titulo);
		
		
		this.getContentPane().add(panel_norte, BorderLayout.NORTH);
		this.getContentPane().add(panel_central, BorderLayout.CENTER);
		this.getContentPane().add(panel_sur, BorderLayout.SOUTH);
		this.setResizable(false);
		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
}
