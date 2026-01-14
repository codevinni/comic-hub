package br.tsi.comichub.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

public class DAO<T> {

	private Class<T> javaClass;

	public DAO(Class<T> javaClass) {
		this.javaClass = javaClass;
	}

	public void add(T t) {

		try (EntityManager em = JPAUtil.getEntityManager()) {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		}
	}

	public void update(T t) {

		try (EntityManager em = JPAUtil.getEntityManager()) {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		}
	}

	public void remove(T t) {

		try (EntityManager em = JPAUtil.getEntityManager()) {
			em.getTransaction().begin();
			em.remove(em.merge(t));
			em.getTransaction().commit();
		}
	}

	public T searchById(Long id) {

		try (EntityManager em = JPAUtil.getEntityManager()) {
			return em.find(javaClass, id);
		}
	}

	public List<T> listAll() {

		try (EntityManager em = JPAUtil.getEntityManager()) {

			CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(javaClass);
			query.select(query.from(javaClass));

			return em.createQuery(query).getResultList();
		}
	}
}
