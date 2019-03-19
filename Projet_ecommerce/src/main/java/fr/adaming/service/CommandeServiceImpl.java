package fr.adaming.service;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Panier;

@Service("comService")
@Transactional
public class CommandeServiceImpl implements ICommandeService{

	//Transformation de l'association UML en Java
	@Autowired
	ICommandeDao comDao;
	
	// Setter
	public void setComDao(ICommandeDao comDao) {
		this.comDao = comDao;
	}



	@Override
	public Commande enregistrerCom(Panier pan, Client c, Adresse a) {
		c.setAdresse(a);
		return comDao.enregistrerCom(pan, c, a);
	} 
	
}
