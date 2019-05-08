package Server;

import KnapsackProblem.Greedy;
import KnapsackProblem.Item;
import KnapsackProblem.Brute_force;
import ServersRegistry.ServersRegistryInterface;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerInterface, Serializable {
	private static final long serialVersionUID = 2L;
	private Registry registry;
	private Registry serversListRegistry;
	private ServersRegistryInterface serversRegistryInterface;
	private ServerDesc serverDesc;
	private int port = 1100;

	public Server() throws RemoteException {
		try {
			
			while (port < 1300) {
				try {
					System.out.println("Serwer: " + (port - 1099) + " " + port);
					registry = LocateRegistry.createRegistry(port);
					registry.rebind("Server" + (port - 1099), this);
					String algorithm;
					if (port % 2 == 0)
						algorithm = "Algorytm Greedy";
					else
						algorithm = "Algorytm Brute Force";

					serverDesc = new ServerDesc("Server" + (port - 1099), port, algorithm);
					break;
				} catch (Exception e) {
					port++;
				}
			}

			serversListRegistry = LocateRegistry.getRegistry(1099);
			serversRegistryInterface = (ServersRegistryInterface) serversListRegistry.lookup("ListOfServers");
			serversRegistryInterface.register(serverDesc);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String solve(ArrayList<Item> listOfItems, int capacity) {
		if (port % 2 == 0) {
			Greedy gr = new Greedy(listOfItems, capacity, listOfItems.size());
			Greedy.startAlgorithm(capacity, listOfItems.size());
			return Greedy.result();
		} else {
			Brute_force bf = new Brute_force(listOfItems, capacity, listOfItems.size());
			Brute_force.startAlgorithm(capacity, listOfItems.size());
			return Brute_force.result();
		}
	}

	public static void main(String args[]) throws RemoteException {
		new Server();
	}

}
