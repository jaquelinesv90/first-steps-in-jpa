package algaworksEbook;

import java.util.List;

import javax.persistence.EntityManager;

public class CarSearchingList {
	
	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
			
		List<Vehicle> vehicles = (List<Vehicle>) manager
				.createQuery("select v from Vehicle v", Vehicle.class)
				.getResultList();
				
			for(Vehicle vehicle : vehicles) {
				System.out.println(vehicle.getModel() + "-"
						+ vehicle.getOwner().getName_owner());
			}
			
			manager.close();
			JpaUtil.close(); 
		/* 
		Vehicle v = manager.find(Vehicle.class, 1L);
		
		System.out.println(v.getModel() + " - "+
				v.getOwner().getName_owner());
		manager.close();
		JpaUtil.close(); */
	}

}
