package algaworksEbook;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import algaworksEbook.inheritanceSingleTable.Cliente;

public class ClienteSearching {
	
	public static void main(String[] args) {
		// Obtém um EntityManager, que é responsável por
		// gerenciar o ciclo de vida das entidades
 		EntityManager manager = JpaUtil.getEntityManager();
 		//Iniciando uma nova transação
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		List<Cliente> clientes = manager.createQuery("SELECT C FROM CLIENTE C",Cliente.class)
										.getResultList();
		tx.commit();
		//fechando o EntityManager e o EntityManagerFactory
		manager.close();
		JpaUtil.close();
		
	}
}
