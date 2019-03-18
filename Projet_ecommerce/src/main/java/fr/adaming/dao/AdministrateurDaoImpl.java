package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Administrateur;

@Stateless
public class AdministrateurDaoImpl implements IAdministrateurDao {

	@PersistenceContext(unitName = "PU_proj")

	private EntityManager em;

	@Override
	public Administrateur existe(Administrateur aIn) {
		String req = "SELECT a FROM Administrateur as a WHERE a.username=:pUser AND a.mdp=:pMdp";

		Query query = em.createQuery(req);

		query.setParameter("pUser", aIn.getUsername());
		query.setParameter("pMdp", aIn.getMdp());
		try {
			return (Administrateur) query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
