package lab_5;

import java.util.Scanner;

public class ManageWorker {

	Connect oracle = new Connect();
	private MainMenu mainMenu;
	private int workerID;
	private int salary;
	private int personID;

	private String firstName;
	private String lastName;
	private int addressID;

	private String cityName;
	private String street;
	private int houseNumber;
	private int flatNumber;

	public void createWorker() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Wprowadz ID pracownika: ");
		workerID = scan.nextInt();
		System.out.println("Wprowadz pensje pracownika: ");
		salary = scan.nextInt();
		personID = workerID;
		System.out.println("Wprowadz imie pracownika: ");
		firstName = scan.toString();
		System.out.println("Wprowadz nazwisko pracownika: ");
		lastName = scan.toString();
		System.out.println("Wprowadz ID adresu: ");
		salary = scan.nextInt();
		System.out.println("Wprowadz miasto: ");
		cityName = scan.toString();
		System.out.println("Wprowadz ulice: ");
		street = scan.toString();
		System.out.println("Wprowadz numer domu: ");
		houseNumber = scan.nextInt();
		System.out.println("Wprowadz numer mieszkania: ");
		flatNumber = scan.nextInt();

		oracle.db_connect();
		oracle.db_createAddress(addressID, cityName, street, houseNumber, flatNumber);
		oracle.db_createPersonalData(personID, firstName, lastName, addressID);
		oracle.db_createAccount(workerID, salary, personID);
		oracle.db_disconnect();

		mainMenu.getArrayListAddresses().add(new Address(addressID, cityName, street, houseNumber, flatNumber));
		mainMenu.getArrayListPersonalData().add(new PersonalData(personID, firstName, lastName, addressID));
		mainMenu.getArrayListWorkers().add(new Worker(workerID, salary, personID));

	}

}
