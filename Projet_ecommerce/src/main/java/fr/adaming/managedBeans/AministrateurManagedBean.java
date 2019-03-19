package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdministrateurService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "aMB")
@RequestScoped
public class AministrateurManagedBean implements Serializable {
	
	//Transformation de l'association UML en Java
	@ManagedProperty(value="#{adminService}") 
	private IAdministrateurService adminService;
	@ManagedProperty(value="#{proService}") 
	private IProduitService proService; 
	@ManagedProperty(value="#{catService}") 
	private ICategorieService catService;

	// Déclaration des attributs
	private Administrateur administrateur;
	private List<Produit> listePro;
	private List<Produit> filteredPro;

	// Constructeur
	public AministrateurManagedBean() {
		this.administrateur = new Administrateur();
	}

	// Getters et Setters
	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}



	public List<Produit> getListePro() {
		return listePro;
	}

	public void setListePro(List<Produit> listePro) {
		this.listePro = listePro;
	}
	
	
	
	public List<Produit> getFilteredPro() {
		return filteredPro;
	}

	public void setFilteredPro(List<Produit> filteredPro) {
		this.filteredPro = filteredPro;
	}

	
	
	public void setAdminService(IAdministrateurService adminService) {
		this.adminService = adminService;
	}

	public void setProService(IProduitService proService) {
		this.proService = proService;
	}

	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}

	@PostConstruct //Cette annotation sert à dire que la méthode doit être exécutée après l'instanciation de l'objet
	public void init(){
		this.filteredPro=proService.recPro();
		this.listePro=proService.recPro();
	}

	// Méthodes métiers
	public String seConnecter() {
		Administrateur aOut = adminService.existe(administrateur);

		if (aOut != null) {
			//Récupérer la liste des Etudiants du formateur
			List<Produit> listePro =proService.recPro();
			List<Categorie> listeCat =catService.recCat();
			
			//Mettre les listes dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pSession", listePro);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cSession", listeCat);
			
			//Mettre l'admin dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", aOut);
			
			return "accueil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mot de passe et/ou login est erroné"));
			return "login";

		}
	}

	public String seDeconnecter() {
		Administrateur aOut = adminService.existe(administrateur);

		if (aOut != null) {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			
			return "login";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Impossible de se déconnecter"));
			return "accueil";

		}
	
	
	}	
}
