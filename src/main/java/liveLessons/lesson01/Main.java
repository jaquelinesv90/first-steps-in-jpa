package liveLessons.lesson01;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * ao fazer duas consultas no banco(iguais) ele faz a primeira
 * e guarda em cache(na memoria, apartir daí passou a ser um objeto gerenciado)
 * quando foi buscado novamente ele retorna o que ja tem na memoria, isso é
 * chamado de cache de primeiro nivel
 */
public class Main {

	//método main cria as instancias
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("Clientes-PU");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// Busca de registro
		Client cliente = entityManager.find(Client.class, 1);
		System.out.print(cliente.getNome());
	
		// Inserindo um registro com persist
		Client cliente1 = new Client();
		cliente1.setId(6);
		cliente1.setName("loja Bahia");
		entityManager.getTransaction().begin();
		entityManager.persist(cliente1);
		entityManager.getTransaction().commit();
		
		/**
		  Operações que vão alterar o banco(insert,update) precisam estar dentro de uma transação
		  só é possível remover objetos que o entityManager está ciente de que existe no banco por isso a 
		  busca antecipada,quando se utiliza o método find o entityManager coloca o objeto como gerenciado.
		    
		  Tudo que altera banco de dados a gente precisa abrir e fechar transação para garantir a consistencia.
		  pesquisa com método find, com jpql ou com criteria não precisa de transação.
		*/
		//Removendo registro
		Client cliente2 = entityManager.find(Client.class, 1);
		entityManager.getTransaction().begin();
		entityManager.remove(cliente2);
		entityManager.getTransaction().commit();
		
		//Usando o cache de primeiro nivel
		//O cache de primeiro nivel é feito automaticamente,só é necessário setar o de segundo nível
		Client cliente3 = entityManager.find(Client.class, 1);
		Client cliente4 = entityManager.find(Client.class, 1);
		
		/**
		 * quando eu defini a estrategia de geração de banco de dados eu disse que queria
		que o banco criasse a chave primaria, neste caso ele vai analisar e ver que tem um identificador sendo passado
		e vai considerar como um update
		*/
		//Atualizando o registro ja gerenciado
		Client cliente5 = entityManager.find(Client.class, 1);
		entityManager.getTransaction().begin();
		cliente5.setName(cliente5.getNome() + "Alterado");
		entityManager.getTransaction().commit();
		
		
		//Atualizando um objeto(que não nasceu gerenciado) com o merge
		Client cliente6 = new Client();
		cliente1.setId(6);
		cliente1.setName("lojão economia");
		entityManager.getTransaction().begin();
		entityManager.persist(cliente6);
		entityManager.getTransaction().commit();
		
		
		//Inserindo com o merge
		Client cliente7 = new Client();
		cliente1.setId(6);
		cliente1.setName("construtora bom preço");
		entityManager.getTransaction().begin();
		entityManager.persist(cliente7);
		entityManager.getTransaction().commit();
			
		//fecha as instancias	
		entityManager.close();
		entityManagerFactory.close();
	}
	
}
