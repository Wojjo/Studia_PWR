package com.wojcinowicz.biblioteka;

import java.util.ArrayList;
import java.util.Scanner;

public class Options
{
	static ArrayList<Items> items = new ArrayList<Items>();
	static ArrayList<Eatables> food = new ArrayList<Eatables>();
	
	public static void addItems()
	{
		String name;
		int how_much;
		float price, total_cost;
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj nazwe nowego przedmiotu ");
		name = scan.nextLine();
		System.out.println("Podaj jaka ilosc przedmiotu bedzie potrzebna");
		how_much = scan.nextInt();
		System.out.println("Podaj koszt przedmiotu");
		price = scan.nextFloat();
		total_cost = price * how_much;
		
		items.add(new Items(name, how_much, total_cost));
		System.out.println("Dodano nowy przedmiot");
	
		
	}
	
	public static void showItems()
	{
		for(int i=0; i<items.size(); i++)
		{
		System.out.println(" Nazwa przedmiotu: '" + items.get(i).name + "' Ilosc przedmiotow: " + items.get(i).how_much + " Laczna cena: " + items.get(i).price);
		}
	}
	
	public static void addFood()
	{
		String name;
		int how_much;
		float price, total_cost;
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj nazwe prowiantu ");
		name = scan.nextLine();
		System.out.println("Podaj jaka ilosc bedzie potrzebna");
		how_much = scan.nextInt();
		System.out.println("Podaj koszt ");
		price = scan.nextFloat();
		total_cost = price * how_much;
		
		food.add(new Eatables(name, how_much, total_cost));
		System.out.println("Dodano nowy prowiant");
	}
	
	public static void showFood()
	{
		for(int i=0; i<food.size(); i++)
		{
		System.out.println(" Nazwa: '" + food.get(i).name + "' Ilosc: " + food.get(i).how_much + " Laczna cena: " + food.get(i).price);
		}
	}
	
	
}
