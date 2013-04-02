package iaws.covoiturage.domain.nomenclature;

public class Adresse {

	private int numero;
	private String rue;
	private String ville;
	
	/**
	 * @param numero
	 * @param rue
	 * @param ville
	 */
	public Adresse(int numero, String rue, String ville) {
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
	}

	/**
	 * @return numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @return rue
	 */
	public String getRue() {
		return rue;
	}
	
	/**
	 * @return ville
	 */
	public String getVille() {
		return ville;
	}
}