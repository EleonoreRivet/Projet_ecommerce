package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdministrateurDao;
import fr.adaming.model.Administrateur;

@Stateful
public class AdministrateurServiceImpl implements IAdministrateurService{
	@EJB
	private IAdministrateurDao aDao;

	@Override
	public Administrateur existe(Administrateur aIn) {
		return aDao.existe(aIn);
	}

}
