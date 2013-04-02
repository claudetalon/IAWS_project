package services;

import iaws.covoiturage.domain.Coordonnee;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Prenom;

public interface InscriptionService {

	String inscrire(int id, Nom myName, Prenom myFirstName, Mail myMail,
			Adresse myAdress, Coordonnee myCoordonnee);
	
	Coordonnee getLatitudeAndLongitude(int numero, String rue, String ville);
}
