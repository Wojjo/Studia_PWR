package zad1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class instancja
{
static ArrayList<przedmiot> itemList;
private int numItems;
private boolean[] solution;
private boolean[] current;
private static int maxWeight;



@SuppressWarnings("resource")
void readfile(String filename) throws FileNotFoundException
{
	FileInputStream in = new FileInputStream(new File(filename));
	
	Scanner sc = new Scanner(in);
	itemList = new ArrayList<przedmiot>();
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
        brute_force bf = new brute_force(maxWeight, solution, current, numItems);
        bf.startAlgorithm();     
    	System.out.println("Done");
	}   
}

@SuppressWarnings("resource")
public static void main(String[] args) {
    
    System.out.println("Podaj nazwe pliku");
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
