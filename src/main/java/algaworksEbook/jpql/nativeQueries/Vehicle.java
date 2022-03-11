package algaworksEbook.jpql.nativeQueries;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import algaworksEbook.FuelType;
import algaworksEbook.IdVehicle;
import algaworksEbook.Owner;

//todas as anotações padronizadas pela JPA ficam 
// dentro do pacote javax.persistence.

// anotação entity diz que a classe é uma entidade JPA
// que representa uma tabela de banco de dados
// classe anotada com o @Entity não pode ser final

//@Entity
@Table(name = "tab_vehicle")
public class Vehicle{
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	*
	* o id do veículo será uma chave composta
	* Nota: Chaves primárias compostas normalmente surgem 
	* durante o mapeamento de bancos de dados legados,
	*  quando a chave do banco de dados é composta por 
	*  várias colunas.
	*/
	@EmbeddedId
	private IdVehicle code;
	
	/* A definição de precisão nos atributos são importantes se
	 você estiver gerando as tabelas com recurso de schema generation do JPA,
	 mas não é recomendado o uso em produção.
	 Caso o schema do seu banco seja gerado externamente, por algumas ferramenta 
	 de gerenciamento de migrações(ex. Flyway),ou até mesmo manualmente, você não
	 precisa especificar todos os detalhes fisícos das colunas.
	@Column( length = 60, nullable = false) */
	private String manufacturer;    
	
	@Column( length = 60, nullable = false)
	private String model;
	
	@Column( name= "year_manufacture", nullable = false)
	private Integer yearManufacture;//fabricação
	
	@Column( name= "model_year", nullable = false)
	private Integer modelYear;
	
	@Column( length = 60, nullable = false)
	private BigDecimal value;
	
	//Anotação @Enumerated, para configurar o tipo de enumeração como String
	//isso serve para que a coluna do banco armezene o nome da constante 
	// e não o número que representa a opção na enumeração.
	// Se o parametro da anotação for alterado para EnumType.ORDINAL(padrão),
	//será inserido o número que representa a opção na enumeração
	@Column(name = "fuel_type",nullable = false)
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	
	//Os atributos de data podem ser do tipo Date ou Calendar, mas nesse caso
	//preisaríamos também da anotação @Temporal para informar a precisão.
	//LocalDate é equivalente a TemporalType.DATE,LocalDateTime equivale a 
	//TemporalType.TIMESTAMP e LocalTime é TemporalType.TIME
	@Temporal(TemporalType.DATE)
	@Column(name="date_Register", nullable = false)
	private Date dateRegister;
	
	//Atributos anotados com @Transient não representam uma coluna no banco de dados.
	@Transient
	private String fullDescription;
	
	//tipo de dado que pode armazenar objetos grandes em caracteres(textos muito longos).
	@Lob
	@Column
	private String especification;
	
	//Objeto grande binário é um tipo de dado em banco que pode armazenar
	//objetos grandes em binário(incluindo executáveis,músicas,imagens)
	@Lob
	private byte[] photo;
	
	/*O relacionamento one-to-one aceita referencias nulas, por padrão.
	 podemos obrigar a atribuição do owner durante a persistencia do
	 veículo, incluindo o atributo optional com valor false na anotação
	 @OneToOne, desta forma, se tentarmos persistir um veículo sem
	 proprietário, uma exceção é lançada.
	 A associação é unidirecional, ou sejam podemos obter o proprietário
	 a partir de um veículo, mas não conseguimos obter o veículo a apartir 
	 de um proprietário - para torna-lá bidirecional é necessário criar 
	 o atributo veículo na classe - proprietario.
	 
	@OneToOne(optional = false)
	@JoinColumn(name ="cod_owner")*/
	private Owner owner;
	
	public Vehicle() {}
	
	public IdVehicle getCode() {
		return code;
	}
	public void setCode(IdVehicle code) {
		this.code = code;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getYearManufacture() {
		return yearManufacture;
	}
	public void setYearManufacture(Integer yearManufacture) {
		this.yearManufacture = yearManufacture;
	}
	public Integer getModelYear() {
		return modelYear;
	}
	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public Date getDateRegister() {
		return dateRegister;
	}
	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	public String getEspecification() {
		return especification;
	}
	public void setEspecification(String especification) {
		this.especification = especification;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	//evitar elementos duplicados
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
		Vehicle other = (Vehicle) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}	
}