package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Repository
public class LigneCommandeDaoImpl implements ILigneCommandeDao{
	@Autowired
	private SessionFactory sf;

	// Le setter pour l'injection de d�pendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public LigneCommande ajoutProduit(Produit p, int qte) {
		// R�cup�rer le bus
		Session s = sf.getCurrentSession();
				
		//instanciation d'une nouvelle ligne de commande
		LigneCommande lc=new LigneCommande();
		
//		ajout du produit
		lc.setProduit(p);	
    	lc.setQuantite(qte);
		
//		calcul du prix
	    lc.setPrix(p.getPrix() * qte);
		
		s.save(lc);
		
		return lc;
	}
	
	public int supprProduit(Produit p){
		// R�cup�rer le bus
		Session s = sf.getCurrentSession();
		
		// Requ�te HQL

				String req = "DELETE FROM LigneCommande as l WHERE l.produit.id=:pId";
				
				//R�cup�rer un objet de type Query
				Query query=s.createQuery(req);		
				
				//Passage des param�tres
				query.setParameter("pId", p.getId());
				
				return query.executeUpdate();
	}

	@Override
	public List<LigneCommande> getListeCo() {
		// R�cup�rer le bus
		Session s = sf.getCurrentSession();
		
		// Requ�te HQL
			String req="FROM LigneCommande as l"; 
			
			//R�cup�rer un objet de type Query
			Query query=s.createQuery(req);

			List<LigneCommande> listeLico = query.list();
			
			for(LigneCommande l:listeLico){
				l.getProduit().setImg("data:image/png;base64,"+Base64.encodeBase64String(l.getProduit().getPhoto()));
			}
			
			
			return listeLico;
		}
	
	public LigneCommande getLigneCoByPro(Produit p){
		// R�cup�rer le bus
		Session s = sf.getCurrentSession();
		
		// Requ�te HQL
		String req="FROM LigneCommande as l WHERE l.produit.id=:pIdp"; 
		
		//R�cup�rer un objet de type Query
		Query query=s.createQuery(req);		
		
		//Passage des param�tres
		query.setParameter("pIdp", p.getId());
		
		LigneCommande lOut= (LigneCommande) query.uniqueResult();
		
		return lOut;
	}
	}
	

