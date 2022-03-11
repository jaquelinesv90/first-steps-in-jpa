package algaworksEbook.jpql.join;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* Um problema bastante conhecido é o problema do N+1.Esse anti-pattern ocorre quando pesquisamos
 * entidades e seus relacionamentos também são carregados na sequencia.Cada objeto retornado gera 
 * pelo menos mais uma nova consulta para pesquisar os relacionamentos.
 * Cada objeto retornado gera pelo menos mais uma nova consulta para pesquisar os relacionamentos.
 */
public class Nplus1 {
	
	public static void main(String[] args) {
			
			EntityManager manager = JpaUtil.getEntityManager();
			
			TypedQuery<Vehicle> query = manager.createQuery(
					"select v from Vehicle v inner join fetch v.owner", Vehicle.class);
			
			List<Vehicle> result = query.getResultList();
			
			for(Vehicle obj: result) {
				System.out.println(obj.getModel() + " - " + obj.getOwner().getName_owner());
			}
			
			manager.close();
			JpaUtil.close();
	}
}
