package algaworksEbook.jpql.join.namedQuery;

import java.math.BigDecimal;

import javax.persistence.NamedQuery;
import javax.persistence.Table;

import algaworksEbook.IdVehicle;

/*Queries nomeadas, também conhecidas como named queries, é uma forma de organizar
 * as consultas JPQL que escrevemos em nossas aplicações.
 * 
 * Além de organizar as queries, ganhamos em performance, pois elas são estáticas e
 * processadas apenas na inicialização da unidade de persistência.
 * 
 * Uma named query é definida com a anotação de @NamedQuery, que pode ser colocada na 
 * declaração da classes de qualquer entidade JPA. A anotação recebe o nome da query 
 * e a própria consulta JPQL.
 * 
 * Para facilitar e evitar conflitos, nomeamos a query com um prefixo "Veiculo",
 * dizendo que a consulta está relacionada a essa entidade.
 * 
 * Para usar uma named query, chamamos o método createNamedQuery de EntityManager na
 * classe NamedQuery.
 */

//@Entity
@Table(name = "veiculo")
@NamedQuery(name = "Vehicle.comProprietarioPorValor",
			query = "select v from Veiculo v"
			+ "inner join fetch v.proprietario where v.valor >:valor")
public class Vehicle {
	
	
	private IdVehicle code;
	
	private String manufacturer;    
	
	private String model;
	
	private Integer yearManufacture;//fabricação
	
	private Integer modelYear;
	
	private BigDecimal value;
	
	

	public IdVehicle getCode() {
		return code;
	}

	public void setCode(IdVehicle code) {
		this.code = code;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYearManufacture() {
		return yearManufacture;
	}

	public void setYearManufacture(Integer yearManufacture) {
		this.yearManufacture = yearManufacture;
	}

	public Integer getModelYear() {
		return modelYear;
	}

	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}