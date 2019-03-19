package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;


@Repository
public class CategorieDaoImpl implements ICategorieDao {
	@Autowired
	private SessionFactory sf;

	// Le setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Categorie ajoutCat(Categorie c) {
		// Récupérer le bus
		Session s = sf.getCurrentSession();
		s.save(c);
		return c;
	}

	@Override
	public int supprCat(Categorie c) {
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "DELETE FROM Categorie as c WHERE c.id=:pId";

		// Récupérer un objet de type Query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pId", c.getId());

		return query.executeUpdate();
	}

	@Override
	public int modifCat(Categorie c) {
		Session s = sf.getCurrentSession();

		// Requête HQL

		String req = "UPDATE Categorie as c SET c.nom=:pNom, c.description=:pDesc WHERE c.id=:pId";

		// Récupérer un objet de type Query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pId", c.getId());
		query.setParameter("pNom", c.getNom());
		query.setParameter("pDesc", c.getDescription());

		return query.executeUpdate();
	}

	@Override
	public List<Categorie> recCat() {
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "FROM Categorie as c";

		// Récupérer un objet de type Query
		Query query = s.createQuery(req);

		List<Categorie> listeCat = query.list();

		for (Categorie c : listeCat) {
			c.setImg("data:image/png;base64," + Base64.encodeBase64String(c.getPhoto()));
		}

		return listeCat;
	}

	@Override
	public Categorie recCatById(Categorie c) {
		Session s = sf.getCurrentSession();

		Categorie cOut = (Categorie) s.get(Categorie.class, c.getId());
		cOut.setImg("data:image/png;base64," + Base64.encodeBase64String(cOut.getPhoto()));
		return cOut;
	}

}
