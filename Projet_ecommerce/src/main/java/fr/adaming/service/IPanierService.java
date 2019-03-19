package fr.adaming.service;

import java.util.List;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;


public interface IPanierService {
	
	public int ajoutLico(LigneCommande lico);
	public int ajoutProduit(Produit p, int quantite);
	public int supprProduit(Produit p);
	public List<LigneCommande> recProduits();
	public int getTaille();
	public double getTotal();
	
	
}
