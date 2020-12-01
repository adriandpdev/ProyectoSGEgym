package AdminFront;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Ac_AdminUserList2 implements ActionListener{

	public V_AdminTransacciones  vent;

	Ac_AdminUserList2(V_AdminTransacciones v) 
	{
		vent = v;
	}
	
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		
	}

}

