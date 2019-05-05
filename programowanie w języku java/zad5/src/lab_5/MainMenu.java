package lab_5;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
	ArrayList<Worker> arrayListWorkers = new ArrayList<Worker>();
	ArrayList<Address> arrayListAddresses =  new ArrayList<Address>();
	ArrayList<PersonalData> arrayListPersonalData = new ArrayList<PersonalData>();
	ManageWorker options;

	public void menu() {
		Scanner scan = new Scanner(System.in);
		int off = 1;
		int choose;
		while (off != 0) {
			System.out.println("\n1. Wyswietl dane pracownikow");
			System.out.println("2. Dodaj pracownika");
			System.out.println("3. Modyfikuj dane pracownikow");
			System.out.println("0. Zakoncz");
			System.out.print("Wybierz => ");
			choose = scan.nextInt();
			switch (choose) {
			case 1:

				break;
			case 2:
				options = new ManageWorker();
				options.createWorker();
				break;
			case 3:

				break;
			case 0:
				off = 0;
				break;

			default:
				System.out.println("Wybierz dostepna opjce");
				choose = scan.nextInt();
			}
		}

	}

	public ArrayList<Address> getArrayListAddresses() {
		return arrayListAddresses;
	}

	public void setArrayListAddresses(ArrayList<Address> arrayListAddresses) {
		this.arrayListAddresses = arrayListAddresses;
	}

	public ArrayList<Worker> getArrayListWorkers() {
		return arrayListWorkers;
	}

	public void setArrayListWorkers(ArrayList<Worker> arrayListWorkers) {
		this.arrayListWorkers = arrayListWorkers;
	}

	public ArrayList<PersonalData> getArrayListPersonalData() {
		return arrayListPersonalData;
	}

	public void setArrayListPersonalData(ArrayList<PersonalData> arrayListPersonalData) {
		this.arrayListPersonalData = arrayListPersonalData;
	}

}
