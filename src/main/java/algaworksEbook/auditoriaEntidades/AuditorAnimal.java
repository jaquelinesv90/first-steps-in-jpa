package algaworksEbook.auditoriaEntidades;

import java.time.LocalDateTime;

/* Você pode definir uma classe de auditoria de entidade, ao invés de
 * criar métodos de callback diretamente dentro da entidade.
 * 
 * Criamos um evento para alterar a data de última atualização do animal
 * automaticamente, antes da persistencia e atualização, para que não haja
 * necessidade de informar isso no código toda vez.
 * 
 */
public class AuditorAnimal {
	
	public void alterarDataUltimaAtualizacao() {
		Animal animal = new Animal();
		animal.setDateLastUpdate(LocalDateTime.now());
	}

}
