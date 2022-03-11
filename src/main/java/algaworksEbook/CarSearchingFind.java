package algaworksEbook;

import javax.persistence.EntityManager;

//Busca utilizando o método find, passando como argumento o 
// tipo da entidade e o código do veículo

// o método find busca o objeto imediatamente no banco de dados
public class CarSearchingFind {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		
		Vehicle vehicle = manager.find(Vehicle.class, 1L);
		
		System.out.print("code vehicle" + vehicle.getCode()
				+ "and is "+ vehicle.getModel());
		
		manager.close();
		JpaUtil.close();
	}
}
