package algaworksEbook;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/* a anotação Embeddable será sempre utilizada de forma
"embutida" em outra classe 
 A anotação @Embeddable significa que teremos uma chave composta,
incluiremos os atributos cidade e placa como identificador do veiculo. 
*/   
@Embeddable
public class IdVehicle implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	@Column
	private String licensePlate;
	
	@Column
	private String city;
	
	public IdVehicle() {}
	
	public IdVehicle(String licensePlate, String city) {
		super();
		this.licensePlate = licensePlate;
		this.city = city;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// a implementação do método é obrigatória caso
	// não haja o erro a seguir é mostrado:
	//Embedded ID class should include method definitions for equals() and hashcode()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
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
		IdVehicle other = (IdVehicle) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		return true;
	}
}
