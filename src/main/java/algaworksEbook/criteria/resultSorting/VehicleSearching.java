package algaworksEbook.criteria.resultSorting;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* Para ordenar o resultado de uma consulta podemos usar o método orderBy de CriteriaQuery.
 * Precisamos passar como parametro para esse método um objeto do tipo Order, que é instanciado
 * usando CriteriaBuilder.desc, para ordenação decrescente.Poderíamos usar asc para ordenação
 * crescente. 
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		Order order = builder.desc(vehicle.<String> get("yearManufacture"));
		
		criteriaQuery.select(vehicle);
		criteriaQuery.orderBy(order);
		
		TypedQuery<Vehicle> query = manager.createQuery(criteriaQuery);
		List<Vehicle> result = query.getResultList();
		
		for(Vehicle p : result) {
			System.out.println(p.getModel());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
