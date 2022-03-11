package algaworksEbook.jpql.projections;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* Trabalhar com vetores de objetos não é nada agradável, é propenso a erros e deixa
 * o código muito feio.
 * 
 * Podemos usar uma classe para representar o resultado da consulta com os atributos
 * que desejamos e um construtor que recebe como parâmetro os dois valores.
 * 
 * Basta usarmos o operador new na query JPQL, informando o nome completo da classe que
 * criamos para representar o resultado da consulta(incluindo o nome do pacote) e passando
 * como parâmetro do construtor as propriedades modelo e valor.
 * 
 * 
 * A consulta SQL não muda em nada, mas o provedor JPA instancia e retorna objetos do tipo
 * que especificamos.
 */
public class NewOperator {
	
	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		TypedQuery<VehiclePrice> query = manager.createQuery(
				"select new com.algaworks.lojaveiculos.dto.VehiclePrice("
						+ "model,value) from Vehicle", VehiclePrice.class);
		
		List<VehiclePrice> result = query.getResultList();
		
		for(VehiclePrice obj: result) {
			System.out.println(obj.getModel());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
