package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateless
public class CategorieDaoImpl implements ICategorieDao{
	@PersistenceContext(name="PU_proj") 
	private  EntityManager em;

	@Override
	public Categorie ajoutCat(Categorie c) {
		em.persist(c);
		return c;
	}

	@Override
	public int supprCat(Categorie c) {
		// Requête JPQL
		String req = "DELETE FROM Categorie as c WHERE c.id=:pId";
		
		//Récupérer un objet de type Query
		Query query=em.createQuery(req);		
		
		//Passage des paramètres
		query.setParameter("pId", c.getId());
		
		return query.executeUpdate();
	}

	@Override
	public int modifCat(Categorie c) {
		// Requête JPQL
		
		String req = "UPDATE Categorie as c SET c.nom=:pNom, c.description=:pDesc WHERE c.id=:pId";
		
		//Récupérer un objet de type Query
		Query query=em.createQuery(req);		
		
		//Passage des paramètres
		query.setParameter("pId", c.getId());
		query.setParameter("pNom", c.getNom());
		query.setParameter("pDesc", c.getDescription());
		
		return query.executeUpdate();
	}

	@Override
	public List<Categorie> recCat() {
		// Requête JPQL
		String req="SELECT c FROM Categorie as c"; 
		
		//Récupérer un objet de type Query
		Query query=em.createQuery(req);
		
		List<Categorie> listeCat = query.getResultList();

		for(Categorie c:listeCat){
			c.setImg("data:image/png;base64,"+Base64.encodeBase64String(c.getPhoto()));
		}
		
		return listeCat;
	}

	@Override
	public Categorie recCatById(Categorie c) {
		Categorie cOut = em.find(Categorie.class, c.getId());
		cOut.setImg("data:image/png;base64,"+Base64.encodeBase64String(cOut.getPhoto()));
		return cOut;
	}
	
	
}
