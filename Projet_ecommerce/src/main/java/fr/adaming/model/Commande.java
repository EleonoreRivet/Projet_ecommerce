package fr.adaming.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "commandes")
public class Commande implements Serializable { 
	
	//Déclaration des attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_co")
	private int id; 
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "cl_id", referencedColumnName = "id_cl")
	private Client client;
	
	@OneToMany(mappedBy="commande")
	private List<LigneCommande> listelico;

	// Constructeurs
	public Commande() {
		super();
	}

	public Commande(Date date) {
		super();
		this.date = date;
	}

	public Commande(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	// Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<LigneCommande> getListelico() {
		return listelico;
	}

	public void setListelico(List<LigneCommande> listelico) {
		this.listelico = listelico;
	}
	
	// To String

	@Override
	public String toString() {
		return "Commande [id=" + id + ", date=" + date + ", client=" + client + ", listelico=" + listelico + "]";
	}

}
