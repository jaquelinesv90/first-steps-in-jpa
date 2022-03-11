package algaworksEbook.callbackAndEntityListeners;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import algaworksEbook.JpaUtil;

public class PostPersist {
	
	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Animal animal = new Animal();
		animal.setName("Dog");
		animal.setDateBirthday(LocalDate.now().minusYears(5));
		animal.setDateLastUpdate(LocalDateTime.now());
		
		System.out.println("age before persistence: " + animal.getAge());
		
		System.out.println(animal);

		System.out.println("age after persistence: " + animal.getAge());
		
		tx.commit();
		manager.close();
		JpaUtil.close();
	}

}
