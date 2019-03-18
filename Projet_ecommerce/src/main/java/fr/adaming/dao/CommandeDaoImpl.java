package fr.adaming.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;

@Stateless
public class CommandeDaoImpl implements ICommandeDao{
	@PersistenceContext(name="PU_proj") 
	private  EntityManager em;

	@Override
	public Commande enregistrerCom(Panier pan, Client c, Adresse a) {
		// Enregistrer le client
		c.setAdresse(a);
		em.persist(c);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// Enregistrer la commande
		Commande com = new Commande();
		com.setDate(date);
		com.setClient(c);
		com.setListelico(pan.getListelico());
		em.persist(com);
		
		return com;
	}
	
	
	
}
