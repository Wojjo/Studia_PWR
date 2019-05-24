/**
 * Klasa ta pozwala rozwi¹zaæ problem plecakowy metod¹ zach³annym
 * @author Przemys³aw Wojcinowicz
 */
package knapsack;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Greedy {
	ArrayList<Przedmiot> orderedList = new ArrayList<Przedmiot>();
	Przedmiot curItem;
	private int maxWeight;
	private static int curWeight;
	private static int numItems;
	private float curRatio;
	private float orderedCurRatio;
	private static float curValue;
	private static boolean solution[];
	private boolean added;

	public Greedy(int maxWeight, int numItems) {
		this.maxWeight = maxWeight;
		this.numItems = numItems;
		curWeight = 0;
		curValue = 0;
		solution = new boolean[numItems];
		algorithm(numItems);
	}

	public void algorithm(int numSize) {
		curWeight = 0;
		// dodanie przedmiotu 0
		orderedList.add(Instancja.itemList.get(0));
		for (int i = 1; i < numSize; i++) {
			added = false;
			// pobranie przedmiotu i
			curItem = Instancja.itemList.get(i);
			// obliczenie zaleznosci wartosci od wagi przedmoitu i
			curRatio = curItem.value / curItem.weight;

			for (int j = 0; j < orderedList.size(); j++) {
				// pobranie przedmiotu j
				Przedmiot orderedItem = orderedList.get(j);

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

		while (curWeight < maxWeight && !orderedList.isEmpty()) {

			int highestValueIndex = orderedList.size() - 1;
			curItem = orderedList.remove(highestValueIndex);

			if (curItem.weight + curWeight <= maxWeight) {
				solution[curItem.index - 1] = true;
				curValue += curItem.value;
				curWeight += curItem.weight;
			}

		}
	}

	public static String result() {

		String items = " ";
		String name = "Greedy ";

		for (int i = 0; i < numItems; i++) {
			if (solution[i]) {
				items += Instancja.itemList.get(i).index + " ";
			}
		}
		String result2 = ("Rozwiazano uzywajac " + name + " Wynik: " + curValue + " Waga: " + curWeight
				+ " Przedmioty: " + items);
		return result2;
	}

}
