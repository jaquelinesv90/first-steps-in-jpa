package algaworksEbook;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import algaworksEbook.inheritanceSingleTable.Cliente;
import algaworksEbook.inheritanceSingleTable.Employee;

public class ClienteEmployeePersistence {
	
	public static void main(String[] args) {
		// Obtém um EntityManager, que é responsável por
		// gerenciar o ciclo de vida das entidades
 		EntityManager manager = JpaUtil.getEntityManager();
 		//Iniciando uma nova transação
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Employee employee = new Employee();
		employee.setName("Fernando");
		employee.setRole("manager");
		employee.setSalary(new BigDecimal(2_000));
		

		Cliente cliente = new Cliente();
		cliente.setName("Mariana");
		cliente.setMonthlyIncome(new BigDecimal(8_500));
		cliente.setCreditLimit(new BigDecimal(2_000));
		
		cliente.setBlocked(true);
		
		
		//executamos o método persist, passando a instancia
		// do veículo como parametro, isso fará com que o
		// JPA insira o objeto no banco de dados.
		manager.persist(employee);
		manager.persist(cliente);
		
		tx.commit();
		//fechando o EntityManager e o EntityManagerFactory
		manager.close();
		JpaUtil.close();
		
	}
}
