package fr.adaming.service;

import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;

public interface ICommandeService {

	public Commande enregistrerCom(Panier pan, Client c, Adresse a);
	
}
