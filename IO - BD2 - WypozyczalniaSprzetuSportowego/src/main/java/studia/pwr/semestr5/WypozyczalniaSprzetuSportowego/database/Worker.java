package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database;

import java.util.Date;

public class Worker extends Person{
	private int workerID;
	private Date hireDate;
	private int salary;
	private int servedClientsAmount;
	private int personID;
	private boolean isAdmin;
	
	public Worker() {
		super();
	}
	
	public Worker(int workerID, Date hireDate, int salary, int servedClientsAmount, int personID, boolean isAdmin) {
		super();
		this.workerID = workerID;
		this.hireDate = hireDate;
		this.salary = salary;
		this.servedClientsAmount = servedClientsAmount;
		this.personID = personID;
		this.isAdmin = isAdmin;
	}

	public int getWorkerID() {
		return workerID;
	}

	public void setWorkerID(int workerID) {
		this.workerID = workerID;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getServedClientsAmount() {
		return servedClientsAmount;
	}

	public void setServedClientsAmount(int servedClientsAmount) {
		this.servedClientsAmount = servedClientsAmount;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Worker [workerID=" + workerID + ", hireDate=" + hireDate + ", salary=" + salary
				+ ", servedClientsAmount=" + servedClientsAmount + ", personID=" + personID + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getBirthDate()=" + getBirthDate()
				+ ", getAddressID()=" + getAddressID() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}


	
	
	
}
