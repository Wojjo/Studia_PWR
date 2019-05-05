package KnapsackProblem;

import java.util.ArrayList;

public class BruteForce {
	private int instanceNumber;
	private int weight;
	private ArrayList<Item> listOfItems;
	private boolean binaryListOfItems[];
	private Knapsack knapsack;
	private Knapsack mostValuableKnapsack;

	public BruteForce(ArrayList<Item> listOfItems, int weight) {
		super();
		this.listOfItems = listOfItems;
		this.weight = weight;

		mostValuableKnapsack = new Knapsack(weight);
		instanceNumber = 0;
		binaryListOfItems = new boolean[listOfItems.size()];
		for (boolean b : binaryListOfItems)
			b = false;

		solve();
		solution();
	}

	public String solution() {
		String desc = "Problem rozwiazany za pomoca algorytmu Brute Force \nMaksymalna waga: "
				+ mostValuableKnapsack.getMaxWeight() + ", waga: "
				+ mostValuableKnapsack.getCurrentWeight() + ", wartosc: " + mostValuableKnapsack.getCurrentValue()
				+ "\nPrzedmioty: ";
		for (int i = 0; i < mostValuableKnapsack.getListOfItems().size(); i++) {
			desc += "\n" + mostValuableKnapsack.getListOfItems().get(i).getIndex() + " "
					+ mostValuableKnapsack.getListOfItems().get(i).getWeight() + " "
					+ mostValuableKnapsack.getListOfItems().get(i).getItemValue();
		}

		return desc;
	}

	public void solve() {
		for (int i = 0; i < Math.pow(2, listOfItems.size()); i++) {
			knapsack = new Knapsack(weight);
			decimalToBinary();
			for (int j = 0; j < listOfItems.size(); j++)
				if (binaryListOfItems[j]) {
					knapsack.getListOfItems().add(listOfItems.get(j));
					knapsack.setCurrentWeight(knapsack.getCurrentWeight() + listOfItems.get(j).getWeight());
					knapsack.setCurrentValue(knapsack.getCurrentValue() + listOfItems.get(j).getItemValue());
				}

			if (knapsack.getCurrentWeight() < weight
					&& knapsack.getCurrentValue() > mostValuableKnapsack.getCurrentValue())
				mostValuableKnapsack = knapsack;

			instanceNumber++;
		}
	}

	private void decimalToBinary() {
		int buffer = instanceNumber;
		int position = binaryListOfItems.length;

		while (buffer > 0) {
			position--;

			if (buffer % 2 == 1)
				binaryListOfItems[position] = true;
			else
				binaryListOfItems[position] = false;

			buffer /= 2;
		}
	}

}
