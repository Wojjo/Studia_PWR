package lab_5;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
	static ArrayList<Worker> arrayListWorkers;
	static ArrayList<Address> arrayListAddresses;
	static ArrayList<PersonalData> arrayListPersonalData;
	static ManageWorker options;

	public static void menu() {
		arrayListWorkers = new ArrayList<Worker>();
		arrayListAddresses = new ArrayList<Address>();
		arrayListPersonalData = new ArrayList<PersonalData>();
		System.out.println("1. Wyswietl dane pracownikow");
		System.out.println("2. Dodaj pracownika");
		System.out.println("3. Modyfikuj dane pracownikow");
		System.out.println("0. Zakoncz");
		System.out.print("Wybierz => ");
		Scanner scan = new Scanner(System.in);
		int off = 1;
		int choose;
		while (off != 0) {
			choose = scan.nextInt();
			switch (choose) {
			case 1:

				break;
			case 2:
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
