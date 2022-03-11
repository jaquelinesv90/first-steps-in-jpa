package algaworksEbook.criteria.complexResults;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* Trabalhar com indices de arrays de deixa o código feio e a chance de errar é muito maior.
 * Podemos retornar um array de Tuple, onde cada tupla representa um registro encontrado.
 * 
 * É possível projetar o retorno da consulta concatenando duas colunas como um único resultado,
 * isso pode ser feito utilizando uma classe DTO com os atributos referentes aquilo que a consulta
 * deve retornar, ou utilizando a interface javax.persistence.Tuple
 * 
 * Chamamos o método createTupleQuery para receber uma instancia de CriteriaQuery e apelidamos cada
 * propriedade projetada com o método alias.Esses apelidos foram usados na iteração, através do
 * método get da tupla.
 * 
 */
public class VehicleSearchingTupleList {

	public static void main(String[] args){
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		criteriaQuery.multiselect(
				vehicle.<String>get("model").alias("vehicle_model"),
				vehicle.<String>get("value").alias("vehicle_value"));
		
		TypedQuery<Tuple> query = manager.createQuery(criteriaQuery);
		List<Tuple> result = query.getResultList();
		
		for(Tuple tuple : result) {
			System.out.println(tuple + "- " + tuple);
		}
		
		manager.close();
		JpaUtil.close();

	}
}