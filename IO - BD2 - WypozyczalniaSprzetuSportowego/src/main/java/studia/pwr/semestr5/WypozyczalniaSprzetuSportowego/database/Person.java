package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database;

import java.util.Date;

public class Person {
	private int personID;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private int addressID;
	private int phoneNumber;
	private String login;
	private String password;
	private String securityQuestion;
	private String securityAnswer;

	public Person() {
		super();
	}

	public Person(int personID, String firstName, String lastName, Date birthDate, int phoneNumber, int addressID, String login,
			String password, String securityQuestion, String securityAnswer) {
		super();
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.addressID = addressID;
		this.login = login;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	@Override
	public String toString() {
		return personID + " !&#*&% " + firstName + " !&#*&% " + lastName + " !&#*&% "
				+ birthDate + " !&#*&% " + phoneNumber + " !&#*&% " + addressID + " !&#*&% " + login + " !&#*&% " + password
				+ " !&#*&% " + securityQuestion + " !&#*&% " + securityAnswer;
	}

}
