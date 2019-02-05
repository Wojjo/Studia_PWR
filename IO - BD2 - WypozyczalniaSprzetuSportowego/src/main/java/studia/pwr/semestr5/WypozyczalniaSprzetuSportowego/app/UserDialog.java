package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;



public interface UserDialog 
{
  static final String ERROR_MESSAGE = 
		  "B��dne dane! \n Spr�buj ponownie.";
  
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
