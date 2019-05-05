package lab_5;

public class Address {
	private int addressID;
	private String cityName;
	private String street;
	private int houseNumber;
	private int flatNumber;
	
	public Address() {
		super();
	}

	public Address(int addressID, String cityName, String street, int houseNumber,
			int flatNumber) {
		super();
		this.addressID = addressID;
		this.cityName = cityName;
		this.street = street;
		this.houseNumber = houseNumber;
		this.flatNumber = flatNumber;
	}

	public int getAdressID() {
		return addressID;
	}

	public void setAdressID(int adressID) {
		this.addressID = adressID;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}

	@Override
	public String toString() {
		return addressID + ". !&#*&% " + cityName + ". !&#*&% "
				+ street + ". !&#*&% " + houseNumber + ". !&#*&% " + flatNumber + ".";
	}
	
	
}
