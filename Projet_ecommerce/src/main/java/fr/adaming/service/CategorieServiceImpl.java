package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;


@Stateful
public class CategorieServiceImpl implements ICategorieService{
	//Transformation de l'asso UML en JAVA
	@EJB
	ICategorieDao cDao;

	@Override
	public Categorie ajoutCat(Categorie c) {
		return cDao.ajoutCat(c);
	}

	@Override
	public int supprCat(Categorie c) {
		return cDao.supprCat(c);
	}

	@Override
	public int modifCat(Categorie c) {
		return cDao.modifCat(c);
	}

	@Override
	public List<Categorie> recCat() {
		return cDao.recCat();
	}

	@Override
	public Categorie recCatById(Categorie c) {
		Categorie cOut= cDao.recCatById(c);
			if(cOut!=null){
				return cOut;
			}else{
				return null;
			}
		}


}
