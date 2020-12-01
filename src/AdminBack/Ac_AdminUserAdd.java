package AdminBack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import AdminFront.V_AdminUserAdd;
import main.Conexion;
import main.Main;

public class Ac_AdminUserAdd implements ActionListener {

	private V_AdminUserAdd vent;

	public Ac_AdminUserAdd(V_AdminUserAdd v) {
		vent = v;
	}

	private static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public boolean isEmail(String correo) {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
		mat = pat.matcher(correo);
		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isTelefono(String tel) {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern.compile("([0-9]{9})");
		mat = pat.matcher(tel);
		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCCC(String ccc) {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern.compile("[0-9]{20}");
		mat = pat.matcher(ccc);
		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean comprobar(String dni) {
		char[] letraDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };
		String num = "";
		int ind = 0;
		boolean valido;
		if (dni.length() == 8) {
			dni = "0" + dni;
		}
		if (!Character.isLetter(dni.charAt(8))) {
			return false;
		}
		if (dni.length() != 9) {
			return false;
		}
		for (int i = 0; i < 8; i++) {
			if (!Character.isDigit(dni.charAt(i))) {
				return false;
			}
			num += dni.charAt(i);
		}
		ind = Integer.parseInt(num);
		ind %= 23;
		if ((Character.toUpperCase(dni.charAt(8))) != letraDni[ind]) {
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("Añadir")) {
			if (vent.getTxtDni().getText().equals("") || vent.getTxtNombre().getText().equals("")
					|| vent.getTxtApellidos().getText().equals("") || vent.getTxtCCC().getText().equals("")
					|| vent.getTxtContraseña().getText().equals("") || vent.getTxtTelefono().getText().equals("")
					|| vent.getTxtemail().getText().equals("")) {
				JOptionPane.showMessageDialog(null, "No puede existir ningun campo vacio", "ATENCIÓN ADMINISTRADOR",
						JOptionPane.WARNING_MESSAGE);
			} else if (!isNumeric(vent.getTxtTelefono().getText())) {
				JOptionPane.showMessageDialog(null, "El Telefono tiene que ser numerico.", "ATENCIÓN ADMINISTRADOR",
						JOptionPane.WARNING_MESSAGE);
				vent.getTxtTelefono().setText("");
				vent.getTxtTelefono().requestFocus();
			} else if (!comprobar(vent.getTxtDni().getText())) {
				JOptionPane.showMessageDialog(null, "Introduce un dni valido", "ATENCIÓN ADMINISTRADOR",
						JOptionPane.WARNING_MESSAGE);
				vent.getTxtDni().setText("");
				vent.getTxtDni().requestFocus();
			} else if (!isEmail(vent.getTxtemail().getText())) {
				JOptionPane.showMessageDialog(null, "¡Introduce un email Valido!", "ATENCIÓN ADMINISTRADOR",
						JOptionPane.WARNING_MESSAGE);
				vent.getTxtemail().requestFocus();
			} else if (!isTelefono((vent.getTxtTelefono().getText()))) {
				JOptionPane.showMessageDialog(null, "¡Introduce un Telefono valido!", "ATENCIÓN ADMINISTRADOR",
						JOptionPane.WARNING_MESSAGE);
				vent.getTxtTelefono().setText("");
				vent.getTxtTelefono().requestFocus();
			} else if (!isCCC((vent.getTxtCCC().getText()))) {
				JOptionPane.showMessageDialog(null, "¡Introduce un numero de cuenta valido!", "ATENCIÓN ADMINISTRADOR",
						JOptionPane.WARNING_MESSAGE);
				vent.getTxtCCC().setText("");
				vent.getTxtCCC().requestFocus();
			} else {
				Conexion c = new Conexion();
				try {
					c.alta(Main.con,
							"INSERT INTO Persona(DNI,nombre,apellido,cuentabanc,pass,fechanac,telefono,correo,rol)VALUES('"
									+ vent.getTxtDni().getText() + "','" + vent.getTxtNombre().getText() + "','"
									+ vent.getTxtApellidos().getText() + "','" + vent.getTxtCCC().getText() + "','"
									+ vent.getTxtContraseña().getText() + "','"
									+ ((JTextComponent) vent.getDate().getDateEditor().getUiComponent()).getText()
									+ "','" + Integer.parseInt(vent.getTxtTelefono().getText()) + "','"
									+ vent.getTxtemail().getText() + "','user')");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (arg0.getActionCommand().equals("Limpiar")) {
			vent.getTxtDni().setText("");
			vent.getTxtNombre().setText("");
			vent.getTxtApellidos().setText("");
			vent.getTxtCCC().setText("");
			vent.getTxtContraseña().setText(vent.getPassword());
			vent.getTxtTelefono().setText("");
			vent.getTxtemail().setText("");
			vent.getDate().setDate(null);
		}
	}
}
