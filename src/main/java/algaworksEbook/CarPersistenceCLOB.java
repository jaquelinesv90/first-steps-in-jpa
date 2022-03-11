package algaworksEbook;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

// consulta utilizando campo CLOB(serve para arquivos,imagem,texto longo) 
public class CarPersistenceCLOB {
	
	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		StringBuilder especification = new StringBuilder();
		especification.append("Excellent car.");
		especification.append("This engine produces a maximum power of 68 PS");
		especification.append("Turism car");
		especification.append("Inline 3 cylinder engine,Petrol or CNG motor");
		especification.append("The power is transmitted to the road by the front wheel drive");
		especification.append("with a 6 speed Manual gearbox");
		especification.append("rear suspension");
		especification.append("with a fuel consumption of 4.6 litres");
		
		Vehicle vehicle = new Vehicle();
		vehicle.setManufacturer("Vw");
		vehicle.setModel("Gol");
		vehicle.setYearManufacture(2018);
		vehicle.setModelYear(2019);
		vehicle.setValue(new BigDecimal(17_200));
		
		manager.persist(vehicle);
		tx.commit();
		
		manager.detach(vehicle);
		
		Vehicle vehicle2 = manager.find(Vehicle.class, vehicle.getCode());
		System.out.println("Veículo:" + vehicle2.getModel());
		System.out.println("---------");
		System.out.println(vehicle2.getEspecification());
		
		manager.close();
		JpaUtil.close();	
	}
}
