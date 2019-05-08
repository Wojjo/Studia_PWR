package KnapsackProblem;

import java.util.ArrayList;

public class Brute_force {
	private static ArrayList<Item> listOfItems;
	private static boolean[] solution;
	private static boolean[] current;
	private static float curBestValue;
	private static int curBestWeight;
	private static int curWeight;
	private static float curValue;
	static int num;

	public Brute_force(ArrayList<Item> listOfItems, int maxWeight, int numItems) {
		current = new boolean[numItems];
		solution = new boolean[numItems];
		curBestValue = 0;
		curBestWeight = 0;
		curWeight = 0;
		curValue = 0;
		num = 0;
		num = numItems;
		Brute_force.listOfItems = new ArrayList<Item>();
		for (Item i : listOfItems)
			Brute_force.listOfItems.add(i);

	}

	public static void startAlgorithm(int maxWeight, int numItems) {

		current = new boolean[numItems];
		solution = new boolean[numItems];
		curBestValue = 0;
		curBestWeight = 0;
		curWeight = 0;
		curValue = 0;
		num = 0;
		num = numItems;
		algorithm(numItems - 1, maxWeight, numItems);
	}

	public static void algorithm(int numSize, int maxWeight, int numItems) {

		if (numSize < 0) {
			curWeight = 0;
			curValue = 0;

			for (int i = 0; i < numItems; i++) {
				if (current[i]) {
					curWeight += listOfItems.get(i).weight;
					curValue += listOfItems.get(i).value;

				}
			}

			if (curWeight <= maxWeight && curValue > curBestValue) {
				curBestValue = curValue;
				curBestWeight = curWeight;
				for (int j = 0; j < solution.length; j++) {
					solution[j] = current[j];
				}
			}
		} else {
			// zaznaczanie ze przedmiot zostal spakowany
			current[numSize] = true;
			algorithm(numSize - 1, maxWeight, numItems);
			// zaznaczanie ze przedmiot nie zostal spakowany
			current[numSize] = false;
			algorithm(numSize - 1, maxWeight, numItems);

		}

	}

	public static String result() {
		String items = " ";
		String name = " Brute force ";
		for (int i = 0; i < num - 1; i++) {
			if (solution[i]) {
				items += listOfItems.get(i).index + " ";
			}
		}
		String result2 = ("Rozwiazano uzywajac " + name + "\n Wynik: " + curBestValue + " Waga: " + curBestWeight
				+ "\n Przedmioty: " + items);
	//	System.out.println(result2);
		return result2;
	}

}