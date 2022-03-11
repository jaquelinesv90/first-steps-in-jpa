package algaworksEbook.inheritanceJoined;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/* 
 *  Adicionado a anotação @PrimaryKeyJoinColumn para informar o nome
 * da coluna que faz referencia a tabela pai, ou seja o identificador
 * Person.Se o nome dessa coluna for igual ao nome da coluna da tabela 
 * pai, essa anotação não precisa ser utilizada.
 * 
 * A persistencia da classe fica da mesma forma,não muda
 */
//@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "code_person")
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
