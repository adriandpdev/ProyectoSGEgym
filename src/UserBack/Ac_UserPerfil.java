package UserBack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


import UserFront.V_UserPerfil;
import main.Conexion;
import main.Main;

public class Ac_UserPerfil implements ActionListener {
	private V_UserPerfil vent;
	
	public Ac_UserPerfil(V_UserPerfil v){
		vent = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//PERMITE MODIFICAR LOS DATOS DEL USUARIO.
		if(e.getSource() == vent.getBtn_Guardar()) {
			modificarDatos();
		//PERMITE CAMBIAR LA CONTRASE�A DEL USUARIO. 
		}else if(e.getSource() == vent.getBtn_CambiarContrase�a()) {
			modificarContrase�a();
		}
	}
	
	
	//MODIFICA LOS DATOS DEL USUARIO SEGUN LOS VALORES DE LAS CAJAS DE TEXTO.
	public void modificarDatos() {
		Conexion c = new Conexion();
		try {
			c.alta(Main.con, "UPDATE Persona SET nombre = '"+vent.getTxt_Nombre().getText()+"', apellido = '"+vent.getTxt_Apell().getText()+"', cuentabanc = '"+vent.getTxt_Cb().getText()+"', fechanac = '"+vent.getTxt_Nac().getText()+"', telefono = '"+Integer.parseInt(vent.getTxt_Tlf().getText())+"', correo = '"+vent.getTxt_Correo().getText()+"' WHERE DNI = '"+vent.getTxt_Dni().getText()+"'");
			JOptionPane.showMessageDialog(vent, "Cambios guardados con exito!");				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	//MODIFICA LA CONTRASE�A DE LA PERSONA.
	public void modificarContrase�a() {
		Conexion c = new Conexion();
		String AntiguaContrase�a = vent.getTxt_Antigua_Contrase�a().getText();
		String NuevaContrase�a = vent.getTxt_Nueva_Contrase�a().getText();
		String pass = "";
		try {
			//Almaceno la contrase�a de la persona con el DNI logeado.
			ResultSet rs = c.consulta(Main.con, "SELECT pass FROM Persona WHERE DNI ='"+vent.getTxt_Dni().getText()+"'");
			while (rs.next()) {
				pass = rs.getString("pass");
			}
			if(AntiguaContrase�a.isEmpty()||NuevaContrase�a.isEmpty()) {
				JOptionPane.showMessageDialog(vent, "Inserte la contrase�a antigua y la nueva.");
			}else if(AntiguaContrase�a.equals(pass)) {
				c.alta(Main.con, "UPDATE Persona SET pass = '"+NuevaContrase�a+"' WHERE DNI = '"+vent.getTxt_Dni().getText()+"'");
				JOptionPane.showMessageDialog(vent, "Contrase�a cambiada con Exito!");
			}else {
				JOptionPane.showMessageDialog(vent, "Contrase�a Incorrecta, Ingrese su contrase�a actual para modificarla!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	//VALIDACIONES-----------------------------------	
	public boolean validarCorreo() {
		//Si el correo posee Almenos 1 caracter o +, seguido de un @ solo, seguido de 1 o m�s caracteres, luego 1 punto y por ultimo 2 o + letras.
		//Ejemplo: a@b.co;	ac@bc.com
		String EmailCaracteres = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		return Pattern.matches(EmailCaracteres, vent.getTxt_Correo().getText());
	}
	
	public boolean validarFechaNac() {
		//Permite modelo : 11/11/1999  Tambien 1/1/99
		String Fecha = "\\d{1,2}/\\d{1,2}/\\{2,4}";
		return Pattern.matches(Fecha, vent.getTxt_Nac().getText());
	}
	
	
}

