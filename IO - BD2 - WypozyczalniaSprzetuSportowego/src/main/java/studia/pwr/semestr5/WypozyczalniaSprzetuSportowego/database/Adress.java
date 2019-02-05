package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database;

public class Adress {
	private int adressID;
	private String cityName;
	private String postalCode;
	private String street;
	private String houseNumber;
	private String flatNumber;
	
	public Adress() {
		super();
	}

	public Adress(int adressID, String cityName, String postalCode, String street, String houseNumber,
			String flatNumber) {
		super();
		this.adressID = adressID;
		this.cityName = cityName;
		this.postalCode = postalCode;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
	}

	public int getAdressID() {
		return adressID;
	}

	public void setAdressID(int adressID) {
		this.adressID = adressID;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	@Override
	public String toString() {
		return "Adress [adressID=" + adressID + ", cityName=" + cityName + ", postalCode=" + postalCode + ", street="
				+ street + ", houseNumber=" + houseNumber + ", flatNumber=" + flatNumber + "]";
	}
	
	
}
