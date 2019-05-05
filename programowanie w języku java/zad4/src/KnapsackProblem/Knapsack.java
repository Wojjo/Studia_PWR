package KnapsackProblem;

import java.util.ArrayList;

public class Knapsack {
	private ArrayList<Item> listOfItems;
	private int maxWeight;
	private int currentWeight;
	private double currentValue;

	public Knapsack(int maxWeight) {
		// super();
		this.maxWeight = maxWeight;
		currentWeight = 0;
		currentValue = 0;
		listOfItems = new ArrayList<Item>();
	}

	public ArrayList<Item> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(ArrayList<Item> listOfItems) {
		this.listOfItems = listOfItems;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public int getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}
}
