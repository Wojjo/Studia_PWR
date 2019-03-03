package zad1;

public class Wynik {
	public static void printResult(String algorithmName, float BestValue, int BestWeight, int numItems, boolean[] solution)
	{
		System.out.println("Using " + algorithmName +
                " solution found: " + BestValue + 
                " Weight: " + BestWeight);
		 for(int i = 0; i < numItems; i++) {
	            if(solution[i]) {
	                System.out.print(Instancja.itemList.get(i).index + " ");
	            }
	        }
	    System.out.println();
	}
}
