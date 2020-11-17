package AdminBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import AdminFront.V_AdminScheAdd;
import main.Conexion;
import main.Main;

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
			if(hora.getHours()==23) {
				hora.setHours(8);
				v.getHora().setValue(hora);
			}
			if(hora.getHours()==7) {
				hora.setHours(22);
				v.getHora().setValue(hora);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== v.getDiaDel()) {
			v.getHoraDel().removeAllItems();
			if(v.getDiaDel().getSelectedItem()!="") {
				try {
					rellenarHoraBorrado();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				v.getHoraDel().removeAllItems();
				v.getHoraDel().setEnabled(false);
			}
		}
		
	}
	private void rellenarHoraBorrado() throws SQLException {
		Conexion con= new Conexion();
		ResultSet rs=con.consulta(Main.con, "select distinct(hora) from Horario where diasemana like '"+v.getDiaDel().getSelectedItem().toString()+"' order by hora asc");
		while (rs.next()) {
			v.getHoraDel().addItem(rs.getTime("hora"));
		}
		v.getHoraDel().setEnabled(true);
	}

}
