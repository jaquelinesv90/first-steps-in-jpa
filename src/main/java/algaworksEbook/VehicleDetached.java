package algaworksEbook;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/*
	Objetos desanexados
São objetos em um estado que não é gerenciado pelo EntityManager, mas ainda representam entidades no banco.
As alterações em objetos desanexados não são sincronizadas com o banco de dados.Utilizamos objetos desanexados, por exemplo,
quando eles são expostos para alteração através de páginas web e apenas em um segundo momento o usuário solicita a 
gravação das alterações do objeto.
*/
public class VehicleDetached {
	
	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Vehicle vehicle = manager.find(Vehicle.class,1L);
		
		tx.commit();
		manager.close();
		
		// essa alteração não será sincronizada
		vehicle.setValue(new BigDecimal(112_000));
		
		JpaUtil.close();
		
		//Podemos reanexar objetos em qualquer EntityManager usando o método merge.
		EntityManager manager1 = JpaUtil.getEntityManager();
		EntityTransaction tx1 = manager.getTransaction();
		
		tx.begin();
		
		Vehicle vehicle1 = manager.find(Vehicle.class, 1L);
		tx1.commit();
		manager.close();
		
		vehicle1.setValue(new BigDecimal(112_000));
		
		manager = JpaUtil.getEntityManager();
		tx1 = manager1.getTransaction();
		tx1.begin();
		
		//reanexamos o objeto ao novo EntityManager
		vehicle1 = manager.merge(vehicle1);
		
		tx1.commit();
		manager1.close();
		JpaUtil.close();
		
		// O conteúdo do objeto desanexado é copiado para um objeto gerenciado com a mesma identidade
		// Se o entityManager ainda não estiver gerenciando um objeto com a mesma identidade, será
		// realizada uma consulta para encontrá-lo, ou ainda, será persistida uma nova entidade.
		
		// O retorno do método merge é uma instância de um objeto gerenciado.O objeto desanexo não
		// muda de estado, ou seja, continua detached.
	}
}
