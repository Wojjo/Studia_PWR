package zad1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Instancja {
	static ArrayList<Przedmiot> itemList;
	private int numItems;
	private boolean[] solution;
	private boolean[] current;
	private static int maxWeight;

	public void readfile(String filename) throws FileNotFoundException {
		FileInputStream in = new FileInputStream(new File(filename));

		Scanner sc = new Scanner(in);
		itemList = new ArrayList<Przedmiot>();
		numItems = sc.nextInt();

		solution = new boolean[numItems];
		for (int i = 0; i < numItems; i++) {
			itemList.add(new Przedmiot(sc.nextInt(), sc.nextFloat(), sc.nextInt()));
		}
		maxWeight = sc.nextInt();

	}
	public void items()
	{
		itemList = new ArrayList<Przedmiot>();
		numItems = 6;
		solution = new boolean[numItems];
		itemList.add(new Przedmiot(1, 5, 3));
		itemList.add(new Przedmiot(2, 1, 6));
		itemList.add(new Przedmiot(3, 7, 2));
		itemList.add(new Przedmiot(4, 4, 6));
		itemList.add(new Przedmiot(5, 2, 4));
		itemList.add(new Przedmiot(6, 9, 9));
		
		maxWeight = 20;
		
	}

	public void solveKnapsackProblem(String option) {

		if (option.equals("Brute Force")) {
			current = new boolean[numItems];
			Brute_force bf = new Brute_force(maxWeight, solution, current, numItems);
			bf.startAlgorithm();

		} else if (option.equals("Greedy")) {
			Greedy gr = new Greedy(maxWeight, solution, numItems);
			gr.startAlgorithm();
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Scanner scanFile = new Scanner(System.in);
		System.out.println("1 - Przedmioty zapisane w programie\n" + "2 - Wczytaj przedmioty z pliku\n");
		int choose = scan.nextInt();
		if (choose == 1) {
			Instancja problem = new Instancja();
			problem.items();
			problem.solveKnapsackProblem("Brute Force");
			problem.solveKnapsackProblem("Greedy");

		} else if (choose == 2) {

			System.out.println("Podaj nazwe pliku");
			String filename = scanFile.nextLine();
			Instancja problem = new Instancja();

			try {
				problem.readfile(filename);
				problem.solveKnapsackProblem("Brute Force");
				problem.solveKnapsackProblem("Greedy");

			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File not found.");
			}
		} else {
			System.exit(0);

		}
	}

}
