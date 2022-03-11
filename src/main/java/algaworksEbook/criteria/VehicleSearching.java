package algaworksEbook.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* A criteria API da JPA é usada para definir queries dinamicas, criadas a partir de
 * objetos que definem uma consulta, oa invés de puramente texto, como a JPQL.
 * 
 * A principal vantagem da Criteria API é poder construir consultas programaticamente,
 * de forma elegante e com maior integração com a linguagem Java.
 * 
 * A consulta busca todos os veículos cadastrados e imprime o modelo deles.
 * Seria o mesmo que fazer em JPQL: select v from Veiculo v
 * 
 * Primeiramente, pegamos uma instância do tipo CriteriaBuilder do EntityManager,
 * através do método getCriteriaBuilder.Essa interface funciona como uma fábrica de 
 * vários objetos que podemos usar para definir uma consulta.
 * 
 * Usamos o método createQuery de CriteriaBuilder para instanciar um CriteriaQuery.
 * A interface CriteriaQuery possui as cláusulas da consulta.
 * 
 * Chamamos o método from de CriteriaQuery para obtermos um objeto do tipo Root.
 * Depois, chamamos o método da cláusula select, informando como parametro o objeto do
 * tipo Root, dizendo que queremos selecionar a entidade Veiculo.
 * 
 * Criamos uma TypedQuery através do método EntityManager.createQuery, e depois recuperamos o
 * resultado da consulta pelo método getResultList.
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		criteriaQuery.select(vehicle);
		
		TypedQuery<Vehicle> query = manager.createQuery(criteriaQuery);
		List<Vehicle> vehicles = query.getResultList();
		
		for(Vehicle v : vehicles) {
			System.out.println(v.getModel());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
