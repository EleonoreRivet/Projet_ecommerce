package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;


public interface IProduitService {
	
	public Produit ajoutPro(Produit p, Categorie c);
	public int supprPro(Produit p);
	public int modifPro(Produit p);
	public List<Produit> recPro(); 
	public Produit recProById(Produit p); 
	public List<Produit> recProByMC(String mc);
	public List<Produit> recProByCat(Categorie c);
	public List<Produit> recProSelect();

}
