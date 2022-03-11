package algaworksEbook;

import javax.persistence.EntityManager;

//classe de exemplo de pesquisa, utilizando a
//anotação @EmbeddedId
public class CarSearchingFind_ {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		
		IdVehicle code = new IdVehicle("AAA-1111","Uberlandia");
		Vehicle vehicle = manager.find(Vehicle.class, code);
		
		System.out.println("Placa:" + vehicle.getCode().getLicensePlate());
		System.out.println("Cidade:" + vehicle.getCode().getCity());
		System.out.println("Fabricante:" + vehicle.getManufacturer());
		
		manager.close();
		JpaUtil.close();
		
	}
}
