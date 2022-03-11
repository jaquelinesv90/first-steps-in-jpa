package algaworksEbook.secondLevelCache;

import javax.persistence.EntityManager;

import algaworksEbook.JpaUtil;

/*
 * fazemos duas consultas da mesma entidade em entityManagers
 * diferentes.
 * Apenas uma consulta sql foi gerada, graças ao cache de 
 * segundo nivel.
 *
 */
public class CentroCustoSearching {
	
	public static void main(String[] args) {
		EntityManager manager1 = JpaUtil.getEntityManager();
		CentroCusto centro1 = manager1.find(CentroCusto.class, 1L);
		manager1.close();
		
		
		EntityManager manager2 = JpaUtil.getEntityManager();
		CentroCusto centro2 = manager1.find(CentroCusto.class, 1L);
		manager2.close();
		
	}
}
