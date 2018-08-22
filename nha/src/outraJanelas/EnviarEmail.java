package outraJanelas;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EnviarEmail {
	
	void enviarEmail(String de,String senha,String para,String titulo,String msg ) {
		//remetente
		boolean ssl = true;
		
		String smtp = "smtp.gmail.com";
		int porta = 445;

		try {
			SimpleEmail email = new SimpleEmail();
			email.setFrom(de);
			email.setSSLOnConnect(ssl);
			email.setHostName(smtp);
			email.setSmtpPort(porta);
			email.setAuthentication(de, senha);
			
			email.addTo(para);
			email.setSubject(titulo);
			email.setMsg(msg);
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
