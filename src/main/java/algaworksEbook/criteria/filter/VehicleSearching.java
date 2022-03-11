package algaworksEbook.criteria.filter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import algaworksEbook.FuelType;
import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* A clausula where é uma parte importantede consultas, pois definem as condições(predicados)
 * que filtam o resultado.
 * 
 * Para filtar o resultado usado Criteria API, precisamos de objetos do tipo Predicate para
 * passar para a cláusula where.Um objeto do tipo Predicate é obtido através do CriteriaBuilder.
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		Predicate predicate = builder.not(builder.equal(vehicle.get("fuelType"),FuelType.DIESEL));
		
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
