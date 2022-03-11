package algaworksEbook.inheritanceSingleTable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/* 		A estratégia de heraça definida foi SINGLE_TABLE com a
 * anotação @Inheritance.Esse tipo de herança é padrão,ou seja,
 * não precisaríamos anotar a classe com @Inheritance.
 * 		A anotação @DiscriminatorColumn foi usada para informar o 
 * nome de coluna de controle para discriminar de qual classe 
 * é o registro.
 */

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
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
