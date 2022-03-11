package algaworksEbook.acessMode.propertyAccess;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/* Para usar o acesso pelas propriedades da entidade
 * (property access), devemos fazer o mapeamento nos 
 * métodos getters.
 * 
 * Neste caso, é obrigatório que existam os métodos getters 
 * e setters, pois o acesso aos atributos é feito por eles.
 * 
 */
//@Entity
@Table(name = "task")
public class Task {
	
	private Long code;
	private String description;
	private LocalDateTime deadline;

	
	@Id
	@GeneratedValue
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}
	@Column(length = 100, nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(nullable = false)
	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

}
