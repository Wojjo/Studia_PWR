package zad1;

import javafx.scene.control.Alert;

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

	public void startAlgorithm() {
		current = new boolean[numItems];
		solution = new boolean[numItems];
		algorithm(numItems - 1);
		Wynik.printResult(algorithmName, curBestValue, curBestWeight, numItems, solution);

	}

	public void algorithm(int numSize) {
		if (numSize < 0) {
			curWeight = 0;
			curValue = 0;
			for (int i = 0; i < numItems; i++) {
				if (current[i]) {
					curWeight += Instancja.itemList.get(i).weight;
					curValue += Instancja.itemList.get(i).value;

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
			current[numSize] = true;
			algorithm(numSize - 1);

			current[numSize] = false;
			algorithm(numSize - 1);

		}

	}

	public static void info() {

		new Alert(Alert.AlertType.INFORMATION,
				"Metoda polegaj¹ca na analizie wszystkich potencjalnych rozwi¹zañ zadania w celu wybrania tego, które spe³nia warunki zadania")
						.showAndWait();
	}
}
