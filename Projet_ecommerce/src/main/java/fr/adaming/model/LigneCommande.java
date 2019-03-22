package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lignes_commandes")
public class LigneCommande implements Serializable{
	 
	// Déclaration des attributs 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_l")
	private int idLigne;
	private int quantite;
	private double prix;
	
	//Transformation de l'association UML en Java
	@ManyToOne
	@JoinColumn(name="p_id", referencedColumnName="id_p")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name="co_id", referencedColumnName="id_co")
	private Commande commande; 
	
	//Constructeurs
	public LigneCommande() {
		super();
	}
	public LigneCommande(int quantite, double prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}
	public LigneCommande(int idLigne, int quantite, double prix) {
		super();
		this.idLigne = idLigne;
		this.quantite = quantite;
		this.prix = prix;
	}
	
	//Getters et Setters
	public int getIdLigne() {
		return idLigne;
	}
	public void setIdLigne(int idLigne) {
		this.idLigne = idLigne;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Commande getCommande() {
		return commande;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	//To String
	@Override
	public String toString() {
		return "LigneCommande [idLigne=" + idLigne + ", quantite=" + quantite + ", prix=" + prix + ", produit="
				+ produit + "]";
	}


	
	
	
}
