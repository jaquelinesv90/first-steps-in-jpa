package algaworksEbook;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/*
 * Consulta utilizando JPQL
 * Jpql é uma extensãp da Sql, porém com características
 * da orientação a objetos,com essa linguagem não referenciamos
 * tabelas do banco de dados,apenas entidades de nosso modelo.
*/ 
public class VehicleList {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		
		Query query = manager
				.createQuery(" select v from Vehicle v");
		List<Vehicle> vehicles = query.getResultList();
		
		for(Vehicle v : vehicles) {
			System.out.println(v.getCode() + " - "
					+ v.getManufacturer() + " "
					+ v.getYearManufacture() + " "
					+ "R$" + v.getValue());
		}
		
		manager.close();
		JpaUtil.close();
		
	}
}
