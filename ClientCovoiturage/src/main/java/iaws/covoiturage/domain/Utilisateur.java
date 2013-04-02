package iaws.covoiturage.domain;

import services.InscriptionService;
import services.impl.InscriptionImpl;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Prenom;

public class Utilisateur {

	private Prenom prenom;
	private Nom nom;
	private Mail mail;
	private Adresse adresse;
	private Coordonnee c;
	
	/**
	 * @param prenom
	 * @param nom
	 * @param mail
	 * @param adresse
	 * @param c
	 */
	public Utilisateur(Prenom prenom, Nom nom, Mail mail,
			Adresse adresse) {
		this.prenom = prenom;
		this.nom = nom;
		this.mail = mail;
		this.adresse = adresse;
		InscriptionService i = new InscriptionImpl();
		c = i.getLatitudeAndLongitude(adresse.getNumero(), adresse.getRue(), adresse.getVille());
	}
	
	/**
	 * @return adresse
	 */
	public Adresse getAdr() {
		return adresse;
	}
	
	public void setAdr(Adresse adr) {
		this.adresse = adr;
	}
	
	/**
	 * @return mail
	 */
	public Mail getUserMail() {
		return mail;
	}
	
	/**
	 * @return coordonnee
	 */
	public Coordonnee getCoordonnee() {
		return c;
	}
	
	public Nom getNom() {
		return nom;
	}
	
	public Prenom getPrenom() {
		return prenom;
	}
}
