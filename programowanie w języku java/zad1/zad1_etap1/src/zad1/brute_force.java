/**
 * Klasa ta pozwala rozwi¹zaæ problem plecakowy metod¹ si³ow¹
 * @author Przemys³aw Wojcinowicz
 */
package zad1;

import java.util.ResourceBundle;


public class Brute_force implements Interfejs {

	private int maxWeight;
	private boolean[] solution;
	private boolean[] current;
	private float curBestValue;
	private int curBestWeight;
	private int curWeight;
	private float curValue;
	private int numItems;
	private String algorithmName = "Brute Force";

	public Brute_force(int maxWeight, int numItems) {
		this.maxWeight = maxWeight;
		this.numItems = numItems;
	}

	/**
	 * Metoda:
	 * inicjalizuje tablice dla znalezionego obecnego rozwi¹zania,
	 * inicjalizuje tablice dla znalezionego najlepszego rozwi¹zania, 
	 * wywo³uje metodê rozwi¹zuj¹c¹ problem,
	 * wywo³uje metodê, która wyœwietla wynik rozwi¹zania,
	 * @param bundle przechowuje informacjê jaki jêzyk zosta³ wybrany 
	 */
	public void startAlgorithm(ResourceBundle bundle) {
		current = new boolean[numItems];
		solution = new boolean[numItems];
		algorithm(numItems - 1);
		Wynik.printResult(algorithmName, curBestValue, curBestWeight, numItems, solution, bundle);

	}
	/**
	 * Metoda rozwi¹zuje problem plecakowy metod¹ zach³ann¹ 
	 * @param numSize przechowuje iloœæ wszystkich przedmiotów 
	 */	 
	public void algorithm(int numSize) {
		if (numSize < 0) {
			curWeight = 0;
			curValue = 0;
			// liczenie waga oraz wartosci
			for (int i = 0; i < numItems; i++) {
				if (current[i]) {
					curWeight += Instancja.itemList.get(i).weight;
					curValue += Instancja.itemList.get(i).value;

				}
			}
			// jesli waga jest mniejsza od maksymalnej oraz nowa wartosc jest wieksza od aktualnej 
			// ustawia nowa wage i wartosc jako dotychczasowe najlepsze 
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
			algorithm(numSize - 1);
			// zaznaczanie ze przedmiot nie zostal spakowany
			current[numSize] = false;
			algorithm(numSize - 1);

		}

	}

}
