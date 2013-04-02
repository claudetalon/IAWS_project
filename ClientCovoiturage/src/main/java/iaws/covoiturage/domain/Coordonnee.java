package iaws.covoiturage.domain;

public class Coordonnee {
	
	private double longitude;
	private double latitude;
	
	/**
	 * @param longitude
	 * @param latitude
	 */
	public Coordonnee(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * @return longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
