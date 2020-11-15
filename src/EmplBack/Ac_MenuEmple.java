package EmplBack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import EmplFront.V_EmplActiList;
import EmplFront.V_EmplHome;
import EmplFront.V_EmplPayLast;
import EmplFront.V_EmplPayList;
import EmplFront.V_EmplScheList;

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
