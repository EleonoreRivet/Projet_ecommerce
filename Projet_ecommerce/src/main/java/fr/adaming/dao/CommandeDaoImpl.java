package fr.adaming.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;

@Repository
public class CommandeDaoImpl implements ICommandeDao{
	@Autowired
	private SessionFactory sf;

	// Le setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Commande enregistrerCom(Panier pan, Client c, Adresse a) {
		// Récupérer le bus
				Session s = sf.getCurrentSession();
				
		// Enregistrer le client
		c.setAdresse(a);
		s.save(c);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// Enregistrer la commande
		Commande com = new Commande();
		com.setDate(date);
		com.setClient(c);
		com.setListelico(pan.getListelico());
		s.save(com);
		
		return com;
	}
	
	
	
}
