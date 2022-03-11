package algaworksEbook.inheritanceSingleTable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/* @DiscriminatorValue para definir o valor discriminador de cada tipo
 * só pode ser utilizado em classes concretas(classes da qual podemos 
 * criar instancia, usando a palavra chave new)
 * ("E") Identifica que a classe Employee será identificada com o 
 * atributo "E" na tabela Employee, ou seja onde constar "E" é porque
 * aquele registro é um employee
 */

@Entity
@DiscriminatorValue("E")
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
