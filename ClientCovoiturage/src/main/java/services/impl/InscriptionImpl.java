package services.impl;

import java.util.List;

import iaws.covoiturage.domain.Coordonnee;
import iaws.covoiturage.domain.Utilisateur;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Prenom;
import services.InscriptionService;

public class InscriptionImpl implements InscriptionService {

	List<Utilisateur> users;
	
	public String inscrire(Nom myName, Prenom myFirstName, Mail myMail,
			Adresse myAdress, Coordonnee myCoordonnee) {
		
		String s = null;
		Utilisateur user = new Utilisateur(myFirstName, myName, myMail, myAdress, myCoordonnee);
		
		boolean b = false;
		
		for (int i = 0; i < users.size(); i++) {
			switch (user.getCode(users.get(i))) {
			case 100: // e-mail utilisé
				s = "NOK : 100";
				break;
			case 110: // e-mail invalide
				s = "NOK : 110";
				break;
			case 200: // Open Street Map
				s = "NOK : 200";
				break;
			}
			
			if (s != null) {
				b = true;
				i = users.size() -1;
			}
		}
		
		if (b) {
			return s;
		}
		
		users.add(user);
		return "OK";
	}

	public Coordonnee getLatitudeAndLongitude(String adresse) {
		// TODO Write method
		return null;
	}
}
