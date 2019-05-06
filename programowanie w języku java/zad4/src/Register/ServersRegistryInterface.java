package Register;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Server.ServerInfo;

public interface ServersRegistryInterface extends Remote {
    boolean register(ServerInfo serverDesc) throws RemoteException;
    List<ServerInfo> getServers() throws RemoteException;
}
