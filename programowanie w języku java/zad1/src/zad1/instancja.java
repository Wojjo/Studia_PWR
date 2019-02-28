package zad1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class instancja
{
private static ArrayList<przedmiot> itemList;
private static ArrayList<przedmiot> knapsack; //aktualna zawartosc plecaka 
private int numItems;
private boolean[] solution;
private boolean[] current;
private static int maxWeight;


void readfile(String filename) throws FileNotFoundException
{
	FileInputStream in = new FileInputStream(new File(filename));
	Scanner sc = new Scanner(in);
	itemList = new ArrayList<przedmiot>();
	knapsack = new ArrayList<przedmiot>();
	numItems = sc.nextInt();
	
	solution = new boolean[numItems];
	for(int i = 0; i < numItems; i++)
	{
		itemList.add(new przedmiot(sc.nextInt(),
								   sc.nextInt(),
								   sc.nextInt()));
	}
	maxWeight = sc.nextInt();
	
}
public void runKnapsack(String option) {
	
	if(option.equals("Brute Force")) {
        //run brute force
        current = new boolean[numItems];
        brute_force bf = new brute_force(numItems, solution, current);
        bf.algorithmBF(numItems-1);
        
       
    	System.out.println("Done");
	}


        //output
     
        
}



public static ArrayList<przedmiot> getItemList() {
	return itemList;
}

public static void setItemList(ArrayList<przedmiot> itemList) {
	instancja.itemList = itemList;
}


public static void main(String[] args) {
    
    System.out.println("Podaj nazwe pliku");
    System.out.println("example: java Knapsack test.txt");
    Scanner scan = new Scanner(System.in);
    String filename = scan.nextLine();            
  

instancja knapsack = new instancja();

try {
    knapsack.readfile(filename);
    knapsack.runKnapsack("Brute Force");
   
} catch (FileNotFoundException e) {
    System.out.println("ERROR: File not found.");
}
}













}
