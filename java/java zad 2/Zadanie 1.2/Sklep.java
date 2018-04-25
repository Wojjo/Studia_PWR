/* 
 *  Sklep  
 *   Dodaje funkcje typu utworz klienta, ustaw imie, nazwisko, haslo i wiele innych.
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
 *  <li> Tworzenie konta, usuwanie konta.</li>
 *  <li> Zapisuje do pliku i wczytuje z pliku konta klientow </li>
 *  <br>
 * </ul>
 *
 * @author Przemyslaw Wojcinowicz
 * @version 9.11.2016 r 
 */


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
class Sklep implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	/** Lista kont */
	private ArrayList<Account> listOfAccounts = new ArrayList<Account>();
	
	/** Metoda umozliwia stowrzenie konta */
	public Account createAccount(String login, String name, String surname, String city, String street, int number) throws Exception {
		if (login==null || login.equals("")) throw(new Exception("Nazwa konta nie moze byc pusta"));
		if (findAccount(login)!=null) throw(new Exception("Konto juz istnieje"));
		Account newAccount = new Account(login, name, surname, city, street, number);
		listOfAccounts.add( newAccount );
		return newAccount;
	}
	
	/** Metoda umozliwia usuniecie konta */
	public void removeAccount(Account account) throws Exception {
		if (account==null) throw(new Exception("Brak konta"));
		if (account.getBalance()!= 0) throw(new Exception("Saldo konta nie jest zerowe"));
		listOfAccounts.remove(account);
	}
	
	/** Metoda umozliwia znalezienie konta */
	public Account findAccount(String login) {
		for (Account account : listOfAccounts)
			if (account.getLogin().equals(login)) return account;
		return null;
	}
	
	
	public Account findAccount(String name, String password) {
		Account account = findAccount(name);
		if (account!=null){
			if (!account.checkPassword(password)) account = null;
		}
		return account;
	}
	
	
	public String listAccounts(){
		StringBuilder sb = new StringBuilder();
		int n = 0;
		for (Account account : listOfAccounts){
			if (n++ != 0) sb.append("\n");		
			sb.append(account.toString());
		}
		return sb.toString();
	}
	
	/** Metoda umozliwia zapisanie kont do pliku */
	void saveSklepToFile(String fileName) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(listOfAccounts);
		out.close();
	}
	
	
	void loadSklepFromFile(String fileName) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		listOfAccounts = (ArrayList<Account>)in.readObject();
		in.close();
	}
	
	
}

