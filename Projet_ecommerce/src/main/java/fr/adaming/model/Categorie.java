package fr.adaming.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="categories")
public class Categorie implements Serializable{
	
	//Déclaration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cat")
	private int id;
	private String nom;
	@Lob
	private byte[] photo; 
	private String description;
	
	@Transient
	private String img;

	
	//Transformation de l'association UML en Java
	@OneToMany(mappedBy="categorie")
	private List<Produit> listepro;
	
	//Déclaration des constructeurs 
	public Categorie() {
		super();
	}
	public Categorie(String nom, byte[] photo, String description) {
		super();
		this.nom = nom;
		this.photo = photo;
		this.description = description;
	}
	public Categorie(int id, String nom, byte[] photo, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.description = description;
	}
	
	// Déclaration des getters et setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public byte[] getPhoto() { 
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<Produit> getListepro() {
		return listepro;
	}
	public void setListepro(List<Produit> listepro) {
		this.listepro = listepro;
	}
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	//To String
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + ", photo="
				+ Arrays.toString(photo) + ", description=" + description + "]";
	}
	
	
	
	
	
	

}
