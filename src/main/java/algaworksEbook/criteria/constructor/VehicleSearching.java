package algaworksEbook.criteria.constructor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;
import algaworksEbook.jpql.projections.VehiclePrice;

/* O jeito mais elegante de retornar um resultado projetado é criando uma 
 * classe para representar os valores de cada tula, com um construtor que
 * recebe os valores e atribui as variaveis de instancia.
 * 
 * Basta criar a consulta e passar como parametro do método select o retorno 
 * de CriteriaBuilder.construct.Este último método deve receber a classe de retorno
 * e as propriedades projetadas.
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<VehiclePrice> criteriaQuery = builder.createQuery(VehiclePrice.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		criteriaQuery.select(builder.construct(VehiclePrice.class, vehicle.<String>get("model"),
								vehicle.<String>get("value")));
		
		TypedQuery<VehiclePrice> query = manager.createQuery(criteriaQuery);
		List<VehiclePrice> result = query.getResultList();
		
		for(VehiclePrice p : result) {
			System.out.println(p.getModel());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
