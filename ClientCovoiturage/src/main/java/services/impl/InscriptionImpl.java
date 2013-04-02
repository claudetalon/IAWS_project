package services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

	/**
	 * Adresse : Numéro facultatif, rue ou avenue etc, ville
	 * http://nominatim.openstreetmap.org/search/fr/toulouse/Avenue%20de%20l'urss/17?format=xml&polygon=1&addressdetails=1
	 */
	public Coordonnee getLatitudeAndLongitude(int numero, String rue, String ville) {
		
		String requete = "http://nominatim.openstreetmap.org/search/fr/";
		requete += ville;
		requete += "/" + rue + "/" + numero + "?format=xml&polygon=1&addressdetails=1";
		requete = requete.replaceAll("\\s", "%20");
		

		// or if you prefer DOM:
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document doc = null;
		
		try {
			
			db = dbf.newDocumentBuilder();
			doc = db.parse(new URL(requete).openStream());
			
			doc.getDocumentElement ().normalize();
            Node place = doc.getElementsByTagName("place").item(0);
            
            return new Coordonnee(Double.parseDouble(place.getAttributes().getNamedItem("lon").getNodeValue()),
            Double.parseDouble(place.getAttributes().getNamedItem("lat").getNodeValue()));

		} catch (NullPointerException e) { // L'adresse est inexistante
			return null;
		} catch (Exception e) {
			System.err.println("Error while reaching xml file");
		}
		
		return null;
	}
	
	// Example
	public static void main(String[] args) {
		InscriptionService i = new InscriptionImpl();
		Coordonnee c = i.getLatitudeAndLongitude(17, "avenue de l'urss", "toulouse");
		if (c != null)
			System.out.println(c.getLatitude() + " " + c.getLongitude());
	}
}
