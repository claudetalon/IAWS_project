package iaws.covoiturage.domain.nomenclature;

public class Adresse {

	private int numero;
	private String rue;
	private String ville;
	
	public Adresse(int numero, String rue, String ville) {
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
	}

	public int getNumero() {
		return numero;
	}

	public String getRue() {
		return rue;
	}
	
	public String getVille() {
		return ville;
	}
}