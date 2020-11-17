package AdminBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import AdminFront.V_AdminScheAdd;

public class Ac_AdminScheAdd implements ChangeListener, ActionListener{

	V_AdminScheAdd v;
	public Ac_AdminScheAdd(V_AdminScheAdd x) {
		v=x;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource()==v.getHora()) {
			Date hora;
			hora= (Date) v.getHora().getValue();
			if(hora.getHours()==0) {
				hora.setHours(7);
				v.getHora().setValue(hora);
			}
			if(hora.getHours()==6) {
				hora.setHours(23);
				v.getHora().setValue(hora);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
