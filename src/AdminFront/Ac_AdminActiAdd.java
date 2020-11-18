package AdminFront;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Ac_AdminActiAdd implements ActionListener{

	public V_AdminActiAdd  vent;
	
	Ac_AdminActiAdd(V_AdminActiAdd v) 
	{
		vent = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getActionCommand().equals("AÑADIR"))
		{
			
		}
		
	}

}
