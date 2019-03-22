package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="pMB")
@RequestScoped
public class ProduitManagedBean  implements Serializable{
	
	//Transformation de l'association UML en Java
	@ManagedProperty(value="#{proService}") 
	private IProduitService proService; 
	
	// D�claration des attributs
	private Produit produit;
	private Produit selectedpro;
	private Categorie categorie;
	private Administrateur admin; 
	private boolean indice;
	private UploadedFile image; 
	private List<Produit> listePro;
	private List<Produit> listeProCat;
	private List<Produit> listeProMC;
	private String mc; 
    
    private HttpSession maSession;
    
    private List<Produit> prodFiltre;

    // Constructeur vide
	public ProduitManagedBean() {
		this.produit= new Produit(); 
		this.categorie = new Categorie(); 
		this.produit.setCategorie(categorie);
		this.indice=false;
	}

	// Getters et Setters
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}
	

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Administrateur getAdmin() {
		return admin;
	}

	public void setAdmin(Administrateur admin) {
		this.admin = admin;
	}
	
	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	public List<Produit> getProdFiltre() {
		return prodFiltre;
	}

	public void setProdFiltre(List<Produit> prodFiltre) {
		this.prodFiltre = prodFiltre;
	}
	

	public List<Produit> getListePro() {
		return listePro;
	}

	public void setListePro(List<Produit> listePro) {
		this.listePro = listePro;
	}

	public List<Produit> getListeProCat() {
		return listeProCat;
	}

	public void setListeProCat(List<Produit> listeProCat) {
		this.listeProCat = listeProCat;
	}

	
	public Produit getSelectedpro() {
		return selectedpro;
	}

	public void setSelectedpro(Produit selectedpro) {
		this.selectedpro = selectedpro;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	
	
	public List<Produit> getListeProMC() {
		return listeProMC;
	}

	public void setListeProMC(List<Produit> listeProMC) {
		this.listeProMC = listeProMC;
	}

	public void setProService(IProduitService proService) {
		this.proService = proService;
	}

	@PostConstruct //Cette annotation sert � dire que la m�thode doit �tre ex�cut�e apr�s l'instanciation de l'objet
	public void init(){
		this.listePro = proService.recPro();
		this.listeProMC=proService.recProByMC(mc);
		this.listeProCat=proService.recProByCat(categorie);
 	    maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	this.admin= (Administrateur) maSession.getAttribute("adminSession");
	}
	
	
	//M�thodes m�tiers
	
	public String ajoutPro() {
		if(this.image!=null){
			this.produit.setPhoto(this.image.getContents());
		}
		
		//appel de la m�thode service
		Produit pAjout=proService.ajoutPro(produit, categorie);
		
		if(pAjout.getId()!=0) { // pAjout ne sera JAMAIS nul
			
			
			//R�cup de la nouvelle liste
			List<Produit> listePro=proService.recPro();
			
			//Mettre � jour la liste dans la session
			maSession.setAttribute("pSession", listePro);
			
			return "listepro";

		}else {
			
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �chou�"));
			
			return "ajoutpro";
			
		}
		
		
	}
	
	public String modifPro() {
		//appel de la m�thode service
		int verif=proService.modifPro(produit);
		if(verif!=0) {
			
			//R�cup de la nouvelle liste
			List<Produit> listePro=proService.recPro();
			
			//Mettre � jour la liste dans la session
			maSession.setAttribute("pSession", listePro);
			
			return "listepro"; 
		}else {
			
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a �chou�"));
			return "modifpro";
		}	
	}
	
	public void modifAuto() {
		 this.produit=proService.recProById(produit); 
	}
	
	public String supprPro() {
		//appel de la m�thode service
		int verif=proService.supprPro(produit);
		if(verif!=0) {
			
			//R�cup de la nouvelle liste
			List<Produit> listePro=proService.recPro();
			
			//Mettre � jour la liste dans la session
			maSession.setAttribute("pSession", listePro);
			
			return "listepro"; 
		}else {
			
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a �chou�"));
			return "listepro";
		}	
	}
	
	public String recProById() {
		//appel de la m�thode service
		Produit pOut=proService.recProById(produit);
		if(pOut!=null) {
			this.produit=pOut; 
			this.indice=true;
			return "recherchepro";
		
		}else {
			
			this.indice=false;
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit n'a pas �t� trouv�"));
			return "recherchepro";
		}
	}
	
	
	public String recProByCat() {
		//appel de la m�thode service
		List<Produit> lpOut=proService.recProByCat(categorie);
		if(lpOut!=null) {
			this.listePro=lpOut; 
			this.indice=true;
			return "testclient";
		
		}else {
			
			this.indice=false;
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit n'a pas �t� trouv�"));
			return "testclient";
		}
}
	
	public String recProByMC() {
		//appel de la m�thode service
		List<Produit> lpOut=proService.recProByMC(mc);
		if(lpOut!=null) {
			this.listePro=lpOut; 
			this.indice=true;
			return "testclient";
		
		}else {
			
			this.indice=false;
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit n'a pas �t� trouv�"));
			return "testclient";
		}
}


	
}