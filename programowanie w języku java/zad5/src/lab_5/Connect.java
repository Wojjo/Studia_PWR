package lab_5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*aby utowrzyc polaczenie z baza danych nalezy:
1. pobrac oracle jdbc ze strony https://www.oracle.com/technetwork/database/application-development/jdbc/downloads/index.html
2. dodac jdbc do projektu: W tym celu klikamy PPM na nazwe naszego projektu ->Build Path->Configure Build Path,
przechodzimy do zakladki Libraries->Add External JARs i wybieramy biblioteke ojbc8.jar z katalogu do ktorego ja zapisalismy.
3. sprawdzamy dane dotyczace naszej bazy danych: musimy znalezc plik o nazwie tnsnames.ora jest on w katalogu bazy oracle w folderze \NETWORK\ADMIN\tnsnames.ora
4. tworzymy baze w oracle sql developer
connection_name: dowolone,
username: system,
password: haslo przy instalacji bazy
connection type: basic
role default
hostname: localhost
Port: odczytac z tnsnames.ora
SID: odczytac z tnsnames.ora = SERVICE_NAME
po utworzeniu takiej bazy mozna sie z nia polaczyc 
*/
public class Connect {

	static Connection connection = null;

	public void db_connect() {
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

	public void db_disconnect() {
		try {
			connection.close();
			System.out.println("Polaczenie z baza danych zakonczone");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean db_createWorker(int workerID, int salary, int personID) {
		try {
			PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO PRACOWNIK VALUES (?, ?, ?)");
			prepStmt.setInt(1, workerID);
			prepStmt.setInt(2, personID);
			prepStmt.setInt(3, salary);
			prepStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean db_createAddress(int adressID, String cityName, String street, int houseNumber, int flatNumber) {

		try {
			PreparedStatement prepStmt = connection
					.prepareStatement("INSERT INTO DANE_ADRESOWE VALUES (?, ?, ?, ?, ?)");
			prepStmt.setInt(1, adressID);
			prepStmt.setString(2, cityName);
			prepStmt.setString(3, street);
			prepStmt.setInt(4, houseNumber);
			prepStmt.setInt(5, flatNumber);
			prepStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean db_createPersonalData(int personID, String name, String last_name, int addressID) {

		try {

			PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO DANE_OSOBOWE VALUES (?, ?, ?, ?)");
			prepStmt.setInt(1, personID);
			prepStmt.setString(2, name);
			prepStmt.setString(3, last_name);
			prepStmt.setInt(4, addressID);
			prepStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<Address> db_loadData_addresses() {
		ArrayList<Address> arrayListAddresses = new ArrayList<Address>();

		try {
			String queryAdress = "SELECT ID_ADRESU, MIEJSCOWOSC, ULICA, NUMER_DOMU, NUMER_MIESZKANIA"
					+ " FROM DANE_ADRESOWE";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryAdress);

			while (rs.next()) {
				int address_id = rs.getInt("ID_ADRESU");
				String city_name = rs.getString("MIEJSCOWOSC");
				String street = rs.getString("ULICA");
				int house_number = rs.getInt("NUMER_DOMU");
				int flat_number = rs.getInt("NUMER_MIESZKANIA");

				arrayListAddresses.add(new Address(address_id, city_name, street, house_number, flat_number));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("adres");
		}

		return arrayListAddresses;
	}

	public ArrayList<PersonalData> db_loadData_personalData() {
		ArrayList<PersonalData> arrayListPersonalData = new ArrayList<PersonalData>();

		try {
			String queryPerson = "SELECT ID_OSOBY, IMIE, NAZWISKO, ID_ADRESU FROM DANE_OSOBOWE";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryPerson);

			while (rs.next()) {
				int person_ID = rs.getInt("ID_OSOBY");
				String first_name = rs.getString("IMIE");
				String last_name = rs.getString("NAZWISKO");
				int address_ID = rs.getInt("ID_ADRESU");

				arrayListPersonalData.add(new PersonalData(person_ID, first_name, last_name, address_ID));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("person");
		}

		return arrayListPersonalData;
	}

	public ArrayList<Worker> db_loadData_workers() {
		ArrayList<Worker> arrayListWorkers = new ArrayList<Worker>();

		try {
			String queryClient = "SELECT ID_PRACOWNIKA, ID_OSOBY, PENSJA FROM PRACOWNIK";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryClient);

			while (rs.next()) {
				int worker_ID = rs.getInt("ID_PRACOWNIKA");
				int person_ID = rs.getInt("ID_OSOBY");
				int salary = rs.getInt("PENSJA");

				arrayListWorkers.add(new Worker(worker_ID, salary, person_ID));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("worker");
		}

		return arrayListWorkers;
	}

	public boolean db_update_Worker(int id, int salary) {
		try {
			PreparedStatement prepStmt = connection
					.prepareStatement("UPDATE PRACOWNIK SET PENSJA = ? WHERE ID_PRACOWNIKA = ?");

			prepStmt.setInt(1, salary);
			prepStmt.setInt(2, id);
			prepStmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("update worker");
			return false;
		}
		return true;
	}

}
