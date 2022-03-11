package algaworksEbook.inheritanceTablePerClass;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/* essa estratégia é ter tabelas apenas para classes 
 * concretas(subclasses).Cada tabela deve possuir todas 
 * as colunas, incluindo as da superclasse.
 * 
 * mudamos a estratégia de geração de identificadores 
 *  "increment", que a implementação do Hibernate disponibiliza(
 *  não é padronizada pelo JPA).Não podemos usar a geração 
 *  automática de chaves nativa do banco de dados.
 *  
 *  Também não precisamos mais da anotação @PrimaryKeyJoinColumn.
 *  Pode removê-la das entidades Cliente e Funcionario.
 * 		
 */

//@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
	
	@Id
	@GeneratedValue(generator = "inc")
	@GenericGenerator(name = "inc", strategy = "increment")
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
