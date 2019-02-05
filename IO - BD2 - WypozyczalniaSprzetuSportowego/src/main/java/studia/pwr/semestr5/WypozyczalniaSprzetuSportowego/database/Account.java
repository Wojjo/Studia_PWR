package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database;
import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

	private static final long serailVersionUID = 1L;
	private String name;
	private String surname;
	private String login;
	private String city;
	private String street;
	private int number;
	private long passwordCode;

	public Account(String login, String name, String surname, String city, String street, int number) {
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.street = street;
		this.number = number;

		passwordCode = 0;

	}

	private ArrayList<Account> listOfAccounts = new ArrayList<Account>();

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public int getNumber() {
		return number;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return surname;
	}

	public Account createAccount(String login, String name, String surname, String city, String street, int number)
			throws Exception {
		if (login == null || login.equals(""))
			throw (new Exception("Nazwa konta nie moze byc pusta"));
		if (findAccount(login) != null)
			throw (new Exception("Konto juz istnieje"));
		Account newAccount = new Account(login, name, surname, city, street, number);
		listOfAccounts.add(newAccount);
		return newAccount;
	}

	public void removeAccount(Account account) throws Exception {
		if (account == null)
			throw (new Exception("Brak konta"));
		listOfAccounts.remove(account);
	}

	public Account findAccount(String login) {
		for (Account account : listOfAccounts)
			if (account.getLogin().equals(login))
				return account;
		return null;
	}

	public Account findAccount(String name, String password) {
		Account account = findAccount(name);
		if (account != null) {
			if (!account.checkPassword(password))
				account = null;
		}
		return account;
	}

	public String listAccounts() {
		StringBuilder sb = new StringBuilder();
		int n = 0;
		for (Account account : listOfAccounts) {
			if (n++ != 0)
				sb.append("\n");
			sb.append(account.toString());
		}
		return sb.toString();
	}

	public boolean checkPassword(String code) {
		if (code == null)
			return false;
		return code.hashCode() == passwordCode;

	}

	public void setPassword(String oldPassword, String newPassword) throws Exception {
	
		if (!checkPassword(oldPassword))
			throw new Exception("Bledne haslo");
															
		passwordCode = newPassword.hashCode();
	}
}
