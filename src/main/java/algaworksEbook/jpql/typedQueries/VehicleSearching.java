package algaworksEbook.jpql.typedQueries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/*  Quando fazemos uma consulta, podemos obter o resultado de um tipo especifico,
 * sem precisar fazer casting dos objetos ou até mesmo conversões não checadas 
 * para coleções genéricas.
 * 
 * Usamos a interface TypedQuery, que é um subtipo de Query.A diferença é que o
 * método getResultList já retorna uma lista do tipo que especificamos na criação
 * da query, no segundo parametro.
 * 
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		TypedQuery<Vehicle> query = manager.createQuery(
				"select v from Vehicle v", Vehicle.class);
		List<Vehicle> vehicles = query.getResultList();
		
		for(Vehicle obj: vehicles) {
			
			System.out.println(obj.getModel() +" "+ obj.getManufacturer());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
