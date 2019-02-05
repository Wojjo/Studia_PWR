package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;

import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Account;

public class RentalOfSportEquipmentApp
{
	public static void main(String[] args) {
		new RentalOfSportEquipmentApp();
	}
		
	
	  private UserDialog UI = new JOptionPaneUserDialog();
	

	private static final String MAIN_MENU = 
			"Witamy w wypo�yczalni sprz�tu sportowego       \n" +
			"1 - Utworz nowe konto           \n" +
			"2 - Zaloguj sie jako klient     \n" + 
			"3 - Zaloguj sie jako pracownik  \n" +
			"0 - Zakoncz program             \n";		
		
	private static final String CLIENT_MENU =
			"1 - Przegladaj sprzet          \n" +
	        "2 - Wypozycz sprzet            \n" + 
	        "3 - Historia zamowien          \n" +
	        "5 - Szczegoly konta            \n" +
			"4 - Zmien haslo                \n" +
			"6 - Usun konto                 \n" +
			"0 - Wyloguj sie 		        \n";
	
	private static final String WORKER_MENU =
			"1 - Przegladaj sprzet 			  \n" +
	        "2 - Wyswietl wszystkich klientow \n" +
			"3 - Dodaj nowy sprzet            \n" +	
			"4 - Usun sprzet		          \n" +
			"5 - Zmien haslo                  \n" +
			"6 - Szczegoly konta              \n" +
			"7 - Zmien dane sprzetu           \n" +
			"0 - Wyloguj sie z konta          \n";


	private Account account1 = new Account(null, null, null, null, null, 0);
	
	
	public RentalOfSportEquipmentApp()
	{
					
		while (true) {
			UI.clearConsole();

			try {
				
				switch (UI.enterInt(MAIN_MENU )) {
				
				case 1:
					createNewAccount();
					break;
				case 2:
					loginAccount1();
					break;
				case 3:
					
					break;
				case 0:
					UI.printInfoMessage("\n Program zakonczyl dzialanie \n");
					System.exit(0);
				}  
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	public void loginAccount1()
	{
		String login, password;
		Account account;

		UI.printMessage("\nLogowanie do konta\n");

		login = UI.enterString("Login:");
		password = UI.enterString("Haslo:");

		account = account1.findAccount(login, password);
		
		if (account == null) {
			UI.printErrorMessage("Bledny login lub haslo");
			return;
		}

		while (true) {
			UI.printMessage("\n Witaj! \n ");
			UI.printMessage("     Login konta: "   + account.getLogin());
			UI.printMessage("     Imie: "           + account.getName());
			UI.printMessage("     Nazwisko: "    + account.getSurname());
			
			try {

				switch (UI.enterInt(CLIENT_MENU + "==>> ")) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
				  
					break;	
				case 4:
					password = changePassword(account, password);
					break;
				case 5:
				
					break;		
				case 6:
					accountDetails();
					break;
				case 7:
					if (removeAccount(account, password) == false)
						break;
					account = null;
					return;
				
				case 0:
					account = null;
					UI.printMessage("Nastapilo wylogowanie z konta");
					return;
				}
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	

public  void createNewAccount() 
{	
	String newLogin;
	String newName;
	String newSurname;
	String newPassword;
	String newCity ;
	String newStreet;
	int newNumber;
	Account newAccount;
	
	
	UI.printMessage("\nTworzenie nowego konta\n");
	while(true) {
		newLogin = UI.enterString("Podaj login: ");
		if (newLogin.equals("")) return;  // rezygnacja z tworzenia nowego konta
		if (account1.findAccount(newLogin)!=null) 
		{
			UI.printErrorMessage("Konto juz istnieje");
			continue;
		}
		newName = UI.enterString("Imie: ");
		if(newName.equals("")) return;
		
		newSurname = UI.enterString("Nazwisko: ");
		if(newSurname.equals("")) return;
		
		newCity = UI.enterString("Miasto: ");
		if(newCity.equals("")) return;
		
		newStreet = UI.enterString("Ulica: ");
		if(newStreet.equals("")) return;
		
		newNumber = UI.enterInt("Numer domu: ");
				
		
		newPassword = UI.enterString("Podaj haslo: ");	
		try {
			newAccount = account1.createAccount(newLogin, newName, newSurname, newCity, newStreet, newNumber);
			newAccount.setPassword("", newPassword);
		} 
		catch (Exception e) {
			UI.printErrorMessage(e.getMessage());
			continue;
		}
		
		
		UI.printMessage("Konto zostalo utworzone");
		break;
	}
}

public void loginAccount()
{
	String login, password;
	Account account;
	

	UI.printMessage("\nLogowanie do konta\n");

	login = UI.enterString("Login:");
	password = UI.enterString("Haslo:");

	account = account1.findAccount(login, password);
	
	if (account == null) {
		UI.printErrorMessage("Bledny login lub haslo");
		return;
	}

	while (true) {
		UI.printMessage("\n Witaj! \n ");
		UI.printMessage("     Login konta: "   + account.getLogin());
		UI.printMessage("     Imie: "           + account.getName());
		UI.printMessage("     Nazwisko: "    + account.getSurname());
		
		try {

			switch (UI.enterInt(CLIENT_MENU + "==>> ")) {
			case 1:
				//sellProduct (account);
				break;
			case 2:
				//payInMoney(account, password);
				break;
			case 3:
			   // payOut(account, password);
				break;	
			case 4:
				password = changePassword(account, password);
				break;
			case 5:
				//changeSurname(account, password);
				break;		
			case 6:
				//accountDetails();
				break;
			case 7:
				if (removeAccount(account, password) == false)
					break;
				account = null;
				return;
			
			case 0:
				account = null;
				UI.printMessage("Nastapilo wylogowanie z konta");
				return;
			}
		} catch (Exception e) {
			UI.printErrorMessage(e.getMessage());
		}
	}
}

public void accountDetails()
{
	String login, password;
	Account account;
	login = UI.enterString("Login:");
	password = UI.enterString("Haslo:");

	account = account1.findAccount(login, password);
	UI.printMessage("\n Dane klienta \n ");
	UI.printMessage("     Miasto: "   + account.getCity());
	UI.printMessage("     Ulica: "           + account.getStreet());
	UI.printMessage("     Numer domu "    + account.getNumber());
	
}


public boolean removeAccount(Account account, String password) throws Exception 
{
	String answer;

	UI.printMessage("\nUsun konto");
	answer = UI.enterString("Czy na pewno usunac to konto? (TAK/NIE)");
	if (!answer.equals("TAK")) {
		UI.printErrorMessage("\nKonto nie zostalo usuniete");
		return false;
	}

	account.removeAccount(account);

	UI.printInfoMessage("\nKonto zostalo usuniete");
	return true;
}


public  String changePassword(Account account, String password) throws Exception
{
	String newPassword;
	
	UI.printMessage("\nZmien haslo");
	newPassword = UI.enterString("Podaj nowe haslo: ");
	account.setPassword(password, newPassword);
	return newPassword;
}

}










