package hibernateTips.statistics;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import algaworksEbook.JpaUtil;

public class Main {
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		
		for(int i = 0; i<10; i++) {
			Product1 p = new Product1();
			System.out.println("inicialização do hibernate statistics");
			p.setName("new Product" + i);
			em.persist(p);
		}
		em.createQuery("Select p from Product1 p").getResultList();
		
		em.close();
		JpaUtil.close();
		
	}

}
