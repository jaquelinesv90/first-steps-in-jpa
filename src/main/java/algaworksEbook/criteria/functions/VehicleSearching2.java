package algaworksEbook.criteria.functions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* Neste exemplo, temos uma consulta que retorna uma lista de strings, com o fabricante
 * e modelo concatenados e separados pelo caracter "-"
 */
public class VehicleSearching2 {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		
		Expression<String> expression = builder.concat(builder.concat(
				vehicle.<String> get("manufacturer"), " - "),
				vehicle.<String> get("model"));
		criteriaQuery.select(expression);
		
		TypedQuery<String> query = manager.createQuery(criteriaQuery);
		List<String> vehicles = query.getResultList();
		
		for(String v : vehicles) {
			System.out.println(v);
		}
		
		manager.close();
		JpaUtil.close();
	}
}
