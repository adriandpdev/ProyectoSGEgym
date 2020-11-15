package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import AdminFront.*;
import EmplFront.*;
import UserFront.*;
import main.*;

public class Ac_Login implements ActionListener {
	public V_Login vent;

	Ac_Login(V_Login v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Iniciar sesión")) {
			Conexion c = new Conexion();
			try {
				Boolean enc = false;
				ResultSet rs = c.consulta(Main.con, "SELECT * FROM Persona");
				while(rs.next()&&enc==false){ 
					if (vent.gettxt()[0].getText().equals(rs.getString("DNI"))) {
						 if(vent.gettxt()[1].getText().equals(rs.getString("pass"))) {
							 enc=true;	
							 switch (rs.getString("rol")) {
							 case "admin":
								 V_AdminHome vAdHome = new V_AdminHome();
								 vent.dispose();
								 break;
							 case "user":
								 V_UserHome vUsHome = new V_UserHome();
								 vent.dispose();
								 break;
							 case "empl":
								 V_EmplHome vEmHome = new V_EmplHome();
								 vent.dispose();
								 break;
							 }
						 }
					}
				}
				if (enc==false) {
					JOptionPane.showMessageDialog(vent, "El DNI o la contraseña introducidos no son validos");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}

}
