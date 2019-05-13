import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.soap.*;

public class Client extends JFrame {

	private static final long serialVersionUID = 1L;
	private int serverPorts;
	private int[] ports;
	private String[] names;
	private String name;
	private String host = "127.0.0.1"; // localhost
	private List<String> sender = new ArrayList<String>();
	private List<String> meesages = new ArrayList<String>();
	private MessageFactory factory; // Format tworzenia SOAPMessage
	private SOAPConnection con; // Po³¹czenie typu punkt-punkt, którego klient mo¿e u¿yæ do wysy³ania wiadomoœci
								// bezpoœrednio do odbiorcy
	private Serwer server;

	public Client() {
	}

	public Client(int serverPorts, String name, int[] ports, String[] names)
			throws IOException, SOAPException, InterruptedException {
		initComponents();
		factory = MessageFactory.newInstance(); // Creates a new MessageFactory object that is an instance of the
												// specified implementation.
		con = SOAPConnectionFactory.newInstance().createConnection(); // A factory for creating SOAPConnection objects
		this.serverPorts = serverPorts;
		this.name = name;
		this.ports = ports;
		this.names = names;
		load();
		server = new Serwer(serverPorts, this);
		server.start();
	}

	public void connect(int portt, String msg, String receiver, String sender, String mode)
			throws IOException, SOAPException {
		Socket sock = new Socket(host, portt);
		System.out.println("Klient " + name + " polaczyl sie do portu " + portt);
		SOAPMessage myMsg = factory.createMessage();
		SOAPPart myPart = myMsg.getSOAPPart();	// The container for the SOAP-specific portion of a SOAPMessage object. All messages are required to have a SOAP part.
		SOAPEnvelope myEnv = myPart.getEnvelope();	// A SOAPPart object contains a SOAPEnvelope object, which in turn contains a SOAPBody object and a SOAPHeader object.
		SOAPBody myBody = myEnv.getBody();
		SOAPHeader myHeader = myEnv.getHeader();
		myHeader.addHeaderElement(myEnv.createName("HostNadawca", "", sender));
		myHeader.addHeaderElement(myEnv.createName("HostOdbiorca", "", receiver));
		myHeader.addHeaderElement(myEnv.createName("Typ adresacji", "", mode));
		myBody.addBodyElement(myEnv.createName("Message", "", msg));
		PrintStream out = new PrintStream(sock.getOutputStream(), true);
		myMsg.writeTo(out);
		System.out.println("Wiad wyslana do serwera ");
		sock.close();
	}

	// wyslanie wiadomosci do okreslonego hosta
	public void unicastWay(String hostS, String hostR, String mode, String message) throws Exception {
		if (name.equals(hostR)) {
			saveFromFile("D:/eclipse-workspace/Zad6/log/" + serverPorts + ".txt", hostS, message);
		}
		else {
			saveFromFile("D:/eclipse-workspace/Zad6/log/" + serverPorts + ".txt", "[" + hostS + "]",
					"Wiadomosc przeslana dalej");
			sendMessage(message, hostR, hostS, mode);
		}
	}
	// wyslanie wiadomosci do wszystkich oprocz hosta ktory nadaje wiadomosc
	public void broadcastWay(String hostS, String hostR, String mode, String message) throws Exception {
		if (!name.equals(hostS)) {
			saveFromFile("D:/eclipse-workspace/Zad6/log/" + serverPorts + ".txt", hostS, message);
			sendMessage(message, hostR, hostS, mode);
		}
	}

	public void loadFromFile(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = "";
		String splitBy = ";";
		String[] country = new String[2];
		sender = new ArrayList<String>();
		meesages = new ArrayList<String>();
		while ((line = br.readLine()) != null && !line.equals("")) {
			country = line.split(splitBy);
			sender.add(country[0]);
			meesages.add(country[1]);
		}
	}

	void load() {
		try {
			loadFromFile("D:/eclipse-workspace/Zad6/log/" + serverPorts + ".txt");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString(), "", JOptionPane.ERROR_MESSAGE);
		}
		list.setListData(new String[0]);
		String[] listData = new String[sender.size()];
		for (int i = 0; i < sender.size(); i++) {
			listData[i] = sender.get(i);
		}
		list.setListData(listData);
	}

	void saveFromFile(String fileName, String nadawca, String tresc) throws Exception {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		tresc = tresc.replace("\n", "\\replace");
		pw.println(nadawca + ";" + tresc);
		pw.close();
		load();
	}

	private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
		sendMessage(sendMessageText.getText(), nameText.getText(), name, "Unicast");

	}

	private void sendButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		sendMessage(sendMessageText.getText(), "C", name, "Broadcast");

	}

	public void sendMessage(String message, String receiver, String sender, String mode) {
		if (!message.equals("") && message != null) {
			for (int i = 0; i < names.length - 1; i++) {
				try {
					connect(ports[i + 1], message, receiver, sender, mode);
				} catch (Exception ex) {
					System.err.print(ex.toString());
				}
			}
		}
	}

	private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int id = list.getSelectedIndex();
		if (id != -1) {
			textArea.setText(meesages.get(id).replace("\\replace", "\n"));
		} else {
			JOptionPane.showMessageDialog(null, "Wybierz wiadomosc");
		}
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		nameText = new javax.swing.JTextField();

		jScrollPane1 = new javax.swing.JScrollPane();
		sendMessageText = new javax.swing.JTextArea();
		sendButton = new javax.swing.JButton();
		sendButton2 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		textArea = new javax.swing.JTextArea();
		jScrollPane3 = new javax.swing.JScrollPane();
		list = new javax.swing.JList<>();
		jLabel3 = new javax.swing.JLabel();
		portLabel = new javax.swing.JLabel();
		nameLabel = new javax.swing.JLabel();
		showButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Nazwa odbiorcy:");

		jLabel2.setText("Wprowadz wiadomosc:");

		sendMessageText.setColumns(20);
		sendMessageText.setRows(5);
		jScrollPane1.setViewportView(sendMessageText);

		sendButton.setText("Wyslij - Unicast");
		sendButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendButtonActionPerformed(evt);
			}
		});

		sendButton2.setText("Wyslij - Broadcast");
		sendButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendButton2ActionPerformed(evt);
			}
		});

		jScrollPane2.setEnabled(false);
		textArea.setEditable(false);
		textArea.setColumns(20);
		textArea.setRows(5);
		jScrollPane2.setViewportView(textArea);
		jScrollPane3.setViewportView(list);
		jLabel3.setText("Odebrane wiadomosci");
		showButton.setText("Wyswietl wiadomosc");
		showButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				showButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(60, 60, 60).addComponent(sendButton))
						.addGroup(layout.createSequentialGroup().addGap(60, 60, 60).addComponent(sendButton2))
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1).addComponent(jLabel2))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup().addComponent(nameText, 0,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGap(32, 32, 32)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(portLabel).addComponent(nameLabel))))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(jLabel3).addGap(67, 67, 67))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollPane2).addComponent(jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE, 205,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addComponent(showButton).addGap(76, 76, 76)))));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout.createSequentialGroup().addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING).addGroup(
												layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
														.addComponent(jScrollPane2,
																javax.swing.GroupLayout.PREFERRED_SIZE, 153,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout
												.createSequentialGroup().addGroup(layout.createParallelGroup(
														javax.swing.GroupLayout.Alignment.LEADING, false)
														.addGroup(layout.createSequentialGroup().addContainerGap()
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(jLabel1).addComponent(nameText,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18, 18).addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(jLabel2))
																.addGap(18, 18, 18)
																.addComponent(
																		jScrollPane1,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 161,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(sendButton)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(sendButton2))
														.addGroup(layout.createSequentialGroup().addComponent(portLabel)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(jLabel3)
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						jScrollPane3,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addComponent(nameLabel))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(showButton).addGap(72, 72, 72)))
												.addGap(0, 92, Short.MAX_VALUE)))
										.addContainerGap()));

		pack();
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Client().setVisible(true);
			}
		});
	}

	private javax.swing.JButton sendButton;
	private javax.swing.JButton sendButton2;
	private javax.swing.JButton showButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JList<String> list;
	private javax.swing.JLabel nameLabel;
	private javax.swing.JTextField nameText;
	private javax.swing.JLabel portLabel;
	private javax.swing.JTextArea sendMessageText;
	private javax.swing.JTextArea textArea;

}
