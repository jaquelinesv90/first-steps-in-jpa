package algaworksEbook.inheritanceJoined;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/*
 * Adicionado a anotação @PrimaryKeyJoinColumn para informar o nome
 * da coluna que faz referencia a tabela pai, ou seja o identificador
 * Person.Se o nome dessa coluna for igual ao nome da coluna da tabela 
 * pai, essa anotação não precisa ser utilizada.
 * 
 * A persistencia da classe fica da mesma forma,não muda
 * 
 */

//@Entity
@Table(name= "employee")
@PrimaryKeyJoinColumn(name="code_person")
public class Employee extends Person{
	
	@Column(nullable = true)
	private BigDecimal salary;
	
	@Column(length = 60, nullable = true)
	private String role;
	
	

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
