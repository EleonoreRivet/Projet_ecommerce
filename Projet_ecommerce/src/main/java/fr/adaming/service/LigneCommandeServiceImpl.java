package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService{
	
	@EJB
	ILigneCommandeDao lcDao;

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
