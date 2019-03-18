package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService{
	
	//Transformation de l'asso UML en JAVA
	@EJB
	IProduitDao pDao;

	@Override
	public int supprPro(Produit p) {
		return pDao.supprPro(p);
	}

	@Override
	public int modifPro(Produit p) {
		return pDao.modifPro(p);
	}

	@Override
	public List<Produit> recPro() {
		return pDao.recPro();
	}

	@Override
	public Produit recProById(Produit p) {
		Produit pOut= pDao.recProById(p);
		if(pOut!=null){
			return pOut;
		}else{
			return null;
		}
	}

	@Override
	public Produit ajoutPro(Produit p, Categorie c) {
		//Lier les objets en Java
		p.setCategorie(c);
		return pDao.ajoutPro(p, c);
	}

	@Override
	public List<Produit> recProByMC(String mc) {
		return pDao.recProByMC(mc);
	}

	@Override
	public List<Produit> recProByCat(Categorie c) {
		return pDao.recProByCat(c);
	}

	@Override
	public List<Produit> recProSelect() {
		return pDao.recProSelect();
	}

}
