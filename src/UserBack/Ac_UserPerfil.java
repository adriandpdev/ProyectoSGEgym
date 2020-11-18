package UserBack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

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
		if(e.getSource() == vent.getBtn_Guardar()) {
			Conexion c = new Conexion();
			try {
				c.alta(Main.con, "UPDATE Persona SET nombre = '"+vent.getTxt_Nombre().getText()+"', apellido = '"+vent.getTxt_Apell().getText()+"', cuentabanc = '"+vent.getTxt_Cb().getText()+"', fechanac = '"+vent.getTxt_Nac().getText()+"', telefono = '"+Integer.parseInt(vent.getTxt_Tlf().getText())+"', correo = '"+vent.getTxt_Correo().getText()+"' WHERE DNI = '"+vent.getTxt_Dni().getText()+"'");
				JOptionPane.showMessageDialog(vent, "Cambios guardados con exito!");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else if(e.getSource() == vent.getBtn_CambiarContraseña()) {
			Conexion c = new Conexion();
			String AntiguaContraseña = vent.getTxt_Antigua_Contraseña().getText();
			String NuevaContraseña = vent.getTxt_Nueva_Contraseña().getText();
			String pass = "";
			try {
				//Almaceno la contraseña de la persona con el DNI logeado.
				ResultSet rs = c.consulta(Main.con, "SELECT pass FROM Persona WHERE DNI ='"+vent.getTxt_Dni().getText()+"'");
				while (rs.next()) {
					pass = rs.getString("pass");
				}
				if(AntiguaContraseña.isEmpty()||NuevaContraseña.isEmpty()) {
					JOptionPane.showMessageDialog(vent, "Inserte la contraseña antigua y la nueva.");
				}else if(AntiguaContraseña.equals(pass)) {
					c.alta(Main.con, "UPDATE Persona SET pass = '"+NuevaContraseña+"' WHERE DNI = '"+vent.getTxt_Dni().getText()+"'");
					JOptionPane.showMessageDialog(vent, "Contraseña cambiada con Exito!");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
