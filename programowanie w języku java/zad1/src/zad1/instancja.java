package zad1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Instancja
{
static ArrayList<Przedmiot> itemList;
private int numItems;
private boolean[] solution;
private boolean[] current;
private static int maxWeight;




void readfile(String filename) throws FileNotFoundException
{
	FileInputStream in = new FileInputStream(new File(filename));
	
	Scanner sc = new Scanner(in);
	itemList = new ArrayList<Przedmiot>();
	numItems = sc.nextInt();
	
	solution = new boolean[numItems];
	for(int i = 0; i < numItems; i++)
	{
		itemList.add(new Przedmiot(sc.nextInt(),
								   sc.nextInt(),
								   sc.nextInt()));
	}
	maxWeight = sc.nextInt();
	
}
public void runKnapsack(String option) {
	
	if(option.equals("Brute Force")) {
        //run brute force
        current = new boolean[numItems];
        Brute_force bf = new Brute_force(maxWeight, solution, current, numItems);
        bf.startAlgorithm();     

	}   else if(option.equals("Greedy")) {
		Greedy gr = new Greedy(maxWeight, solution, numItems);
		gr.startAlgorithm();
	}
}


public static void main(String[] args) {
    
    System.out.println("Podaj nazwe pliku");
    Scanner scan = new Scanner(System.in);
    String filename = scan.nextLine();            
    Instancja knapsack = new Instancja();

try {
    knapsack.readfile(filename);
    knapsack.runKnapsack("Brute Force");
    knapsack.runKnapsack("Greedy");
   
} catch (FileNotFoundException e) {
    System.out.println("ERROR: File not found.");
}
}


}
