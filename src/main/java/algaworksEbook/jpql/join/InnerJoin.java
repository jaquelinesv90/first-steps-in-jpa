package algaworksEbook.jpql.join;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;
import algaworksEbook.Owner;

/* Inner join entre entidades
 * Se um proprietário possuir dois ou mais veículos, ele repetirá no resultado da consulta,
 * por isso é melhor usarmos o operador distinct.
 * 
 *  select distinct p
 *  from Proprietario p
 *  inner join p.veiculos v
 *  
 *  Outra forma de fazer a consulta retornar o mesmo resultado:
 *  
 *  select distinct p
 *  from Veiculo v
 *  inner join v.proprietario p
 */
public class InnerJoin {

	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		TypedQuery<Owner> query = manager.createQuery(
				"select p from Owner p inner join p.vehicle v", Owner.class );
		
		List<Owner> owners = query.getResultList();
		
		for(Owner obj: owners) {
			System.out.println(obj.getName_owner());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
