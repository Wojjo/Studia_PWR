package lab_5;

public class Worker extends PersonalData {
	private int workerID;
	private int salary;
	private int personID;

	public Worker() {
		super();
	}

	public Worker(int workerID, int salary, int personID) {
		super();
		this.workerID = workerID;
		this.salary = salary;
		this.personID = personID;
	}

	public int getWorkerID() {
		return workerID;
	}

	public void setWorkerID(int workerID) {
		this.workerID = workerID;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	@Override
	public String toString() {
		return "Worker [workerID=" + workerID + ", salary=" + salary + ", personID=" + personID + "]";
	}

}
