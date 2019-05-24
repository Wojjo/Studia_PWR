
package knapsack;

import java.util.ArrayList;

public class Instancja {
	public static ArrayList<Przedmiot> itemList;
	private static int numItems = 15;
	private static int maxWeight = 1000;
	private int a = 4;

	public void generate() {
		itemList = new ArrayList<Przedmiot>();
		int value, weight;
		itemList.clear();
		for (int i = 0; i < 15; i++)
			if (itemList.size() < 15) {
				weight = 2 * (i + 1) * a;
				value = 3 * (i + 1) * a;
				
				Przedmiot item = new Przedmiot(i, weight, value);
				itemList.add(item);
				System.out.println(itemList.get(i).index + " " + itemList.get(i).weight + " " + itemList.get(i).value);

			}
		a++;
	}

	public void solveKnapsackProblem(String option) {
		generate();
		if (option.equals("Brute Force")) {
			new Brute_force(maxWeight, numItems);

		} else if (option.equals("Greedy")) {
			new Greedy(maxWeight, numItems);
		}
	}

}
