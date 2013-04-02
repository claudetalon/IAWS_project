package iaws.covoiturage.domain;

import services.InscriptionService;
import services.impl.InscriptionImpl;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Prenom;

public class Utilisateur {

	private int id;
	private Prenom prenom;
	private Nom nom;
	private Mail mail;
	private Adresse adresse;
	private Coordonnee c;
	
	/**
	 * @param id
	 * @param prenom
	 * @param nom
	 * @param mail
	 * @param adresse
	 * @param c
	 */
	public Utilisateur(int id, Prenom prenom, Nom nom, Mail mail,
			Adresse adresse) {
		this.id = id;
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
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return coordonnee
	 */
	public Coordonnee getCoordonnee() {
		return c;
	}
	
	/**
	 * TODO
	 * @param u
	 * @return
	 */
	public Utilisateur getUser() {
		/*
		// E-mail déjà utilise
		if (u.getUserMail().getMail().equals(mail.getMail())) {
			return 100; // KO
		} else if (u.getUserMail().isMail()) { // E-mail non valide
			return 110; // KO
		} else if (u.getCoordonnee() == null) {
			return 200; // KO
		}
		*/
		return this; // OK
	}
}
