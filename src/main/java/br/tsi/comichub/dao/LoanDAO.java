package br.tsi.comichub.dao;

import java.util.List;

import br.tsi.comichub.model.Account;
import br.tsi.comichub.model.Loan;
import jakarta.persistence.EntityManager;

public class LoanDAO extends DAO<Loan> {

    public LoanDAO() {
        super(Loan.class);
    }

    public List<Loan> findByUser(Account user) {
    	
        try (EntityManager em = JPAUtil.getEntityManager()) {
        	
            return em.createQuery("SELECT l FROM Loan l WHERE l.user = :user", Loan.class)
            		 .setParameter("user", user).getResultList();
        }
    }
    
    public List<Loan> findLateLoans() {
    	
    	 try (EntityManager em = JPAUtil.getEntityManager()) {
         	
             return em.createQuery("SELECT l FROM Loan l WHERE l.predictedDevolutionDate < CURRENT_DATE AND l.devolutionDate IS NULL", Loan.class).getResultList();
         }
    }
}
