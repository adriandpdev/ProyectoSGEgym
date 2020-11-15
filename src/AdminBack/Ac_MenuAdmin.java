package AdminBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import AdminFront.V_AdminActiAdd;
import AdminFront.V_AdminActiList;
import AdminFront.V_AdminEmplAdd;
import AdminFront.V_AdminEmplList;
import AdminFront.V_AdminHome;
import AdminFront.V_AdminPayEm;
import AdminFront.V_AdminPayUs;
import AdminFront.V_AdminProm;
import AdminFront.V_AdminScheAdd;
import AdminFront.V_AdminScheExp;
import AdminFront.V_AdminScheList;
import AdminFront.V_AdminUserAdd;
import AdminFront.V_AdminUserList;
import AdminFront.V_AdminUserPend;
import AdminFront.V_AdminWarn;

public class Ac_MenuAdmin implements ActionListener {
	private V_AdminHome vent;

	public Ac_MenuAdmin(V_AdminHome v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cerrarventanas();
		if (arg0.getActionCommand().equals("Alta usuario")) {
			V_AdminUserAdd vAdUsAdd = new V_AdminUserAdd();
			vent.add(vAdUsAdd);
			vAdUsAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Lista usuarios")) {
			V_AdminUserList vAdUsList = new V_AdminUserList();
			vent.add(vAdUsList);
			vAdUsList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Pendiente de pago")) {
			V_AdminUserPend vAdUsPend = new V_AdminUserPend();
			vent.add(vAdUsPend);
			vAdUsPend.setVisible(true);
		} else if (arg0.getActionCommand().equals("Alta profesor")) {
			V_AdminEmplAdd vAdEmAdd = new V_AdminEmplAdd();
			vent.add(vAdEmAdd);
			vAdEmAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Lista profesores")) {
			V_AdminEmplList vAdEmList = new V_AdminEmplList();
			vent.add(vAdEmList);
			vAdEmList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Alta actividad")) {
			V_AdminActiAdd vAdAcAdd = new V_AdminActiAdd();
			vent.add(vAdAcAdd);
			vAdAcAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Lista actividades")) {
			V_AdminActiList vAdAcList = new V_AdminActiList();
			vent.add(vAdAcList);
			vAdAcList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Añadir hora")) {
			V_AdminScheAdd vAdScAdd = new V_AdminScheAdd();
			vent.add(vAdScAdd);
			vAdScAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Visualizar horario")) {
			V_AdminScheList vAdScList = new V_AdminScheList();
			vent.add(vAdScList);
			vAdScList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Exportar PDF")) {
			V_AdminScheExp vAdScExp = new V_AdminScheExp();
			vent.add(vAdScExp);
			vAdScExp.setVisible(true);
		} else if (arg0.getActionCommand().equals("Promociones")) {
			V_AdminProm vAdProm = new V_AdminProm();
			vent.add(vAdProm);
			vAdProm.setVisible(true);
		} else if (arg0.getActionCommand().equals("Avisos")) {
			V_AdminWarn vAdWarn = new V_AdminWarn();
			vent.add(vAdWarn);
			vAdWarn.setVisible(true);
		} else if (arg0.getActionCommand().equals("Pagos usuarios")) {
			V_AdminPayUs vAdPayUs = new V_AdminPayUs();
			vent.add(vAdPayUs);
			vAdPayUs.setVisible(true);
		} else if (arg0.getActionCommand().equals("Nominas profesores")) {
			V_AdminPayEm vAdPayEm = new V_AdminPayEm();
			vent.add(vAdPayEm);
			vAdPayEm.setVisible(true);
		} else if (arg0.getActionCommand().equals("Cerrar ventana")) {
			cerrarventanas();
		} else if (arg0.getActionCommand().equals("Cerrar sesión")) {
			// Pedir confirmación
			System.exit(0);
		}
	}
	private void cerrarventanas() {
		vent.getContentPane().setVisible(false);
		vent.getContentPane().removeAll();
		vent.getContentPane().setVisible(true);
	}
}
