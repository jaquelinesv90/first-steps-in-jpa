package algaworksEbook;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/* classe de exemplo de persistencia, utilizando a
* anotação @EmbeddedId
*/ 
public class CarPersistence_ {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Vehicle vehicle = new Vehicle();
		vehicle.setCode(new IdVehicle("ABC-123","Uberlandia"));
		vehicle.setManufacturer("Honda");
		vehicle.setModel("Civic");
		vehicle.setYearManufacture(2020);
		vehicle.setModelYear(2020);
		vehicle.setValue(new BigDecimal(71_300));
		vehicle.setFuelType(FuelType.BIOFUEL);
		
		manager.persist(vehicle);

		tx.commit();
		manager.close();
		JpaUtil.close();
	}
}
