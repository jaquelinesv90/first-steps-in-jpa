package algaworksEbook.jpql.join.namedQuery;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;

/* 
 * é interessante a utilização de Named Queries quando  você tem certeza que sua query não 
 * mudará e ela é bastante executada dentro da aplicação.
 * 
 * Para usar uma named query, chamamos o método createNamedQuery de EntityManager na
 * classe NamedQuery.
 * 
 * namedQuery é uma consulta jpql estaticamente definida com parametros já definidos.
 * São mais rápidas por serem parseadas para SQL e  cacheadas uma unica vez.
 * 
 * Named queries podem ser externalizádas para arquivos XML, assim evitar a poluição
 * da sua classe java. 
 * 
 */
public class NamedQuery {
	
	public static void main(String[] args) {
			
			EntityManager manager = JpaUtil.getEntityManager();
			
			TypedQuery<Vehicle> query = manager.createNamedQuery(
					"Veiculo.comProprietarioPorValor", Vehicle.class );
			
			query.setParameter("valor", new BigDecimal(10_000));
			
			List<Vehicle> vehicle = query.getResultList();
			
			for(Vehicle obj: vehicle) {
				System.out.println(obj.getModel());
			}
			
			manager.close();
			JpaUtil.close();
	}
}
