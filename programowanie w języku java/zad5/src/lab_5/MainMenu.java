package lab_5;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
	static ArrayList<Worker> arrayListWorkers;
	static ArrayList<Address> arrayListAddresses;
	static ArrayList<PersonalData> arrayListPersonalData;
	ManageWorker options;
	
	public MainMenu()
	{
		initVariables();
	}
	
	private void initVariables() {
		
		Connect oracle = new Connect();
		oracle.db_connect();
		arrayListWorkers = oracle.db_loadData_workers();
		arrayListAddresses =  oracle.db_loadData_addresses();
		arrayListPersonalData = oracle.db_loadData_personalData();
		oracle.db_disconnect();
	}
	
	public void menu() {
		options = new ManageWorker();
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
				showData();
				break;
			case 2:
				options.createWorker();
				break;
			case 3:
				options.updateWorker();
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
	
	
	public void showData()
	{	
		for(int i=0; i < arrayListWorkers.size(); i++)
		{
			System.out.println(arrayListWorkers);
			System.out.println(arrayListAddresses);
			System.out.println(arrayListPersonalData);
		}
	}

	public static ArrayList<Address> getArrayListAddresses() {
		return arrayListAddresses;
	}

	public void setArrayListAddresses(ArrayList<Address> arrayListAddresses) {
		this.arrayListAddresses = arrayListAddresses;
	}

	public static ArrayList<Worker> getArrayListWorkers() {
		return arrayListWorkers;
	}

	public void setArrayListWorkers(ArrayList<Worker> arrayListWorkers) {
		this.arrayListWorkers = arrayListWorkers;
	}

	public static ArrayList<PersonalData> getArrayListPersonalData() {
		return arrayListPersonalData;
	}

	public void setArrayListPersonalData(ArrayList<PersonalData> arrayListPersonalData) {
		this.arrayListPersonalData = arrayListPersonalData;
	}

}
