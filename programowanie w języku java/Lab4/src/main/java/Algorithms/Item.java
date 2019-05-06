package Algorithms;

import java.io.Serializable;

public class Item implements Serializable {

	private String name;
	private int weight;
	private double value;

	public Item(String name, int weight, double value) {
		super();
		this.name = name;
		this.weight = weight;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getItemValue() {
		return value;
	}

	public void setItemValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Nazwa przedmiotu: " + name + ", waga: " + weight + ", wartosc: " + value;
	}

}
