package UserBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import AdminFront.V_AdminScheAdd;
import UserFront.V_UserActiAdd;
import main.Conexion;
import main.Main;

public class Ac_UserActiAdd implements ChangeListener, ActionListener {
	V_UserActiAdd v;
	Conexion c = new Conexion();
	public Ac_UserActiAdd(V_UserActiAdd x) {
		v = x;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String q2 = "select Hora from Horario where idactividad in(select idactividad from Actividad where nombre like '"
				+ v.getCbaula().getSelectedItem() + "') and Diasemana like '" + v.getDiasemana().getSelectedItem() + "'";
		String x2 = "Hora";

		v.getCbhora().removeAllItems();
		
		ArrayList<String> lista2 = new ArrayList<String>();
		try {
			lista2 = c.llenarCombo(Main.con, q2, x2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < lista2.size(); i++) {
			v.getCbhora().addItem(lista2.get(i));

		}
		
		
		

	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	



}
