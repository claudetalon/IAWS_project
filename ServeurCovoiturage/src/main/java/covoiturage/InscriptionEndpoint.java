package covoiturage;

import iaws.covoiturage.domain.nomenclature.Prenom;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.domain.Coordonnee;
import services.InscriptionService;
import services.impl.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.jdom2.Element;

@Endpoint
public class InscriptionEndpoint {

	private InscriptionService inscriptionService;

	private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

	public InscriptionEndpoint(InscriptionService inscriptionService) {
		this.inscriptionService = inscriptionService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "InscriptionRequest")
	@ResponsePayload
	public Element handleInscriptionRequest(@XPathParam("/Inscription/personne/id") int id,
			@XPathParam("/Inscription/personne/nom") String nom,
			@XPathParam("/Inscription/personne/prenom") String prenom,
			@XPathParam("/Inscription/personne/adresse/numero") int numero,
			@XPathParam("/Inscription/personne/adresse/rue") String rue,
			@XPathParam("/Inscription/personne/adresse/ville") String ville,
			@XPathParam("/Inscription/personne/mail") String mail,
			@XPathParam("/Inscription/personne/coordonnee") Coordonnee myCoordonnee) throws Exception {

		System.out.println("lName: " + id + "||");
		System.out.println("lName: " + nom + "||");
		System.out.println("fName: " + prenom + "||");
		System.out.println("Num: " + numero + "||");
		System.out.println("Rue: " + rue + "||");
		System.out.println("Ville: " + ville + "||");
		System.out.println("domain: " + mail + "||");

		Nom myName = new Nom(nom);
		Prenom myFirstName = new Prenom(prenom);
		Mail myMail = new Mail(mail);
		Adresse myAddress = new Adresse(numero, rue, ville);


	    String resultat = inscriptionService.inscrire(id, myName, myFirstName,
	    		myMail, myAddress, myCoordonnee);

	     
	     
	     InscriptionImpl myImpl = new InscriptionImpl();
	     Coordonnee coord = myImpl.getLatitudeAndLongitude(numero, rue, ville);
	     Element elt = null;
	     if(coord != null)
	     	elt = XmlHelper.resultsXml(coord, id, nom, prenom, mail, numero, rue, ville);

	     return  elt;	
	}
}