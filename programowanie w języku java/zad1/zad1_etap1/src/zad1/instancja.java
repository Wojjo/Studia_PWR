package zad1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Instancja {
	static ArrayList<Przedmiot> itemList;
	private static int numItems = 0;
	private static int maxWeight;

	public void readfile(String filename) throws FileNotFoundException {
		FileInputStream in = new FileInputStream(new File(filename));

		Scanner sc = new Scanner(in);
		itemList = new ArrayList<Przedmiot>();
		numItems = sc.nextInt();

		for (int i = 0; i < numItems; i++) {
			itemList.add(new Przedmiot(sc.nextInt(), sc.nextFloat(), sc.nextInt()));
		}
		maxWeight = sc.nextInt();

	}

	public static void enterData(float value, int weight) {

		numItems += 1;
		itemList.add(new Przedmiot(numItems, value, weight));

	}
	
	public void setmaxWeight()
	{
		
	}

	public void solveKnapsackProblem(String option) {

		if (option.equals("Brute Force")) {
			Brute_force bf = new Brute_force(maxWeight, numItems);
			bf.startAlgorithm();

		} else if (option.equals("Greedy")) {
			Greedy gr = new Greedy(maxWeight, numItems);
			gr.startAlgorithm();
		}
	}

	

}
