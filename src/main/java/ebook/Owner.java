package ebook;

import java.util.List;

import javax.persistence.*;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Owner" )
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column
	private String name_owner;
	
	/* @ElementCollection : cria relacionamentos da nossa entidade com
	 * outros tipos que não são entidades. tipos simples como String 
	 * 
	 * @CollectionTable :
	 * 
	 * @AttributeOverrides : 
	 * 
	 * 
	 * @AttributeOverride : 
	 * 
	 */
	
	@ElementCollection
	@CollectionTable(name = "owner_phone",
			joinColumns = @JoinColumn(name = "owner_code"))
	@AttributeOverrides({
		@AttributeOverride(name="number",
				column = @Column(name = "phone_number", length = 20, 
				nullable = false))
	})
	private List<String> telephone_owner;
	
	@Column
	private String email;
	
	/* Campo não obrigatório - o mapeamento pode ser
	 * unidirecional.
	 * Ao adicionar este campo e a anotação fazemos a
	 * associação um-para-um bidirecional e então conseguimos
	 * obter o veículo a partir de um proprietário.
	@OneToOne(mappedBy = "owner")
	private Vehicle vehicle;
	*/

	public Owner() {}
	

	public String getName_owner() {
		return name_owner;
	}

	public void setName_owner(String name_owner) {
		this.name_owner = name_owner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public List<String> getTelephone_owner() {
		return telephone_owner;
	}

	public void setTelephone_owner(List<String> telephone_owner) {
		this.telephone_owner = telephone_owner;
	}
	
}
