package algaworksEbook.entity.ManyToMany_unidirectional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* classe que representa todos os acessórios que 
 * um carro pode ter.
 * Um veículo poderá possuir vários acessórios e um acessório 
 * poderá ser associado a vários veículos.
 * 
 */

@Entity
@Table(name = "accessory")
public class Accessory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(length = 60, nullable = false)
	private String description;

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
