import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ac_Menu implements ActionListener {
	private V_AdminHome vent;

	Ac_Menu(V_AdminHome v) {
		vent = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Usuarios")) {
			V_AdminUserAdd vAdUsAdd = new V_AdminUserAdd();
			//vAdUsAdd.setVisible(true);
		} else if (arg0.getActionCommand().equals("Profesores")) {
			V_AdminHome vNum = new V_AdminHome();
			vNum.setVisible(true);
		} else if (arg0.getActionCommand().equals("Clases")) {
			vent.dispose();
		} else if (arg0.getActionCommand().equals("Horarios")) {
			V_AdminHome vNum = new V_AdminHome();
			vNum.setVisible(true);
		} else if (arg0.getActionCommand().equals("Newslater")) {
			vent.dispose();
		} else if (arg0.getActionCommand().equals("Facturas")) {
			V_AdminHome vNum = new V_AdminHome();
			vNum.setVisible(true);
		}
	}
}
