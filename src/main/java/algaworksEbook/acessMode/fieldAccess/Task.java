package algaworksEbook.acessMode.fieldAccess;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "task")
public class Task {
	
	/*
	 * Quando a anotação @Id é colocada no atributo, fica
	 * automaticamente definido que o modo de acesso é
	 * pelos atributos(field access)
	 */
	
	@Id
	@GeneratedValue
	private Long code;
	
	private String description;
	
	private LocalDateTime deadline;

	
	
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

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
}
