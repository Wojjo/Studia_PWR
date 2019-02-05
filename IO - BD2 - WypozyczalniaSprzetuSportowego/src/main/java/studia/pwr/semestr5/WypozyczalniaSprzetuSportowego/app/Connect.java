package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Address;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Assortment;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Client;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Model;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Person;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;

// aby utowrzyc polaczenie z baza danych nalezy:
// 1. pobrac oracle jdbc ze strony https://www.oracle.com/technetwork/database/application-development/jdbc/downloads/index.html
// 2. dodac jdbc do projektu: W tym celu klikamy PPM na nazw� naszego projektu ->Build Path->Configure Build Path,
// przechodzimy do zakladki Libraries->Add External JARs i wybieramy biblioteke ojbc8.jar z katalogu do ktorego ja zapisalismy.
// 3. sprawdzamy dane dotyczace naszej bazy danych: musimy znalezc plik o nazwie tnsnames.ora jest on w katalogu bazy oracle w folderze \NETWORK\ADMIN\tnsnames.ora
// 4. tworzymy baze w oracle sql developer
// connection_name: dowolone,
// username: system,
// password: haslo przy instalacji bazy
// connection type: basic
// role default
// hostname: localhost
// Port: odczytac z tnsnames.ora
// SID: odczytac z tnsnames.ora = SERVICE_NAME
// po utworzeniu takiej bazy mozna sie z nia polaczyc 

public class Connect {
	static Connection connection = null;
	 public void db_connect()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Sterowniki zaladowane");
			
			// jdbc:oracle:thin:username/password@localhost:port:SID
			connection = DriverManager.getConnection("jdbc:oracle:thin:system/Bazydanych2@localhost:1521:ORACLE");

			System.out.println("Polaczenie nawiazane");
			
		} catch (Exception e) {
			System.out.println("Blad polaczenia z baza danych");
		}
		
		
	}
	
	public void db_disconnect()
	{
		try {
			connection.close();
			System.out.println("Polaczenie z baza danych zakonczone");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean db_createAccount(int accountID, String login, String password, String security_question, String security_answer)
	{
		try {
			PreparedStatement prepStmt = connection.prepareStatement(
					"INSERT INTO KONTO VALUES (?, ?, ?, ?, ?)");
			prepStmt.setInt(1, accountID);
			prepStmt.setString(2, login);
			prepStmt.setString(3, password);
			prepStmt.setString(4, security_question);
			prepStmt.setString(5, security_answer);
			prepStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean db_createAddress(int adressID, String cityName, String postalCode, String street, int houseNumber,
			int flatNumber)
	{

		try {
			PreparedStatement prepStmt = connection.prepareStatement(
					"INSERT INTO DANE_ADRESOWE VALUES (?, ?, ?, ?, ?, ?)");
			prepStmt.setInt(1, adressID);
			prepStmt.setString(2, cityName);
			prepStmt.setString(3, postalCode);
			prepStmt.setString(4, street);
			prepStmt.setInt(5, houseNumber);
			prepStmt.setInt(6, flatNumber);
			prepStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;

    }

	
	public boolean db_createPerson(int personID, String name, String last_name, java.util.Date birth_date, int telephoneNumber , int addressID) 
	{
		java.sql.Date sDate = convertUtilToSql(birth_date);

		try {
		
			PreparedStatement prepStmt = connection.prepareStatement(
					"INSERT INTO Dane_Osobowe VALUES (?, ?, ?, ?, ?,?)");
			prepStmt.setInt(1, personID);
			prepStmt.setString(2, name);
			prepStmt.setString(3, last_name);
			prepStmt.setDate(4, sDate);
			prepStmt.setInt(5, telephoneNumber);
			prepStmt.setInt(6, addressID);
			prepStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean db_createClient(int clientID, int accountID, int personID, java.util.Date date, int orderQuantity, java.util.Date lastOrderDate, int suspendedAccount)
	{
	
		java.sql.Date sDate = convertUtilToSql(date);
		
		try {
			PreparedStatement prepStmt = connection.prepareStatement(
					"INSERT INTO KLIENT VALUES (?, ?, ?, ?, ?, ?, ?)");
			prepStmt.setInt(1, clientID);
			prepStmt.setInt(2, accountID);
			prepStmt.setInt(3, personID);
			prepStmt.setDate(4, sDate);
			prepStmt.setInt(5, orderQuantity);
			prepStmt.setDate(6, null);
			prepStmt.setInt(7, suspendedAccount);
			prepStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean db_createWorker(int workerID, int personID, int accountID, java.util.Date hireDate, int salary)
	{
	
		java.sql.Date sDate = convertUtilToSql(hireDate);
		
		try {
			PreparedStatement prepStmt = connection.prepareStatement(
					"INSERT INTO PRACOWNIK VALUES (?, ?, ?, ?, ?, ?)");
			prepStmt.setInt(1, workerID);
			prepStmt.setInt(2, accountID);
			prepStmt.setInt(3, personID);
			prepStmt.setDate(4, sDate);
			prepStmt.setInt(5, salary);
			prepStmt.setInt(6, 1);
			prepStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean db_createAssortment(int assortmentID, java.util.Date buyDate, int rentNumber, java.util.Date lastRentDate, int availability, java.util.Date dateNextMaintenance, String condition, int modelID)
	{
		java.sql.Date sDate = convertUtilToSql(buyDate);
		java.sql.Date sDate2 = convertUtilToSql(dateNextMaintenance);
		try {
			PreparedStatement prepStmt = connection.prepareStatement(
					"INSERT INTO SPRZET VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			prepStmt.setInt(1, assortmentID);
			prepStmt.setDate(2, sDate);
			prepStmt.setInt(3, 0);
			prepStmt.setDate(4, null);
			prepStmt.setInt(5, availability);
			prepStmt.setDate(6, sDate2);
			prepStmt.setString(7, condition);
			prepStmt.setInt(8, modelID);
			prepStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean db_createModel(int modelID, String modelName, String producer, String equipmentType, int season, int costPerDay,	int damageDeposit)
	{
		try {
			PreparedStatement prepStmt = connection.prepareStatement(
					"INSERT INTO MODEL VALUES (?, ?, ?, ?, ?, ?, ?)");
			prepStmt.setInt(1, modelID);
			prepStmt.setString(2, modelName);
			prepStmt.setString(3, producer);
			prepStmt.setString(4, equipmentType);
			prepStmt.setInt(5, season);
			prepStmt.setInt(6, costPerDay);
			prepStmt.setInt(7, damageDeposit);
			prepStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean db_createOrder(int orderID, java.util.Date orderDate, int accountID, int total_cost)
	{
		java.sql.Date sDate = convertUtilToSql(orderDate);
		try {
			PreparedStatement prepStmt = connection.prepareStatement(
					"INSERT INTO ZAMOWIENIE VALUES (?, ?, ?, ?)");
			prepStmt.setInt(1, orderID);
			prepStmt.setDate(2, sDate);
			prepStmt.setInt(3, accountID);
			prepStmt.setInt(4, total_cost);

			prepStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean db_orderedEquipment(int orderID, int assortmentID, java.util.Date orderStart, java.util.Date orderFinish)
	{
		java.sql.Date sDate = convertUtilToSql(orderStart);
		java.sql.Date sDate2 = convertUtilToSql(orderFinish);
		try {
			PreparedStatement prepStmt = connection.prepareStatement(
					"INSERT INTO ZAMOWIONY_SPRZET VALUES (?, ?, ?, ?)");
			prepStmt.setInt(1, orderID);
			prepStmt.setInt(2, assortmentID);
			prepStmt.setDate(3, sDate);
			prepStmt.setDate(4, sDate2);
				
			prepStmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<Person> db_loadData_people() {
		ArrayList<Person> arrayListPeople = new ArrayList<Person>();

		try {
			String queryPerson = "SELECT ID_OSOBY, IMIE, NAZWISKO, DATA_URODZENIA, NR_TELEFONU, ID_ADRESU FROM DANE_OSOBOWE";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryPerson);

			String queryAccountDate = "SELECT ID_KONTA, LOGIN, HASLO, PYTANIE, ODPOWIEDZ FROM KONTO";
			Statement stmt2 = connection.createStatement();
			ResultSet rs2 = stmt2.executeQuery(queryAccountDate);

			while (rs.next() && rs2.next()) {
				int person_ID = rs2.getInt("ID_KONTA");
				String first_name = rs.getString("IMIE");
				String last_name = rs.getString("NAZWISKO");
				Date birth_date = (Date) convertSqlToUtil(rs.getDate("DATA_URODZENIA"));
				int phone_number = rs.getInt("NR_TELEFONU");
				int address_ID = rs.getInt("ID_ADRESU");		
				String security_question = rs2.getString("PYTANIE");
				String security_answer = rs2.getString("ODPOWIEDZ");
				String login = rs2.getString("LOGIN");
				String password = rs2.getString("HASLO");

				arrayListPeople.add(new Person(person_ID, first_name, last_name, birth_date, phone_number, address_ID,
						login, password, security_question, security_answer));
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("person");
		}
		


		return arrayListPeople;
	}
	
	
	
	
	public ArrayList<Address> db_loadData_addresses() {
		ArrayList<Address> arrayListAddresses = new ArrayList<Address>();

		try {
			String queryAdress = "SELECT ID_ADRESU, MIEJSCOWOSC, KOD_POCZTOWY, ULICA, NUMER_DOMU, NUMER_MIESZKANIA"
					+ " FROM DANE_ADRESOWE";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryAdress);

			while (rs.next()) {
				int address_id = rs.getInt("ID_ADRESU");
				String city_name = rs.getString("MIEJSCOWOSC");
				String postal_code = rs.getString("KOD_POCZTOWY");
				String street = rs.getString("ULICA");
				String house_number = rs.getString("NUMER_DOMU");
				String flat_number = rs.getString("NUMER_MIESZKANIA");

				arrayListAddresses
						.add(new Address(address_id, city_name, postal_code, street, house_number, flat_number));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("adres");
		}

		return arrayListAddresses;
	}

	public ArrayList<Client> db_loadData_clients() {
		ArrayList<Client> arrayListClients = new ArrayList<Client>();

		try {
			String queryClient = "SELECT ID_KLIENTA, DATA_REJESTRACJI, ILOSC_ZAMOWIEN, DATA_OSTATNIEGO_ZAMOWIENIA"
					+ ", AKTYWNOSC_KONTA, ID_OSOBY FROM KLIENT";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryClient);

			while (rs.next()) {
				int client_ID = rs.getInt("ID_KLIENTA");
				Date registration_date = (Date) convertSqlToUtil(rs.getDate("DATA_REJESTRACJI"));
				int order_quantity = rs.getInt("ILOSC_ZAMOWIEN");
				Date last_order_date = (Date) convertSqlToUtil(rs.getDate("DATA_OSTATNIEGO_ZAMOWIENIA"));
				boolean suspended_account = false;
				if (rs.getString("AKTYWNOSC_KONTA").equals("1"))
					suspended_account = true;
				int person_ID = rs.getInt("ID_OSOBY");

				arrayListClients.add(new Client(client_ID, registration_date, order_quantity, last_order_date,
						suspended_account, person_ID));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("client");
		}

		return arrayListClients;
	}

	public ArrayList<Worker> db_loadData_workers() {
		ArrayList<Worker> arrayListWorkers = new ArrayList<Worker>();

		try {
			String queryClient = "SELECT ID_KONTA, ID_OSOBY, DATA_ZATRUDNIENIA, PENSJA FROM PRACOWNIK";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryClient);

			while (rs.next()) {
				int worker_ID = rs.getInt("ID_KONTA");
				int person_ID = rs.getInt("ID_OSOBY");
				Date hire_date = rs.getDate("DATA_ZATRUDNIENIA");
				int salary = rs.getInt("PENSJA");
				

				arrayListWorkers.add(new Worker(worker_ID, hire_date, salary,0, person_ID, true));
			
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("worker");
		}

		return arrayListWorkers;
	}
	
	
	
	public ArrayList<Model> db_loadData_models() {
		ArrayList<Model> arrayListModels = new ArrayList<Model>();

		try {
			String queryModel = "SELECT ID_MODELU, NAZWA_SPRZETU, PRODUCENT, KATEGORIA, SEZON_UZYTKOWY, CENA_ZA_DZIEN,"
					+ " KAUCJA_ZA_ZNISZCZENIE FROM MODEL";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryModel);

			while (rs.next()) {
				int model_ID = rs.getInt("ID_MODELU");
				String model_name = rs.getString("NAZWA_MODELU");
				String producer = rs.getString("PRODUCENT");
				String equipment_type = rs.getString("RODZAJ_SPRZĘTU");
				boolean season = false;
				if (rs.getString("SEZON_UZYTKOWY").equals("1"))
					season = true;
				int cost_per_day = rs.getInt("CENA_DZIEN");
				int damage_deposit = rs.getInt("KAUCJA_ZA_ZNISZCZENIE");

				arrayListModels.add(new Model(model_ID, model_name, producer, equipment_type, season, cost_per_day,
						damage_deposit));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("model");
		}

		return arrayListModels;
	}

	public ArrayList<Assortment> db_loadData_assortment() {
		ArrayList<Assortment> arrayListAssortment = new ArrayList<Assortment>();

		try {
			String queryAssortment = "SELECT ID_SPRZETU, DATA_ZAKUPU, LICZBA_WYPOZYCZEN, DATA_OSTATNIEGO_WYPOZYCZENIA,"
					+ " DOSTEPNOSC, DATA_KONSERWACJI, STAN, ID_MODELU FROM SPRZET";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryAssortment);

			while (rs.next()) {
				int item_ID = rs.getInt("ID_SPRZETU");
				Date pucharse_date = convertSqlToUtil(rs.getDate("DATA_ZAKUPU"));
				int loans_number = rs.getInt("LICZBA_WYPOZYCZEN");
				Date last_loan_date = convertSqlToUtil(rs.getDate("DATA_OSTATNIEGO_WYPOZYCZENIA"));
				boolean availability = false;
				if (rs.getBoolean("DOSTEPNOSC"))
					availability = true;
				Date next_maintenance_date = rs.getDate("DATA_NASTEPNEJ_KONSERWACJI");
				String condition = rs.getString("STAN");
				int model_ID = rs.getInt("ID_MODELU");

				ArrayList<Integer> arrayList_order_ID = new ArrayList<Integer>();
				ArrayList<Date> arrayList_equipment_loan_date = new ArrayList<Date>();
				ArrayList<Integer> arrayList_equipment_loan_length = new ArrayList<Integer>();

				String query_equipment_ID_and_length = "SELECT NUMER_ZAMOWIENIA, DATA_ROZPOCZECIA, DLUGOSC_ZAMOWIENIA"
						+ " FROM administrator.Zamowienia_Asortyment WHERE ID_SPRZETU = " + item_ID;
				Statement stmt2 = connection.createStatement();
				ResultSet rs2 = stmt2.executeQuery(query_equipment_ID_and_length);

				while (rs2.next()) {
					arrayList_order_ID.add(rs2.getInt("NUMER_ZAMOWIENIA"));
					arrayList_equipment_loan_date.add(convertSqlToUtil(rs2.getDate("DATA_ROZPOCZECIA")));
					arrayList_equipment_loan_length.add(rs2.getInt("DLUGOSC_ZAMOWIENIA"));
				}

				arrayListAssortment.add(new Assortment(item_ID, pucharse_date, loans_number, last_loan_date,
					availability, next_maintenance_date, condition, model_ID));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("assortment");
		}

		return arrayListAssortment;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private Date convertSqlToUtil(java.sql.Date date) {
		try {
			String[] parts = date.toString().split("-");
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, Integer.parseInt(parts[0]));
			c.set(Calendar.MONTH, Integer.parseInt(parts[1]));
			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(parts[2]));
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			return (Date) (c.getTime());
		} catch (Exception ex) {
			return null;
		}
	}

}