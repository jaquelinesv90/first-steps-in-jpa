package algaworksEbook.jpql.aggregationFunctions;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;
import algaworksEbook.jpql.projections.VehiclePrice;

/* A sintaxe para funções de agregação em JPQL é similar a SQL.Você pode usar as
 * funções avg, count,min, max e sum e agrupar os resultados com a cláusula group by.
 * 
 */
public class VehicleSearching {
	
	public static void main(String[] args) {
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		TypedQuery<TotalCarPerYear> query = manager.createQuery(
				"select new com.algaworks.lojaveiculos.dto.TotalCarPerYear("
						+ "v.yearManufacture, avg(v.value), count(v)) "
						+ "from Vehicle v group by v.yearManufacture",
						TotalCarPerYear.class);
		
		List<TotalCarPerYear> result = query.getResultList();
		
		for(TotalCarPerYear obj: result) {
			System.out.println("year:" + obj.getYearManufacture() 
								+ "- preço médio: "+ obj.getMediaPrice());
		}
		
		manager.close();
		JpaUtil.close();
	}
}
