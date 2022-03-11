package algaworksEbook.jpql.pagination;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/*  Uma consulta que retorna muitos objetos pode ser um problema para 
 * muitas aplicações.Se existir a necessidade de exibir um conjunto de dados
 * grande, é interessante implementar uma paginação de dados e deixar o 
 * usuário navegar entre as páginas.
 * 
 * As interfaces Query e TypedQuery suportam paginação através dos métodos
 * setFirstResult e setMaxResult, que definem a posição do primeiro registro
 * (começando de 0) eo número máximo de registros que podem ser retornados,
 * respectivamente.
 * 
 * Foi definido que queremos receber apenas os 10 primeiros registros.
 * 
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		TypedQuery<Vehicle> query = manager.createQuery(
				"select v from Vehicle v", Vehicle.class);
		query.setFirstResult(0);
		query.setMaxResults(10);
		
		List<Vehicle> vehicles = query.getResultList();
		
		for(Vehicle obj: vehicles) {
			System.out.println(obj.getModel() +" "+ obj.getManufacturer());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
