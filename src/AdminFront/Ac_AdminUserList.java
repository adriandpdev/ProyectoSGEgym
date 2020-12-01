package AdminFront;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Conexion;
import main.Main;


public class Ac_AdminUserList implements ActionListener{
	
	public V_AdminTransacciones  vent;
	Conexion c = new Conexion();

	Ac_AdminUserList(V_AdminTransacciones v) 
	{
		vent = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
