package covoiturage;

import iaws.covoiturage.domain.nomenclature.Prenom;
import iaws.covoiturage.domain.nomenclature.Nom;
import iaws.covoiturage.domain.nomenclature.Adresse;
import iaws.covoiturage.domain.nomenclature.Mail;
import iaws.covoiturage.services.InscriptionService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.w3c.dom.Element;

@Endpoint
public class InscriptionEndpoint {

	private InscriptionService inscriptionService;

	private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

	public InscriptionEndpoint(InscriptionService inscriptionService) {
		this.inscriptionService = inscriptionService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "InscriptionRequest")
	@ResponsePayload
	public Element handleInscriptionRequest(@XPathParam("/Inscription/pers/Nom") String nom,
			@XPathParam("/Inscription/pers/prenom") String prenom,
			@XPathParam("/Inscription/pers/adresse") String adresse,
			@XPathParam("/Inscription/pers/mail") String mail) throws Exception {

		System.out.println("lName: " + nom + "||");
		System.out.println("fName: " + prenom + "||");
		System.out.println("Perso: " + adresse + "||");
		System.out.println("domain: " + mail + "||");

		Nom myName = new Nom(nom);
		Prenom myFirstName = new Prenom(prenom);
		Mail myMail = new Mail(mail);
		Adresse myAddress = new Adresse(adresse);

		Element resp = inscriptionService.postUser(myName, myFirstName, myMail, myAddress);

		return resp;
	}

}