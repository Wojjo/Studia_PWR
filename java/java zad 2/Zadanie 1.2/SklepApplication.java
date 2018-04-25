/* 
 *  SklepApplication  
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
 *Program posiada funkcje:
 * <ul>
 *  <li> Tworzenie klienta konta, usuwanie konta.</li>
 *  <li> Zapisuje do pliku i wczytuje z pliku konta klientow, liste produktow, liste klientow ktorzy cos kupili </li>
 *  <li> Robienie zakupow, placenie za zakupy, wplacanie na konto pieniedzy.</li>
 *  <li> Administrator moze sprawdzic liste kont, sprawdzic ile zarobil, kto kupil jakie przedmioty </li>
 *  <li> Administrator moze uzupelnic zapasy produktow, dodac nowe, usunac niepotrzebne</li>
 *  <br>
 * </ul>
 *
 * @author Przemyslaw Wojcinowicz
 * @version 9.11.2016 r 
 */


class SklepApplication {
	
	public static void main(String[] args) {
		new SklepApplication();
	}
		
	/* UWAGA!!!!
	 * ABY STWORZYC KONTO WLASCICIELA SKLEPU TRZEBA UZYC LOGINU "Administrator"
	 * ------------------------------------------------------------------------
	 * Tu mozna wybrac sposob, w jaki aplikacja MiniBankApplication 
	 * bedzie sie komunikowala z uzytkownikiem.
	 * 
	 * Klasa ConsoleUserInterface implenentuje metody umozliwiajace
	 * wyswietlanie komunikatow oraz wczytywanie danych bezposrednio
	 * w konsoli, czyli wykorzystywane sa standardowe strumienie 
	 * wejecia/wyjycia:  System.out,  System.in,   System.err
	 * 
	 * Klasa ConsoleUserInterface implenentuje metody umozliwiajace
	 * wyswietlanie komunikatow oraz wczytywanie danych w oknach
	 * dialogowych wyswietlanych przez klase  JOptionPane.
	 * 
	 *  PONIZEJ PROSZE WYBRAC KLASE, KLASA, KTORA MA ZOSTAC UZYTA
	 *  DO REALIZACJI DIALOGU Z UZYTKOWNIKIEM.
	 *  (Prosze odkomentowac wybranie instrukcj ktora tworzy obiekt UI)
	 *  
	 *  
	 *  
	 *   UWAGA!!!!
	 * ABY STWORZYC KONTO WLASCICIELA SKLEPU TRZEBA UZYC LOGINU "Administrator"
	 * ------------------------------------------------------------------------
	 */
	//private UserDialog UI = new ConsoleUserDialog();
	  private UserDialog UI = new JOptionPaneUserDialog();
	

	private static final String HELLO_MESSAGE =
			"Program Sklep                 \n" +
			"Autor: Przyemys³aw Wojcinowicz\n" +
			"Data: 19 pazdziernika 2016 r. \n";

	private static final String MENU = 
			"Witamy w sklepie z butami       \n" +
			"1 - Utworz nowe konto           \n" +
			"2 - Zaloguj sie do konta        \n" + 
			"3 - Zaloguj sie jako wlasciciel \n" +
			"0 - Zakoncz program             \n";		
		
	private static final String ACCOUNT_MENU =
			"1 - Zrob zakupy                \n" +
	        "2 - Wplac na konto             \n" + 
	        "3 - Wyplac z konta             \n" +
			"4 - Zmien haslo                \n" +
			"5 - Zmien login                \n" +
			"6 - Szczegoly konta            \n" +
			"7 - Usun konto                 \n" +
			"0 - Wyloguj sie z konta        \n";
	
	private static final String ADMINISTRATOR_MENU =
			"1 - Wyswietl wszystkie produkty  \n" +
	        "2 - Wyswietl wszystkich klientow \n" +
			"3 - Dodaj nowy produkt           \n" +	
			"4 - Uzupelnij ilosc towaru       \n" +
			"5 - Zmien haslo                  \n" +
			"6 - Wyplac z konta               \n" +
			"7 - Szczegoly konta              \n" +
			"8 - Zmien cene produktu          \n" +
			"9 - Usun produkt                 \n" +
			"10 - Lista sprzedanych produktow \n" +
			"0 - Wyloguj sie z konta          \n";
		
	
	private static final String DATA_FILE_NAME = " Sklep.txt ";
	private static final String DATA_FILE_NAME2 = " Products.txt ";
	private static final String DATA_FILE_NAME3 = " Buyers.txt ";
		
	private Sklep sklep = new Sklep();
	private Products products = new Products();
	
	public SklepApplication()
	{
		UI.printMessage(HELLO_MESSAGE);
		
		try {
			sklep.loadSklepFromFile(DATA_FILE_NAME);
			products.loadSklepFromFile(DATA_FILE_NAME2);
			products.loadBuyersFromFile(DATA_FILE_NAME3);
			UI.printMessage("Konta zostaly wczytane z pliku " + DATA_FILE_NAME);
			UI.printMessage("Produkty zostaly wczytane z pliku " + DATA_FILE_NAME2);
			UI.printMessage("Lista sprzedanych rzeczy zostala wczytana: " + DATA_FILE_NAME3);
		} catch (Exception e) {
			UI.printErrorMessage(e.getMessage());
		}
		
		while (true) {
			UI.clearConsole();

			try {
				
				switch (UI.enterInt(MENU )) {
				
				case 1:
					createNewAccount();
					break;
				case 2:
					loginAccount();
					break;
				case 3:
					Administrator();
					break;
				case 0:
					try {
						sklep.saveSklepToFile(DATA_FILE_NAME);
						products.saveProductsToFile(DATA_FILE_NAME2);
						products.saveBuyersToFile(DATA_FILE_NAME3);
						UI.printMessage("Konta zostaly zapisane do pliku: " + DATA_FILE_NAME);
						UI.printMessage("Produkty zostaly zapisane: " + DATA_FILE_NAME2);
						UI.printMessage("Lista sprzedanych rzeczy zostala zapisana: " + DATA_FILE_NAME3);
					} catch (Exception e) {
						UI.printErrorMessage(e.getMessage());
					}

					UI.printInfoMessage("\n Program zakonczyl dzialanie! \n");
					System.exit(0);
				}  // end of switch(option)
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
			if (sklep.findAccount(newLogin)!=null) 
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
				newAccount = sklep.createAccount(newLogin, newName, newSurname, newCity, newStreet, newNumber);
				newAccount.setPassword("", newPassword);
			} catch (Exception e) {
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

		account = sklep.findAccount(login, password);
		if (login.equals("Administrator") ) {
			UI.printErrorMessage("Bledny login lub haslo");
			return;
		}
		if (account == null) {
			UI.printErrorMessage("Bledny login lub haslo");
			return;
		}

		while (true) {
			UI.printMessage("\n Witaj! \n ");
			UI.printMessage("     Login konta: "   + account.getLogin());
			UI.printMessage("     Imie: "           + account.getName());
			UI.printMessage("     Nazwisko: "    + account.getSurname());
			UI.printMessage("     Srodki na koncie: " + account.getBalance());
			try {

				switch (UI.enterInt(ACCOUNT_MENU + "==>> ")) {
				case 1:
					sellProduct (account);
					break;
				case 2:
					payInMoney(account, password);
					break;
				case 3:
				    payOut(account, password);
					break;	
				case 4:
					password = changePassword(account, password);
					break;
				case 5:
					changeSurname(account, password);
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
	
	
	public void Administrator()
	{
		String login, password;
		Account account;
		
		
		
		UI.printMessage("\nLogowanie jako administrator\n");

		login = UI.enterString("Login:");
		password = UI.enterString("Haslo:");
		account = sklep.findAccount(login, password);
		
		if (account == null) {
			UI.printErrorMessage("Bledny login lub haslo");
			return;
		}
		
		
		
		while (login.equals("Administrator")) {
			UI.printMessage("\n Witaj! \n ");
			UI.printMessage("     Login konta: "   + account.getLogin());
			UI.printMessage("     Imie: "           + account.getName());
			UI.printMessage("     Nazwisko: "    + account.getSurname());
			UI.printMessage("     Srodki na koncie: " + account.getBalance());
			
			try {

				switch (UI.enterInt(ADMINISTRATOR_MENU )) {
				case 1:
					listAllProducts();
				break;
				case 2:
					listAllAccounts();
					break;
				case 3:
					createNewProduct();
					break;
				case 4:
					supply();
					break;
				case 5:
					password = changePassword(account, password);
					break;
				case 6:
				    payOut(account, password);
					break;	
				case 7:
					accountDetails();
					break;
				case 8:
					changePrice();
					break;
				case 9:
					removeProduct();
					break;
				case 10:
					listAllBuyers();
					break;
					
					
			
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
	
	
	public void createNewProduct()
	{
		String newModel;
		String newBrand;
		double newPrice;
		int newQuantity;
		Product newProduct;
		
		
		UI.printMessage("\nTworzenie nowego produktu\n");
		while(true) {
			newModel = UI.enterString("Podaj model obuwia: ");
			if (newModel.equals("")) return;  // rezygnacja z tworzenia nowego konta
			if (products.findProduct(newModel)!=null) 
			{
				UI.printErrorMessage("Produkt juz istnieje");
				continue;
			}
						
			newBrand = UI.enterString("Marka: ");
			if(newBrand.equals("")) return;
			
			newPrice = UI.enterDouble("Cena: ");
           
			
			newQuantity = UI.enterInt("Ilosc sztuk: ");
			try {
				newProduct = products.createProduct(newModel, newBrand, newPrice, newQuantity);
			}
				
			catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
				continue;
			}
			
			
			UI.printMessage("Produkt dodany");
			break;
		}
	}
	
	public void sellProduct(Account account) throws Exception
	{
		listAllProducts();
		Product product;
		Product newBuyer;
		String model, who;
		who=account.getLogin();
		int howMany;
		double price;
		model = UI.enterString("Model:");
		product = products.findProduct(model);
		
		UI.printMessage("Marka "   + product.getBrand());
		UI.printMessage("Model "   + product.getModel());
		UI.printMessage("Cena "   + product.getPrice());
		UI.printMessage("Ilosc dostepnych par "   + product.getQuantity());
		
		howMany = UI.enterInt("Ile par:  ");
		
		product.sell(howMany);
		price = howMany*product.getPrice();
		transferMoney(account, price);
		
		try {
			newBuyer = products.addToBuyersList(who,model , price, howMany);
		}
			
		catch (Exception e) {
			UI.printErrorMessage(e.getMessage());
			//continue;
		}
		
	
		
	}
	public void supply() throws Exception
	{
		listAllProducts();
		String model;
		Product product;
		int howMuch;
		model = UI.enterString("Model:");
		product = products.findProduct(model);
		howMuch = UI.enterInt("Ile par dostarczono:  ");
		product.supply(howMuch);
	}
	
	
	
	public void changePrice() throws Exception
	{
		listAllProducts();
		String model;
		Product product;
		int howMuch;
		model = UI.enterString("Model:");
		product = products.findProduct(model);
		howMuch = UI.enterInt("Podaj nowa cene  ");
		product.changePrice(howMuch);
	}
	
	
	
	
	
	
	public  void listAllAccounts() 
	{
		StringBuilder list = new StringBuilder("\nLista Klientów:\n");
		list.append(sklep.listAccounts());		
		UI.printMessage(list.toString());
	}
	
	
	public  void listAllProducts() 
	{
		StringBuilder list = new StringBuilder("\nLista produktow:\n");
		list.append(products.listProduct());		
		UI.printMessage(list.toString());
	}
	public  void listAllBuyers() 
	{
		StringBuilder list = new StringBuilder("\nLista sprzedanych produktow:\n");
		list.append(products.listBuyers());		
		UI.printMessage(list.toString());
	}
	
	
	public void accountDetails()
	{
		String login, password;
		Account account;
		login = UI.enterString("Login:");
		password = UI.enterString("Haslo:");

		account = sklep.findAccount(login, password);
		UI.printMessage("\n Dane klienta \n ");
		UI.printMessage("     Miasto: "   + account.getCity());
		UI.printMessage("     Ulica: "           + account.getStreet());
		UI.printMessage("     Numer domu "    + account.getNumber());
		
	}
	
		
	public void payInMoney(Account account, String password)throws Exception 
	{
		double amount;
		UI.printMessage("\nWplata na konto");
		amount = UI.enterDouble("Podaj kwota: ");
		account.payIn(amount);
	}
	
	
	public void payOut(Account account, String password) throws Exception 
	{
		double amount;
		UI.printMessage("\n Wyplata");
		amount = UI.enterDouble("Podaj kwota: ");
		account.payOut( amount);
	}
	
	
	public void transferMoney(Account account, double price) throws Exception 
	{
		String destLogin="Administrator";
		Account destAccount;
		double amount=price;
		destAccount = sklep.findAccount(destLogin);
		if (destAccount == null) {
			UI.printErrorMessage("Nieznane konto docelowe");
			return;
		}
		
		account.transfer( amount, destAccount);
		UI.printMessage("\nPlatnosc wykonana poprawnie.");
	}
	
	
	public  String changePassword(Account account, String password) throws Exception
	{
		String newPassword;
		
		UI.printMessage("\nZmien haslo");
		newPassword = UI.enterString("Podaj nowe haslo: ");
		account.setPassword(password, newPassword);
		return newPassword;
	}
	
	
	public  void changeSurname(Account account, String password)
	{
		String newSurname;
		UI.printMessage("\nZmiana nazwiska wlasciciela konta");
		newSurname = UI.enterString("Podaj nazwisko: ");
		account.setSurname(newSurname);
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

		sklep.removeAccount(account);

		UI.printInfoMessage("\nKonto zostalo usuniete");
		return true;
	}
	
	public boolean removeProduct() throws Exception 
	{
		listAllProducts();
		String model;
		Product product;
		String answer;

		model = UI.enterString("Wybierz model obuwia ktory chcesz usunac:");
		product = products.findProduct(model);
		answer = UI.enterString("Czy na pewno usunac ten produkt? (TAK/NIE)");
		if (!answer.equals("TAK")) {
			UI.printErrorMessage("\nKonto nie zostalo usuniete");
			return false;
		}

		products.removeProduct(product);

		UI.printInfoMessage("\nProdukt usuniety ze sklepu");
		return true;
	}
	
}
