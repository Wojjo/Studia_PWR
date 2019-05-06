package Register;

import Server.ServerInfo;
import javafx.scene.control.TextArea;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServersRegistry extends UnicastRemoteObject implements ServersRegistryInterface {

	private static final long serialVersionUID = 1L;
	private Registry registry;
    private ArrayList<ServerInfo> servers = new ArrayList<ServerInfo>();
    private TextArea textArea;

    public ServersRegistry() throws RemoteException {
        super();
        registry = LocateRegistry.createRegistry(1099);
        registry.rebind("ListOfServers", this);
    }

    public boolean register(ServerInfo serverInfo) {
        boolean success = servers.add(serverInfo);
        textArea.clear();
        for(ServerInfo sd : servers)
            textArea.setText(textArea.getText() + "\n" + sd.getName() + " "+ sd.getDescription());

        return success;
    }

    public List<ServerInfo> getServers() {
        return servers;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}
