package algaworksEbook.jpql.aggregationFunctions;

public class TotalCarPerYear {
	
	private Integer yearManufacture;
	private Double mediaPrice;
	private Long  quantityCars;
	
	public TotalCarPerYear(Integer yearManufacture,Double mediaPrice,
			Long  quantityCars) {
		super();
		this.yearManufacture = yearManufacture;
		this.mediaPrice = mediaPrice;
		this.quantityCars = quantityCars;
	}

	public Integer getYearManufacture() {
		return yearManufacture;
	}

	public void setYearManufacture(Integer yearManufacture) {
		this.yearManufacture = yearManufacture;
	}

	public Double getMediaPrice() {
		return mediaPrice;
	}

	public void setMediaPrice(Double mediaPrice) {
		this.mediaPrice = mediaPrice;
	}

	public Long getQuantityCars() {
		return quantityCars;
	}

	public void setQuantityCars(Long quantityCars) {
		this.quantityCars = quantityCars;
	}	
}