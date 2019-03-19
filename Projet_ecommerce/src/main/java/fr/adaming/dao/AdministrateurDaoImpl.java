package fr.adaming.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Administrateur;

@Repository
public class AdministrateurDaoImpl implements IAdministrateurDao {

	@Autowired
	private SessionFactory sf;

	// Le setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public Administrateur existe(Administrateur aIn) {
		// Récupérer le bus (session de hibernate)
		Session s=sf.getCurrentSession(); 
				
		//Requete HQL
		String req = "FROM Administrateur as a WHERE a.username=:pUser AND a.mdp=:pMdp";

		Query query = s.createQuery(req);

		query.setParameter("pUser", aIn.getUsername());
		query.setParameter("pMdp", aIn.getMdp());

		return (Administrateur) query.uniqueResult();
	}

}
