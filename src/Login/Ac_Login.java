package Login;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.commons.codec.digest.DigestUtils;

import AdminFront.*;
import EmplFront.*;
import UserFront.*;
import main.*;

public class Ac_Login implements ActionListener {
	public static V_Login vent;

	Ac_Login(V_Login v) {
		vent = v;
	}
	
	public static String cifrar(String cadena) {
		String codi=DigestUtils.md5Hex(cadena);
		return codi;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("INICIAR SESIÓN")) {
			iniciarsesion();
		}

	}
	public static void inputcache() throws IOException {
		File f = new File ("cache.txt");
		FileWriter fw = new FileWriter (f, true);
		PrintWriter fp = new PrintWriter (fw);
		fp.println(vent.gettxt()[0].getText());
		fp.close();
		fw.close();
	}
	public static void delcache() {
		File f = new File ("cache.txt");
		f.delete();
	}
	
	public static void iniciarsesion() {
		Conexion c = new Conexion();
		try {
			Boolean enc = false;
			ResultSet rs = c.consulta(Main.con, "SELECT * FROM Persona");
			while (rs.next() && enc == false) {
				if (vent.gettxt()[0].getText().equals(rs.getString("DNI"))) {
					if (cifrar(vent.gettxt()[1].getText()).equals(rs.getString("pass"))) {
						enc = true;
						switch (rs.getString("rol")) {
						case "admin":
							V_AdminHome vAdHome = new V_AdminHome(vent.gettxt()[0].getText());
							vent.dispose();
							break;
						case "user":
							V_UserHome vUsHome = new V_UserHome(vent.gettxt()[0].getText());
							vent.dispose();
							break;
						case "empl":
							V_EmplHome vEmHome = new V_EmplHome(vent.gettxt()[0].getText());
							vent.dispose();
							break;
						}
						try {
							if(vent.getch().isSelected()) {
								inputcache();								
							}else {
								delcache();
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			if (enc == false) {
				JOptionPane.showMessageDialog(vent, "El DNI o la contraseña introducidos no son validos");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
