package iaws.covoiturage.domain;

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
	
	public Utilisateur(Prenom prenom, Nom nom, Mail mail, Adresse adresse, Coordonnee c) {
		this.prenom = prenom;
		this.nom = nom;
		this.mail = mail;
		this.adresse = adresse;
		this.c = c;
	}
	
	public Adresse getAdr() {
		return adresse;
	}
	
	public void setAdr(Adresse adr) {
		this.adresse = adr;
	}
	
	public Mail getUserMail() {
		return mail;
	}
	
	public Coordonnee getCoordonnee() {
		return c;
	}
	
	public String getCode(Utilisateur u) {
		
		// E-mail déjà utilise
		if (u.getUserMail().getMail().equals(mail.getMail())) {
			return "KO 100";
		} else if (u.getUserMail().isMail()) { // E-mail non valide
			return "KO 110";
		}
		
		return "OK";
	}
}
