package Client;

import KnapsackProblem.Item;
import Server.ServerDesc;
import Server.ServerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;

public class ClientController {
	private Client client;
	public ArrayList<Item> listOfItems = new ArrayList<Item>();
	private Registry serverRegistry;
	private ServerInterface serverInterface;

	@FXML TextArea textAreaResult;
	@FXML ComboBox<ServerDesc> comboBoxServers;

	public void initialize() throws RemoteException, NotBoundException {
		setClient(new Client());
		client.setClientController(this);
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public TextArea getTextAreaResult() {
		return textAreaResult;
	}

	public ComboBox<ServerDesc> getComboBoxServers() {
		return comboBoxServers;
	}

	public void actionSolve() throws RemoteException, NotBoundException {
		textAreaResult.clear();
		String serverName = comboBoxServers.getSelectionModel().getSelectedItem().getName();
		int port = comboBoxServers.getSelectionModel().getSelectedItem().getPort();
		serverRegistry = LocateRegistry.getRegistry(port);
		serverInterface = (ServerInterface) serverRegistry.lookup(serverName);
		textAreaResult.setText(serverInterface.solve(listOfItems, 1000));
	}

	public void actionGenerateRandom() {
		int value, weight;
		Random random = new Random();
		listOfItems.clear();
		textAreaResult.clear();
		for (int i = listOfItems.size(); i < 15; i++)
			if (listOfItems.size() < 15) {
				weight = Math.abs(random.nextInt() % 1000);
				value = Math.abs(random.nextInt() % 100);
				
				Item item = new Item(i, weight, value);
				listOfItems.add(item);
				textAreaResult.setText(textAreaResult.getText() + i + ":  waga: " + item.getWeight() + " wartosc: "
						+ item.getValue() + "\n");

			}
	}
}
