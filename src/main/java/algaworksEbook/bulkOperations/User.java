package algaworksEbook.bulkOperations;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*Quando precisamos atualizar ou remover centenas ou milhares de 
 * registros do banco de dados,pode se tornar inviavel fazer isso
 * por objeto.
 * Neste caso podemos usar bulk operations ou operações em lote.
 * Jpa suporta esse tipo de operação através da JPQL.
 */

//@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	private String email;
	
	private boolean active;
	

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
