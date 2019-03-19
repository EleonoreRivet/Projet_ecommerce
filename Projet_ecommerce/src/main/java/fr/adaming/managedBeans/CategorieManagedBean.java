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
import fr.adaming.service.ICategorieService;

@ManagedBean(name="cMB")
@RequestScoped
public class CategorieManagedBean implements Serializable{

	//Transformation de l'association UML en Java
	@ManagedProperty(value="#{catService}") 
	private ICategorieService catService;
	
	// Déclaration des attributs
	private Produit produit;
	private Categorie categorie;
	private Administrateur admin; 
	private boolean indice;
	private UploadedFile image; 
	private List<Categorie> listeCat; 
	
    private HttpSession maSession;
    
    private List<Categorie> catFiltre;

    // Constructeur
	public CategorieManagedBean() {
		this.categorie= new Categorie();
		this.indice= false;
		this.produit=new Produit();
	}

	// Getters et Setters
	public Produit getProduit() {
		return produit;
	}

	public void setCatduit(Produit produit) {
		this.produit = produit;
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

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}
	
	
	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public List<Categorie> getCatFiltre() {
		return catFiltre;
	}

	public void setCatFiltre(List<Categorie> catFiltre) {
		this.catFiltre = catFiltre;
	}
	

	public List<Categorie> getListeCat() {
		return listeCat;
	}

	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}
	

	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}

	@PostConstruct //Cette annotation sert à dire que la méthode doit être exécutée après l'instanciation de l'objet
	public void init(){
		this.listeCat = catService.recCat();
		maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin= (Administrateur) maSession.getAttribute("adminSession");
	}
    
	// Méthodes métiers
	public String ajoutCat() {
		if(this.image!=null){
			this.categorie.setPhoto(this.image.getContents());
		}
		
		//appel de la méthode service
		Categorie cAjout=catService.ajoutCat(categorie);
		
		if(cAjout.getId()!=0) { // pAjout ne sera JAMAIS nul
			
			
			//Récup de la nouvelle liste
			List<Categorie> listeCat=catService.recCat();
			
			//Mettre à jour la liste dans la session
			maSession.setAttribute("cSession", listeCat);
			
			return "listecat"; 
		}else {
			
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué"));
			return "ajoutcat";
		}
		
	}
	
	public String modifCat() {
		//appel de la méthode service
		int verif= catService.modifCat(categorie);
		if(verif!=0) {
			
			//Récup de la nouvelle liste
			List<Categorie> listeCat=catService.recCat();
			
			//Mettre à jour la liste dans la session
			maSession.setAttribute("cSession", listeCat);
			
			return "listecat"; 
		}else {
			
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a échoué"));
			return "listecat";
		}	
	}
	
	public void modifAuto() {
		 this.categorie=catService.recCatById(categorie); 
	}
	
	public String supprCat() {
		//appel de la méthode service
		int verif=catService.supprCat(categorie);
		if(verif!=0) {
			
			//Récup de la nouvelle liste
			List<Categorie> listeCat=catService.recCat();
			
			//Mettre à jour la liste dans la session
			maSession.setAttribute("cSession", listeCat);
			
			return "listecat"; 
		}else {
			
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué"));
			return "listecat";
		}	
	}
	
	public String recCatById() {
		//appel de la méthode service
		Categorie cOut=catService.recCatById(categorie);
		if(cOut!=null) {
			this.categorie=cOut; 
			this.indice=true;
			return "recherchecat";
		
		}else {
			
			this.indice=false;
			//Ajouter un message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La catégorie n'a pas été trouvée"));
			return "recherchecat";
		}
	}
	
	
	
	
	
	
    
	
}
