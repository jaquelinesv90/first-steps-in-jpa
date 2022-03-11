package algaworksEbook.inheritanceSuperClass;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/* Pode ser útil em algumas situações compartilhar 
 * propriedades através de uma superclasse, sem 
 * considerá-la como uma entidade mapeada.
 * 
 * As subclasses são mapeadas normalmente,sem nada
 * especial.Continuam somente com @Entity e @Table
 * 
 * Com esse tipo de mapeamento não é uma estratégia de
 * herança da JPA, não conseguimos fazer uma consulta 
 * polimórfica.
 *
 */

@MappedSuperclass
public abstract class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(length = 100, nullable = false)
	private String name;

	
	public Person() {}
	
	public Person(Long code,String name) {
		this.code = code;
		this.name = name;
	}
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
