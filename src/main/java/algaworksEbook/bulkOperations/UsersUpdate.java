package algaworksEbook.bulkOperations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import algaworksEbook.JpaUtil;

/* Iremos inativar todos os usuários que possuem emails do
 * gmail.Para isso fazemos uma operação em lotem criando 
 * um objeto do tipoQuery e chamando o método executeUpdate.
 * 
 * Todos os usuários com email gmail serão inativados de uma
 * única vez, sem carregar os objetos em memória(é muito mais
 * rápido).
 * Temos que ser cuidadosos ao fazer operações em lote, pois o
 * contexto de persistência não é atualizado de acordo com as 
 * operações executadas, portanto, é sempre bom fazer esse tipo de
 * operação em uma transação isolada, onde nenhuma outra alteração nas
 * entidades é feita.
 */
public class UsersUpdate {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Query query = manager.createQuery(
			"update User set active = false where email like :email");
		query.setParameter("email", "%@gmail.com");
		
		int linhasAfetadas = query.executeUpdate();
		
		System.out.println(linhasAfetadas + "registros atualizados");
		
		tx.commit();
		manager.close();
		JpaUtil.close();
	}
}
