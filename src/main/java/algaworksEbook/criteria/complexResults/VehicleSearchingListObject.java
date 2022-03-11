package algaworksEbook.criteria.complexResults;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* No exemplo, projetamos duas propriedades de veiculo usando o método multiselect de 
 * CriteriaQuery e obtemos o resultado da consulta como um List<Object[]>.
 * 
 */
public class VehicleSearchingListObject {

	public static void main(String[] args){
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		criteriaQuery.multiselect(vehicle.<String>get("model"),
				vehicle.<String>get("value"));
		
		TypedQuery<Object[]> query = manager.createQuery(criteriaQuery);
		List<Object[]> result = query.getResultList();
		
		for(Object[] value : result) {
			System.out.println(value[0] + "- " + value[1]);
		}
		
		manager.close();
		JpaUtil.close();

	}
}