package algaworksEbook.jpql.simpleQuery;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* O método EntityManager.createQuery é usado para consultar entidades e valores
 * usando JPQL. As consultas 
 * 
 * As consultas criadas através do método createQuery são chamadas de consultas
 * dinâmicas, pois elas são definidas diretamente no código da aplicação.
 * 
 * O método createQuery retorna um objeto do tipo Query, que pode ser consultado
 * através de getResultList.Este método retorna um List não tipado, por isso
 * fizemos um cast na iteração dos objetos.
 * 
 * select v from Vehicle v where yearManufacture = 2019 - essa consulta é o mesmo que:
 * from Vehicle where yearManufacture = 2019
 * Não é obrigatório a instrução select neste caso e nem informar um alias para a,
 * entidade Veiculo, mas a segunda forma só funciona com hibernate. De acordo com a 
 * especificação JPA é preciso incluir a instrução e o alias. 
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		Query query = manager.createQuery(
				"select v from Vehicle v where yearManufacture = 2019");
		
		List vehicles = query.getResultList();
		
		for(Object obj: vehicles) {
			Vehicle vehicle = (Vehicle) obj;
			
			System.out.println(vehicle.getModel() +" "+ vehicle.getManufacturer());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
