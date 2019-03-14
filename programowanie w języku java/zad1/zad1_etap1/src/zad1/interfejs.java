/**
 * Interfejs dla algorytmów, posiada metody:
 * algorithm, która rozwi¹zuje problem
 * startAlgorithm, która w³¹cza rozwi¹zywanie problemu
 * @autor Przemys³aw Wojcinowicz
 */

package zad1;

import java.util.ResourceBundle;

public interface Interfejs {
	public void algorithm(int numSize);
	public void startAlgorithm(ResourceBundle bundle);
}
