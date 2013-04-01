package iaws.covoiturage.domain.nomenclature;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {

	String mail;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@univ-tlse3.fr$",
		    		Pattern.CASE_INSENSITIVE);

	public Mail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isMail() {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(mail);
        return matcher.find();
	}
}