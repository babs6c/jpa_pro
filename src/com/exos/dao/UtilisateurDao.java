package com.exos.dao;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.exos.entities.Utilisateur;

@Stateless
public class UtilisateurDao {
	private final static String JPQL_LISTE_MEMBERS="select u from Utilisateur u";
	private final static String JPQL_FIND_MEMBER_BY_EMAIL="select u from Utilisateur u where u.email=:email";
	private final static String JPQL_FIND_MEMBER="select u from Utilisateur u where u.email=:email and u.pass=:pass";
    
	// Injection du manager, qui s'occupe de la connexion avec la BDD
	@PersistenceContext(unitName="user_PU")
	private EntityManager em;
	
	public List<Utilisateur> lister() throws DAOException {
		
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		Query requete=em.createQuery(JPQL_LISTE_MEMBERS);
		try
		{
			utilisateurs =(List<Utilisateur>)requete.getResultList();
			
		}
		catch(Exception e )
		{
			throw new DAOException("Impossible de communiquer avec la base de données");
		}
		return utilisateurs;
	}


	public Utilisateur trouver(String email, String pass) throws DAOException {
		Utilisateur utilisateur=null;
		Query requete=em.createQuery(JPQL_FIND_MEMBER);
		requete.setParameter("email", email);
		requete.setParameter("pass", pass);
		
		try
		{
			utilisateur=(Utilisateur)requete.getSingleResult();
		}
		catch(NoResultException e)
		{
			
		}
		catch(Exception e )
		{
			throw new DAOException("Impossible de communiquer avec la base de données");
		}
		return utilisateur;
	}
	
	

	public Utilisateur trouver(String email) throws DAOException {
		Utilisateur utilisateur=null;
		Query requete=em.createQuery(JPQL_FIND_MEMBER_BY_EMAIL);
		requete.setParameter("email", email);
		
		try
		{
			utilisateur=(Utilisateur)requete.getSingleResult();
			
		}
		catch(NoResultException e)
		{
			 
		}
		catch(Exception e )
		{
			throw new DAOException("Impossible de communiquer avec la base de données");
		}
		
		return utilisateur;
	}
	
	public void add(Utilisateur utilisateur) throws DAOException {
		try {
			em.persist(utilisateur);
		}
		catch(Exception e)
		{
			throw new DAOException("Impossible de communiquer avec la base de données");
		}
	}
	
}
