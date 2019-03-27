package zad2;

import java.util.ArrayList;

public class Greedy {
	ArrayList<Items> orderedList = new ArrayList<Items>();
	Items curItem;
	private int maxWeight;
	private int curWeight;
	private int numItems;
	private long curRatio;
	private long orderedCurRatio;
	private long curValue;
	private boolean solution[];
	private boolean added;
	

	public Greedy(int maxWeight, int numItems) {
		this.maxWeight = maxWeight;
		this.numItems = numItems;
	}

	public void startAlgorithm() {
		solution = new boolean[numItems];
		algorithm(numItems);
		show_result();

	}

	public void algorithm(int numSize) {
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
			curItem = orderedList.remove(highestValueIndex);
			// jesli waga przedmiotu i obecna waga plecaka nie przekracza maksymalnej wagi
			// plecaka
			if (curItem.weight + curWeight <= maxWeight) {
				
				//solution[curItem.index - 1] = true;
				// przypisanie nowych wartoci
				curValue += curItem.value;
				curWeight += curItem.weight;
			}

		}
	}
	
	public void show_result() {
		System.out.println(" Wynik: " + curValue + " waga: " + curWeight);
	}

}