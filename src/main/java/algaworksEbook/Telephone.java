package algaworksEbook;

/*
 *  Classe criada para utilizar o objeto telefone embutido.
 *  
 *  Caso necessite separar o numero do telefone em 
 *  ddd+numero e armazenar o ramal, para isso
 *  criamos a classe e anotamos com @Embeddable
 * 
 */

import javax.persistence.Embeddable;

@Embeddable
public class Telephone {
	
	private String prefixo;
	
	private String number;
	
	private String ramal;
	
	public Telephone() {}
	
	public Telephone(String prefixo, String number,String ramal) {
		this.prefixo = prefixo;
		this.number = number;
		this.ramal = ramal;
	}

	public String getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
}
