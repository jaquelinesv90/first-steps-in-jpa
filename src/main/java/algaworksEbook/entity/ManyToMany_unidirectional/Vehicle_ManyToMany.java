package algaworksEbook.entity.ManyToMany_unidirectional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import algaworksEbook.FuelType;
import algaworksEbook.IdVehicle;
import algaworksEbook.Owner;

import javax.persistence.JoinColumn;


//@Entity
@Table(name = "tab_vehicle")
public class Vehicle_ManyToMany{

	@EmbeddedId
	private IdVehicle code;
	
	@Column( length = 60, nullable = false) 
	private String manufacturer;    
	
	@Column( length = 60, nullable = false)
	private String model;
	
	@Column( name= "year_manufacture", nullable = false)
	private Integer yearManufacture;//fabricação
	
	@Column( name= "model_year", nullable = false)
	private Integer modelYear;
	
	@Column( length = 60, nullable = false)
	private BigDecimal value;
	
	@Column(name = "fuel_type",nullable = false)
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_Register", nullable = false)
	private Date dateRegister;
	
	@Transient
	private String fullDescription;
	
	@Lob
	@Column
	private String especification;
	
	@Lob
	private byte[] photo;
	
	private Owner owner;
	
	@ManyToMany
	@JoinTable(name = "accessory_vehicle",
			joinColumns = @JoinColumn(name = "code_vehicle"),
			inverseJoinColumns = @JoinColumn(name = "code_accessory"))
	private Set<Accessory> accessory = new HashSet<>();
	
	
	public Vehicle_ManyToMany() {}
	
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
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public Date getDateRegister() {
		return dateRegister;
	}
	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	public String getEspecification() {
		return especification;
	}
	public void setEspecification(String especification) {
		this.especification = especification;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Set<Accessory> getAccessory() {
		return accessory;
	}
	public void setAccessory(Set<Accessory> accessory) {
		this.accessory = accessory;
	}

	//evitar elementos duplicados
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle_ManyToMany other = (Vehicle_ManyToMany) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}	
}