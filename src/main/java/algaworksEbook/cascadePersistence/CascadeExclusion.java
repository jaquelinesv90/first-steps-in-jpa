package algaworksEbook.cascadePersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import algaworksEbook.JpaUtil;

public class CascadeExclusion {
	
	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Category category = manager.find(Category.class, 1L);
		manager.remove(category);
		
		tx.commit();
		manager.close();
		JpaUtil.close();
	
	}

}
