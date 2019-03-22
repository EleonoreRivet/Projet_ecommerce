package fr.adaming.model;

import java.util.Properties;

import javax.mail.*;

import javax.mail.internet.*;

public class Mail {

	public static void send(String from, String mdp, String to, String sub, String msg) {

		// paramétrage de la boite mail
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.socketFactory.port","465");
		prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.starttls.enable", "true");

		
		Authenticator auth=new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(from, mdp);
			}
		};

		// recupération de la session
		Session session=Session.getInstance(prop, auth);

		try{
			MimeMessage message = new MimeMessage(session);
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(sub);
			
			message.setText(msg);
			
			//envoie du message
			Transport.send(message);
			
			//verif console pour l'envoie du message
			System.out.println("le message a bien été envoyé");
			
		}catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
