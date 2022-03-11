package algaworksEbook.jpql.join;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;

/* Neste exemplo consultamos a quantidade de veículos que cada proprietário possui.
 * Usamos a função de agregação count e a clausula group by.
 * 
 *  select o.nome, count(v)
 *  from Owner o
 *  left join o.vehicle v
 *  group by o.name_owner
 *  
 */
public class LeftJoin {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		TypedQuery<Object[]> query = manager.createQuery(
				"select o.name_owner, count(v) from Owner o "
				 + "inner join o.vehicle v group by o.name_owner", Object[].class);
		
		List<Object[]> result = query.getResultList();
		
		for(Object[] obj: result) {
			System.out.println(obj[0] + "-" + obj[1]);
		}
		
		manager.close();
		JpaUtil.close();
	}
}
