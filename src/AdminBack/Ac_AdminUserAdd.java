package AdminBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import AdminFront.V_AdminHome;
import AdminFront.V_AdminUserAdd;
import main.Conexion;
import main.Main;

public class Ac_AdminUserAdd implements ActionListener {

	private V_AdminUserAdd vent;

	public Ac_AdminUserAdd(V_AdminUserAdd v) {
		vent = v;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("A�adir")) {
			Conexion c = new Conexion();
			try {
				 c.alta(Main.con, "INSERT INTO Persona(DNI,nombre,apellido,cuentabanc,pass,fechanac,telefono,correo,rol)VALUES('"+vent.getTxtDni().getText()+"','"+vent.getTxtNombre().getText()+"','"+vent.getTxtApellidos().getText()+"','"+vent.getTxtCCC().getText()+"','"+vent.getTxtContrase�a().getText()+"','"+vent.getTxtfecha().getText().toString()+"','"+Integer.parseInt(vent.getTxtTelefono().getText())+"','"+vent.getTxtemail().getText()+ "','user')");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} if(arg0.getActionCommand().equals("Limpiar")) {
			vent.getTxtDni().setText("");
			vent.getTxtNombre().setText("");
			vent.getTxtApellidos().setText("");
			vent.getTxtCCC().setText("");
			vent.getTxtContrase�a().setText("");
			vent.getTxtTelefono().setText("");
			vent.getTxtemail().setText("");
		}
		
	}

}
