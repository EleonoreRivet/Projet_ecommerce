package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	@Autowired
	private SessionFactory sf;

	// Le setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Produit ajoutPro(Produit p, Categorie c) {
		// Récupérer le bus
		Session s = sf.getCurrentSession();
		s.save(p);
		return p;
	}

	@Override
	public int supprPro(Produit p) {
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "DELETE FROM Produit as p WHERE p.id=:pId";

		// Récupérer un objet de type Query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pId", p.getId());

		return query.executeUpdate();
	}

	@Override
	public int modifPro(Produit p) {
		Session s = sf.getCurrentSession();

		// Requête HQL

		String req = "UPDATE Produit as p SET p.categorie.id=:pCat, p.designation=:pDes, p.description=:pDesc, p.prix=:pPrix, p.quantite=:pQuantite WHERE p.id=:pId";

		// Récupérer un objet de type Query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pId", p.getId());
		query.setParameter("pDes", p.getDesignation());
		query.setParameter("pDesc", p.getDescription());
		query.setParameter("pPrix", p.getPrix());
		query.setParameter("pQuantite", p.getQuantite());
		query.setParameter("pCat", p.getCategorie().getId());

		return query.executeUpdate();
	}

	@Override
	public List<Produit> recPro() {

		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "FROM Produit as p";

		// Récupérer un objet de type Query
		Query query = s.createQuery(req);

		List<Produit> listePro = query.list();

		for (Produit p : listePro) {
			p.setImg("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		}

		return listePro;
	}

	@Override
	public Produit recProById(Produit p) {
		Session s = sf.getCurrentSession();

		Produit pOut = (Produit) s.get(Produit.class, p.getId());
		pOut.setImg("data:image/png;base64," + Base64.encodeBase64String(pOut.getPhoto()));

		return pOut;
	}

	@Override
	public List<Produit> recProByMC(String mc) {
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "FROM Produit as p WHERE p.description LIKE :pMC OR p.designation LIKE :pMC";

		// Récupérer un objet de type Query
		Query query = s.createQuery(req);

		// Paramètres
		query.setParameter("pMC", "%" + mc + "%");

		List<Produit> listePro = query.list();

		for (Produit p : listePro) {
			p.setImg("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		}

		return listePro;

	}

	@Override
	public List<Produit> recProByCat(Categorie c) {
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "FROM Produit as p WHERE p.categorie.id=:pIdc";

		// Récupérer un objet de type Query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pIdc", c.getId());

		List<Produit> listePro = query.list();

		for (Produit p : listePro) {
			p.setImg("data:image/png;base64," + Base64.encodeBase64String(p.getPhoto()));
		}

		return listePro;

	}

	@Override
	public List<Produit> recProSelect() {
		Session s = sf.getCurrentSession();

		// Requête HQL
		String req = "FROM Produit as p WHERE p.selectionne=true";

		// Récupérer un objet de type Query
		Query query = s.createQuery(req);

		return query.list();
	}

}
