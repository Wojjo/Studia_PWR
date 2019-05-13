
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import javax.xml.soap.*;

public class Serwer extends Thread {
	private int port;
	private Client client;
	private MessageFactory factory;
	private SOAPConnection con;

	public Serwer(int port, Client client) {
		this.port = port;
		this.client = client;
	}

	public void run() {
		try {
			System.out.println("Otwarty serwer na porcie " + port);
			factory = MessageFactory.newInstance();
			con = SOAPConnectionFactory.newInstance().createConnection();
			ServerSocket serv = new ServerSocket(port);
			Socket sock;
			while (true) {
				sock = serv.accept();
				System.out.println("Polaczono z serwerem " + port);
				// odczytanie tresci
				BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				String inputLine = in.readLine();
				InputStream is = new ByteArrayInputStream(inputLine.getBytes());
				// stworzenie wiadomosci
				SOAPMessage myMsg = factory.createMessage(null, is);
				SOAPPart myPart = myMsg.getSOAPPart();
				SOAPEnvelope myEnv = myPart.getEnvelope();
				SOAPHeader myHeader = myEnv.getHeader();
				// odczytanie danych
				Iterator itr = myHeader.getChildElements();
				SOAPHeaderElement headerElement = (SOAPHeaderElement) itr.next();
				// klient wysylajacy wiad
				String hostS = headerElement.getNamespaceURI();
				headerElement = (SOAPHeaderElement) itr.next();
				// klient otrzymujacy wiad
				String hostR = headerElement.getNamespaceURI();
				headerElement = (SOAPHeaderElement) itr.next();
				// tryb adresacji
				String mode = headerElement.getNamespaceURI();
				// odczytanie wiadomosci
				SOAPBody myBody = myEnv.getBody();
				Iterator itr2 = myBody.getChildElements();
				SOAPBodyElement bodyElement = (SOAPBodyElement) itr2.next();
				String msg = bodyElement.getNamespaceURI();
				if (mode.equals("Broadcast")) {
					client.broadcastWay(hostS, hostR, mode, msg);
				} else {
					client.unicastWay(hostS, hostR, mode, msg);
				}
			}
		} catch (Exception ex) {
			System.err.print(ex.toString());
		}
	}
}