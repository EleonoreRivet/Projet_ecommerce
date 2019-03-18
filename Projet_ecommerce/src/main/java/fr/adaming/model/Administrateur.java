package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrateurs")
public class Administrateur implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_a")
	private int id;
	private String username;
	private String mdp;  

	public Administrateur() {
		super();
	}

	public Administrateur(String username, String mdp) {
		super();
		this.username = username;
		this.mdp = mdp;
	}

	public Administrateur(int id, String username, String mdp) {
		super();
		this.id = id;
		this.username = username;
		this.mdp = mdp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "Administrateur [id=" + id + ", username=" + username + ", mdp=" + mdp + "]";
	}

}
