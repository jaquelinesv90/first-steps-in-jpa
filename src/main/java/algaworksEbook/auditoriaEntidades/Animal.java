package algaworksEbook.auditoriaEntidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

/*A anotação @EntityListerners informa as classes que escutarão
 * os eventos da entidade.
 */

//@Entity
@Table(name ="animal")
@EntityListeners(AuditorAnimal.class)
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(length = 60, nullable = false)
	private String name;
	
	@Column(name = "date_birthday", nullable = false)
	private LocalDate dateBirthday;
	
	@Column(name = "date_last_birthday", nullable = false)
	private LocalDateTime dateLastUpdate;
	
	//propriedade não persistida
	//essa idade será calculada automaticamente nos eventos @PostLoad,@PostPersist
	//@PostUpdate, através do método calcAge.
	@Transient  
	private Integer age;
	
	@PostLoad  //
	@PostPersist
	@PostUpdate
	public void calcAge() {
		long years = ChronoUnit.YEARS
				.between(this.dateBirthday, LocalDate.now());
		this.age = (int) years;
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
	
	public LocalDate getDateBirthday() {
		return dateBirthday;
	}
	
	public void setDateBirthday(LocalDate dateBirthday) {
		this.dateBirthday = dateBirthday;
	}

	public LocalDateTime getDateLastUpdate() {
		return dateLastUpdate;
	}

	public void setDateLastUpdate(LocalDateTime dateLastUpdate) {
		this.dateLastUpdate = dateLastUpdate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}	
}