package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database;

import java.util.Date;

public class Client extends Person{
	private int clientID;
	private Date registrationDate;
	private int orderQuantity;
	private Date lastOrderDate;
	private boolean suspendedAccount;
	private int personID;
	
	public Client() {
		super();
	}
	
	public Client(int clientID, Date registrationDate, int orderQuantity, Date lastOrderDate, boolean suspendedAccount,
			int personID) {
		super();
		this.clientID = clientID;
		this.registrationDate = registrationDate;
		this.orderQuantity = orderQuantity;
		this.lastOrderDate = lastOrderDate;
		this.suspendedAccount = suspendedAccount;
		this.personID = personID;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Date getLastOrderDate() {
		return lastOrderDate;
	}

	public void setLastOrderDate(Date lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
	}

	public boolean isSuspendedAccount() {
		return suspendedAccount;
	}

	public void setSuspendedAccount(boolean suspendedAccount) {
		this.suspendedAccount = suspendedAccount;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	@Override
	public String toString() {
		return "Client [clientID=" + clientID + ", registrationDate=" + registrationDate + ", orderQuantity="
				+ orderQuantity + ", lastOrderDate=" + lastOrderDate + ", suspendedAccount=" + suspendedAccount
				+ ", personID=" + personID + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getBirthDate()=" + getBirthDate() + ", getAddressID()=" + getAddressID() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
}
