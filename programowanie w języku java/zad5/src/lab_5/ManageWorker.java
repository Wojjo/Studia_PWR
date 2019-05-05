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
		mainMenu = new MainMenu();
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		System.out.print("Wprowadz ID pracownika: ");
		workerID = scan.nextInt();
		System.out.print("Wprowadz pensje pracownika: ");
		salary = scan.nextInt();
		System.out.print("Wprowadz imie pracownika: ");
		firstName = scan2.nextLine();
		System.out.print("Wprowadz nazwisko pracownika: ");
		lastName = scan2.nextLine();
		System.out.print("Wprowadz miasto: ");
		cityName = scan2.nextLine();
		System.out.print("Wprowadz ulice: ");
		street = scan2.nextLine();
		System.out.print("Wprowadz numer domu: ");
		houseNumber = scan.nextInt();
		System.out.print("Wprowadz numer mieszkania: ");
		flatNumber = scan.nextInt();
		personID = workerID;
		addressID = workerID;

		oracle.db_connect();
		oracle.db_createAddress(addressID, cityName, street, houseNumber, flatNumber);
		oracle.db_createPersonalData(personID, firstName, lastName, addressID);
		oracle.db_createWorker(workerID, salary, personID);
		oracle.db_disconnect();

		mainMenu.getArrayListAddresses().add(new Address(addressID, cityName, street, houseNumber, flatNumber));
		mainMenu.getArrayListPersonalData().add(new PersonalData(personID, firstName, lastName, addressID));
		mainMenu.getArrayListWorkers().add(new Worker(workerID, salary, personID));

	}

}
