package lab_5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//aby utowrzyc polaczenie z baza danych nalezy:
//1. pobrac oracle jdbc ze strony https://www.oracle.com/technetwork/database/application-development/jdbc/downloads/index.html
//2. dodac jdbc do projektu: W tym celu klikamy PPM na nazwe naszego projektu ->Build Path->Configure Build Path,
//przechodzimy do zakladki Libraries->Add External JARs i wybieramy biblioteke ojbc8.jar z katalogu do ktorego ja zapisalismy.
//3. sprawdzamy dane dotyczace naszej bazy danych: musimy znalezc plik o nazwie tnsnames.ora jest on w katalogu bazy oracle w folderze \NETWORK\ADMIN\tnsnames.ora
//4. tworzymy baze w oracle sql developer
//connection_name: dowolone,
//username: system,
//password: haslo przy instalacji bazy
//connection type: basic
//role default
//hostname: localhost
//Port: odczytac z tnsnames.ora
//SID: odczytac z tnsnames.ora = SERVICE_NAME
//po utworzeniu takiej bazy mozna sie z nia polaczyc 

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

	public boolean db_createAccount(int workerID, int salary, int personID) {
		try {
			PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO KONTO VALUES (?, ?, ?)");
			prepStmt.setInt(1, workerID);
			prepStmt.setInt(2, salary);
			prepStmt.setInt(3, personID);
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

			PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO Dane_Osobowe VALUES (?, ?, ?, ?)");
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

}
