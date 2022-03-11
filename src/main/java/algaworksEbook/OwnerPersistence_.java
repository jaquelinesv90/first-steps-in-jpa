package algaworksEbook;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

// Persistencia  utilizando a anotação @ManyToOne
public class OwnerPersistence_ {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Owner owner = new Owner();
		owner.setName_owner("Jurandir");
		owner.getTelephone_owner().add("(34) 1234-5678");
		owner.getTelephone_owner().add("(11) 7777-0764");
		
		manager.persist(owner);
		
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setManufacturer("GM");
		vehicle1.setModel("Celta");
		vehicle1.setYearManufacture(2015);
		vehicle1.setModelYear(2015);
		vehicle1.setValue(new BigDecimal(11_000));
		vehicle1.setDateRegister(new Date());
		
		
		manager.persist(vehicle1);
		
		
		Vehicle vehicle2 = new Vehicle();
		vehicle1.setManufacturer("VW");
		vehicle1.setModel("Gol");
		vehicle1.setYearManufacture(2018);
		vehicle1.setModelYear(2018);
		vehicle1.setValue(new BigDecimal(17_000));
		vehicle1.setDateRegister(new Date());
		
		
		manager.persist(vehicle2);
		tx.commit();
		manager.close();
		JpaUtil.close();
	}
	
}
