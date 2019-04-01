package com.wojcinowicz.biblioteka;


public class Eatables {
	String name;
	int how_much;
	float price;

	public Eatables(String name, int how_much, float price) {
		this.name = name;
		this.how_much = how_much;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getHowMuch() {
		return how_much;
	}

	public float getPrice() {
		return price;
	}

}
