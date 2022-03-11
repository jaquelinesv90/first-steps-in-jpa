package algaworksEbook.jpql.pagination;

import java.util.List;
import java.util.Scanner;

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
 * O usuário pode digitar o número de registros por página e navegar entre 
 * elas.Ou o método pode receber como parametro essas informações vindas do
 * front-end.
 * 
 */
public class VehicleDynamicSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Registros por página");
		
		int registerPerPage = scanner.nextInt();
		int pageNumber = 0;
		
		TypedQuery<Vehicle> query = manager.createQuery(
				"from Vehicle v", Vehicle.class);
		
		do {
			System.out.println("Número da página");
			pageNumber = scanner.nextInt();
			
			if(pageNumber != 0) {
				int firstRegister = (pageNumber - 1) * registerPerPage;
				
				query.setFirstResult(firstRegister);
				query.setMaxResults(registerPerPage);
				
				List<Vehicle> vehicles = query.getResultList();
				
				for(Vehicle v : vehicles) {
					System.out.println(v.getModel() + "" + v.getManufacturer());
				}
			}
		}while (pageNumber != 0);
		manager.close();
		JpaUtil.close();
	}
}
