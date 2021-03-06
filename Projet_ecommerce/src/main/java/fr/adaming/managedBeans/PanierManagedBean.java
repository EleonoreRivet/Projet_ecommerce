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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.model.SendMailSSL;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IPanierService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "panMB")
@SessionScoped
public class PanierManagedBean implements Serializable {

	// Transformation de l'association UML en Java
	@ManagedProperty(value = "#{licoService}")
	ILigneCommandeService liService;
	@ManagedProperty(value = "#{panService}")
	IPanierService panService;
	@ManagedProperty(value = "#{proService}")
	IProduitService proService;
	@ManagedProperty(value = "#{comService}")
	ICommandeService comService;

	// D�claration des attributs
	private Client client;
	private Panier panier;
	private LigneCommande liCo;
	private Commande commande; 
	private Produit produit;
	private Adresse adresse;
	private int quantite;
	private double prix;
	private double prixT;
	private int taille;
	private double total;
	private String messageMail;
	private List<LigneCommande> listeLico;
	private List<Produit> listePro;
	private String promo; 

	private HttpSession maSession;

	// Constructeur
	public PanierManagedBean() {
		this.client = new Client();
		this.adresse = new Adresse();
		this.client.setAdresse(this.adresse);
		this.panier = new Panier();
		this.produit = new Produit();
		this.listeLico = new ArrayList<LigneCommande>();
		this.liCo = new LigneCommande();
		this.total=0;
		this.taille = listeLico.size();
		liCo.setProduit(produit);
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

	public double getPrixT() {
		return prixT;
	}

	public void setPrixT(double prixT) {
		this.prixT = prixT;
	}

	public void setLiService(ILigneCommandeService liService) {
		this.liService = liService;
	}

	public void setPanService(IPanierService panService) {
		this.panService = panService;
	}

	public void setProService(IProduitService proService) {
		this.proService = proService;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public void setComService(ICommandeService comService) {
		this.comService = comService;
	}

	
	public String getMessageMail() {
		return messageMail;
	}

	public void setMessageMail(String messageMail) {
		this.messageMail = messageMail;
	}

	
	
	
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	@PostConstruct // Cette annotation sert � dire que la m�thode doit �tre
					// ex�cut�e apr�s l'instanciation de l'objet
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		maSession.setAttribute("lsession", listeLico);
		maSession.setAttribute("taille", taille);
		maSession.setAttribute("total", total);
	}

	// Les m�thodes m�tier

	public String ajoutProPanier() {
		
		// Cr�er une nouvelle ligne de Commande
		LigneCommande lcOut = new LigneCommande();
		lcOut.setProduit(produit);
		
		// V�rifier le stock
		if(quantite<=produit.getQuantite()){
		lcOut.setQuantite(quantite);
		this.prixT = produit.getPrix() * this.quantite;
		lcOut.setPrix(prixT);
		
		// Modifier la quantit� du produit dans la database
		produit.setQuantite(produit.getQuantite()-quantite);
		proService.modifPro(produit);
		
		// Ajouter la ligne de Commande � la liste
		this.listeLico.add(lcOut);
		this.taille=listeLico.size();
		this.total = this.total + lcOut.getPrix();
		

		// Appel de la m�thode pour cr�er la ligne de co dans la database
		 lcOut = liService.ajoutProduit(produit, quantite);
		 

		// Mettre � jour la liste dans la session
		maSession.setAttribute("lsession", listeLico);
		maSession.setAttribute("taille", taille);
		maSession.setAttribute("total", total);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit a �t� ajout� au panier"));
		
		return "panier";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Il n'y a pas assez de produit en stock"));
			return "testclient";
		}
		
	}

	public String supprProPanier() {
		// appel de la m�thode service pour supprimer un produit du panier (suppr la ligne de co) 
		int verif = liService.supprProduit(produit);
		if (verif != 0) {

			// On cherche la ligne du panier o� se trouve le produit qu'on veut
			// supprimer
			for (int i = 0; i < listeLico.size(); i++) {
				if (produit.getId() == listeLico.get(i).getProduit().getId()) {
					this.total = this.total - listeLico.get(i).getPrix();
					this.listeLico.remove(i);
					this.taille=listeLico.size();
					maSession.setAttribute("taille", taille);
					maSession.setAttribute("total", total);
				}
			}

			// Mettre � jour la liste dans la session
			maSession.setAttribute("lsession", listeLico);
			maSession.setAttribute("taille", taille);
			maSession.setAttribute("total", total);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit a �t� supprim� du panier"));
			
			return "panier";
		}
		return "panier";

	}
	
	public String valider(){
		if(this.listeLico.isEmpty()==true){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre panier est vide!"));
			return "panier";
		}
		return "client";
		
	}
	
	
	public String validerPanier(){
		
		// On r�cup�re la liste du panier
		this.listeLico = (List<LigneCommande>) maSession.getAttribute("lsession");
		this.panier.setListelico(this.listeLico);
	
		
		// On enregistre la commande avec le panier, le client et son adresse
		this.commande = comService.enregistrerCom(panier, client, adresse);
		this.commande.setListelico(listeLico);
		client.setAdresse(adresse);
		this.commande.setClient(client);
		
		for (int i = 0; i < listeLico.size(); i++) {
			LigneCommande lcIn = this.listeLico.get(i);
			lcIn.setCommande(this.commande);
	}
		
			// On envoie un mail 
			
			messageMail = "Bonjour M/Mme "+ this.commande.getClient().getNom()+", \n Nous vous informons que votre commande, faite le "+ this.commande.getDate()+", a �t� valid�e."
					+"\n Veuillez trouver ci-joint le r�capitulatif de votre commande: "
					+ "\n"+ listeLico.toString(); 

			int verifMail = 0;
			
			if (this.commande.getId() != 0) {
				// Ici on envoie concr�tement le mail en renseignant le destinataire et le message
				// On oublie pas de surround la fonction pour ne pas faire planter l'appli si ca crash
				SendMailSSL sm = new SendMailSSL();
				try {
					// V�rif va servir � savoir si le mail est envoy� vu que la fonction sendmail retourne un int
					verifMail = sm.sendMail(client.getEmail(), messageMail);

				} catch (Exception e) {

					e.printStackTrace();
				}
				if (verifMail != 0) {
					// Si la commande est valid�e, on vide le panier
					for (int i = 0; i < listeLico.size(); i++) {
							this.listeLico.remove(i);
					}
					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Merci, la commande a �t� valid�e, un r�capitulatif de la commande vous a �t� envoy� par email."));
					return "testclient";
				} else {
					// ajouter le message d'erreur
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Commande valid�e mais impossible d'envoyer le mail, v�rifier l'adresse du client"));
					return "panier";
				}
			}else{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande n'a pas pu �tre valid�e"));
		return "panier";
		}
	}

	
	public String codePromo(){
		if(this.promo=="35XC59"){
			total = this.total - (20*this.total/100);
			maSession.setAttribute("total", total);
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ce code promo n'est pas/plus valide!"));
		}
		return "panier";
		
	}
	
	
	
}



