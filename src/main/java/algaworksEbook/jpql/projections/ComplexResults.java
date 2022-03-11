package algaworksEbook.jpql.projections;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* Quando uma query projeta mais de uma propriedade ou expressão na cláusula
 * select, o resultado da consulta é um List<Object[]>, ou seja, uma lista de
 * vetores de objetos.
 * No exemplo buscaremos todos os nomes e valores de veículos e precisaremos
 * trabalhar com posições de arrays para separar cada informação.
 * 
 */
public class ComplexResults {
	
	public static void main(String[] args) {
			
			EntityManager manager = JpaUtil.getEntityManager();
			
			TypedQuery<Object[]> query = manager.createQuery(
					"select model, value from Vehicle", Object[].class);
			
			List<Object[]> result = query.getResultList();
			
			for(Object[] obj: result) {
				String model = (String) obj[0];
				BigDecimal value = (BigDecimal) obj[1];
				System.out.println(model +" "+ value);
			}
			
			manager.close();
			JpaUtil.close();
		}
}
