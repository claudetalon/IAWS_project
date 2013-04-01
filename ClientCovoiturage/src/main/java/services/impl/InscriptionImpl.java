package services.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import iaws.covoiturage.domain.Coordonnee;
import iaws.covoiturage.domain.Utilisateur;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Prenom;
import services.InscriptionService;

public class InscriptionImpl implements InscriptionService {

	Map<Integer, Utilisateur> users;
	
	public String inscrire(int id, Nom myName, Prenom myFirstName, Mail myMail,
			Adresse myAdress, Coordonnee myCoordonnee) {
		
		String s = null;
		Utilisateur user = new Utilisateur(id, myFirstName, myName, myMail,
				myAdress, myCoordonnee);
		
		boolean b = false;
		
		for (Iterator<Entry<Integer, Utilisateur>> iterator = users.entrySet()
				.iterator(); iterator.hasNext() && !b;) {
			
			Entry<Integer, Utilisateur> e = iterator.next();
			
			switch (user.codeInscription(e.getValue())) {
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
			}
		}
		
		if (b) {
			return s;
		}
		
		users.put(id, user);
		return "Ok";
	}

	public Coordonnee getLatitudeAndLongitude(String adresse) {
		// TODO Write method
		return null;
	}
}
