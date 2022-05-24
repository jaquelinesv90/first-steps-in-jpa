package liveLessons.lesson03;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import liveLessons.lesson03.dto.UserDto;
import liveLessons.lesson03.model.Control;
import liveLessons.lesson03.model.User;


public class JPQLQueryExample {
	
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("Usuarios-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		firstSelect(entityManager);
		choosingReturn(entityManager);
		makingProjections(entityManager);
		passParameter(entityManager);
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
		
		/** implementação do for em java 8+
		 * 
		 * list.forEach(arr -> {
		 *  	String out = ((Usuario) arr[0]).getNome(); 
		 * 	if( arr[1] == null){
		 * 		out+= ", NULL";
		 * } else {
		 *  	out += "," + (Configuracao) arr[1]).getId();
		 *  }
		 *  
		 *  System.out.println(out);
		 *  
		 * });
		 */
	}
	
	public static void innerJoin(EntityManager entityManager) {
		String jpql = "select u from User u  join u.control d where d.id = 1";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class);
		List<User> list = typedQuery.getResultList();
		
		for(User u : list) {
			System.out.println(u.getId()+","+u.getName());
		}
	}
	
	//passagem de parametros
	public static void passParameter(EntityManager entityManager) {
		String jpql = "select u from User u where u.id = :idUser";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class);
		typedQuery.setParameter("idUser", 1);
		User user = typedQuery.getSingleResult();
		System.out.println(user.getId() + ","+ user.getName());
		
		
		String jpqlString = "select u from User u where u.name = :nameUser";
		TypedQuery<User> typedQueryString = entityManager.createQuery(jpqlString, User.class);
		typedQueryString.setParameter("nameUser", "celo");
		User userResult = typedQueryString.getSingleResult();
		System.out.println("resultado :"+userResult.getId() + ","+ userResult.getName());
	}
	
	
	//selecionando apenas alguns dados da tabela
	//retorna uma lista de array de objetos
	public static void makingProjections(EntityManager entityManager) {
		String jpqlArray = "select id,name from User";
		TypedQuery<Object[]> typedQueryArray = entityManager.createQuery(jpqlArray, Object[].class);
		List<Object[]> listArray = typedQueryArray.getResultList();
		for(Object u : listArray) {
			System.out.println("%s,%s,%s"+u.getClass());
		}
		
		
		String jpqlDto = "select new lesson02.dto.UserDto(id,name) from User";
		TypedQuery<UserDto> typedQueryDto = entityManager.createQuery(jpqlDto, UserDto.class);
		List<UserDto> listDto = typedQueryDto.getResultList();
		
		for(UserDto u : listDto) {
			System.out.println(u.getId()+","+u.getName());
		}
	}
	
	
	public static void choosingReturn(EntityManager entityManager) {
		
		String jpql = "select u.control from User u where u.id =1";
		TypedQuery<Control> typedQuery = entityManager.createQuery(jpql, Control.class);
		Control control = typedQuery.getSingleResult();
		System.out.println(control.getId()+ "," + control.getName());
		
		String jpqlNames = "select u.name from User u";
		TypedQuery<String> typedQueryName = entityManager.createQuery(jpqlNames, String.class);
		List<String> listNames = typedQueryName.getResultList();
		//list.forEach(u -> System.out.println(u.getId() + ","+u.getName()));
		for(String u : listNames) {
			System.out.println(u);
		}
	}
	

	public static void firstSelect(EntityManager entityManager) {
		
		String jpql = "select u from User u";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class);
		List<User> list = typedQuery.getResultList();
		//list.forEach(u -> System.out.println(u.getId() + ","+u.getName()));
		for(User u : list) {
			System.out.println(u.getId()+ "," + u.getName());
		}
		
		String jpqlSingle = "select u from User u where u.id = 1";
		TypedQuery<User> typedQuerySingle = entityManager.createQuery(jpqlSingle, User.class);
		User user = typedQuerySingle.getSingleResult();
		System.out.println(user.getId()+ "," + user.getName());
		
		String jpqlCast = "select u from User u where u.id = 1";
		Query query = entityManager.createQuery(jpqlCast);
		User user2 = (User)query.getSingleResult();
		System.out.println(user2.getId()+ "," + user2.getName());
	}	
}
