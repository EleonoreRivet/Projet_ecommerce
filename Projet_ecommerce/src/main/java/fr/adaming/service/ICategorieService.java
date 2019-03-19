package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;


public interface ICategorieService {

	public Categorie ajoutCat(Categorie c);
	public int supprCat(Categorie c);
	public int modifCat(Categorie c);
	public List<Categorie> recCat(); 
	public Categorie recCatById(Categorie c); 	
	
}
