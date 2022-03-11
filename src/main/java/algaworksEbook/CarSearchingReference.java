package algaworksEbook;

import javax.persistence.EntityManager;

/* o método getReference só executa o Sql quando o objeto
 * for usado pela primeira vez, ou seja quando invocamos um 
 * método getter da instância, desde que não seja o getCodigo 
 */
public class CarSearchingReference {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		
		Vehicle vehicle = manager.getReference(Vehicle.class, 1L);
		System.out.print("code vehicle "+ vehicle.getCode()
				+ "and is " + vehicle.getModel());
		
		manager.close();
		JpaUtil.close();
	}

}
