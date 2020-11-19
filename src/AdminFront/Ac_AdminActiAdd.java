package AdminFront;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import main.Conexion;
import main.Main;

public class Ac_AdminActiAdd implements ActionListener{

	public V_AdminActiAdd  vent;
	Conexion c = new Conexion();
	
	Ac_AdminActiAdd(V_AdminActiAdd v) 
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
		
		if(arg0.getActionCommand().equals("AÑADIR"))
		{
			if(vent.getTxtIdclase().getText().equals("") || vent.getTxtNombreactividad().getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "No puede existir ningun campo vacio");
			}
			else if(!isNumeric(vent.getTxtIdclase().getText()))
			{
				 JOptionPane.showMessageDialog(null,"El campo ID CLASE tiene que ser numerico.");
				 vent.getTxtIdclase().setText("");
				 vent.getTxtIdclase().requestFocus();
			}
			else if(isNumeric(vent.getTxtNombreactividad().getText()))
			{
				JOptionPane.showMessageDialog(null,"El campo NOMBRE ACTIVIDAD no puede ser numerico.");
				 vent.getTxtNombreactividad().setText("");
				 vent.getTxtNombreactividad().requestFocus();
			} else
				try {
					if(c.comprobarId(Main.con, vent.getTxtIdclase().getText().toString())==true)
					{
						JOptionPane.showMessageDialog(null,"El ID de esa actividad ya existe");
						vent.getTxtIdclase().setText("");
					}
					else
					{
					
						String idclase = vent.getTxtIdclase().getText();
						String nomAct = vent.getTxtNombreactividad().getText();
						Object cbDni = vent.getCbDniprofesor().getSelectedItem();
						Object cbAula = vent.getCbIdaula().getSelectedItem();
						
						try {
							c.alta(Main.con, "INSERT INTO Actividad (idActividad, nombre, dni, idAula) VALUES ("+idclase+",'"+nomAct+"', '"+cbDni+"', "+cbAula+")");
							JOptionPane.showMessageDialog(null,"La actividad ha sido introducida correctamente.");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (HeadlessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

}
