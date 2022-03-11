package algaworksEbook;

import java.util.List;

import javax.persistence.EntityManager;

import algaworksEbook.inheritanceSingleTable.Cliente;
import algaworksEbook.inheritanceSingleTable.Person;

public class ClienteEmployeeSearching {
	
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		
		List<Person> people = manager.createQuery("SELECT P FROM PERSON P",Person.class).getResultList();
		
		for(Person person : people) {
			System.out.println(person.getName());
			
			if(person instanceof Cliente) {
				System.out.println("it's a cliente");
			}else {
				System.out.println("it's employee");
			}
		}
		
		manager.close();
		JpaUtil.close();
	}

}
