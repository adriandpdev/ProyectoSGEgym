import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ac_MenuEmple implements ActionListener {
	private V_EmplHome vent;

	Ac_MenuEmple(V_EmplHome v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cerrarventanas();
		if (arg0.getActionCommand().equals("Mis clases")) {
			V_EmpleActiList vEmAcList = new V_EmpleActiList();
			vent.add(vEmAcList);
			vEmAcList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Ver horario")) {
			V_EmpleScheList vEmScList = new V_EmpleScheList();
			vent.add(vEmScList);
			vEmScList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Última nómina")) {
			V_EmplePayLast vEmPayLast = new V_EmplePayLast();
			vent.add(vEmPayLast);
			vEmPayLast.setVisible(true);
		} else if (arg0.getActionCommand().equals("Historial")) {
			V_EmplePayList vEmPayList = new V_EmplePayList();
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
