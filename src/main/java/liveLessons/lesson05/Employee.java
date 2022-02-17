package liveLessons.lesson05;

import javax.persistence.Id;
import javax.persistence.Version;

/*	É necessário incluir a anotação @Version.Ao usá-lo,
 * cada transação que lê dados contém o valor da propriedade da 
 * versão.
 * 	Antes que a transação queira fazer uma atualização, ela verifica
 * a propriedade da versão novamente.
 * 	Cada classe pode ter somente um atributo version.Deve ser colocado
 * na tabela primária para uma entidade mapeada para várias tabelas.O tipo
 * deve ser: Long,Integer,Short...

*/

//@Entity
public class Employee {
	
	@Id
	private Integer id;
	
	@Version
	private Integer version;
	
	private String name;
	
	private Integer annualLeave;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAnnualLeave() {
		return annualLeave;
	}

	public void setAnnualLeave(Integer annualLeave) {
		this.annualLeave = annualLeave;
	}
}
