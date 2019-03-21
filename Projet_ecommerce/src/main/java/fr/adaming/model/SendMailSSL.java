package fr.adaming.model;

public class SendMailSSL {
	
	public SendMailSSL(){
		super();
	}
	
	public int sendMail(String destinataire, String message) throws Exception{
		int a=0;
		Mail.send(votre_mail, votre_mdp, destinataire, "Résumé de la commande", message);
		a++;
		return a;
	}

}
