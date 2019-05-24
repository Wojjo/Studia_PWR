/**
 * Ta klasa implementuje przedmioty, które s¹ potrzebne do rozwi¹zania problemu plecakowego.
 * @autor Przemys³aw Wojcinowicz
 * 
 */

package knapsack;

public class Przedmiot 
{
	
	public float value;
	public int weight;
	public int index;
	
	/**
	 * Ten konstruktor buduje przedmiot ze zmiennymi index, value, weight.
	 * @param index przechowuje index przedmiotu, pozwoli okresliæ iloœæ przedmiotów
	 * @param value przechowuje wartoœæ przedmiotu 
	 * @param weight przechowuje wagê przedmiotu
	 */
	
	public Przedmiot(int index, float value, int weight)
	{
		this.index=index;
		this.value=value;
		this.weight=weight;
	}
	/**
	 * @return Metoda ta zwraca index przedmiotu
	 */
	public int getIndex()
	{
		return index;
	}
	/**
	 * @return Metoda ta zwraca wartoœæ przedmiotu 
	 */
	public float getValue()
	{
		return value;
	}
	/**
	 * @return Metoda ta zwraca wagê przedmiotu
	 */
	public int getWeight()
	{
		return weight;
	}

}
