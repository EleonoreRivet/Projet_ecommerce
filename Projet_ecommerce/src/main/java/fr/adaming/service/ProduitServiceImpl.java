package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Service("proService")
@Transactional
public class ProduitServiceImpl implements IProduitService{
	
	//Transformation de l'asso UML en JAVA
	@Autowired
	IProduitDao produitDao;
	
	//Setter
	public void setProduitDao(IProduitDao produitDao) {
		this.produitDao = produitDao;
	}

	@Override
	public int supprPro(Produit p) {
		return produitDao.supprPro(p);
	}

	@Override
	public int modifPro(Produit p) {
		return produitDao.modifPro(p);
	}

	@Override
	public List<Produit> recPro() {
		return produitDao.recPro();
	}

	@Override
	public Produit recProById(Produit p) {
	return produitDao.recProById(p);
	}

	@Override
	public Produit ajoutPro(Produit p, Categorie c) {
		//Lier les objets en Java
		p.setCategorie(c);
		return produitDao.ajoutPro(p, c);
	}

	@Override
	public List<Produit> recProByMC(String mc) {
		return produitDao.recProByMC(mc);
	}

	@Override
	public List<Produit> recProByCat(Categorie c) {
		return produitDao.recProByCat(c);
	}

	@Override
	public List<Produit> recProSelect() {
		return produitDao.recProSelect();
	}

}
