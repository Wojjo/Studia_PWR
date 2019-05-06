package Algorithms;

import java.util.ArrayList;
import java.util.List;


public class BruteForceAlgorithm implements AlgorithmInterface {

	private List<Item> listOfItems;
	private List<Item> listOfSortedItems;
	Knapsack knapsack;

	public BruteForceAlgorithm(List<Item> listOfItems, int capacity) {
		super();
		listOfSortedItems = new ArrayList<Item>();
		
		this.knapsack = new Knapsack(capacity);

		this.listOfItems = new ArrayList<Item>();
		for (Item i : listOfItems)
			this.listOfItems.add(i);
		
		sort();
	}

	public String description() {
		String desc = "Rozwiazano za pomoca Brute Froce \nWaga maksymalna: " + knapsack.getMaxCapacity()
				+ ", waga: " + knapsack.getCurrentCapacity() + ", wartosc: "
				+ knapsack.getCurrentValue() + "\nPrzedmioty: ";
		for (int i = 0; i < knapsack.getListOfItems().size(); i++) {
			desc += "\n" + i + ": waga: " + knapsack.getListOfItems().get(i).getWeight() + " wartosc: " + knapsack.getListOfItems().get(i).getItemValue();
		}
		return desc;
	}

	public void solve() {
		while (listOfSortedItems.size() > 0) {
			if (knapsack.getMaxCapacity() > knapsack.getCurrentCapacity() + listOfSortedItems.get(0).getWeight()) {
				knapsack.setCurrentCapacity(knapsack.getCurrentCapacity() + listOfSortedItems.get(0).getWeight());
				knapsack.setCurrentValue(knapsack.getCurrentValue() + listOfSortedItems.get(0).getItemValue());
				knapsack.getListOfItems().add(listOfSortedItems.get(0));
			}
			listOfSortedItems.remove(0);
		}
	}

	private void sort() {
		while (listOfItems.size() > 0) {
			int itemIndex = 0;
			double itemValueToWeight = 0;
			for (int i = 0; i < listOfItems.size(); i++) {
				if (itemValueToWeight < listOfItems.get(i).getItemValue() / listOfItems.get(i).getWeight()) {
					itemValueToWeight = listOfItems.get(i).getItemValue() / listOfItems.get(i).getWeight();
					itemIndex = i;
				}
			}
			listOfSortedItems.add(listOfItems.get(itemIndex));
			listOfItems.remove(itemIndex);
		}
	}
}
