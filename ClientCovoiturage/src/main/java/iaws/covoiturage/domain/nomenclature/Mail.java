package iaws.covoiturage.domain.nomenclature;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {

	String mail;

	/** Regex pour le mail */
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@univ-tlse3.fr$",
		    		Pattern.CASE_INSENSITIVE);

	public Mail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return true si le pattern du mail est correct, false sinon
	 */
	public boolean isMail() {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return matcher.find();
	}
	
	public static void main(String[] args) {
		Mail m = new Mail("loic.martinez@univ-tlse3.fr");
		System.out.println(m.isMail());
	}
}