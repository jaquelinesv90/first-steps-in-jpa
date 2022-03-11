package algaworksEbook.jpql.join.namedQuery.externalArchive;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Main {
	
	private static EntityManager manager;
	
	//exemplo de chamada de named Querie em arquivo externo
	public static void main(String[] args) {
		TypedQuery<Person> query = manager.createNamedQuery("Person.all", Person.class);
		List<Person> people = query.getResultList();

	}

}
