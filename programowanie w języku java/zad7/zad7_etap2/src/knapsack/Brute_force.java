package knapsack;

public class Brute_force {

	private int maxWeight;
	private static boolean[] solution;
	private boolean[] current;
	private static float curBestValue;
	private static int curBestWeight;
	private int curWeight;
	private float curValue;
	private static int numItems;

	public Brute_force(int maxWeight, int numItems) {
		this.maxWeight = maxWeight;
		this.numItems = numItems;
		current = new boolean[numItems];
		solution = new boolean[numItems];
		curBestValue = 0;
		curBestWeight = 0;
		algorithm(numItems - 1);
	}

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
			// jesli waga jest mniejsza od maksymalnej oraz nowa wartosc jest wieksza od
			// aktualnej
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

	public static String result() {
		String items = " ";
		String name = " Brute force ";
		for (int i = 0; i < numItems - 1; i++) {
			if (solution[i]) {
				items += Instancja.itemList.get(i + 1).index + " ";
			}
		}
		String result2 = ("Rozwiazano uzywajac " + name + " Wynik: " + curBestValue + " Waga: " + curBestWeight
				+ " Przedmioty: " + items);
		return result2;
	}

}
