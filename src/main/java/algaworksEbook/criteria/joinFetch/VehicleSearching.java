package algaworksEbook.criteria.joinFetch;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Owner;
import algaworksEbook.Vehicle;

/* Para fazer join entre duas entidades podemos usar seus relacionamentos mapeados.
 * No exemplo abaixo, chamamos o método join de um objeto do tipo From.Depois,
 * filtramos a consulta a partir da entidade usada pelo join.
 * 
 * Para evitar o problema do N+1, podemos fazer um fetch no relacionamento da 
 * entidade Veiculo.
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		Join<Vehicle, Owner> owner = (Join)vehicle.fetch("owner");
		
		criteriaQuery.select(vehicle);
		criteriaQuery.where(builder.equal(owner.get("name"),
										  "Joao Santos"));
		
		TypedQuery<Vehicle> query = manager.createQuery(criteriaQuery);
		List<Vehicle> results = query.getResultList();
		
		for(Vehicle p : results) {
			System.out.println(p.getModel());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
