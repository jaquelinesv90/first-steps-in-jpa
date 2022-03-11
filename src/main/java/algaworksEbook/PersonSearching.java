package algaworksEbook;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import algaworksEbook.inheritanceSingleTable.Person;

public class PersonSearching {
	
	public static void main(String[] args) {
		// Obtém um EntityManager, que é responsável por
		// gerenciar o ciclo de vida das entidades
 		EntityManager manager = JpaUtil.getEntityManager();
 		//Iniciando uma nova transação
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		List<Person> people = manager.createQuery("SELECT P FROM PERSON P",Person.class)
										.getResultList();
		
		for(Person person : people) {
			if(person instanceof Person) {
				System.out.println("its a client");
			}else {
				System.out.println("its an employee");
			}
		}
		
		
		tx.commit();
		//fechando o EntityManager e o EntityManagerFactory
		manager.close();
		JpaUtil.close();
		
	}

}
