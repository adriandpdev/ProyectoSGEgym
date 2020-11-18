package AdminBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import AdminFront.V_AdminScheAdd;
import main.Conexion;
import main.Main;

public class Ac_AdminScheAdd implements ChangeListener, ActionListener {

	V_AdminScheAdd v;

	public Ac_AdminScheAdd(V_AdminScheAdd x) {
		v = x;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == v.getHora()) {
			Date hora;
			hora = (Date) v.getHora().getValue();
			if (hora.getHours() == 23) {
				hora.setHours(8);
				v.getHora().setValue(hora);
			}
			if (hora.getHours() == 7) {
				hora.setHours(22);
				v.getHora().setValue(hora);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == v.getSubir()) {
			try {
				if (!comprobarAula()) {
					insertarClase();
				}
				else {
					JOptionPane.showMessageDialog(null, "Hay una clase que ya está ocupando el mismo aula que la actividad elegida a esta hora");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==v.getDiaDel()) {
			try {
				rellenarHorasBorrar();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==v.getHoraDel()) {
			rellenarActividadesBorrar();
		}
		if(e.getSource()==v.getQuitar()) {
			borrarHora();
		}

	}

	private void borrarHora() {
		if(v.getHoraDel().isEnabled() && v.getActividadDel().isEnabled() && v.getDiaDel().getSelectedIndex()!=-1) {
			try {
				Conexion con=new Conexion();
				ResultSet rs= con.consulta(Main.con, "Select idactividad from Actividad where nombre like '"+v.getActividadDel().getSelectedItem()+"'");
				rs.next();
				con.alta(Main.con, "DELETE from Horario where idactividad="+rs.getInt("idactividad")+" and hora like '"+v.getHoraDel().getSelectedItem()+"' and diasemana like '"+v.getDiaDel().getSelectedItem()+"'");
				JOptionPane.showMessageDialog(null, "Clase borrada con éxito");
				rellenarHorasBorrar();
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Esta clase ya ha sido borrada");
			}
		}
		else JOptionPane.showMessageDialog(null, "Es necesario rellenar todos los campos");
	}

	private void rellenarActividadesBorrar() {
		if(v.getHoraDel().isEnabled()) {
			v.getActividadDel().removeAllItems();
			v.getActividadDel().setEnabled(true);
			try {
				Conexion con=new Conexion();
				ResultSet rs= con.consulta(Main.con, "select * from Horario h, Actividad a where h.idactividad=a.idactividad and h.diasemana like '"+v.getDiaDel().getSelectedItem()+"' and h.hora like'"+v.getHoraDel().getSelectedItem()+"'");
				while (rs.next()) {
					v.getActividadDel().addItem(rs.getString("a.nombre"));
				}
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		else {
			try {
			v.getActividadDel().removeAllItems();
			v.getActividadDel().setEnabled(false);
			}catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	private void rellenarHorasBorrar() throws SQLException {
		if(v.getDiaDel().getSelectedIndex()!=-1)
			v.getHoraDel().removeAllItems();
		try {
			Conexion con=new Conexion();
			ResultSet rs= con.consulta(Main.con, "Select distinct(hora) from Horario where diasemana like '"+v.getDiaDel().getSelectedItem()+"' order by hora asc");
			if(!rs.next() ) {
				try {
					v.getHoraDel().setEnabled(false);
					v.getActividadDel().removeAllItems();
					v.getActividadDel().setEnabled(false);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			else {
				do{
					v.getHoraDel().addItem(rs.getTime("hora"));
				}while (rs.next()); 
				v.getHoraDel().setEnabled(true);
				v.getHoraDel().setSelectedIndex(-1);
			}
			
			
		}catch (SQLException e) {
			// TODO: handle exception
		}
	}
	private void insertarClase() throws SQLException {
		Conexion con = new Conexion();
		ResultSet rs=con.consulta(Main.con, "select idactividad from Actividad where nombre like '"+v.getActividad().getSelectedItem()+"'");
		rs.next();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date hora=(Date) v.getHora().getValue() ;
		
		try {
			con.alta(Main.con, "INSERT INTO Horario VALUES (0, "+rs.getInt("idactividad")+",'"+v.getDia().getSelectedItem().toString()+"','"+formatter.format(hora)+"')");
			JOptionPane.showMessageDialog(null, "Se ha introducido la actividad correctamente");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private boolean comprobarAula() throws SQLException {
		boolean ocupado = false;
		Conexion con = new Conexion();
		int aula;
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date hora=(Date) v.getHora().getValue() ;
		ResultSet rs = con.consulta(Main.con,
				"Select * from Actividad , Horario  where Actividad.idActividad=Horario.idActividad and Horario.Diasemana like '"
						+ v.getDia().getSelectedItem().toString() + "' and Horario.hora like '"
						+ formatter.format(hora)+"'");
		ResultSet rs2 = con.consulta(Main.con, "select idaula from Actividad where nombre like '"
				+ v.getActividad().getSelectedItem().toString() + "'");
		rs2.next();
		aula = rs2.getInt("idaula");

		while (rs.next()) {
			if (rs.getInt("Actividad.idaula") == aula)
				ocupado = true;
		}
		return ocupado;
	}

	
}
