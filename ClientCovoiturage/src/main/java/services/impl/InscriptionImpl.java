package services.impl;

import iaws.covoiturage.domain.Coordonnee;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Prenom;
import services.InscriptionService;

public class InscriptionImpl implements InscriptionService {

	public String inscrire(Nom myName, Prenom myFirstName, Mail myMail,
			Adresse myAddress) {
		// TODO Write method
		return null;
	}

	public Coordonnee getLatitudeAndLongitude(String adresse) {
		// TODO Write method
		return null;
	}
}
