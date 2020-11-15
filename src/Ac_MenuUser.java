import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ac_MenuUser implements ActionListener {
	private V_UserHome vent;

	Ac_MenuUser(V_UserHome v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cerrarventanas();
		if (arg0.getActionCommand().equals("Ver Horario")) {
			V_UserScheList vUsScList = new V_UserScheList();
			vent.add(vUsScList);
			vUsScList.setVisible(true);
		} else if (arg0.getActionCommand().equals("asdasd")) {
			// V_AdminUserList vAdUsList = new V_AdminUserList();
			// vent.add(vUsScList);
			// vAdUsList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Reserva")) {
			V_UserActiAdd vUsAcAdd = new V_UserActiAdd();
			vent.add(vUsAcAdd);
			vUsAcAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Mis reservas")) {
			V_UserActiList vUsAcList = new V_UserActiList();
			vent.add(vUsAcList);
			vUsAcList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Información")) {
			V_UserPayInfo vUsPayIn = new V_UserPayInfo();
			vent.add(vUsPayIn);
			vUsPayIn.setVisible(true);
		} else if (arg0.getActionCommand().equals("Últimos pagos")) {
			V_UserPayList vUsPayList = new V_UserPayList();
			vent.add(vUsPayList);
			vUsPayList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Últimos avisos")) {
			V_UserAvList vUsAvList = new V_UserAvList();
			vent.add(vUsAvList);
			vUsAvList.setVisible(true);
		} else if (arg0.getActionCommand().equals("Promociones activas")) {
			V_UserPromList vUsPrList = new V_UserPromList();
			vent.add(vUsPrList);
			vUsPrList.setVisible(true);
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
