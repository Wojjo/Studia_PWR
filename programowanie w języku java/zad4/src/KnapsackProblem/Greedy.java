package KnapsackProblem;

import java.util.ArrayList;

public class Greedy {
	private ArrayList<Item> listOfItems;
	private ArrayList<Item> listOfSortedItems;
	Knapsack knapsack;

	public Greedy(ArrayList<Item> listOfItems, int capacity) {
		super();
		listOfSortedItems = new ArrayList<Item>();
		this.knapsack = new Knapsack(capacity);
		this.listOfItems = new ArrayList<Item>();
		for (Item i : listOfItems)
			this.listOfItems.add(i);
		sort();
	}
	
	
	public String solution() {
		String desc = "Problem rozwiazany za pomoca algorytmu Greedy \nMaksymalna waga: " + knapsack.getMaxWeight()
				+ ", waga: " + knapsack.getCurrentWeight() + ", wartosc: "
				+ knapsack.getCurrentValue() + "\nPrzedmioty: ";
		for (int i = 0; i < knapsack.getListOfItems().size(); i++) {
			desc += "\n" + knapsack.getListOfItems().get(i).getIndex() + " "
					+ knapsack.getListOfItems().get(i).getWeight() + " " + knapsack.getListOfItems().get(i).getItemValue();
		}
		return desc;
	}
	
	
	public void solve() {
		while (listOfSortedItems.size() > 0) {
			if (knapsack.getMaxWeight() > knapsack.getCurrentWeight() + listOfSortedItems.get(0).getWeight()) {
				knapsack.setCurrentWeight(knapsack.getCurrentWeight() + listOfSortedItems.get(0).getWeight());
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
