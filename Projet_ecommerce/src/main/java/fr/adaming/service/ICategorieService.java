package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieService {

	public Categorie ajoutCat(Categorie c);
	public int supprCat(Categorie c);
	public int modifCat(Categorie c);
	public List<Categorie> recCat(); 
	public Categorie recCatById(Categorie c); 	
	
}
