package algaworksEbook.jpql.projections;

import java.math.BigDecimal;

public class VehiclePrice {
	
	private String model;
	private BigDecimal value;
	
	public VehiclePrice(String model, BigDecimal value) {
		super();
		this.model = model;
		this.value = value;
	}
	
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}