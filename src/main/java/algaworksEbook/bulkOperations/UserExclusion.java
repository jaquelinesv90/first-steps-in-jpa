package algaworksEbook.bulkOperations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import algaworksEbook.JpaUtil;

public class UserExclusion {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Query query = manager.createQuery("delete from user where active = false");
		int linhasAfetadas = query.executeUpdate();
		
		System.out.println(linhasAfetadas + "registros atualizados");
		tx.commit();
		manager.close();
		JpaUtil.close();
	}

}
