package algaworksEbook.eagerLoading;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Eager loading carrega o relacionamento ansiosamente, mesmo se a informação não for
 * usada.Isso pode ser bom se, na maioria das vezes, precisarmos de alguma informação 
 * do relacionamento, mas também pode ser ruim, se na maioria das vezes as informações 
 * desse relacionamento não forem necesárias e mesmo assim consultadas por causa do 
 * eager loading.
 * Saber escolher a melhor estratégia é muito importante para uma boa performance
 * do software que está sendo desenvolvido.
 */

//@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(length = 60)
	private String name;
	
	/*
	 * a parte do relacionamento em que usamos
	 * o atributo mappedBy é a parte non-owner
	 */
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
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