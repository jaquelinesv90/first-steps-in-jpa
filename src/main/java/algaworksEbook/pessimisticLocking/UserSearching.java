package algaworksEbook.pessimisticLocking;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;

import algaworksEbook.JpaUtil;

/* O locking pessimista "trava" o objeto imediatamente, ao invés de aguardar o commit com
 * otimismo, esperando que nada de errado.
 * 
 * Um lock pessimista garante que o objeto travado não será modificado por outra transação 
 * até que a transação atual seja finalizada e libere a trava.
 * 
 * Usar essa abordagem limita a escalabilidade de sua aplicação, pois deixa as operações
 * ocorrerem apensa em série, por isso, deve ser usada com cuidado.Na maioria dos casos, 
 * as operações poderiam ocorrer em paralelo, usando locking otimista.
 * 
 * Uma das formas de usar locking pessimistaa é passando um parametro para o método find
 * de EntityManager.
 * 
 * 
 * Jpa prove 3 tipos de lock pessimista:
 * 1 - PESSIMISTIC_READ : Nos permite obter um bloqueio compartilhado e evitar que os dados
 * sejam atualizados ou excluídos.
 * 2 - PESSIMISTIC_WRITE : Nos permite obter um bloqueio exclusivo e evitar que os dados sejam 
 * lidos, atualizados ou excluídos.
 * 3 - PESSIMISTIC_FORCE_INCREMENT : Funciona como PESSIMISTIC_WRITE e, adicionalmente, incrementa
 * um atributo de versão de uma entidade com versão.
 * 	Todos eles são membros estáticos da classe LockModeType e permitem que as  transações obtenham
 * um bloqueio de banco de dados.Todos eles são retidos ate que a transação seja confirmada ou 
 * revertida.
 *  é importante notar que podemos obter apenas um bloqueio de cada vez.
 *  
 *  O lock adiciona um for update na consulta SQL.
 * 
 */
public class UserSearching {
	
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		
		User user = manager.find(User.class, 1L, LockModeType.PESSIMISTIC_WRITE);
		
		tx.commit();
		manager.close();
		JpaUtil.close();
	}

}
