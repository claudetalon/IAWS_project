package services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
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

	/** Map contenant les utilisateurs inscrits */
	Map<Integer, Utilisateur> users = new HashMap<Integer, Utilisateur>();
	private static int id = 0;
	
	/**
	 * Permet à un utilisateur de s'inscrire pour le covoiturage
	 * @param id
	 * @param myName
	 * @param myFirstName
	 * @param myMail
	 * @param myAdress
	 * @param myCoordonnee
	 * @return le code de validation ou d'erreur
	 */
	public String inscrire(Nom myName, Prenom myFirstName, Mail myMail,
			Adresse myAdress) {
		
		String s = null;
		Utilisateur user = new Utilisateur(myFirstName, myName, myMail,
				myAdress);
		
		if (! user.getUserMail().isMail()) {
			return "KO 110";
		} else if (user.getCoordonnee() == null) {
			return "KO 200";
		}
		
		boolean b = false;
		
		for (Iterator<Entry<Integer, Utilisateur>> iterator = users.entrySet()
				.iterator(); iterator.hasNext() && !b;) {
			
			Entry<Integer, Utilisateur> e = iterator.next();
			
			if (user.getUserMail().getMail().equals
					(e.getValue().getUserMail().getMail())) {
				s = "KO 100";
			}
			
			if (s != null) {
				b = true;
			}
		}
		
		if (b) {
			return s;
		}
		
		users.put(id, user);
		id++;
		
		return "Ok";
	}

	/**
	 * Permet d'avoir la latitude et la longitude d'une adresse
	 * @param numero
	 * @param rue
	 * @param ville
	 * @return la coordonnée représentant ce lieu à partir d'open street map
	 *
	 * Adresse : Numéro facultatif, rue ou avenue etc, ville
	 * http://nominatim.openstreetmap.org/search/fr/toulouse/Avenue%20de%20l'urss/17?format=xml&polygon=1&addressdetails=1
	 */
	public Coordonnee getLatitudeAndLongitude(int numero, String rue, String ville) {
		
		String requete = "http://nominatim.openstreetmap.org/search/fr/";
		requete += ville;
		requete += "/" + rue + "/" + numero + "?format=xml&polygon=1&addressdetails=1";
		requete = requete.replaceAll("\\s", "%20");
		

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
	
	public Map<Integer, Utilisateur> getUsers() {
		return users;
	}
	
	// Example
	public static void main(String[] args) {
		InscriptionService i = new InscriptionImpl();
		Coordonnee c = i.getLatitudeAndLongitude(17, "avenue de l'urss", "toulouse");
		if (c != null)
			System.out.println(c.getLatitude() + " " + c.getLongitude());
	}
}
