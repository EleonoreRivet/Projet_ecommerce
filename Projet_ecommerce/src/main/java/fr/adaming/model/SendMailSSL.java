package fr.adaming.model;

public class SendMailSSL {
	
	public SendMailSSL(){
		super();
	}
	
	public int sendMail(String destinataire, String message) throws Exception{
		int a=0;
		Mail.send("ele.rivet@gmail.com", "cheval06", destinataire, "Résumé de la commande", message);
		a++;
		return a;
	}

}
