package Algorithms;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {

	private int maxCapacity;
	private int currentCapacity;
	private double currentValue;
	private List<Item> listOfItems;

	public Knapsack(int maxCapacity) {
		super();

		this.maxCapacity = maxCapacity;
		currentCapacity = 0;
		currentValue = 0;
		listOfItems = new ArrayList<Item>();
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}

	public List<Item> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(List<Item> listOfItems) {
		this.listOfItems = listOfItems;
	}
}
