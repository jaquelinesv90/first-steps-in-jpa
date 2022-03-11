package algaworksEbook.jpql.namedParameter;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* Se você precisar fazer uma consulta filtrando por valores informados
 * pelo usuário, não é recomendado que faça concatenações na string da consulta,
 * principalmente para evitar SQL Injection. O ideal é usar parâmetros da Query.
 * 
 * Nomeamos os parâmetros com um prefixo : e vinculamos os valores para cada
 * parametro usando o método setParameter de Query.
 * 
 * Primeiramente, pegamos uma instância do tipo CriteriaBuilder do EntityManager, 
 * através do método getCriteriaBuilder.Essa interface funciona como uma fábrica de vários
 * objetos que podemos usar para definir uma consulta.
 * 
 * Usamos o método createQuery de CriteriaBuilder para instanciar um CriteriaQuery.A interface
 * CriteriaQuery possui as clausulas da consulta.
 * 
 * Chamamos o método from de CriteriaQuery para obtermos um objeto do tipo Root.Depois,chamamos
 * o método da cláusula select, informando como parametro o objeto do tipo Root, dizendo que queremos
 * selecionar a entidade Veiculo.
 * 
 * Criamos uma TypedQuery através do método EntityManager.createQuery, e depois recuperamos o resultado
 * da consulta pelo método getResultList.
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		Query query = manager.createQuery(
				"select v from Vehicle v where yearManufacture >= :year " +
				" and value <= :price");
		query.setParameter("year", 2020);
		query.setParameter("price", new BigDecimal(107_000));
		
		List vehicles = query.getResultList();
		
		for(Object obj: vehicles) {
			Vehicle vehicle = (Vehicle) obj;
			
			System.out.println(vehicle.getModel() +" "+ vehicle.getManufacturer());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
