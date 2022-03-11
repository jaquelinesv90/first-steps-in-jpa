package algaworksEbook;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CarPersistence {
	
	public static void main(String[] args) {
		// Obtém um EntityManager, que é responsável por
		// gerenciar o ciclo de vida das entidades
 		EntityManager manager = JpaUtil.getEntityManager();
 		//Iniciando uma nova transação
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Vehicle vehicle = new Vehicle();
		vehicle.setManufacturer("Honda");
		vehicle.setModel("Civic");
		vehicle.setYearManufacture(2020);
		vehicle.setModelYear(2020);
		vehicle.setValue(new BigDecimal(90500));
		
		/*executamos o método persist, passando a instancia
		* do veículo como parametro, isso fará com que o
		* JPA insira o objeto no banco de dados.
		*/
		manager.persist(vehicle);
		
		tx.commit();
		/*
		 * fechando o EntityManager e o EntityManagerFactory
		 * quando fechamos o EntityManager, todas as instancias se 
		 * tornam detached
		 */
		manager.close();
		JpaUtil.close();
		
	}
}
