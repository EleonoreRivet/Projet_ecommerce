package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;


@Service("catService")
@Transactional
public class CategorieServiceImpl implements ICategorieService{
	//Transformation de l'asso UML en JAVA
	@Autowired
	ICategorieDao catDao;

	
	//Setter
	public void setCatDao(ICategorieDao catDao) {
		this.catDao = catDao;
	}

	@Override
	public Categorie ajoutCat(Categorie c) {
		return catDao.ajoutCat(c);
	}

	@Override
	public int supprCat(Categorie c) {
		return catDao.supprCat(c);
	}

	@Override
	public int modifCat(Categorie c) {
		return catDao.modifCat(c);
	}

	@Override
	public List<Categorie> recCat() {
		return catDao.recCat();
	}

	@Override
	public Categorie recCatById(Categorie c) {
		Categorie cOut= catDao.recCatById(c);
			if(cOut!=null){
				return cOut;
			}else{
				return null;
			}
		}


}
