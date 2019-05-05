package lab_5;

public class PersonalData {
	private int personID;
	private String firstName;
	private String lastName;
	private int addressID;

	public PersonalData() {
		super();
	}

	public PersonalData(int personID, String firstName, String lastName, int addressID) {
		super();
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressID = addressID;

	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	@Override
	public String toString() {
		return personID + " !&#*&% " + firstName + " !&#*&% " + lastName + " !&#*&% " + " !&#*&% " + " !&#*&% "
				+ addressID + " !&#*&% ";
	}

}
