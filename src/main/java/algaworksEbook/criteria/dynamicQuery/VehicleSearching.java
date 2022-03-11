package algaworksEbook.criteria.dynamicQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import algaworksEbook.FuelType;
import algaworksEbook.JpaUtil;
import algaworksEbook.Vehicle;

/* A grande vantagem de Criteria API é poder criar queries dinâmicas, com filtros condicionais,
 * por exemplo.Neste caso, não sabemos qual será a estrutura final da consulta, pois ela é montada 
 * em tempo de execução.
 * 
 * Criamos um método pesquisarVeiculos que retorna uma lista de veículos, dado o tipo do combustível
 * e o valor maximo.É permitido passar null para qualquer um dos parâmetros do método, portanto, os 
 * filtros da query devem ser montados programaticamente.
 * 
 * O exemplo mostramos também o uso de parâmetros usando o método CriteriaBuilder.parameter, que 
 * recebe como argumento o tipo do parâmetro e o nome dele, que depois é definido pelo método
 * TypedQuery.setParameter.
 * 
 */
public class VehicleSearching {

	public static List<Vehicle> vehicleSearching(FuelType fuelType, BigDecimal highestValue){
		
		EntityManager manager = JpaUtil.getEntityManager();
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = builder.createQuery(Vehicle.class);
		
		Root<Vehicle> vehicle = criteriaQuery.from(Vehicle.class);
		criteriaQuery.select(vehicle);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(fuelType != null) {
			ParameterExpression<FuelType> paramFuelType = builder.parameter(FuelType.class,"fuelType");
			predicates.add(builder.equal(vehicle.get("fuelType"), paramFuelType));
		}
		
		if(highestValue != null) {
			ParameterExpression<BigDecimal> paramValue = builder.parameter(BigDecimal.class,"highestValue");
			predicates.add(builder.lessThanOrEqualTo(vehicle.<BigDecimal>get("value"), paramValue));
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Vehicle> query = manager.createQuery(criteriaQuery);
		
		if(fuelType != null) {
			query.setParameter("fuelType", fuelType);
		}
		
		if(highestValue != null) {
			query.setParameter("highestValue", highestValue);
		}
		
		List<Vehicle> vehicle2 = query.getResultList();
		manager.close();
		
		return vehicle2;
	}
	
	public static void main(String[] args) {
		List<Vehicle> vehicles = vehicleSearching(
				FuelType.BIOFUEL, new BigDecimal(50_000));
		
		for(Vehicle v : vehicles) {
			System.out.println(v.getModel() + " - " +v.getValue());
		}
	}
}