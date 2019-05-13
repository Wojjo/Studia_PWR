
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import javax.xml.soap.SOAPException;


public class NetworkCommunication {

    public static void main(String[] args) throws UnknownHostException, MalformedURLException, IOException, SOAPException {
        Client[] client = new Client[5];
        int[] ports = {9001,9002,9003,9004};
        String[] names = {"A","B","C","D"};
        int[][] cPorts = new int[][]{{9001,9002},{9002,9003},{9003,9004}, {9004,9001}} ;
        String[][] cNames = {{"A","B"}, {"B","C"}, {"C","D"}, {"D","A"}};
        for(int i=0;i<4;i++){
            try {
            	client[i] = new Client(ports[i], names[i],cPorts[i],cNames[i]);
            } catch (Exception ex) {
                System.err.print(ex.toString());
                continue;
            }
            client[i].setTitle("Nazwa: " + names[i] + " Port: " + ports[i] );
            client[i].setResizable(false);
            client[i].setVisible(true);
        }
    }
    
}
