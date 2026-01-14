package br.tsi.comichub.dao;

import java.util.List;

import br.tsi.comichub.model.Box;
import br.tsi.comichub.model.Comic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class ComicDAO extends DAO<Comic>{

	public ComicDAO() {
		super(Comic.class);
	}

	public List<Comic> findByBox(Box box) {
		
		 try (EntityManager em = JPAUtil.getEntityManager()) {
		    	
	    	return em.createQuery("SELECT c FROM Comic c WHERE c.box = :box", Comic.class)
	    			 .setParameter("box", box).getResultList();
		        
	    } catch (NoResultException e) {
	        return null;
	    }
	}
}
