package services;

import java.util.Map;

import iaws.covoiturage.domain.Coordonnee;
import iaws.covoiturage.domain.Utilisateur;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Prenom;

public interface InscriptionService {

	/**
	 * Permet à un utilisateur de s'inscrire pour le covoiturage
	 * @param id
	 * @param myName
	 * @param myFirstName
	 * @param myMail
	 * @param myAdress
	 * @return le code de validation ou d'erreur
	 */
	String inscrire(Nom myName, Prenom myFirstName, Mail myMail,
			Adresse myAdress);
	
	/**
	 * Permet d'avoir la latitude et la longitude d'une adresse
	 * @param numero
	 * @param rue
	 * @param ville
	 * @return la coordonnée représentant ce lieu à partir d'open street map
	 */
	Coordonnee getLatitudeAndLongitude(int numero, String rue, String ville);
	
	public Map<Integer, Utilisateur> getUsers();
}
