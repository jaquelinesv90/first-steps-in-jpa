package liveLessons.lesson02;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import liveLessons.lesson02.dto.UserDto;
import liveLessons.lesson02.model.Control;
import liveLessons.lesson02.model.User;

public class JPQLQueryExample {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Usuarios-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		firstSelect(entityManager);
		choosingReturn(entityManager);
		makingProjections(entityManager);
		passParameter(entityManager);

		entityManager.close();
		entityManagerFactory.close();
	}

	public static void firstSelect(EntityManager entityManager) {

		String jpql = "select u from User u";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class);
		List<User> list = typedQuery.getResultList();

		list.forEach(u -> System.out.println(u.getId() + "," + u.getName()));

		String jpqlSingle = "select u from User u where u.id = 1";
		TypedQuery<User> typedQuerySingle = entityManager.createQuery(jpqlSingle, User.class);
		User user = typedQuerySingle.getSingleResult();
		System.out.println(user.getId() + "," + user.getName());

		String jpqlCast = "select u from User u where u.id = 1";
		Query query = entityManager.createQuery(jpqlCast);
		User user2 = (User) query.getSingleResult();
		System.out.println(user2.getId() + "," + user2.getName());

	}

	public static void choosingReturn(EntityManager entityManager) {

		String jpql = "select u.control from User u where u.id = 1";
		TypedQuery<Control> typedQuery = entityManager.createQuery(jpql, Control.class);
		Control control = typedQuery.getSingleResult();
		System.out.println(control.getId() + "," + control.getName());

		String jpqlNames = "select u.name from User u";
		TypedQuery<String> typedQueryName = entityManager.createQuery(jpqlNames, String.class);
		List<String> listNames = typedQueryName.getResultList();

		listNames.forEach(nome -> System.out.println(nome));
	}
	
	/*  Fazer projeções é basicamente retornar somente os campos que eu quero de determinado objeto
	 *  selecionando apenas alguns dados da tabela - o retorno com os campos está em um DTO
	 *   retorna uma lista de array de objetos
	 */
	public static void makingProjections(EntityManager entityManager) {
		String jpqlArray = "select id,name from User";
		
		TypedQuery<Object[]> typedQueryArray = entityManager.createQuery(jpqlArray, Object[].class);
		List<Object[]> listArray = typedQueryArray.getResultList();
		listArray.forEach(arr -> System.out.println(String.format("%s,%s,%s",arr)));

		/*
		 *  Eu não quero que ele me devolva um Array de objetos,eu quero 
		 *   como retorno uma lista do tipo UsuarioDTO.
		 *   com o 'new' com o pacote e o nome da classe é usado, dentro do parenteses os
		 *   parametros que eu quero.
		 */ 
		String jpqlDto = "select new lesson02.dto.UserDto(id,name) from User";
		TypedQuery<UserDto> typedQueryDto = entityManager.createQuery(jpqlDto, UserDto.class);
		List<UserDto> listDto = typedQueryDto.getResultList();

		listDto.forEach(u -> System.out.println(u.getId() + "," + u.getName()));
	}

	// passagem de parametros
	public static void passParameter(EntityManager entityManager) {
		String jpql = "select u from User u where u.id = :idUser";
		
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class);
		typedQuery.setParameter("idUser", 1);
		User user = typedQuery.getSingleResult();
		System.out.println(user.getId() + "," + user.getName());

		String jpqlString = "select u from User u where u.name = :nameUser";
		
		TypedQuery<User> typedQueryString = entityManager.createQuery(jpqlString, User.class);
		typedQueryString.setParameter("nameUser", "celo");
		User userResult = typedQueryString.getSingleResult();
		
		System.out.println("resultado :" + userResult.getId() + "," + userResult.getName());
	}
}
