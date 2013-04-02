package services.impl.test;

import static org.junit.Assert.*;
import iaws.covoiturage.domain.Utilisateur;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Prenom;

import org.junit.Test;

import services.InscriptionService;
import services.impl.InscriptionImpl;

public class InscriptionTest {
	
	// Creating users
	InscriptionService i = new InscriptionImpl();
	Utilisateur uTrue = new Utilisateur(new Prenom("monPrenom"),
			new Nom("monNom"),
			new Mail("mail@univ-tlse3.fr"),
			new Adresse(17, "Avenue de l'urss", "toulouse"));
	
	Utilisateur uMailFalse = new Utilisateur(new Prenom("monPrenom"),
			new Nom("monNom"),
			new Mail("mail@gmail.com"),
			new Adresse(17, "Avenue de l'urss", "toulouse"));
	
	Utilisateur uAddressFalse = new Utilisateur(new Prenom("monPrenom"),
			new Nom("monNom"),
			new Mail("mail@univ-tlse3.fr"),
			new Adresse(17, "fakeStreet", "fakeTown"));
	
	@Test
	public void test() {
		
		// Testing if mail is well formed
		assertTrue(uTrue.getUserMail().isMail());
		
		// Inscription of a normal user
		assertTrue((i.inscrire(uTrue.getNom(),
				uTrue.getPrenom(),
				uTrue.getUserMail(),
				uTrue.getAdr())).equals("Ok"));
		
		// Trying to add an user with the same mail
		assertTrue((i.inscrire(uTrue.getNom(),
				uTrue.getPrenom(),
				uTrue.getUserMail(),
				uTrue.getAdr())).equals("KO 100"));
		
		// Now with a not valid mail address
		// Inscription of a fake user
		assertTrue((i.inscrire(uMailFalse.getNom(),
				uMailFalse.getPrenom(),
				uMailFalse.getUserMail(),
				uMailFalse.getAdr())).equals("KO 110"));
		
		// We have only add one person
		assertTrue(i.getUsers().size() == 1);
		
		// Now, we try to add a person who has a fake address
		// So Open Street Map cannot find latitude and longitude
		// for this
		assertTrue((i.inscrire(uAddressFalse.getNom(),
				uAddressFalse.getPrenom(),
				uAddressFalse.getUserMail(),
				uAddressFalse.getAdr())).equals("KO 200"));
	}
}
