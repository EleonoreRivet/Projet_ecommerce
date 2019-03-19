package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Service("licoService")
@Transactional
public class LigneCommandeServiceImpl implements ILigneCommandeService{
	
	//Transfo de l'asso UML en JAVA
	@Autowired
	ILigneCommandeDao lcDao;
	
	
	//Setter
	public void setLcDao(ILigneCommandeDao lcDao) {
		this.lcDao = lcDao;
	}

	@Override
	public LigneCommande ajoutProduit(Produit p, int qte) {
		return lcDao.ajoutProduit(p, qte);
	}

	@Override
	public List<LigneCommande> getListeCo() {
		return lcDao.getListeCo();
	}

	@Override
	public LigneCommande getLigneCoByPro(Produit p) {
		return lcDao.getLigneCoByPro(p);
	}

	@Override
	public int supprProduit(Produit p) {
		// TODO Auto-generated method stub
		return lcDao.supprProduit(p);
	}

}
