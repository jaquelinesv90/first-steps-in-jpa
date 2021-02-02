package ebook.jpql.projections;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ebook.JpaUtil;
import ebook.Vehicle;

/* Projeções é uma técnica muit útil para quando precisamos de apenas algumas
 * poucas informações de entidades.
 * 
 * Dependendo de como o mapeamento é feito, o provedor JPA pode gerar uma query 
 * SQL grande e complexa para buscar o estado da entidade,podendo deixar o sistema 
 * lento.
 * Suponhamos que precisamos listar apenas os modelos de veículos que temos armazenados.
 * Somente o modelo.
 * 
 * A consulta abaixo fica acaba gerando outras queries para buscar o proprietário do 
 * veículo.
 * 
 * Projeções são cruciais para o desempenho do seu aplicativo e a capacidade de manutenção 
 * do código.Além disso, você precisa escolher uma projeção que mantenha o overhead o mais 
 * baixo possível e forneça os dados em uma estrutura fácil de usar.
 * 
 */
public class VehicleSearching {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		TypedQuery<Vehicle> query = manager.createQuery(
				"select v from Vehicle v", Vehicle.class);
		
		List<Vehicle> vehicles = query.getResultList();
		
		for(Vehicle obj: vehicles) {
			System.out.println(obj.getModel() +" "+ obj.getManufacturer());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
