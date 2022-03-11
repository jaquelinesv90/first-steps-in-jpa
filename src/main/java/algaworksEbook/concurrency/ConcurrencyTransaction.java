package algaworksEbook.concurrency;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import algaworksEbook.JpaUtil;

/* normalmente em sistemas é comum existir concorrência em entidades
 * por dois ou mais EntityManagers. No código abaixo, abrimos dois 
 * EntityManagers, buscamos usuários representados pelo identificador 1
 * em cada EntityManager e depois alteramos o email em cada objeto.
 */
public class ConcurrencyTransaction {
	
	public static void main(String[] args) {
		//obtém o primeiro EntityManager e inicia transação
		EntityManager manager1 = JpaUtil.getEntityManager();
		EntityTransaction tx1 = manager1.getTransaction();
		tx1.begin();
		
		//obtém o segundo EntityManager e inicia transação
		EntityManager manager2 = JpaUtil.getEntityManager();
		EntityTransaction tx2 = manager2.getTransaction();
		tx2.begin();
		
		//altera o objeto associado ao primeiro EntityManager
		User u1 = manager1.find(User.class,1L);
		u1.setEmail("maria@teste.com");
		
		//altera o objeto associado ao primeiro EntityManager
		User u2 = manager2.find(User.class,1L);
		u2.setEmail("jose@teste.com");
		
		//faz commit na primeira transacao
		tx1.commit();
		manager1.close();
		
		tx2.commit();
		manager2.close();
		
		JpaUtil.close();
		
		/* como buscamos os usuários usando EntityManagers diferentes, os
		 * objetos não são os mesmos na memória da JVM.
		 * Quando fizemos commit no primeiro EntityManager, a alteração foi 
		 * sincronizada com o banco de dados. O outro commit, do segundo, também 
		 * sincronizou a alteração feita no user associado a este contexto de
		 * persistencia, substituindo a modificação anterior.
		 * 
		 */
		
	}
	
	
	
	
	

}
