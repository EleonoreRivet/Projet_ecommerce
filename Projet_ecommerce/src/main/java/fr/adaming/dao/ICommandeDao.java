package fr.adaming.dao;

import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;


public interface ICommandeDao {

	public Commande enregistrerCom(Panier pan, Client c, Adresse a);
	
}
 