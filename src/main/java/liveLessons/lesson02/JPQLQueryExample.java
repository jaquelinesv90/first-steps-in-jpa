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
		// choosingReturn(entityManager);
		// makingProjections(entityManager);
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

	// selecionando apenas alguns dados da tabela
	// retorna uma lista de array de objetos
	public static void makingProjections(EntityManager entityManager) {
		String jpqlArray = "select id,name from User";
		TypedQuery<Object[]> typedQueryArray = entityManager.createQuery(jpqlArray, Object[].class);
		List<Object[]> listArray = typedQueryArray.getResultList();
		for (Object u : listArray) {
			System.out.println("%s,%s,%s" + u.getClass());
		}

		String jpqlDto = "select new lesson02.dto.UserDto(id,name) from User";
		TypedQuery<UserDto> typedQueryDto = entityManager.createQuery(jpqlDto, UserDto.class);
		List<UserDto> listDto = typedQueryDto.getResultList();

		for (UserDto u : listDto) {
			System.out.println(u.getId() + "," + u.getName());
		}

	}

}
