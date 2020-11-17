package UserFront;
import java.awt.*;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import main.Conexion;
import main.Main;

public class V_UserPerfil extends JInternalFrame{
	
	private V_UserHome v1;
	private JLabel [] lbl = new JLabel[11];
	private JTextField txt_Dni, txt_Rol, txt_Nombre, txt_Apell, txt_Antigua_Contraseña,txt_Nueva_Contraseña, txt_Correo, txt_Cb, txt_Nac, txt_Tlf;
	private JButton btn_Guardar, btn_CambiarContraseña;
	
	public V_UserPerfil(V_UserHome v){
		v1 = v;

		//Atributos del panel
		setLayout(new BorderLayout());
		//INSTANCIO LOS ELEMENTOS
		lbl[0] = new JLabel("Ajustes de Perfil");
		lbl[1] = new JLabel("DNI");
		lbl[2] = new JLabel("ROL");
		lbl[3] = new JLabel("NOMBRE");
		lbl[4] = new JLabel("APELLIDO");
		lbl[5] = new JLabel("CORREO");
		lbl[6] = new JLabel("FECHA DE NACIMINETO");
		lbl[7] = new JLabel("TELEFONO");
		lbl[8] = new JLabel("CUENTA BANCARIA");
		lbl[9] = new JLabel("Antigua Contraseña:");
		lbl[10] = new JLabel("Nueva Contraseña:");
		txt_Dni = new JTextField();
		txt_Rol = new JTextField();
		txt_Nombre = new JTextField();
		txt_Apell = new JTextField();
		txt_Antigua_Contraseña = new JTextField();
		txt_Nueva_Contraseña = new JTextField();
		txt_Correo = new JTextField();
		txt_Cb = new JTextField();
		txt_Nac = new JTextField();
		txt_Tlf = new JTextField();
		btn_Guardar = new JButton("Guardar Cambios");
		btn_CambiarContraseña = new JButton("Cambiar Contraseña");
		
		//Atributos de los elementos
		txt_Dni.setPreferredSize(new Dimension(200,30));
		txt_Rol.setPreferredSize(new Dimension(200,30));
		txt_Nombre.setPreferredSize(new Dimension(250,30));
		txt_Apell.setPreferredSize(new Dimension(250,30));
		txt_Antigua_Contraseña.setPreferredSize(new Dimension(250,30));
		txt_Nueva_Contraseña.setPreferredSize(new Dimension(250,30));
		txt_Correo.setPreferredSize(new Dimension(250,30));
		txt_Cb.setPreferredSize(new Dimension(200,30));
		txt_Nac.setPreferredSize(new Dimension(200,30));
		txt_Tlf.setPreferredSize(new Dimension(200,30));
		btn_Guardar.setPreferredSize(new Dimension(150,30));
		btn_CambiarContraseña.setPreferredSize(new Dimension(150,30));
		//BLOQUEADOS
		txt_Dni.setEnabled(false);
		txt_Rol.setEnabled(false);
		txt_Cb.setEnabled(false);
		
		Conexion c = new Conexion();
		try {
			Boolean enc = false;
			ResultSet rs = c.consulta(Main.con, "SELECT * FROM Persona WHERE DNI = "+v1.getDNI1());
			while (rs.next()&&enc==false) {
				enc=true;
				txt_Dni.setText(rs.getString("DNI"));
				txt_Nombre.setText(rs.getString("nombre"));
				txt_Apell.setText(rs.getString("apellido"));
				txt_Cb.setText(rs.getString("cuentabanc"));
				txt_Nac.setText(rs.getString("fechanac"));
				txt_Tlf.setText(rs.getString("telefono"));
				txt_Correo.setText(rs.getString("correo"));
				txt_Rol.setText(rs.getString("rol"));
			}
			if (enc==false) {
				JOptionPane.showMessageDialog(v1, "Error en la lectura de datos del usuario");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//INSTANCIACION PANELES INDIVIDUALES
		//DNI
		JPanel panel_Dni = new JPanel();
		panel_Dni.setLayout(new FlowLayout(2));
		panel_Dni.add(lbl[1]);
		panel_Dni.add(txt_Dni);
		//Rol
		JPanel panel_Rol = new JPanel();
		panel_Rol.setLayout(new FlowLayout(2));
		panel_Rol.add(lbl[2]);
		panel_Rol.add(txt_Rol);
		//Nombre
		JPanel panel_Nombre = new JPanel();
		panel_Nombre.setLayout(new FlowLayout(2));
		panel_Nombre.add(lbl[3]);
		panel_Nombre.add(txt_Nombre);
		//Apellido
		JPanel panel_Apell = new JPanel();
		panel_Apell.setLayout(new FlowLayout(2));
		panel_Apell.add(lbl[4]);
		panel_Apell.add(txt_Apell);
		//Correo
		JPanel panel_Correo = new JPanel();
		panel_Correo.setLayout(new FlowLayout(2));
		panel_Correo.add(lbl[5]);
		panel_Correo.add(txt_Correo);
		//fecha nacimiento
		JPanel panel_Nac = new JPanel();
		panel_Nac.setLayout(new FlowLayout(2));
		panel_Nac.add(lbl[6]);
		panel_Nac.add(txt_Nac);
		//Telefono
		JPanel panel_Telefono = new JPanel();
		panel_Telefono.setLayout(new FlowLayout(2));
		panel_Telefono.add(lbl[7]);
		panel_Telefono.add(txt_Tlf);
		//Cuenta Bancaria
		JPanel panel_Bancaria = new JPanel();
		panel_Bancaria.setLayout(new FlowLayout(2));
		panel_Bancaria.add(lbl[8]);
		panel_Bancaria.add(txt_Cb);
		//Contraseña antigua
		JPanel panel_Antigua_Contraseña = new JPanel();
		panel_Antigua_Contraseña.setLayout(new FlowLayout(2));
		panel_Antigua_Contraseña.add(lbl[9]);
		panel_Antigua_Contraseña.add(txt_Antigua_Contraseña);
		//Contraseña nueva
		JPanel panel_Nueva_Contraseña = new JPanel();
		panel_Nueva_Contraseña.setLayout(new FlowLayout(2));
		panel_Nueva_Contraseña.add(lbl[10]);
		panel_Nueva_Contraseña.add(txt_Nueva_Contraseña);
		//Boton Guardar
		JPanel panel_btn_Guardar = new JPanel();
		panel_btn_Guardar.setLayout(new FlowLayout(1));
		panel_btn_Guardar.add(btn_Guardar);
		//Boton Guardar
		JPanel panel_btn_Cambiar_Contraeña = new JPanel();
		panel_btn_Cambiar_Contraeña.setLayout(new FlowLayout(1));
		panel_btn_Cambiar_Contraeña.add(btn_CambiarContraseña);
		
				
				
		//PanelNorte----
		JPanel PanelNorte = new JPanel();
		PanelNorte.setLayout(new GridLayout(1,2));
		PanelNorte.setBorder(new TitledBorder("Privado"));
		PanelNorte.add(panel_Dni);
		PanelNorte.add(panel_Rol);
		//PanelCentral-----
		JPanel PanelCentral = new JPanel();
		PanelCentral.setLayout(new BorderLayout());
		PanelCentral.setBorder(new TitledBorder("Datos Personales"));
			JPanel PanelCS1 = new JPanel();
			PanelCS1.setLayout(new GridLayout(3,2));
			PanelCS1.add(panel_Nombre);
			PanelCS1.add(panel_Correo);
			PanelCS1.add(panel_Apell);
			PanelCS1.add(panel_Nac);
			PanelCS1.add(panel_Telefono);
				PanelCS1.add(panel_Bancaria);
		PanelCentral.add(PanelCS1, BorderLayout.CENTER);
		PanelCentral.add(panel_btn_Guardar, BorderLayout.SOUTH);
		//PanelSur-----
		JPanel PanelSur = new JPanel();
		PanelSur.setLayout(new BorderLayout());
		PanelSur.setBorder(new TitledBorder("Cambio de contraseña"));
			JPanel PanelSurCentro = new JPanel();
			PanelSurCentro.setLayout(new GridLayout(1,2));
			PanelSurCentro.add(panel_Antigua_Contraseña);
			PanelSurCentro.add(panel_Nueva_Contraseña);	
		PanelSur.add(PanelSurCentro, BorderLayout.CENTER);
		PanelSur.add(panel_btn_Cambiar_Contraeña, BorderLayout.SOUTH);
					
				
		//ESCUCHAS Y EVENTOS
		//btn_Guardar.addActionListener(new);
		//btn_CambiarContraseña.addActionListener(new);
		
		//AÑADO LOS PANELES PRINCIPALES
		this.add(PanelNorte, BorderLayout.NORTH);
		this.add(PanelCentral, BorderLayout.CENTER);
		this.add(PanelSur,BorderLayout.SOUTH);
		setVisible(true);
				
	}
	

}
