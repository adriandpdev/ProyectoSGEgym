package AdminFront;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Conexion;
import main.Main;


public class Ac_AdminUserList implements ActionListener{
	
	public V_AdminUserList  vent;
	Conexion c = new Conexion();

	Ac_AdminUserList(V_AdminUserList v) 
	{
		vent = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		String rol;
		
		if(vent.getCbRol().getSelectedItem().equals("Empleado"))
		{
			rol="emple";
		}
		else
		{
			rol="user";
		}
		
		if(vent.getCbRol().getSelectedIndex() > -1)
		{
			String qu = "SELECT DNI FROM Persona WHERE rol LIKE'"+rol+"'";
			String xu = "dni";
			
			vent.getCbDni().removeAllItems();
			ArrayList<String> lista = new ArrayList<String>();
			try {
				lista = c.llenarCombo(Main.con, qu, xu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i <lista.size();i++)
			{
				vent.getCbDni().addItem(lista.get(i));
			}
		}
	}

}
