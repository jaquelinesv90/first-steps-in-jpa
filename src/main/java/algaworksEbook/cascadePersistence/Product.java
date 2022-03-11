package algaworksEbook.cascadePersistence;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * 
 * 
 */

//@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(length = 60)
	private String name;
	
	/*
	 * Quando persistimos um produto, a categoria será persistida também 
	 * automaticamente
	 * 
	 * As operações do EntityManager são identificadas pela enumeração CascadeType
	 * com as constantes PERSIST,REFRESH,REMOVE,MERGE E DETACH
	 * A constante ALL é um atalho para declarar que todas as operações devem ser 
	 * em cascata.
	 * 
	 */
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	private Category category;

	
	
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
