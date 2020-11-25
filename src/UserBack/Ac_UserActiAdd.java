package UserBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import AdminFront.V_AdminScheAdd;
import UserFront.V_UserActiAdd;
import main.Conexion;
import main.Main;

public class Ac_UserActiAdd implements ChangeListener, ActionListener{
	V_UserActiAdd v;
	
	public Ac_UserActiAdd(V_UserActiAdd x) {
		v = x;
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	private void rellenarActividadesBorrar() {
		if(v.getHoraDel().isEnabled()) {

			try {
				Conexion con=new Conexion();
				ResultSet rs= con.consulta(Main.con, "select * from Horario h, Actividad a where h.idactividad=a.idactividad and h.diasemana like '"+v.getDiaDel().getSelectedItem()+"' and h.hora like'"+v.getHoraDel().getSelectedItem()+"'");
			
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		}
	

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
