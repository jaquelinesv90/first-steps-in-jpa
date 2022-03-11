package algaworksEbook.criteria.aggregationFunctions;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* As funções de agregação são representadas por métodos do CriteriaBuilder,
 * incluindo max,greatest,min,least,avg,sum,sumAsLong,sumAsDouble,count e countDistinct.
 * 
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		criteriaQuery.select(vehicle.<BigDecimal>get("value"));
		
		TypedQuery<BigDecimal> query = manager.createQuery(criteriaQuery);
		BigDecimal total = query.getSingleResult();
		
		System.out.println("total value "+total);
		
		manager.close();
		JpaUtil.close();
	}
}
