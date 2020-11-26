package AdminBack;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Aux_AutentificadorSMTP extends Authenticator {

	String usuario, clave;

	public Aux_AutentificadorSMTP(String u, String c) {
		usuario = u;
		clave = c;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(usuario, clave);
	}
}
