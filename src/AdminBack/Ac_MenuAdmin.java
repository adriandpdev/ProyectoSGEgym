package AdminBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import AdminFront.*;
import Login.*;
import listado.*;

public class Ac_MenuAdmin implements ActionListener {
	private V_AdminHome vent;

	public Ac_MenuAdmin(V_AdminHome v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cerrarventanas();
		// 
		if (arg0.getActionCommand().equals("Listado general")) {
			listado vAdGeList = new listado(); //CAMBIAR
			vent.add(vAdGeList);
			vAdGeList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Alta usuario")) {
			V_AdminUserAdd vAdUsAdd = new V_AdminUserAdd();
			vent.add(vAdUsAdd);
			vAdUsAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Lista usuarios")) {
			ListadoUsuarios vAdUsList = new ListadoUsuarios();
			vent.add(vAdUsList);
			vAdUsList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Pendiente de pago")) {
			V_AdminUserPend vAdUsPend;
			try {
				vAdUsPend = new V_AdminUserPend();
				vent.add(vAdUsPend);
				vAdUsPend.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (arg0.getActionCommand().equals("Alta profesor")) {
			V_AdminEmplAdd vAdEmAdd = new V_AdminEmplAdd();
			vent.add(vAdEmAdd);
			vAdEmAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Lista profesores")) {
			ListadoEmpleados vAdEmList = new ListadoEmpleados();
			vent.add(vAdEmList);
			vAdEmList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Alta actividad")) {
			V_AdminActiAdd vAdAcAdd = new V_AdminActiAdd();
			vent.add(vAdAcAdd);
			vAdAcAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Lista actividades")) {
			V_AdminActiList vAdAcList;
			try {
				vAdAcList = new V_AdminActiList(vent);
				vent.add(vAdAcList);
				vAdAcList.setVisible(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (arg0.getActionCommand().equals("Añadir hora")) {
			V_AdminScheAdd vAdScAdd = new V_AdminScheAdd();
			vent.add(vAdScAdd);
			vAdScAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Visualizar horario")) {
			V_AdminScheList vAdScList;
			try {
				vAdScList = new V_AdminScheList();
				vent.add(vAdScList);
				vAdScList.setVisible(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("Promociones")) {
			V_AdminProm vAdProm = new V_AdminProm();
			vent.add(vAdProm);
			vAdProm.setVisible(true);
		} else if (arg0.getActionCommand().equals("Avisos")) {
			V_AdminWarn vAdWarn = new V_AdminWarn();
			vent.add(vAdWarn);
			vAdWarn.setVisible(true);
		} else if (arg0.getActionCommand().equals("Pagos y Nominas")) {
			V_AdminPayUs vAdPayUs;
			try {
				vAdPayUs = new V_AdminPayUs();
				vent.add(vAdPayUs);
				vAdPayUs.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("Mi Perfil")) {
			V_AdminPerfil vAdPerfil = new V_AdminPerfil(vent);
			vent.add(vAdPerfil);
			vAdPerfil.setVisible(true);
		} else if (arg0.getActionCommand().equals("Cerrar ventana")) {
			cerrarventanas();
		} else if (arg0.getActionCommand().equals("Cerrar sesión")) {
			try {
				V_Login vLogin = new V_Login();
				vent.dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("De Actividades")) {
			V_AdminEstActi vAdEstAct;
			try {
				vAdEstAct = new V_AdminEstActi();
				vent.add(vAdEstAct);
				vAdEstAct.setVisible(true);
			} catch (SQLException e) {
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