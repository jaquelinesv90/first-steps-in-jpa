package algaworksEbook.optimisticLock;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/* Uma das formas de resolver o problema de concorrencia é usando locking
 * otimista.
 * Este tipo de locking tem como filosofia que, dificilmente outro EntityManager
 * estará fazendo uma alteração no mesmo objeto ao mesmo tempo, ou seja, é uma
 * estrategia otimista que entende que o problema de concorrencia é uma exceção.
 * 
 * No momento que uma alteração for sincronizada com o banco de dados, o prov-
 * vedor JPA verifica se o objeto foi alterado por outra transação e lança uma
 * exceção OptimisticLoclException, caso exista concorrencia.
 * 
 * Mas como o provedor JPA sabe se houve uma alteração no objeto?
 * 
 * A resposta é que o provedor mantém um controle de versão em um atributo da 
 * entidade. Precisamos mapear uma propriedade para armazenar a versão da enti-
 * dade, usando a anotação @Version.
 * 
 * Esse campo deve ser adicionado na coluna de banco de dados, e não será setado
 * na mão.Cada vez que um novo registro for inserido o Hibernate incrementará o
 * valor da coluna em 1.
 * 
 */

//@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	private String email;
	
	private boolean active;
	
	@Version
	private Long versao;

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
