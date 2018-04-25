/* 
 *  Interfejs UserDialog  
 *  Autor: Pawel Rogalinski
 *  Indeks: 225943
 *  Grupa Czwartek 9:15
 *   Data: 16-26 pazdziernika 2016 r.
 */

public interface UserDialog 
{
  static final String ERROR_MESSAGE = 
		  "B³êdne dane! \n Spróbuj ponownie.";
  
  public void printMessage(String message);
  
  public void printInfoMessage(String message);
  
  public void printErrorMessage(String message);
  
  public void clearConsole();
  
  public String enterString (String prompt);
  
  public char enterChar(String prompt);
  
  public int enterInt(String prompt);
  
  public float enterFloat(String prompt);
  
  public double enterDouble(String prompt);
  
}
