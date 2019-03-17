/**
 * Klasa ta pozwala rozwi¹zaæ problem plecakowy metod¹ zach³annym
 * @author Przemys³aw Wojcinowicz
 */
package zad1;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Greedy implements Interfejs {
	ArrayList<Przedmiot> orderedList = new ArrayList<Przedmiot>();
	Przedmiot curItem;
	private int maxWeight;
	private int curWeight;
	private int numItems;
	private float curRatio;
	private float orderedCurRatio;
	private float curValue;
	private boolean solution[];
	private boolean added;
	private String algorithmName = "Greedy";

	/**
	 * Kontruktor inicjalizuje nastêpuj¹ce zmienne
	 * 
	 * @param maxWeight przechowuje maksymalna wage plecaka
	 * @param numItems  przechowuje iloœæ wszystkich przedmiotów
	 */

	public Greedy(int maxWeight, int numItems) {
		this.maxWeight = maxWeight;
		this.numItems = numItems;
	}

	/**
	 * Metoda: inicjalizuje tablicê dla rozwiazania, wywo³uje metodê algorytm, która
	 * rozwi¹zuje problem plecakowy, wywo³uje metodê, która wyœwietla wynik
	 * rozwi¹zania.
	 * 
	 * @param bundle przechowuje informacjê jaki jêzyk zosta³ wybrany
	 */
	public void startAlgorithm(ResourceBundle bundle) {
		solution = new boolean[numItems];
		algorithm(numItems);
		Wynik.printResult(algorithmName, curValue, curWeight, numItems, solution, bundle);

	}

	/**
	 * Metoda rozwi¹zuje problem plecakowy metod¹ zach³ann¹
	 * 
	 * @param numSize przechowuje iloœæ wszystkich przedmiotów
	 */

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

}
