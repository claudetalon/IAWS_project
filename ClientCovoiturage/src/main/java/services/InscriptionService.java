package services;

import iaws.covoiturage.domain.Coordonnee;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Prenom;

public interface InscriptionService {

	String inscrire(Nom myName, Prenom myFirstName, Mail myMail,
			Adresse myAddress);
	
	Coordonnee getLatitudeAndLongitude(String adresse);
}
