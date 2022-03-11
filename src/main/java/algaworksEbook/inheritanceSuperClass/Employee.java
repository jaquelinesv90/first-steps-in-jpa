package algaworksEbook.inheritanceSuperClass;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Table;

/* 
 * 
 */

//@Entity
@Table(name = "employee")
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
