package algaworksEbook.jpql.singleResult;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;

/* As interfaces Query e TypedQuery fornecem o método getSingleResult, que deve ser usado
 * quando estamos esperando que a consulta retorne apenas um resultado.
 * 
 * O codigo busca a quantidade de veículos cadastrados e atribui à variável vehiclesNumber,
 * do tipo Long.
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		TypedQuery<Long> query = manager.createQuery(
				"select count(v) from Vehicle v", Long.class);
		
		Long vehiclesNumber = query.getSingleResult();
		
		System.out.println("Qunatidade de veículos:" + vehiclesNumber);
		
		manager.close();
		JpaUtil.close();
	}
}
