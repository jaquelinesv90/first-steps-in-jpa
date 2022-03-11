package algaworksEbook.secondLevelCache;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "centro_custo")
@Cacheable(true)
public class CentroCusto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

}
