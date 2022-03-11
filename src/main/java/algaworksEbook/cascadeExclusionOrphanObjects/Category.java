package algaworksEbook.cascadeExclusionOrphanObjects;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(length = 60)
	private String name;
	
	/*A operação de exclusão em cascata, remove a entidadde passada como
	 * parâmetro para o método remove, mas se o vínculo entre duas entidades 
	 * for desfeito sem que haja uma exclusão de entidade o objeto ficará orfão.
	 * Podemos configurar a remoção de orfãoes incluindo a propriedade
	 * orphanRemoval.
	 * caso um registro fique órfão de categoria, ele será excluído automaticamente
	 * do banco de dados.
	 * 
	 */
	@OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST,
			orphanRemoval = true)
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