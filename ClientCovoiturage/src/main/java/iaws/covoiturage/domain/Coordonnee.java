package iaws.covoiturage.domain;

public class Coordonnee {
	
	private Utilisateur myUser;
	private double longitude;
	private double latitude;
	
	public Coordonnee(Utilisateur myUser, double longitude, double latitude) {
		this.myUser = myUser;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Utilisateur getMyUser() {
		return myUser;
	}

	public void setMyUser(Utilisateur myUser) {
		this.myUser = myUser;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
