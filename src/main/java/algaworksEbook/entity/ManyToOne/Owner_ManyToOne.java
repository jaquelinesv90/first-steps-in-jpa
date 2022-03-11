package algaworksEbook.entity.ManyToOne;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Owner_ManyToOne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column
	private String name_owner;
	
	@Column
	private String telephone_owner;
	
	@Column
	private String email;
	

	public Owner_ManyToOne() {}
	

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
}
