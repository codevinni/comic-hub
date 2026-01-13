package br.tsi.comichub.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAUtil{
	
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("comichub");

	private JPAUtil() {
		throw new UnsupportedOperationException("Utility Class");
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
