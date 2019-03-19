package fr.adaming.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="produits")
public class Produit implements Serializable { 
	
	//Déclaration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_p")
	private int id; 
	
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	private boolean selectionne;
	@Lob
	private byte[] photo;
	
	@Transient
	private String img;
	//Transformation de l'association UML en Java
	@ManyToOne
	@JoinColumn(name="cat_id", referencedColumnName="id_cat")
	private Categorie categorie; 
	

	@OneToMany(fetch=FetchType.EAGER)
	private List<LigneCommande> listelico;
	
	//Déclaration des constructeurs
	public Produit() {
		super();
	}
	public Produit(String designation, String description, double prix, int quantite, boolean selectionne,
			byte[] photo) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
	}	
	public Produit(int id, String designation, String description, double prix, int quantite, boolean selectionne,
			byte[] photo, Categorie categorie, List<LigneCommande> listelico) {
		super();
		this.id = id;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.categorie = categorie;
		this.listelico = listelico;
	}
	// Déclaration des Getters et Setters 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public boolean isSelectionne() {
		return selectionne;
	}
	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public List<LigneCommande> getListelico() {
		return listelico;
	}
	public void setListelico(List<LigneCommande> listelico) {
		this.listelico = listelico;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	// To String
	@Override
	public String toString() {
		return "Produit [id=" + id + ", designation=" + designation + ", description=" + description
				+ ", prix=" + prix + ", quantite=" + quantite + ", selectionne=" + selectionne + ", photo="
				+ Arrays.toString(photo) + "]";
	} 
	
	
	
	
	

}
