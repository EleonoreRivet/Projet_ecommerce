package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

public class Panier implements Serializable{ 
	
	// Attributs
	private List<LigneCommande> listelico;

	// Constructeurs
	public Panier() {
		super();
	}

	public Panier(List<LigneCommande> listelico) {
		super();
		this.listelico = listelico;
	}

	// Getters et Setters
	public List<LigneCommande> getListelico() {
		return listelico;
	}

	public void setListelico(List<LigneCommande> listelico) {
		this.listelico = listelico;
	}

	//To String
	@Override
	public String toString() {
		return "Panier [listelico=" + listelico + "]";
	}
	
	

}
