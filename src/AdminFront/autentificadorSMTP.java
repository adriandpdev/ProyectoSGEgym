package AdminFront;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class autentificadorSMTP extends Authenticator {
 
 String usuario,clave;
 
 public autentificadorSMTP(String u, String c){
  usuario=u;
  clave=c;
 }
 
 public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(usuario, clave);
    }
}
