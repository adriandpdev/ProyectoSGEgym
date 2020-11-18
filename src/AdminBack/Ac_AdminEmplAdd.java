package AdminBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import AdminFront.V_AdminEmplAdd;
import main.Conexion;
import main.Main;

public class Ac_AdminEmplAdd implements ActionListener {

	private V_AdminEmplAdd vent;

	public Ac_AdminEmplAdd(V_AdminEmplAdd v) {
		vent = v;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("Añadir")) {
			Conexion c = new Conexion();
			try {
				 c.alta(Main.con, "INSERT INTO Persona(DNI,nombre,apellido,cuentabanc,pass,fechanac,telefono,correo,rol)VALUES('"+vent.getTxtDni().getText()+"','"+vent.getTxtNombre().getText()+"','"+vent.getTxtApellidos().getText()+"','"+vent.getTxtCCC().getText()+"','"+vent.getTxtContraseña().getText()+"','"+vent.getTxtfecha().getText().toString()+"','"+Integer.parseInt(vent.getTxtTelefono().getText())+"','"+vent.getTxtemail().getText()+ "','empl')");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} if(arg0.getActionCommand().equals("Limpiar")) {
			vent.getTxtDni().setText("");
			vent.getTxtNombre().setText("");
			vent.getTxtApellidos().setText("");
			vent.getTxtCCC().setText("");
			vent.getTxtContraseña().setText("");
			vent.getTxtTelefono().setText("");
			vent.getTxtemail().setText("");
			vent.getTxtContraseña().setText("");
		}
		
	}
}
