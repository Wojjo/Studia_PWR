package zad2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Greedy {
	static ArrayList<Items> orderedList = new ArrayList<Items>();
	static Items curItem;
	//private int maxWeight;
	private static int curWeight;
	//private int numItems;
	private static long curRatio;
	private static long orderedCurRatio;
	private static long curValue;
	private static boolean solution[];

	public Greedy(int maxWeight, int numItems) {
		solution = new boolean[numItems];
		curRatio = 0;
		orderedCurRatio = 0;
		curValue = 0;
		curWeight = 0;
		algorithm(numItems, maxWeight, numItems);
		result(numItems);
	}

	public static void startAlgorithm(int maxWeight, int numItems) {
		solution = new boolean[numItems];
		curRatio = 0;
		orderedCurRatio = 0;
		curValue = 0;
		curWeight = 0;
		algorithm(numItems, maxWeight, numItems);
		result(numItems);

	}

	public static void algorithm(int numSize, int maxWeight, int numItems) {

		boolean added;
		curWeight = 0;
		// dodanie przedmiotu 0
		orderedList.add(Threads.list.get(0));
		for (int i = 1; i < numSize; i++) {
			added = false;
			// pobranie przedmiotu i
			curItem = Threads.list.get(i);
			// obliczenie zaleznosci wartosci od wagi przedmoitu i
			curRatio = curItem.value / curItem.weight;

			for (int j = 0; j < orderedList.size(); j++) {
				// pobranie przedmiotu j
				Items orderedItem = orderedList.get(j);

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
				// przypisanie nowych wartoci
				curValue += curItem.value;
				curWeight += curItem.weight;
			}
			curItem = orderedList.remove(highestValueIndex);
			
		}

	}

	public static String result(int numItems) {

		String items = " ";
		String name = "Greedy ";

		for (int i = 0; i < numItems; i++) {
			if (solution[i]) {
				items += Threads.list.get(i).index + " ";
			}
			
		}
		Threads.result = new LinkedList<Solution>();
		Threads.result.add(new Solution(items, curWeight));
		String result2 = ("Rozwiazano uzywajac " + name + " Wynik: " + curValue + " Waga: " + curWeight
				+ " Przedmioty: " + items);

		System.out.println(result2);
		return result2;
	}

}