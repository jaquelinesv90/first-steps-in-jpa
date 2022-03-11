package algaworksEbook.cascadePersistence;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*

 */

//@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(length = 60)
	private String name;
	
	/*configuração de cascata no relacionamento, caso o cascadeType.REMOVE
	 * é necessário para que seja excluído a categoria e o produto associado 
	 * a ela.caso também não seja adicionada o JPA tentará remover apenas a categoria,
	 * e gerará erro de violação de integridade de banco.
	 * 
	 */
	@OneToMany(mappedBy = "category", 
			cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Product> products;
	
	
	public Category() {}
	
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}