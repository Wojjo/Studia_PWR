/* 
 *  Account  
 *  Przyjmuje wartosci: login, imie, nazwisko ,miasto, gdzie ,mieszka ,ulica i numer domu klienta lub wlasciciela.
 *  Aby stworzyc konto administratora nalezy uzyc loginu Administrator
 *  Realizuje: zmiane hasla, transfer pieniedzy, wplacanie, wyplacania, przelew za zakupine rzeczy
 *  Autor: Przemys³aw Wojcinowicz
 *  Indeks: 225943
 *  Grupa Czwartek 9:15
 *  Data: 16-26 pazdziernika 2016 r.
 */

/**
 * Klasa Account
 * <br>
 *  
 *
 *Klasa posiada funkcje:
 * <ul>
 *  <li> Przyjmuje wartosci: login, imie, nazwisko ,miasto, gdzie ,mieszka ,ulica i numer domu klienta lub wlasciciela.</li>
 *  <li>  Aby stworzyc konto administratora nalezy uzyc loginu Administrator </li>
 *  <li> Realizuje: zmiane hasla, transfer pieniedzy, wplacanie, wyplacania, przelew za zakupine rzeczy </li>
 *  <br>
 * </ul>
 *
 * @author Przemyslaw Wojcinowicz
 * @version 9.11.2016 r 
 */

import java.io.Serializable;
public class Account implements Serializable 
{
 private static final long serailVersionUID = 1L;
 private String name;
 private String surname;
 private String login;
 private String city;
 private String street;
 private int number;
 private long passwordCode; 
 private double balance; // pieniadze na koncie *
 

 
 
/** Konstruktor */
Account(String login, String name, String surname, String city, String street, int number)
{
	this.login = login;
	this.name = name;
	this.surname=surname;
	this.city=city;
	this.street=street;
	this.number=number;
	
	passwordCode = 0;
	balance = 0;
	
}

public String getLogin()
{
	return login;
}

public String getName()
{
	return name;
}
public String getCity()
{
	return city;
}
public String getStreet()
{
	return street;
}
public int getNumber()
{
	return number;
}
public void setSurname(String surname)
{
	this.surname = surname;
}

public String getSurname()
{
	return surname;
}

public double getBalance(){
	return balance;
}


 /** Metoda sprawdza haslo */
 public boolean checkPassword(String code)
 {
	 if (code==null)
		 return false;
	 return code.hashCode()==passwordCode;
	 
 }
 /** Metoda ustawia haslo */
 public void setPassword(String oldPassword, String newPassword) throws Exception
 {
	 boolean check = false; 
	 if(!checkPassword(oldPassword)) throw new Exception("Bledne haslo"); 
	 check = oldPassword.equals(newPassword); // Sprawdzamy czy nowe haslo rzeczywiscie jest nowe.
	 if (check == true)
	 {
		 throw new Exception("Podales stare haslo");
	 }
	 passwordCode=newPassword.hashCode();
 }
 

 /** Metoda umozliwia wplacenie pieniedzy */
 public void payIn(double amount) throws Exception {
		if (amount<0) throw new Exception("Wplata nie moze byc ujemna");
		balance += amount;
	}
 /** Metoda umozliwia wyplacenie pieniedzy */
	public void payOut( double amount) throws Exception {
		
		if (amount<0) throw new Exception("Bledne dane");
		if (amount>balance) throw new Exception("Za malo srodkow na koncie. Zasil konto");
		balance -= amount;
	}
	
	/** Metoda zaplacenie za zakupy */
	public void transfer( double amount, 
			Account account) throws Exception {
		if (account==null) throw new Exception("Nieznane konto docelowe");
		payOut(amount);
		account.payIn(amount);
		
	}
	/** Metoda przekazuje (String) dane klienta*/
	
	public String toString(){
		return String.format("   %s [ %s ] [ %s ] ", login, name, surname);
	}

	
 
}
