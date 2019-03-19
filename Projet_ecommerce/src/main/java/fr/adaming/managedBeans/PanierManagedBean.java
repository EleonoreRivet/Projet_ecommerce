package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IPanierService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="panMB")
@SessionScoped
public class PanierManagedBean implements Serializable {

	//Transformation de l'association UML en Java
	@ManagedProperty(value="#{licoService}") 
	ILigneCommandeService liService;
	@ManagedProperty(value="#{panService}") 
	IPanierService panService; 
	@ManagedProperty(value="#{proService}") 
	IProduitService proService;
	
	// Déclaration des attributs
	private Client client; 
	private Panier panier;
	private LigneCommande liCo;
	private Produit produit;
	private int quantite;
	private double prix; 
	private List<LigneCommande> listeLico;
	private List<Produit> listePro;
	private Map<Integer, LigneCommande> listeCo = new HashMap<Integer, LigneCommande>();
	private HttpSession maSession; 
	
	// Constructeur
	public PanierManagedBean() {
		this.panier = new Panier();
		this.produit = new Produit();
		this.listeLico= new ArrayList<LigneCommande>();
		this.liCo = new LigneCommande();
		liCo.setProduit(produit);
		listeLico.add(liCo);
		panier.setListelico(listeLico);
	}

	// Setters et Getters
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}



	public LigneCommande getLiCo() {
		return liCo;
	}

	public void setLiCo(LigneCommande liCo) {
		this.liCo = liCo;
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
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
	
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	

//	@PostConstruct Cette annotation sert à dire que la méthode doit être exécutée après l'instanciation de l'objet
//	public void init(){
//		maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//	    maSession.setAttribute("clientSession",listeLico);
//	}
	
	public void setLiService(ILigneCommandeService liService) {
		this.liService = liService;
	}

	public void setPanService(IPanierService panService) {
		this.panService = panService;
	}

	public void setProService(IProduitService proService) {
		this.proService = proService;
	}

	public Map<Integer, LigneCommande> getListeCo() {
		return listeCo;
	}

	public void setListeCo(Map<Integer, LigneCommande> listeCo) {
		this.listeCo = listeCo;
	}
	
	@PostConstruct //Cette annotation sert à dire que la méthode doit être exécutée après l'instanciation de l'objet
	public void init(){
		maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		maSession.setAttribute("lsession", listeLico);
	}
	
	public String ajoutProPan(){
	//	this.liCo = liService.ajoutProduit(produit, quantite);
//		this.liCo = listeCo.get(produit.getId());
		
//       if(liCo.getIdLigne()!=0) { 
			
			//Récup de la nouvelle liste
//			this.listeLico = liService.getListeCo();
//    	   this.listeLico = new ArrayList<LigneCommande>();
//    	  if(listeLico.isEmpty()==false){
//			this.listeLico.add(liCo);
//			panier.setListelico(listeLico); 	   
//    	  }
			//Mettre à jour la liste dans la session
//			maSession.setAttribute("clientSession", listeLico);
		
//			if(liCo == null){
//				LigneCommande lcOut = new LigneCommande();
				liCo = liService.ajoutProduit(produit, quantite);
				this.prix=produit.getPrix()*quantite;
				liCo.setPrix(this.prix);
				liCo.setQuantite(quantite);
//				lcOut.setProduit(produit);
//				lcOut.setQuantite(quantite);
//				lcOut.setPrix(produit.getPrix()*quantite);
//				listeCo.put(produit.getId(), lcOut);
				
				if(liCo.getIdLigne()!=0) { 
					
					//Récup de la nouvelle liste
					this.listeLico.add(liCo);
					
					//Mettre à jour la liste dans la session
					maSession.setAttribute("lsession", listeLico);			
			       
			        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit a été ajouté"));
				}
				
			return "panier";

//		}else {
//			this.liCo.setQuantite(liCo.getQuantite()+quantite);
//			
//			//Ajouter un message d'erreur
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La quantité du produit a été ajusté"));
//			
//			return "panier";
//			
//		}
	}
	
	
	public String supprPanier(){
		int verif = liService.supprProduit(produit);
		if(verif!=0) {
			
			//Récup de la nouvelle liste
			this.listeLico= liService.getListeCo();
			
			//Mettre à jour la liste dans la session
			maSession.setAttribute("lsession", listeLico);
			
			return "panier"; 
		}else {
			
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué"));
			return "panier";
		}
	
		
	
	}
	
	
	public String ajoutPanier(){
		int index = this.exists(produit);
		if (index == -1) {
			LigneCommande lcOut = new LigneCommande();
			lcOut = liService.ajoutProduit(produit, quantite);
			lcOut.setProduit(produit);
			lcOut.setQuantite(quantite);
			lcOut.setPrix(produit.getPrix()*quantite);
			this.listeLico.add(lcOut);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit a été ajouté"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La quantité du produit a été ajusté"));
			int newQuantite = this.listeLico.get(index).getQuantite() + quantite;
			this.listeLico.get(index).setQuantite(newQuantite);;
		}
		return "panier";
	}
	
	
	public String prodPanier(){
//		panier.setListelico(listeLico); 
		this.prix = produit.getPrix()*quantite;
		LigneCommande liCoOut = new LigneCommande(quantite, this.prix); 
		liCoOut = liService.ajoutProduit(produit, quantite);

		 if(liCoOut.getIdLigne()!=0) { 
			//	this.listeLico.add(liCoOut);
				this.panier.getListelico().add(liCoOut);
				return "panier";
		 }else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit n'a pas été ajouté"));
				return "panier";
				
		 }
	}
	
	
	public String panier(){
		LigneCommande liCoOut = new LigneCommande(quantite, prix); 
		liCoOut = liService.ajoutProduit(produit, quantite);
		 if(liCoOut.getIdLigne()!=0) { 
			 int etat = 0;
	            if (maSession.getAttribute("listeLico") != null) {
	               this.listeLico = (List<LigneCommande>) maSession.getAttribute("listeLico");
	                for (LigneCommande lc : listeLico) {
	                    if (lc.getProduit() == produit) {
	                        lc.setQuantite(lc.getQuantite() + 1);
	                        etat = 1;
	                    }
	                }
	            }
	            if (etat == 0) {
	            	liCoOut = new LigneCommande(quantite, prix);
	                listeLico.add(liCoOut);
	                maSession.setAttribute("listelico", listeLico);
	            }
				return "panier";
		 }else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit n'a pas été ajouté"));
				return "panier";
				
		 }
		
		
	}
	
	
	
}
