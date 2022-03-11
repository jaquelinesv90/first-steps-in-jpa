package algaworksEbook.criteria.subQueries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import algaworksEbook.JpaUtil;
import algaworksEbook.Owner;
import algaworksEbook.Vehicle;

/* Subqueries podem ser usadas pela Criteria API nas clausulas select,where, order,
 * group by e having.Para criar uma subquery, chamamos o método CriteriaQuery.subquery.
 * Obtemo os veículos a partir do valor médio de todos os veículos armazenados.
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
		
		Subquery<Double> subquery = criteriaQuery.subquery(Double.class);
		
		Root<Vehicle> vehicleA = criteriaQuery.from(Vehicle.class);
		Root<Vehicle> vehicleB = subquery.from(Vehicle.class);
		
		subquery.select(builder.avg(vehicleB.<Double>get("value")));
		criteriaQuery.select(vehicleA);
		criteriaQuery.where(builder.greaterThanOrEqualTo(vehicleA.<Double>get("value"), subquery));
		
		TypedQuery<Vehicle> query = manager.createQuery(criteriaQuery);
		List<Vehicle> results = query.getResultList();
		
		for(Vehicle p : results) {
			System.out.println(p.getModel());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
