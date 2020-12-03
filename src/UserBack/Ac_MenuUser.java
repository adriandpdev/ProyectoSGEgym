package UserBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import AdminFront.V_AdminScheList;
import Login.*;
import UserFront.*;

public class Ac_MenuUser implements ActionListener {
	private V_UserHome vent;

	public Ac_MenuUser(V_UserHome v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cerrarventanas();
		if (arg0.getActionCommand().equals("Ver Horario")) {
			V_AdminScheList vUsScList;
			try {
				vUsScList = new V_AdminScheList();
				vent.add(vUsScList);
				vUsScList.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (arg0.getActionCommand().equals("Imprimir horario")) {
			// IMPRIMIR HORARIO
		} else if (arg0.getActionCommand().equals("Reserva")) {
			V_UserActiAdd vUsAcAdd = new V_UserActiAdd(vent);
			vent.add(vUsAcAdd);
			
		} else if (arg0.getActionCommand().equals("Mis reservas")) {
			V_UserActiList vUsAcList = new V_UserActiList();
			vent.add(vUsAcList);
			vUsAcList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Información")) {
			V_UserPayInfo vUsPayIn;
			try {
				vUsPayIn = new V_UserPayInfo(vent);
				vent.add(vUsPayIn);
				vUsPayIn.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		} else if (arg0.getActionCommand().equals("Últimos pagos")) {
			V_UserPayList vUsPayList = new V_UserPayList();
			vent.add(vUsPayList);
			vUsPayList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Últimos avisos")) {
			V_UserAvList vUsAvList;
			try {
				vUsAvList = new V_UserAvList();
				vent.add(vUsAvList);
				vUsAvList.setVisible(true);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (arg0.getActionCommand().equals("Promociones activas")) {
			V_UserPromList vUsPrList;
			try {
				vUsPrList = new V_UserPromList();
				vent.add(vUsPrList);
				vUsPrList.setVisible(true);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (arg0.getActionCommand().equals("Mi Perfil")) {
			V_UserPerfil vUsPerfil = new V_UserPerfil(vent);
			vent.add(vUsPerfil);
			vUsPerfil.setVisible(true);
		}else if (arg0.getActionCommand().equals("Cerrar ventana")) {
			cerrarventanas();
		} else if (arg0.getActionCommand().equals("Cerrar sesión")) {
			try {
				V_Login vLogin = new V_Login();
				vent.dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	private void cerrarventanas() {
		vent.getContentPane().setVisible(false);
		vent.getContentPane().removeAll();
		vent.getContentPane().setVisible(true);
	}
}
