package zad2;

import java.util.LinkedList;

public class Brute_force {

	private static boolean[] solution;
	private static boolean[] current;
	private static float curBestValue;
	private static int curBestWeight;
	private static int curWeight;
	private static float curValue;

	public Brute_force(int maxWeight, int numItems) {
		// this.maxWeight = maxWeight;
		// this.numItems = numItems;

	}

	public static void startAlgorithm(int maxWeight, int numItems) {

		current = new boolean[numItems];
		solution = new boolean[numItems];
		curBestValue = 0;
		curBestWeight = 0;
		curWeight = 0;
		curValue = 0;
		algorithm(numItems - 1, maxWeight, numItems);
		result(numItems);
	}

	public static void algorithm(int numSize, int maxWeight, int numItems) {

		if (numSize < 0) {
			curWeight = 0;
			curValue = 0;

			for (int i = 0; i < numItems; i++) {
				if (current[i]) {
					curWeight += Threads.list.get(i).weight;
					curValue += Threads.list.get(i).value;

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

	public static String result(int numItems) {
		String items = " ";
		String name = " Brute force ";
		for (int i = 0; i < numItems-1; i++) {
			if (solution[i]) {
				items += Threads.list.get(i + 1).index + " ";
				
			}
			
			
		}
		String result2 = ("Rozwiazano uzywajac " + name + " Wynik: " + curBestValue + " Waga: " + curBestWeight
				+ " Przedmioty: " + items);
		Threads.result = new LinkedList<Solution>();
		Threads.result.add(new Solution(items, curBestWeight));
		

		System.out.println(result2);
		return result2;
	}

}
