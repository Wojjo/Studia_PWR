package KnapsackProblem;

import java.util.ArrayList;

public class Greedy {
	static ArrayList<Item> orderedList;
	private static ArrayList<Item> listOfItems;
	static Item curItem;
	private static int curWeight;
	private static long curRatio;
	private static long orderedCurRatio;
	private static long curValue;
	private static boolean solution[];
	static int num;

	public Greedy(ArrayList<Item> listOfItems, int maxWeight, int numItems) {
		solution = new boolean[numItems];
		curRatio = 0;
		orderedCurRatio = 0;
		curValue = 0;
		curWeight = 0;
		num = 0;
		num = numItems;
		orderedList = new ArrayList<Item>();
		this.listOfItems = new ArrayList<Item>();
		for (Item i : listOfItems)
			this.listOfItems.add(i);

	}

	public static void startAlgorithm(int maxWeight, int numItems) {
		solution = new boolean[numItems];
		curRatio = 0;
		orderedCurRatio = 0;
		curValue = 0;
		curWeight = 0;
		num = 0;
		num = numItems;
		algorithm(numItems, maxWeight, numItems);
		result();

	}

	public static void algorithm(int numSize, int maxWeight, int numItems) {

		boolean added;
		curWeight = 0;
		// dodanie przedmiotu 0
		orderedList.add(listOfItems.get(0));
		for (int i = 1; i < numSize; i++) {
			added = false;
			// pobranie przedmiotu i
			curItem = listOfItems.get(i);
			// obliczenie zaleznosci wartosci od wagi przedmoitu i
			curRatio = curItem.value / curItem.weight;

			for (int j = 0; j < orderedList.size(); j++) {
				// pobranie przedmiotu j
				Item orderedItem = orderedList.get(j);

				// obliczenie zaleznosci wartosci od wagi przedmiotu j
				orderedCurRatio = orderedItem.value / orderedItem.weight;

				// jesli zaleznosc przedmiotu i jest mniejsza od zaleznosci przedmiotu j
				// dodaj przedmiot j
				if (curRatio < orderedCurRatio) {
					orderedList.add(j, curItem);
					added = true;
					break;
				}
			}
			// w przeciwnym wypadku dodaj przedmiot i
			if (!added) {

				orderedList.add(curItem);
			}

		}
		// dopoki obecna waga plecak nie przekroczy maksymalnej wagi plecaka i
		// orderedList nie jest pusta
		while (curWeight < maxWeight && !orderedList.isEmpty()) {

			int highestValueIndex = orderedList.size() - 1;

			// jesli waga przedmiotu i obecna waga plecaka nie przekracza maksymalnej wagi
			// plecaka
			
			if (curItem.weight + curWeight <= maxWeight) {
				try
				{
				 solution[curItem.index] = true;
				}catch(Exception e)
				{
					System.out.print("");
				}
				// przypisanie nowych wart
				curValue += curItem.value;
				curWeight += curItem.weight;
			}
			curItem = orderedList.remove(highestValueIndex);
			
		}

	}

	public static String result() {

		String items = " ";
		String name = "Greedy ";

		for (int i = 0; i < num; i++) {
			if (solution[i]) {
				items += listOfItems.get(i).index + " ";
			}
			
		}
		String result2 = ("Rozwiazano uzywajac " + name + "\n Wynik: " + curValue + " Waga: " + curWeight
				+ "\n Przedmioty: " + items);

	//	System.out.println(result2);
		return result2;
	}

}