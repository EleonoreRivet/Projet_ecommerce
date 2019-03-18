package fr.adaming.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


@Embeddable 
@Access(AccessType.FIELD)
public class Adresse {
	// Attributs

	@Column(name="id_num")
	private int numero;
	private String rue;
	private String ville;
	private String codePostal;
	
	//Transformation de l'association UML en JAVA
//	@OneToOne
//	@JoinColumn(name="cl_id", referencedColumnName="id_cl")
//	private Client client; 
	
	// Constructeurs
	public Adresse() {
		super();
	}
	public Adresse(String rue, String ville, String codePostal) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}
	
	public Adresse(int numero, String rue, String ville, String codePostal) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}
	// Getters et Setters
	
	public String getRue() {
		return rue;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	
	// To String
	@Override
	public String toString() {
		return "Adresse [numero=" + numero + ", rue=" + rue + ", ville=" + ville + ", codePostal=" + codePostal
				+  "]";
	}


}
