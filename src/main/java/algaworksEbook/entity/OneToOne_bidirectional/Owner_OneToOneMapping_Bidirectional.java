package algaworksEbook.entity.OneToOne_bidirectional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Owner_OneToOneMapping_Bidirectional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column
	private String name_owner;
	
	@Column
	private String telephone_owner;
	
	@Column
	private String email;
	
	/* Campo não obrigatório - o mapeamento pode ser
	 * unidirecional.
	 * Ao adicionar este campo e a anotação fazemos a
	 * associação um-para-um bidirecional e então conseguimos
	 * obter o veículo a partir de um proprietário.*/
	@OneToOne(mappedBy = "owner")
	private Vehicle_OneToOneMapping_Bidirectional vehicle;
	

	public Owner_OneToOneMapping_Bidirectional() {}


	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName_owner() {
		return name_owner;
	}

	public void setName_owner(String name_owner) {
		this.name_owner = name_owner;
	}

	public String getTelephone_owner() {
		return telephone_owner;
	}

	public void setTelephone_owner(String telephone_owner) {
		this.telephone_owner = telephone_owner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Vehicle_OneToOneMapping_Bidirectional getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle_OneToOneMapping_Bidirectional vehicle) {
		this.vehicle = vehicle;
	}
	
}
