package EmplBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import EmplFront.*;
import Login.*;
import UserFront.V_UserPerfil;

public class Ac_MenuEmple implements ActionListener {
	private V_EmplHome vent;

	public Ac_MenuEmple(V_EmplHome v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cerrarventanas();
		if (arg0.getActionCommand().equals("Mis clases")) {
			V_EmplActiList vEmAcList = new V_EmplActiList();
			vent.add(vEmAcList);
			vEmAcList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Ver horario")) {
			V_EmplScheList vEmScList = new V_EmplScheList();
			vent.add(vEmScList);
			vEmScList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Última nómina")) {
			V_EmplPayLast vEmPayLast = new V_EmplPayLast();
			vent.add(vEmPayLast);
			vEmPayLast.setVisible(true);
		} else if (arg0.getActionCommand().equals("Historial")) {
			V_EmplPayList vEmPayList = new V_EmplPayList();
			vent.add(vEmPayList);
			vEmPayList.setVisible(true);
		}else if (arg0.getActionCommand().equals("Mi Perfil")) {
			V_EmplPerfil vEmPerfil = new V_EmplPerfil(vent);
			vent.add(vEmPerfil);
			vEmPerfil.setVisible(true); 
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
