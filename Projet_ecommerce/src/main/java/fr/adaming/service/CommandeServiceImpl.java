package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;

@Stateful
public class CommandeServiceImpl implements ICommandeService{

	//Transformation de l'asso UML en JAVA
	@EJB
	ICommandeDao comDao;

	@Override
	public Commande enregistrerCom(Panier pan, Client c, Adresse a) {
		return comDao.enregistrerCom(pan, c, a);
	} 
	
}
