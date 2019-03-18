package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao{
	@PersistenceContext(name="PU_proj")
	private EntityManager em;

	@Override
	public LigneCommande ajoutProduit(Produit p, int qte) {
		//instanciation d'une nouvelle ligne de commande
		LigneCommande lc=new LigneCommande();
		
		//ajout du produit
		lc.setProduit(p);	
		lc.setQuantite(qte);
		
		//calcul du prix
		lc.setPrix(p.getPrix() * qte);
		
		em.persist(lc);
		
		return lc;
	}
	
	public int supprProduit(Produit p){
		// Requête JPQL

				String req = "DELETE FROM LigneCommande as l WHERE l.produit.id=:pId";
				
				//Récupérer un objet de type Query
				Query query=em.createQuery(req);		
				
				//Passage des paramètres
				query.setParameter("pId", p.getId());
				
				return query.executeUpdate();
	}

	@Override
	public List<LigneCommande> getListeCo() {
			// Requête JPQL
			String req="SELECT l FROM LigneCommande as l"; 
			
			//Récupérer un objet de type Query
			Query query=em.createQuery(req);

			List<LigneCommande> listeLico = query.getResultList();
			
			for(LigneCommande l:listeLico){
				l.getProduit().setImg("data:image/png;base64,"+Base64.encodeBase64String(l.getProduit().getPhoto()));
			}
			
			
			return listeLico;
		}
	
	public LigneCommande getLigneCoByPro(Produit p){
		// Requête JPQL
		String req="SELECT l FROM LigneCommande as l WHERE l.produit.id=:pIdp"; 
		
		//Récupérer un objet de type Query
		Query query=em.createQuery(req);		
		
		//Passage des paramètres
		query.setParameter("pIdp", p.getId());
		
		LigneCommande lOut= (LigneCommande) query.getSingleResult();
		
		return lOut;
	}
	}
	

