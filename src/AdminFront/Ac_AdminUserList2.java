package AdminFront;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Ac_AdminUserList2 implements ActionListener{

	public V_AdminUserList  vent;

	Ac_AdminUserList2(V_AdminUserList v) 
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
		
		if(vent.getTxtNombre().getText().equals("") || vent.getTxtApell().getText().equals("") || vent.getTxtCuenta().getText().equals("") || vent.getTxtNac().getText().equals("") || vent.getTxtTlf().getText().equals("") || vent.getTxtCoreo().getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"No puede existir ningun campo vacion");
		}
		else if(isNumeric(vent.getTxtNombre().getText()) || isNumeric(vent.getTxtApell().getText()))
		{
			JOptionPane.showMessageDialog(null,"Los campos nombre y apellidos no pueden ser numericos");
			 vent.getTxtNombre().setText("");
			 vent.getTxtApell().setText("");
			 vent.getTxtNombre().requestFocus();
		}
		else if(!isNumeric(vent.getTxtTlf().getText()))
		{
			JOptionPane.showMessageDialog(null,"El campo telefono tiene que ser numerico");
			vent.getTxtTlf().setText("");
			vent.getTxtTlf().requestFocus();
		}
		else
		{
			String query = "UPDATE Persona SET nombre='"+vent.getTxtNombre()+"', apellidos'"+vent.getTxtApell()+"', cuentabanc='"+vent.getTxtCuenta()+"', fechanac='"+vent.getTxtNac()+"', telefono='"+vent.getTxtTlf()+"', correo='"+vent.getTxtCoreo()+"' WHERE dni='"+vent.getCbDni().getSelectedItem()+"'";
			
		}
		
	}

}

