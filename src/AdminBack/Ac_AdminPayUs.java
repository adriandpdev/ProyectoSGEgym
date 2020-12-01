package AdminBack;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import AdminFront.V_AdminPayUs;

public class Ac_AdminPayUs implements FocusListener, ActionListener {
	private V_AdminPayUs v;
	public Ac_AdminPayUs(V_AdminPayUs x) {
		v=x;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		if (v.getDni().getText().isEmpty()) {
			v.getDni().setText("");
			v.getDni().setForeground(Color.BLACK);
        }
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (v.getDni().getText().isEmpty()) {
			v.getDni().setText("Introduce el dni que buscas");
			v.getDni().setForeground(Color.gray);
        }
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
