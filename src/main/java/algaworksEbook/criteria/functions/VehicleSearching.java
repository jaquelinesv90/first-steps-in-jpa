package algaworksEbook.criteria.functions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* A Criteria API suporta diversas funções de banco de dados, que estão definidas em
 * CriteriaBuilder.
 * 
 * No emxemplo usamos a função upper para passar o modelo do veículo para maiusculo e
 * fazer uma comparação case-insensitive.
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		Predicate predicate = builder.equal(builder.upper(vehicle.<String>get("model")),
				"Gol".toUpperCase());
		
		criteriaQuery.select(vehicle);
		criteriaQuery.where(predicate);
		
		TypedQuery<Vehicle> query = manager.createQuery(criteriaQuery);
		List<Vehicle> vehicles = query.getResultList();
		
		for(Vehicle v : vehicles) {
			System.out.println(v.getModel());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
