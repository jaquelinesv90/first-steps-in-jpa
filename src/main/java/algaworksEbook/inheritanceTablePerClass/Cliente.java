package algaworksEbook.inheritanceTablePerClass;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Table;

//@Entity
@Table(name = "employee")
public class Cliente extends Person {
	
	@Column(name = "credit_limit", nullable = true)
	private BigDecimal creditLimit;
	
	@Column(name = "monthly_income", nullable = true)
	private BigDecimal monthlyIncome;
	
	@Column(nullable = true)
	private boolean blocked;

	
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}
