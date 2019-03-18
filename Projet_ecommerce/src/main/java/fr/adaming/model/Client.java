package fr.adaming.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client implements Serializable {
	
	//Déclaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cl")
	private int id;
	private String nom;
	private String email;
	private String tel;
	
	//Transformation de l'association UML en JAVA
	//Lier l'adresse au client
	@Embedded
	private Adresse adresse; //Il ne faudra pas oublier de SET l'adresse au client dans le DAO !
	
	@OneToMany(mappedBy="client") 
	private List<Commande> listeco;
	

	// Déclaration des constructeurs
	public Client() {
		super();
	}
	
	public Client(String nom, String email, String tel, Adresse adresse) {
		super();
		this.nom = nom;
		this.email = email;
		this.tel = tel;
		this.adresse = adresse;
	}

	public Client(int id, String nom, String email, String tel, Adresse adresse) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.tel = tel;
		this.adresse = adresse;
	}

	// Getters et Setters

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Commande> getListeco() {
		return listeco;
	}

	public void setListeco(List<Commande> listeco) {
		this.listeco = listeco;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	// To String
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", email=" + email + ", tel=" + tel + ", adresse=" + adresse + "]";
	}



	

}
