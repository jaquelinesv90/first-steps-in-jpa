package algaworksEbook.criteria.projections;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* A projeção descreve quais colunas você seleciona em seu banco de dados e de que 
 * forma o hibernate as fornece para você.Ou em outras palavras, se você estiver 
 * escrevendo uma consulta JPQL, é tudo entre as palavras-chave SELECT e FROM.
 * JPA e Hibernate suportam 3 grupos de projeções:
 *  1. Valores Scalares
 *  2. Entidades
 *  3. DTOs
 *  
 * SQL suporta somente projeções escalares, como tabelas, colunas e o retorno de valor de 
 * uma função de banco.
 * 
 * Suponha que precisamos listar apenas os modelos dos veículos que temos armazenados.
 * Podemos projetar essa propriedade chamando o método Root.get e passando para a 
 * cláusula select.
 * 
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		criteriaQuery.select(vehicle.<String>get("model"));
		
		TypedQuery<String> query = manager.createQuery(criteriaQuery);
		List<String> models = query.getResultList();
		
		for(String  m : models) {
			System.out.println(m);
		}
		
		manager.close();
		JpaUtil.close();
	}
}
