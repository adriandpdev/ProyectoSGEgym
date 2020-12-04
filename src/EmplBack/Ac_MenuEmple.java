package EmplBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

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
		try {
			cerrarventanas();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (arg0.getActionCommand().equals("Mis clases")) {
			V_EmplActiList vEmAcList;
			try {
				vEmAcList = new V_EmplActiList(vent);
				vent.add(vEmAcList);
				vEmAcList.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("Ver horario")) {
			V_EmplScheList vEmScList;
			try {
				vEmScList = new V_EmplScheList(vent);
				vent.add(vEmScList);
				vEmScList.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} else if (arg0.getActionCommand().equals("Última nómina")) {
			V_EmplPayLast vEmPayLast;
			try {
				vEmPayLast = new V_EmplPayLast(vent);
				vent.add(vEmPayLast);
				vEmPayLast.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (arg0.getActionCommand().equals("Historial")) {
			V_EmplPayList vEmPayList;
			try {
				vEmPayList = new V_EmplPayList(vent);
				vent.add(vEmPayList);
				vEmPayList.setVisible(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else if (arg0.getActionCommand().equals("Mi Perfil")) {
			V_EmplPerfil vEmPerfil = new V_EmplPerfil(vent);
			vent.add(vEmPerfil);
			vEmPerfil.setVisible(true); 
		}else if (arg0.getActionCommand().equals("Cerrar ventana")) {
			try {
				cerrarventanas();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (arg0.getActionCommand().equals("Cerrar sesión")) {
			try {
				V_Login vLogin = new V_Login();
				vLogin.setVisible(true);
				vent.dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void cerrarventanas() throws IOException {
		vent.getContentPane().setVisible(false);
		vent.getContentPane().removeAll();
		vent.getContentPane().setVisible(true);
		vent.setfoto();
	}
}