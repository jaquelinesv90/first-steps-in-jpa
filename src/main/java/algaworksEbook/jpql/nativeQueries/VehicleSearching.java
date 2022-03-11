package algaworksEbook.jpql.nativeQueries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* JPQL é flexivel suficiente para executar quase todas as queries que você precisará
 * e tem a vantagem de usar o modelo de entidades mapeadas.
 * 
 * As colunas retornadas pela query devem coincidir com os nomes definidos no mapeamento.
 * É possível configurar o mapeamento do result set usando @SqlResultSetMapping.
 * 
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		Query query = manager.createQuery(
				"select * from tab_vehicle", Vehicle.class);
		
		List<Vehicle> vehicles = query.getResultList();
		
		for(Vehicle obj: vehicles) {
			System.out.println(obj.getModel() +" "+ obj.getManufacturer());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
