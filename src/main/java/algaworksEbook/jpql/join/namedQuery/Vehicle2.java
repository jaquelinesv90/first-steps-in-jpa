package algaworksEbook.jpql.join.namedQuery;

import java.math.BigDecimal;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import algaworksEbook.IdVehicle;

/* Para definir mais de uma named query em uma entidade, podemos agrupá-las 
 * com a anotação @NamedQueries.
 */

//@Entity
@Table(name = "veiculo")
@NamedQueries({
		@NamedQuery(name = "Vehicle.comProprietarioPorValor",
			query = "select v from Veiculo v"
			+ "inner join fetch v.proprietario where v.valor >:valor"),
		@NamedQuery(name = "Vehicle.porModelo",
			query = "select v from Vehicle v where modelo like :model")
})
public class Vehicle2 {
	
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
