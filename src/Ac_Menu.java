
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Ac_Menu implements ActionListener {
	private V_AdminHome vent;

	Ac_Menu(V_AdminHome v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Alta usuario")) {
			V_AdminUserAdd vAdUsAdd = new V_AdminUserAdd();
			//vAdUsAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Lista usuarios")) {
			V_AdminUserList vAdUsList = new V_AdminUserList();
			//vAdUsList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Pendiente de pago")) {
			V_AdminUserPend vAdUsPend = new V_AdminUserPend();
			//vAdUsPend.setVisible(true);
		} else if (arg0.getActionCommand().equals("Alta profesor")) {
			V_AdminEmplAdd vAdEmAdd = new V_AdminEmplAdd();
			//vAdEmAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Lista profesores")) {
			V_AdminEmplList vAdEmList = new V_AdminEmplList();
			//vAdEmList.setVisible(true);
		}else if (arg0.getActionCommand().equals("Alta actividad")) {
			V_AdminActiAdd vAdAcAdd = new V_AdminActiAdd();
			//vAdAcAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Lista actividades")) {
			V_AdminActiList vAdAcList = new V_AdminActiList();
			//vAdAcList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Añadir hora")) {
			V_AdminScheAdd vAdScAdd = new V_AdminScheAdd();
			//vAdScAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Visualizar horario")) {
			V_AdminScheList vAdScList = new V_AdminScheList();
			//vAdScList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Exportar PDF")) {
			V_AdminScheExp vAdScExp = new V_AdminScheExp();
			//vAdScExp.setVisible(true);
		}else if (arg0.getActionCommand().equals("Promociones")) {
			V_AdminProm vAdProm = new V_AdminProm();
			//vAdProm.setVisible(true);
		} else if (arg0.getActionCommand().equals("Avisos")) {
			V_AdminWarn vAdWarn = new V_AdminWarn();
			//vAdWarn.setVisible(true);
		} else if (arg0.getActionCommand().equals("Pagos usuarios")) {
			V_AdminPayUs vAdPayUs = new V_AdminPayUs();
			//vAdPayUs.setVisible(true);
		} else if (arg0.getActionCommand().equals("Nominas profesores")) {
			V_AdminPayEm vAdPayEm = new V_AdminPayEm();
			//vAdPayEm.setVisible(true);
		} else if (arg0.getActionCommand().equals("Cerrar ventana")) {
			vent.removeAll();
		}else if (arg0.getActionCommand().equals("Salir")) {
			// Pedir confirmación
			System.exit(0);
		}
	}
}
