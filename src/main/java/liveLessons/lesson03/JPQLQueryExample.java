package liveLessons.lesson03;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import liveLessons.lesson03.model.Configuration;
import liveLessons.lesson03.model.Control;
import liveLessons.lesson03.model.User;


public class JPQLQueryExample {
	
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("Usuarios-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		innerJoin(entityManager);
		leftJoin(entityManager);
		joinFetch(entityManager);
		filteringData(entityManager);
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	// exemplo de filtragem de dados usando JOIN
	public static void filteringData(EntityManager entityManager) {
		// filtros disponiveis : LIKE, IS NULL, IS EMPTY, BETWEEN, >,<, >=, <=, =, <>
		// LIKE = select u from User u where u.nome like concat(:nomeUser, '%')
		// IS NULL = select u from Usuario u where u.senha is null
		// IS EMPTY = select d from Control d where d.userList is empty
		
		String jpql = "select u from Usuario u where u.lastAcess between :yesterday and :today";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql,User.class)
				.setParameter("yesterday", LocalDateTime.now().minusDays(1))
				.setParameter("today",LocalDateTime.now());
		List<User> list = typedQuery.getResultList();
		
		list.forEach(u -> System.out.println(u.getId() + ","+ u.getName()));
	}
	
	
	
	public static void joinFetch(EntityManager entityManager) {
		String jpql = "select u from Usuario u join fetch u.configuracao join fetch u.dominio";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql,User.class);
		List<User> list = typedQuery.getResultList();
		
		//list.forEach(u -> System.out.println(u.getId() + ","+ u.getName()));
	}
	
	
	public static void leftJoin(EntityManager entityManager) {
		String jpql = "select u, c from user u left join u.configuration c ";
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> list = typedQuery.getResultList();

		// arr[0] == Usuario - no indice 0 ele guarda o usuario
		// arr[1] == Configuracao - no indice 1 ele guarda a configuracao
		// escrever a palavra outer na instrução é opcional
		
			list.forEach(arr -> {
				String out = ((User) arr[0]).getName();
				if (arr[1] == null) {
					out += ",NULL";
				} else {
					out += ((Configuration) arr[1]).getId();
				}
			}

		);
	}
	
	public static void innerJoin(EntityManager entityManager) {
		String jpql = "select u from User u  join u.control d where d.id = 1";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class);
		List<User> list = typedQuery.getResultList();
		
		for(User u : list) {
			System.out.println(u.getId()+","+u.getName());
		}
	}
}
