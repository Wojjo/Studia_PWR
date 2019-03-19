package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import models.Worker;

/**
 * Contains methods for generating queries sent to the database by means of a
 * connection session.
 */
public class SQLConnection {

	/**
	 * <p>
	 * This method is used to connect to a specific database. SQL statements are
	 * executed and the results are returned in the context of the connection.
	 * </p>
	 * 
	 * @return Connection This returns connection to a specific database.
	 */
	public Connection getConnection() {

		Connection connection = null;

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.sqlite.JDBC");
			// connection = DriverManager.getConnection(
			// "jdbc:mysql://localhost:3306/fitness_center?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			// "root", "");
			connection = DriverManager.getConnection("jdbc:sqlite::resource:database.sql");
			return connection;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	/**
	 * <p>
	 * This method is used to pass or not the customer through the login system to
	 * his panel.
	 * </p>
	 * 
	 * @param login    This is the parameter specifying the customer login
	 * @param password This is the parameter specifying the customer password
	 * @return long This returns id of the customer being logged in.
	 */
	public long customerLogin(String login, String password) {

		long customer_id = 0;
		try {

			Connection connection = getConnection();

			String query = "SELECT customer_id, login, password FROM customer WHERE login=? and password=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, login);

			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				customer_id = (rs.getInt("customer_id"));
				JOptionPane.showMessageDialog(null, "Login successful.");

			} else {
				JOptionPane.showMessageDialog(null, "The username or password is incorrect. Try again later!");
				return 0;
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return customer_id;
	}

	/**
	 * <p>
	 * This method is used to creating a new customer.
	 * </p>
	 * 
	 * @param firstName        This is the parameter specifying the customer first
	 *                         name
	 * @param lastName         This is the parameter specifying the customer last
	 *                         name
	 * @param login            This is the parameter specifying the customer login
	 * @param password         This is the parameter specifying the customer
	 *                         password
	 * @param repeatedPassword This is the parameter specifying the customer
	 *                         repeated password by the customer to verify its
	 *                         correctness
	 * @return boolean This returns success or failure to create a customer account.
	 */
	public boolean createCustomer(String firstName, String lastName, String login, String password,
			String repeatedPassword) {

		if (password.equals(repeatedPassword)) {
			try {

				Connection connection = getConnection();

				String query = "INSERT INTO customer (customer_id, first_name, last_name, login, password) VALUES (?,?,?,?,?) ;";

				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, null);
				ps.setString(2, firstName);
				ps.setString(3, lastName);
				ps.setString(4, login);
				ps.setString(5, password);
				ps.executeUpdate();
				ps.close();

				JOptionPane.showMessageDialog(null, "The user " + login + " has been created successfully.");
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect password. Try again!");
		}
		return false;
	}

	/**
	 * <p>
	 * This method is used returns the available funds on the customer's account.
	 * </p>
	 * 
	 * @param customerId This is the parameter specifying the customer id in
	 *                   database
	 * @return long This returns the available funds on the customer's account.
	 */
	public long getTotalBalance(long customerId) {
		long currentAccountBalance = 0;
		try {

			Connection connection = getConnection();

			String query = "SELECT funds FROM customer WHERE customer_id=? ";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, customerId);
			ResultSet rs = ps.executeQuery();
			if (rs.getInt("funds") >= 0) {
				if (rs.next()) {
					currentAccountBalance = (rs.getInt("funds"));

				} else {
					JOptionPane.showMessageDialog(null,
							"There was an unexpected problem with obtaining data about your account. Contact technical support.");
				}
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return currentAccountBalance;
	}

	/**
	 * <p>
	 * This method adds a top-up amount to the available funds on the customer's
	 * account
	 * </p>
	 * 
	 * @param customerId This is the parameter specifying the customer id in
	 *                   database
	 * @param funds      This is the parameter determining the amount of the top-up
	 *                   amount
	 */
	public void addFundsToYourAccount(long customerId, long funds) {

		long currentAccountBalance = 0;
		try {

			Connection connection = getConnection();

			String query = "SELECT funds FROM customer WHERE customer_id=? ";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, customerId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				currentAccountBalance = (rs.getInt("funds"));

			} else {
				JOptionPane.showMessageDialog(null,
						"There was an unexpected problem with obtaining data about your account. Contact technical support.");
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		long expectedAccountBalance = currentAccountBalance + funds;

		try {
			Connection connection = getConnection();

			String query = "UPDATE customer SET funds='" + expectedAccountBalance + "' WHERE customer_id=" + customerId
					+ ";";

			PreparedStatement st = connection.prepareStatement(query);
			st.executeUpdate();
			JOptionPane.showMessageDialog(null,
					"You have successfully added funds to your account. Your current account balance is: "
							+ expectedAccountBalance);
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
	
	public void removeFundsToYourAccount(long customerId, long funds) {

		long currentAccountBalance = 0;
		try {

			Connection connection = getConnection();

			String query = "SELECT funds FROM customer WHERE customer_id=? ";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, customerId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				currentAccountBalance = (rs.getInt("funds"));

			} else {
				JOptionPane.showMessageDialog(null,
						"There was an unexpected problem with obtaining data about your account. Contact technical support.");
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		long expectedAccountBalance = funds;

		try {
			Connection connection = getConnection();

			String query = "UPDATE customer SET funds='" + expectedAccountBalance + "' WHERE customer_id=" + customerId
					+ ";";

			PreparedStatement st = connection.prepareStatement(query);
			st.executeUpdate();
			JOptionPane.showMessageDialog(null,
					"You have successfully paid for course: "
							+ expectedAccountBalance);
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	
	
	

	/**
	 * <p>
	 * This method removes amount from the the available funds on the customer's
	 * account
	 * </p>
	 * 
	 * @param customerId This is the parameter specifying the customer id in
	 *                   database
	 * @param funds      This is the parameter determining the amount of removing
	 *                   the amount from the funds in the account
	 */
	public void removeFundsFromeYourAccount(long customerId, long funds) {

		long currentAccountBalance = 0;
		try {

			Connection connection = getConnection();

			String query = "SELECT funds FROM customer WHERE customer_id=? ";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, customerId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				currentAccountBalance = (rs.getInt("funds"));

			} else {
				JOptionPane.showMessageDialog(null,
						"There was an unexpected problem with obtaining data about your account. Contact technical support.");
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		long expectedAccountBalance = currentAccountBalance - funds;
		if (expectedAccountBalance >= 0) {
			try {
				Connection connection = getConnection();

				String query = "UPDATE customer SET funds='" + expectedAccountBalance + "' WHERE customer_id="
						+ customerId + ";";

				PreparedStatement st = connection.prepareStatement(query);
				st.executeUpdate();
				JOptionPane.showMessageDialog(null,
						"You have successfully completed the transaction. Your current account balance is: "
								+ expectedAccountBalance);
			} catch (Exception ex) {

				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"You do not have enough funds in your account to complete the transaction");
		}
	}

	/**
	 * <p>
	 * This method is used to pass or not the worker through the login system to his
	 * panel.
	 * </p>
	 * 
	 * @param login    This is the parameter specifying the worker login
	 * @param password This is the parameter specifying the worker password
	 * @return boolean This returns success or failure to log in into a worker
	 *         account.
	 */
	public Worker workerLogin(String login, String password) {

		try {

			Connection connection = getConnection();

			String query = "SELECT * FROM administrator WHERE login=? and password=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, login);

			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Login successful.");
				
				Worker logedWorker = new Worker();
				
				logedWorker.setId(rs.getInt(1)+"");
				logedWorker.setName(rs.getString(2));
				logedWorker.setSurname(rs.getString(3));
				
				return logedWorker;
			} else {
				JOptionPane.showMessageDialog(null, "The username or password is incorrect. Try again later!");
			}

			rs.close();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return null;
	}

	/**
	 * <p>
	 * This method is used to create a new group in the database.
	 * </p>
	 * 
	 * @param name                  This is the parameter specifying the name of the
	 *                              group
	 * @param type                  This is the parameter specifying the type of the
	 *                              group
	 * @param leader                This is the parameter specifying the leader of
	 *                              the group
	 * @param place                 This is the parameter specifying the place where
	 *                              classes take place
	 * @param numberOfFreePlaces    This is the parameter specifying the number of
	 *                              free places in the group
	 * @param numberOfOcupiedPlaces This is the parameter specifying the number of
	 *                              ocupied places in the group
	 * @param price                 This is the parameter specifying the price of
	 *                              enrollment for classes
	 * @param time                  This is the parameter specifying the time when
	 *                              classes take place
	 */
	public void createActivity(String name, String type, String leader, String place, long numberOfFreePlaces,
			long numberOfOcupiedPlaces, long price, String time) {

		try {

			Connection connection = getConnection();

			String query = "INSERT INTO activity (activity_id, name, type, leader, place, number_of_free_places, number_of_occupied_places, price, time) VALUES (?,?,?,?,?,?,?,?,?) ;";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, null);
			ps.setString(2, name);
			ps.setString(3, type);
			ps.setString(4, leader);
			ps.setString(5, place);
			ps.setLong(6, numberOfFreePlaces);
			ps.setLong(7, numberOfOcupiedPlaces);
			ps.setLong(8, price);
			ps.setString(9, time);
			ps.executeUpdate();
			ps.close();

			JOptionPane.showMessageDialog(null, "The group has been successfully created " + name);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	/**
	 * <p>
	 * This method is used to generate queries and extract data from the database
	 * for testing
	 * </p>
	 * 
	 * @param query     This is the parameter specifying query queries to the
	 *                  database
	 * @param queryData This is the parameter specifying data to extract from the
	 *                  database
	 * @return String This returns the data extracted from the database.
	 */
	public String theQuery(String query, String queryData) {

		String result = null;
		try {
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(queryData);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return result;
	}
}
