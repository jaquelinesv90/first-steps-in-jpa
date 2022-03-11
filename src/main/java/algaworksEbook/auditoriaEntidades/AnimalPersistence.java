package algaworksEbook.auditoriaEntidades;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import algaworksEbook.JpaUtil;

/* Agora, não existe mais a necessidade de informar a data de 
 * última atualização de um animal.Ao persisti-lo, a data será
 * calculada automaticamente
 * 
 */
public class AnimalPersistence {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Animal animal = new Animal();
		animal.setName("champion");
		animal.setDateBirthday(LocalDate.now().minusYears(7));
		
		manager.persist(animal);
		
		tx.commit();
		manager.close();
		JpaUtil.close();
	}
}
