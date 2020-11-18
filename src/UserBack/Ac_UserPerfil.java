package UserBack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
				c.con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else if(e.getSource() == vent.getBtn_CambiarContrase�a()) {
			Conexion c = new Conexion();
			try {
				//c.alta(Main.con, "UPDATE Persona SET DNI = '"+vent.getTxt_Dni().getText()+"', nombre = '"+vent.getTxt_Nombre().getText()+"', apellido = '"+vent.getTxt_Apell().getText()+"', cuentabanc = '"+vent.getTxt_Cb().getText()+"', fechanac = '"+vent.getTxt_Nac().getText()+"', telefono = '"+Integer.parseInt(vent.getTxt_Tlf().getText())+"', correo = '"+vent.getTxt_Correo().getText()+"' WHERE DNI = '"+vent.getTxt_Dni().getText()+"'");
				JOptionPane.showMessageDialog(vent, "Contrase�a cambiada con Exito!");
				c.con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
