package algaworksEbook;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CarExclusion {

	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Vehicle vehicle = manager.find(Vehicle.class, 1L);
		manager.remove(vehicle);
		
		tx.commit();
		manager.close();
		JpaUtil.close();
	}
}
