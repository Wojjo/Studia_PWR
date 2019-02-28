package zad1;

public class Brute_force
{
	
	private int maxWeight;
	private boolean[] solution;
	private boolean[] current;
	private float curBestValue;
	private int curBestWeight;
	private int curWeight;
	private float curValue;
	private int numItems;
	
	public Brute_force(int maxWeight, boolean[] solution, boolean[] current, int numItems)
	{
		this.maxWeight = maxWeight;
		this.solution = solution;
		this.current = current;
		this.numItems = numItems;
	}
	
	void startAlgorithm()
	{
		algorithmBF(numItems-1);
		printBruteForceKnapsack();
		
	}
	
	
	void algorithmBF(int numSize)
	{ 
		if(numSize < 0)
		{
			curWeight = 0;
			curValue = 0;
			
			for(int i =0; i < numItems; i++)
			{
				if(current[i])
				{
					curWeight += Instancja.itemList.get(i).weight;
                    curValue += Instancja.itemList.get(i).value;
					
				}
			}
			
			if(curWeight <= maxWeight && curValue > curBestValue)
			{
				curBestValue = curValue;
				curBestWeight = curWeight;
				for(int j = 0; j < solution.length; j++)
				{
					solution[j] = current[j];
				}
			}
		} else
		{
			current[numSize] = true;
			algorithmBF(numSize - 1);
			current[numSize] = false;
			algorithmBF(numSize - 1);
					
		}	

	}
	
	public void printBruteForceKnapsack()
	{
		System.out.println("Using Brute force the best" +
                " solution found: " + this.curBestValue + 
                " Weight: " + this.curBestWeight);
		 for(int i = 0; i < numItems; i++) {
	            if(solution[i]) {
	                System.out.print(Instancja.itemList.get(i).index + " ");
	            }
	        }
	    System.out.println();
	}
	
	
	
}
