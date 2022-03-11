package algaworksEbook.entity.ManyToMany_bidirectional;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import algaworksEbook.Vehicle;

/* classe que representa todos os acessórios que 
 * um carro pode ter.
 * Um veículo poderá possuir vários acessórios e um acessório 
 * poderá ser associado a vários veículos.
 * 
 */

//@Entity
@Table(name = "accessory")
public class Accessory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(length = 60, nullable = false)
	private String description;
	
	@ManyToMany(mappedBy = "accessory")
	private Set<Vehicle> vehicle = new HashSet<>();

	public Accessory() {}
	
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
