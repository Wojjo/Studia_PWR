/* 
 *  Interfejs UserDialog   
 *  Autor: Przemys³aw Wojcinowicz
 *  Indeks: 225943
 *  Grupa Czwartek 9:15
 *  Data: 18 pazdziernika 2016 r.  
 */

import javax.swing.JOptionPane;
public class JOptionPaneUserDialog implements UserDialog
{
    private StringBuilder messagePrinter = new StringBuilder();
    
    public void printMessage(String message)
    {
    	messagePrinter.append(message);
    	messagePrinter.append("\n");
    }
    
    public void printInfoMessage(String message)
    {
    	messagePrinter.append(message);
    	messagePrinter.append("\n");
    	JOptionPane.showMessageDialog(null, messagePrinter);
    	messagePrinter = new StringBuilder();
    }
    
    public void printErrorMessage(String message)
    {
    	messagePrinter.append(message);
    	messagePrinter.append("\n");
    	JOptionPane.showMessageDialog(null, messagePrinter, "", JOptionPane.ERROR_MESSAGE);
    	messagePrinter=new StringBuilder();
    }
    public void clearConsole()
    {
    	messagePrinter.append("\n");
    }
    
    public String enterString(String prompt)
    {
    	messagePrinter.append(prompt);
    	String message = JOptionPane.showInputDialog(messagePrinter);
    	messagePrinter = new StringBuilder();
    	if(message!=null)
    		return message;
    	return "";
    	
    }
    
	public char enterChar(String prompt)
	{
		boolean Error = false;
		char c = ' ';
		do
		{
			try
			{
				c = enterString(prompt).charAt(0);
			} 
			catch (IndexOutOfBoundsException e)
			{
				printErrorMessage(ERROR_MESSAGE);
				Error = true;
			}
		} while (Error);
		return c;
	}
	
	public int enterInt(String prompt) {
        boolean Error;
        int i = 0;
        do{
            Error = false;
            try{ 
                i = Integer.parseInt(enterString(prompt));
            } catch(NumberFormatException e){
            	printErrorMessage(ERROR_MESSAGE);
            	Error = true;
            }
        }while(Error);
        return i;
    }
	
	
    @Override
	public float enterFloat(String prompt) {
        boolean Error;
        float d = 0;
        do{
            Error = false;
            try{
                d = Float.parseFloat(enterString(prompt));
            } catch(NumberFormatException e){
            	printErrorMessage(ERROR_MESSAGE);
                Error = true;
            }
        } while(Error);
        return d;
    }   
	
	
    @Override
	public double enterDouble(String prompt) {
        boolean Error;
        double d = 0;
        do{
            Error = false;
            try{
                d = Double.parseDouble(enterString(prompt));
            } catch(NumberFormatException e){
            	printErrorMessage(ERROR_MESSAGE);
                Error = true;
            }
        }while(Error);
        return d;
    }
    
    
}