package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.IPanierService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="clMB")
@SessionScoped
public class ClientManagedBean implements Serializable {

	//Transformation de l'association UML en Java
	@EJB
	ILigneCommandeDao liService;
	@EJB
	ICommandeService comService;
	@EJB
	IPanierService panService; 
	@EJB
	IProduitService pService;
	
	// Déclaration des attributs
	private Client client;
	private Adresse adresse;
	private Panier panier;
	private Commande commande;
	private LigneCommande ligneco;
	private Produit produit;
	private int quantite;
	private List<LigneCommande> listeLico;
	private List<Produit> listePro;
	
    private HttpSession maSession;
	
	// Déclaration des constructeurs
	public ClientManagedBean() {
		this.client = new Client();
		this.adresse = new Adresse();
		this.client.setAdresse(adresse);
	}
	
	// Getters et Setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public LigneCommande getLigneco() {
		return ligneco;
	}

	public void setLigneco(LigneCommande ligneco) {
		this.ligneco = ligneco;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	} 
	
	
	
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public List<LigneCommande> getListeLico() {
		return listeLico;
	}

	public void setListeLico(List<LigneCommande> listeLico) {
		this.listeLico = listeLico;
	}

	
	
	public List<Produit> getListePro() {
		return listePro;
	}

	public void setListePro(List<Produit> listePro) {
		this.listePro = listePro;
	}

	@PostConstruct //Cette annotation sert à dire que la méthode doit être exécutée après l'instanciation de l'objet
	public void init(){
		maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.client= (Client) maSession.getAttribute("clientSession");
	}
	
	// Méthodes métiers 
	public String ajoutProduit(){
		LigneCommande liOut = liService.ajoutProduit(produit, quantite);
	if(liOut.getIdLigne()!=0) { 
			//Récup de la nouvelle liste
			List<LigneCommande> listeLico=liService.getListeCo();
			
			
			
			//Mettre à jour la liste dans la session
			maSession.setAttribute("clientSession", listeLico);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout est un succès"));
			
			
			return "espaceadmin";

		}else {
			
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué"));
			
			return "ajoutadmin";
			
		}
		
	}
	
//	public String supprProduit(){
//		int verif = panService.supprProduit(produit);
//	if(verif!=0) { 
//			//Récup de la nouvelle liste
//			List<LigneCommande> listeLico=liService.getListeCo();
//			
//			//Mettre à jour la liste dans la session
//			maSession.setAttribute("clientSession", listeLico);
//			
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression est un succès"));
//			
//			
//			return "espaceadmin";
//
//		}else {
//			
//			//Ajouter un message d'erreur
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué"));
//			
//			return "ajoutadmin";
//			
//		}
//		
//	}
	
	public String enregistrerCo(){
		Commande comOut = comService.enregistrerCom(panier, client, adresse);
	if(comOut.getId()!=0) { 
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande est enregistrée"));

			return "espaceadmin";

		}else {
			
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande a échoué"));
			
			return "ajoutadmin";
			
		}
		
		
	}
	
	
	
	
	
	
}
