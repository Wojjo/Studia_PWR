package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app.HighlightTest.HighlightEvaluator;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Account;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Address;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Assortment;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Client;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.MaintenanceHistory;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Model;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.OrderHistory;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Person;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.RepairHistory;
import studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database.Worker;

public class MainWindow {
	private JFrame mainFrame;
	private boolean loggedIn;
	private int loggedID;

	ArrayList<Client> arrayListClients;
	ArrayList<Worker> arrayListWorkers;
	ArrayList<Person> arrayListPeople;
	ArrayList<Account> arrayListAccounts;
	ArrayList<Address> arrayListAddresses;
	ArrayList<Assortment> arrayListAssortment;
	ArrayList<Model> arrayListModels;
	ArrayList<OrderHistory> arrayListOrders;
	ArrayList<MaintenanceHistory> arrayListMaintenances;
	ArrayList<RepairHistory> arrayListRepairs;

	ArrayList<Assortment> arrayListCartItems;
	ArrayList<Date> arrayListCartDates;
	ArrayList<Integer> arrayListCartLength;

	// elementy ekranu startowego aplikacji
	private List<Component> mainScreenComponents;
	JLabel labelLogo;
	JButton buttonLogin;
	JButton buttonBrowseEquipment;
	JButton buttonCreateAccount;
	JButton buttonFirmInfo;
	JButton buttonPopularItemPhoto1;
	JLabel labelPopularItemDesc1;
	JButton buttonPopularItemPhoto2;
	JLabel labelPopularItemDesc2;
	JButton buttonPopularItemPhoto3;
	JLabel labelPopularItemDesc3;
	JButton buttonPopularItemPhoto4;
	JLabel labelPopularItemDesc4;
	JButton buttonPopularItemPhoto5;
	JLabel labelPopularItemDesc5;
	JLabel labelLoggedAs;

	// elementy ekranu logowania
	private List<Component> loginScreenComponents;
	JTextField textFieldLogin;
	JPasswordField passwordFieldPassword;
	JCheckBox checkBoxShowPassword;
	JButton buttonLogIn;
	JButton buttonRemindPassword;
	JButton buttonCreateAccount1;
	JButton buttonReturnToMainScreen;
	JLabel labelCorrectData;

	// elementy przegląania sprzętu
	private List<Component> itemBrowseScreenComponents;
	JLabel labelLogo1;
	JButton buttonLogin1;
	JButton buttonCreateAccount2;
	JButton buttonToCart;
	JButton buttonSearch;
	JButton buttonFilter;
	JButton buttonReturnToMainScreen2;
	JTextField textFieldItemName;
	JPanel panelItems; // tutaj zaczynają się elementy scrollowalnego panelu
	GridBagConstraints gridBagConstraintsBrowse;
	JScrollPane scrollPaneItemPanel;
	List<JLabel> listOfAllItemsNames;
	List<JButton> listOfAllButtonPhotos;

	// elementy ekranu dla każdego ze sprzętów
	private List<Component> itemInfoScreenComponents;
	JLabel labelLogo2;
	JButton buttonReturnToBrowse;
	JButton buttonLogin2;
	JButton buttonCreateAccount3;
	JButton buttonToCart2;
	JLabel labelItemPhoto;
	JLabel labelItemName;
	JButton buttonAddToCart;
	JTextPane textPaneItemDescription;
	JLabel labelLength;
	JTextField textFieldlength;
	JCalendar calendar;
	HighlightEvaluator evaluator;
	HighlightEvaluator evaluatorAllItems;
	int selectedMonth;
	Model selectedModel;
	JLabel labelItemsOfModel;
	JComboBox<Integer> comboBoxItemsOfModel;
	JLabel labelErrorDate;

	// elementy ekranu akcji pracownika
	private List<Component> workerActionsComponents;
	JButton buttonReturnToMainScreen3;
	JButton buttonBrowseOrders;
	JButton buttonManageAccounts;
	JButton buttonManageModels;
	JButton buttonManageEqupiment;
	JButton buttonManageMaintenances;
	JButton buttonManageRepairs;
	JButton buttonAddWorker;
	JButton buttonLogOut;
	JPanel panelManageDataAsWorker;
	JScrollPane scrollPaneDBContent;
	GridBagConstraints gridBagConstraintsWorkerActions;
	List<JButton> listOfDBContent;

	// elementy ekranu koszyk
	private List<Component> cartActionsComponents;
	JLabel labelLogo3;
	JLabel labelRemoved;
	JButton buttonReturnToMainScreen4;
	JButton buttonReturnToBrowse2;
	JButton buttonLogin3;
	JButton buttonCreateAccount4;
	JButton buttonOrder;
	JPanel panelItems2;
	JScrollPane scrollPaneItemPanel2;
	GridBagConstraints gridBagConstraintsBrowse2;

	// elementy widziane/nie widziane niezależnie od ekranu;
	JButton buttonWorkerActions;

	public MainWindow() {
		initVariables();
		new TestData(this);
		initComponents(); // tylko tworzenie i dodawanie elementów do okna
		initListeners(); // tworzenie i obsługa listenerów,

		for (Component c : mainScreenComponents)
			c.setVisible(true);
		for (Component c : loginScreenComponents)
			c.setVisible(false);
		for (Component c : itemBrowseScreenComponents)
			c.setVisible(false);
		for (Component c : itemInfoScreenComponents)
			c.setVisible(false);
		for (Component c : workerActionsComponents)
			c.setVisible(false);
		for (Component c : cartActionsComponents)
			c.setVisible(false);
		mainFrame.setVisible(true);
	}

	private void initVariables() {
		loggedIn = false;
		
		Connect oracle = new Connect();
	

		//arrayListClients = new ArrayList<Client>();
		//arrayListWorkers = new ArrayList<Worker>();
		//arrayListPeople = new ArrayList<Person>();
		//arrayListAccounts = new ArrayList<Account>();
		//arrayListAddresses = new ArrayList<Address>();
		arrayListAssortment = new ArrayList<Assortment>();
		arrayListModels = new ArrayList<Model>();
		arrayListOrders = new ArrayList<OrderHistory>();
		arrayListMaintenances = new ArrayList<MaintenanceHistory>();
		arrayListRepairs = new ArrayList<RepairHistory>();
		
		oracle.db_connect();

		arrayListClients = oracle.db_loadData_clients();
		arrayListWorkers = oracle.db_loadData_workers();
		arrayListPeople = oracle.db_loadData_people();
		arrayListAddresses = oracle.db_loadData_addresses();
		arrayListAccounts = new ArrayList<Account>();

	//	arrayListModels = oracle.dbQueryModels();
	//	arrayListAssortment = oracle.dbQueryAssortment();
	//	arrayListOrders = oracle.dbQueryOrders();
		//arrayListPopularItems = oracle.dbQueryPopularItems();
		oracle.db_disconnect();

		arrayListCartItems = new ArrayList<Assortment>();
		arrayListCartDates = new ArrayList<Date>();
		arrayListCartLength = new ArrayList<Integer>();

		mainScreenComponents = new ArrayList<Component>();
		loginScreenComponents = new ArrayList<Component>();
		itemBrowseScreenComponents = new ArrayList<Component>();
		itemInfoScreenComponents = new ArrayList<Component>();
		workerActionsComponents = new ArrayList<Component>();
		cartActionsComponents = new ArrayList<Component>();
	}

	private void initComponents() {
		// ELEMENTY EKRANU STARTOWEGO
		// ------------------------------------------------------------------------------------
		mainFrame = new JFrame("Wypozyczalnia sprzetu sportowego");
		mainFrame.setSize(new Dimension(1280, 720));
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);

		labelLogo = new JLabel();
		labelLogo.setIcon(new ImageIcon(getClass().getResource("/Resources/Logo.png")));
		labelLogo.setBounds(800, 50, 320, 320);
		mainFrame.add(labelLogo);
		mainScreenComponents.add(labelLogo);

		buttonLogin = new JButton("Zaloguj się");
		buttonLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonLogin.setBounds(800, 420, 320, 40);
		mainFrame.add(buttonLogin);
		mainScreenComponents.add(buttonLogin);

		buttonBrowseEquipment = new JButton("Przeglądaj sprzęt");
		buttonBrowseEquipment.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonBrowseEquipment.setBounds(800, 480, 320, 40);
		mainFrame.add(buttonBrowseEquipment);
		mainScreenComponents.add(buttonBrowseEquipment);

		buttonCreateAccount = new JButton("Załóż konto");
		buttonCreateAccount.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonCreateAccount.setBounds(800, 540, 320, 40);
		mainFrame.add(buttonCreateAccount);
		mainScreenComponents.add(buttonCreateAccount);

		buttonFirmInfo = new JButton("O wypożyczalni");
		buttonFirmInfo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonFirmInfo.setBounds(800, 600, 320, 40);
		mainFrame.add(buttonFirmInfo);
		mainScreenComponents.add(buttonFirmInfo);

		buttonPopularItemPhoto1 = new JButton();
		buttonPopularItemPhoto1.setIcon(new ImageIcon(getClass().getResource("/Resources/popularnyprzedmiot.png")));
		buttonPopularItemPhoto1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonPopularItemPhoto1.setBounds(100, 60, 150, 100);
		mainFrame.add(buttonPopularItemPhoto1);
		mainScreenComponents.add(buttonPopularItemPhoto1);

		labelPopularItemDesc1 = new JLabel("Popularny przedmiot #1");
		labelPopularItemDesc1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		labelPopularItemDesc1.setBounds(300, 60, 400, 100);
		mainFrame.add(labelPopularItemDesc1);
		mainScreenComponents.add(labelPopularItemDesc1);

		buttonPopularItemPhoto2 = new JButton();
		buttonPopularItemPhoto2.setIcon(new ImageIcon(getClass().getResource("/Resources/popularnyprzedmiot.png")));
		buttonPopularItemPhoto2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonPopularItemPhoto2.setBounds(100, 180, 150, 100);
		mainFrame.add(buttonPopularItemPhoto2);
		mainScreenComponents.add(buttonPopularItemPhoto2);

		labelPopularItemDesc2 = new JLabel("Popularny przedmiot #2");
		labelPopularItemDesc2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		labelPopularItemDesc2.setBounds(300, 180, 400, 100);
		mainFrame.add(labelPopularItemDesc2);
		mainScreenComponents.add(labelPopularItemDesc2);

		buttonPopularItemPhoto3 = new JButton();
		buttonPopularItemPhoto3.setIcon(new ImageIcon(getClass().getResource("/Resources/popularnyprzedmiot.png")));
		buttonPopularItemPhoto3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonPopularItemPhoto3.setBounds(100, 300, 150, 100);
		mainFrame.add(buttonPopularItemPhoto3);
		mainScreenComponents.add(buttonPopularItemPhoto3);

		labelPopularItemDesc3 = new JLabel("Popularny przedmiot #3");
		labelPopularItemDesc3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		labelPopularItemDesc3.setBounds(300, 300, 400, 100);
		mainFrame.add(labelPopularItemDesc3);
		mainScreenComponents.add(labelPopularItemDesc3);

		buttonPopularItemPhoto4 = new JButton();
		buttonPopularItemPhoto4.setIcon(new ImageIcon(getClass().getResource("/Resources/popularnyprzedmiot.png")));
		buttonPopularItemPhoto4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonPopularItemPhoto4.setBounds(100, 420, 150, 100);
		mainFrame.add(buttonPopularItemPhoto4);
		mainScreenComponents.add(buttonPopularItemPhoto4);

		labelPopularItemDesc4 = new JLabel("Popularny przedmiot #4");
		labelPopularItemDesc4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		labelPopularItemDesc4.setBounds(300, 420, 400, 100);
		mainFrame.add(labelPopularItemDesc4);
		mainScreenComponents.add(labelPopularItemDesc4);

		buttonPopularItemPhoto5 = new JButton();
		buttonPopularItemPhoto5.setIcon(new ImageIcon(getClass().getResource("/Resources/popularnyprzedmiot.png")));
		buttonPopularItemPhoto5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonPopularItemPhoto5.setBounds(100, 540, 150, 100);
		mainFrame.add(buttonPopularItemPhoto5);
		mainScreenComponents.add(buttonPopularItemPhoto5);

		labelPopularItemDesc5 = new JLabel("Popularny przedmiot #5");
		labelPopularItemDesc5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		labelPopularItemDesc5.setBounds(300, 540, 400, 100);
		mainFrame.add(labelPopularItemDesc5);
		mainScreenComponents.add(labelPopularItemDesc5);

		labelLoggedAs = new JLabel();
		labelLoggedAs.setBounds(30, 650, 350, 30);
		mainFrame.add(labelLoggedAs);
		mainScreenComponents.add(labelLoggedAs);

		// ELEMENTY EKRANU LOGOWANIA
		// ------------------------------------------------------------------------------------
		textFieldLogin = new JTextField("Login");
		textFieldLogin.setBounds(500, 250, 300, 40);
		textFieldLogin.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		textFieldLogin.setForeground(Color.GRAY);
		mainFrame.add(textFieldLogin);
		loginScreenComponents.add(textFieldLogin);

		passwordFieldPassword = new JPasswordField("Haslo");
		passwordFieldPassword.setBounds(500, 300, 300, 40);
		passwordFieldPassword.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		passwordFieldPassword.setEchoChar((char) 0);
		passwordFieldPassword.setForeground(Color.GRAY);
		mainFrame.add(passwordFieldPassword);
		loginScreenComponents.add(passwordFieldPassword);

		checkBoxShowPassword = new JCheckBox("Pokaż hasło");
		checkBoxShowPassword.setBounds(810, 305, 150, 30);
		mainFrame.add(checkBoxShowPassword);
		loginScreenComponents.add(checkBoxShowPassword);

		buttonLogIn = new JButton("Zaloguj się");
		buttonLogIn.setBounds(600, 350, 100, 40);
		mainFrame.add(buttonLogIn);
		loginScreenComponents.add(buttonLogIn);

		buttonCreateAccount1 = new JButton("Utwórz konto");
		buttonCreateAccount1.setBounds(590, 550, 120, 40);
		mainFrame.add(buttonCreateAccount1);
		loginScreenComponents.add(buttonCreateAccount1);

		buttonRemindPassword = new JButton("Zapomniałem hasła");
		buttonRemindPassword.setBounds(350, 550, 200, 40);
		mainFrame.add(buttonRemindPassword);
		loginScreenComponents.add(buttonRemindPassword);

		buttonReturnToMainScreen = new JButton("Powrót do ekranu głównego");
		buttonReturnToMainScreen.setBounds(750, 550, 200, 40);
		mainFrame.add(buttonReturnToMainScreen);
		loginScreenComponents.add(buttonReturnToMainScreen);

		labelCorrectData = new JLabel();
		labelCorrectData.setBounds(590, 420, 150, 30);
		mainFrame.add(labelCorrectData);
		loginScreenComponents.add(labelCorrectData);

		// ELEMENTY EKRANU PRZEGLADANIA SPRZETU
		// ------------------------------------------------------------------------------------
		labelLogo1 = new JLabel();
		labelLogo1.setBounds(500, 20, 400, 150);
		labelLogo1.setIcon(new ImageIcon(getClass().getResource("/Resources/Logo.png")));
		mainFrame.add(labelLogo1);
		itemBrowseScreenComponents.add(labelLogo1);

		buttonLogin1 = new JButton("Logowanie");
		buttonLogin1.setBounds(1050, 50, 150, 30);
		mainFrame.add(buttonLogin1);
		itemBrowseScreenComponents.add(buttonLogin1);

		buttonCreateAccount2 = new JButton("Utwórz konto");
		buttonCreateAccount2.setBounds(1050, 100, 150, 30);
		mainFrame.add(buttonCreateAccount2);
		itemBrowseScreenComponents.add(buttonCreateAccount2);

		buttonToCart = new JButton("Koszyk");
		buttonToCart.setBounds(1050, 150, 150, 30);
		mainFrame.add(buttonToCart);
		itemBrowseScreenComponents.add(buttonToCart);

		buttonSearch = new JButton("Szukaj");
		buttonSearch.setBounds(50, 150, 100, 30);
		mainFrame.add(buttonSearch);
		itemBrowseScreenComponents.add(buttonSearch);

		buttonFilter = new JButton("Filtrowanie");
		buttonFilter.setBounds(200, 150, 100, 30);
		mainFrame.add(buttonFilter);
		itemBrowseScreenComponents.add(buttonFilter);

		buttonReturnToMainScreen2 = new JButton("Powrót do ekranu głównego");
		buttonReturnToMainScreen2.setBounds(250, 20, 200, 30);
		mainFrame.add(buttonReturnToMainScreen2);
		itemBrowseScreenComponents.add(buttonReturnToMainScreen2);

		textFieldItemName = new JTextField();
		textFieldItemName.setBounds(50, 100, 350, 30);
		mainFrame.add(textFieldItemName);
		itemBrowseScreenComponents.add(textFieldItemName);

		panelItems = new JPanel();
		panelItems.setBackground(Color.LIGHT_GRAY);
		panelItems.setLayout(new GridBagLayout());
		gridBagConstraintsBrowse = new GridBagConstraints();

		scrollPaneItemPanel = new JScrollPane(panelItems);
		scrollPaneItemPanel.setBounds(50, 200, 1170, 450);
		mainFrame.add(scrollPaneItemPanel);
		itemBrowseScreenComponents.add(scrollPaneItemPanel);

		listOfAllItemsNames = new ArrayList<JLabel>();
		listOfAllButtonPhotos = new ArrayList<JButton>();
		for (int i = 0; i < arrayListModels.size(); i++) {
			final Model model = arrayListModels.get(i);

			JButton button_model = new JButton();
			button_model.setPreferredSize(new Dimension(170, 100));
			button_model.setIcon(new ImageIcon(getClass().getResource("/Resources/przedmiot.png")));
			button_model.setContentAreaFilled(false);
			button_model.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setupItemInfoScreen(model);
				}
			});
			gridBagConstraintsBrowse.gridx = i % 5;
			gridBagConstraintsBrowse.gridy = i / 5 * 2;
			panelItems.add(button_model, gridBagConstraintsBrowse);
			listOfAllButtonPhotos.add(button_model);

			JLabel label_model = new JLabel(model.getModelName(), SwingConstants.CENTER);
			label_model.setPreferredSize(new Dimension(200, 30));
			gridBagConstraintsBrowse.gridx = i % 5;
			gridBagConstraintsBrowse.gridy = i / 5 * 2 + 1;
			panelItems.add(label_model, gridBagConstraintsBrowse);
			listOfAllItemsNames.add(label_model);
		}

		// ELEMENTY SZEGÓŁÓW KAŻDEGO ZE SPRZĘTÓW
		labelLogo2 = new JLabel();
		labelLogo2.setBounds(500, 20, 400, 150);
		labelLogo2.setIcon(new ImageIcon(getClass().getResource("/Resources/Logo.png")));
		mainFrame.add(labelLogo2);
		itemInfoScreenComponents.add(labelLogo2);

		buttonReturnToBrowse = new JButton("Powrót do przeglądania sprzętu");
		buttonReturnToBrowse.setBounds(50, 50, 250, 30);
		mainFrame.add(buttonReturnToBrowse);
		itemInfoScreenComponents.add(buttonReturnToBrowse);

		buttonLogin2 = new JButton("Logowanie");
		buttonLogin2.setBounds(1050, 50, 150, 30);
		mainFrame.add(buttonLogin2);
		itemInfoScreenComponents.add(buttonLogin2);

		buttonCreateAccount3 = new JButton("Utwórz konto");
		buttonCreateAccount3.setBounds(1050, 100, 150, 30);
		mainFrame.add(buttonCreateAccount3);
		itemInfoScreenComponents.add(buttonCreateAccount3);

		buttonToCart2 = new JButton("Koszyk");
		buttonToCart2.setBounds(1050, 150, 150, 30);
		mainFrame.add(buttonToCart2);
		itemInfoScreenComponents.add(buttonToCart2);

		labelItemPhoto = new JLabel();
		labelItemPhoto.setBounds(50, 200, 150, 150);
		labelItemPhoto.setIcon(new ImageIcon(getClass().getResource("/Resources/Logo.png")));
		mainFrame.add(labelItemPhoto);
		itemInfoScreenComponents.add(labelItemPhoto);

		labelItemName = new JLabel();
		labelItemName.setBounds(220, 250, 300, 30);
		mainFrame.add(labelItemName);
		itemInfoScreenComponents.add(labelItemName);

		buttonAddToCart = new JButton("Dodaj do koszyka");
		buttonAddToCart.setBounds(220, 320, 150, 30);
		mainFrame.add(buttonAddToCart);
		itemInfoScreenComponents.add(buttonAddToCart);

		textPaneItemDescription = new JTextPane();
		textPaneItemDescription.setBounds(50, 370, 1170, 300);
		textPaneItemDescription.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		textPaneItemDescription.setEnabled(false);
		mainFrame.add(textPaneItemDescription);
		itemInfoScreenComponents.add(textPaneItemDescription);

		labelLength = new JLabel("Dlugosc wypozyczenia:");
		labelLength.setBounds(900, 335, 150, 30);
		mainFrame.add(labelLength);
		itemInfoScreenComponents.add(labelLength);

		textFieldlength = new JTextField();
		textFieldlength.setBounds(1040, 335, 60, 30);
		mainFrame.add(textFieldlength);
		itemInfoScreenComponents.add(textFieldlength);

		calendar = new JCalendar();
		calendar.setBounds(900, 200, 300, 150);
		Date date = Calendar.getInstance().getTime();
		date.setTime(date.getTime() + 86400000);
		calendar.setMinSelectableDate(date);
		mainFrame.add(calendar);
		itemInfoScreenComponents.add(calendar);

		selectedMonth = 0;

		labelItemsOfModel = new JLabel("Egzemplarz modelu:");
		labelItemsOfModel.setBounds(900, 120, 150, 30);
		mainFrame.add(labelItemsOfModel);
		itemInfoScreenComponents.add(labelItemsOfModel);

		comboBoxItemsOfModel = new JComboBox<Integer>();
		comboBoxItemsOfModel.setBounds(920, 150, 100, 30);
		mainFrame.add(comboBoxItemsOfModel);
		itemInfoScreenComponents.add(comboBoxItemsOfModel);

		labelErrorDate = new JLabel();
		labelErrorDate.setFont(new Font("Arial", Font.PLAIN, 25));
		labelErrorDate.setBounds(500, 250, 400, 60);
		mainFrame.add(labelErrorDate);
		itemInfoScreenComponents.add(labelErrorDate);

		// ELEMENTY EKRANU Z AKCJAMI PRACOWNIKA
		buttonReturnToMainScreen3 = new JButton("Powrot do ekranu gl.");
		buttonReturnToMainScreen3.setBounds(290, 20, 200, 30);
		mainFrame.add(buttonReturnToMainScreen3);
		workerActionsComponents.add(buttonReturnToMainScreen3);

		buttonManageAccounts = new JButton("Zarządzaj kontami");
		buttonManageAccounts.setBounds(540, 20, 200, 30);
		mainFrame.add(buttonManageAccounts);
		workerActionsComponents.add(buttonManageAccounts);

		buttonBrowseOrders = new JButton("Przegladaj rezerwacje");
		buttonBrowseOrders.setBounds(790, 20, 200, 30);
		mainFrame.add(buttonBrowseOrders);
		workerActionsComponents.add(buttonBrowseOrders);

		buttonManageModels = new JButton("Zarzadzaj modelami");
		buttonManageModels.setBounds(40, 70, 200, 30);
		mainFrame.add(buttonManageModels);
		workerActionsComponents.add(buttonManageModels);

		buttonManageEqupiment = new JButton("Zarzadzaj sprzetem");
		buttonManageEqupiment.setBounds(290, 70, 200, 30);
		mainFrame.add(buttonManageEqupiment);
		workerActionsComponents.add(buttonManageEqupiment);

		buttonManageMaintenances = new JButton("Zarzadzaj konserwacjami");
		buttonManageMaintenances.setBounds(540, 70, 200, 30);
		mainFrame.add(buttonManageMaintenances);
		workerActionsComponents.add(buttonManageMaintenances);

		buttonManageRepairs = new JButton("Zarzadzaj naprawami");
		buttonManageRepairs.setBounds(790, 70, 200, 30);
		mainFrame.add(buttonManageRepairs);
		workerActionsComponents.add(buttonManageRepairs);

		buttonAddWorker = new JButton("Dodaj pracownika");
		buttonAddWorker.setBounds(1040, 20, 200, 30);
		buttonAddWorker.setEnabled(false);
		mainFrame.add(buttonAddWorker);
		workerActionsComponents.add(buttonAddWorker);

		buttonLogOut = new JButton("Wyloguj");
		buttonLogOut.setBounds(1040, 70, 200, 30);
		mainFrame.add(buttonLogOut);
		workerActionsComponents.add(buttonLogOut);

		// ELEMENTY EKRANU KOSZYK
		labelLogo3 = new JLabel();
		labelLogo3.setBounds(500, 20, 400, 150);
		labelLogo3.setIcon(new ImageIcon(getClass().getResource("/Resources/Logo.png")));
		mainFrame.add(labelLogo3);
		cartActionsComponents.add(labelLogo3);

		labelRemoved = new JLabel();
		labelRemoved.setBounds(50, 150, 400, 50);
		labelRemoved.setFont(new Font("Arial", Font.PLAIN, 20));
		mainFrame.add(labelRemoved);
		cartActionsComponents.add(labelRemoved);

		buttonReturnToBrowse2 = new JButton("Powrót do przeglądania sprzętu");
		buttonReturnToBrowse2.setBounds(240, 100, 250, 30);
		mainFrame.add(buttonReturnToBrowse2);
		cartActionsComponents.add(buttonReturnToBrowse2);

		buttonReturnToMainScreen4 = new JButton("Powrot do ekranu gl.");
		buttonReturnToMainScreen4.setBounds(290, 50, 200, 30);
		mainFrame.add(buttonReturnToMainScreen4);
		cartActionsComponents.add(buttonReturnToMainScreen4);

		buttonLogin3 = new JButton("Logowanie");
		buttonLogin3.setBounds(1050, 50, 150, 30);
		mainFrame.add(buttonLogin3);
		cartActionsComponents.add(buttonLogin3);

		buttonCreateAccount4 = new JButton("Utwórz konto");
		buttonCreateAccount4.setBounds(1050, 100, 150, 30);
		mainFrame.add(buttonCreateAccount4);
		cartActionsComponents.add(buttonCreateAccount4);

		buttonOrder = new JButton("Zamow");
		buttonOrder.setBounds(1050, 150, 150, 30);
		buttonOrder.setEnabled(false);
		mainFrame.add(buttonOrder);
		cartActionsComponents.add(buttonOrder);

		panelItems2 = new JPanel();
		panelItems2.setBackground(Color.LIGHT_GRAY);
		panelItems2.setLayout(new GridBagLayout());
		gridBagConstraintsBrowse2 = new GridBagConstraints();

		scrollPaneItemPanel2 = new JScrollPane(panelItems2);
		scrollPaneItemPanel2.setBounds(50, 200, 1170, 450);
		mainFrame.add(scrollPaneItemPanel2);
		cartActionsComponents.add(scrollPaneItemPanel2);

		// panel tylko po to zeby byl kolor tla
		panelManageDataAsWorker = new JPanel();
		panelManageDataAsWorker.setSize(1000, 1000);
		panelManageDataAsWorker.setBackground(Color.LIGHT_GRAY);
		panelManageDataAsWorker.setLayout(new GridBagLayout());

		gridBagConstraintsWorkerActions = new GridBagConstraints();

		scrollPaneDBContent = new JScrollPane(panelManageDataAsWorker, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneDBContent.setBounds(100, 130, 1080, 500);
		mainFrame.add(scrollPaneDBContent);
		workerActionsComponents.add(scrollPaneDBContent);
		cartActionsComponents.add(scrollPaneDBContent);

		// ELEMENTY WIDZIANE/NIEWIDZIANE NIEZALEZNIE OD EKRANU
		buttonWorkerActions = new JButton("Opcje pracownika");
		buttonWorkerActions.setBounds(0, 0, 150, 30);
		buttonWorkerActions.setVisible(false);
		mainFrame.add(buttonWorkerActions);
	}

	private void initListeners() {
		// listenery ekranu
		// głównego-------------------------------------------------------------------------------------------
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginScreen();
			}
		});
		buttonBrowseEquipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainScreenComponents)
					c.setVisible(false);
				for (Component c : itemBrowseScreenComponents)
					c.setVisible(true);
			}
		});
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();
			}
		});
		buttonFirmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame, "Jeszcze sie napisze\n Ludzi: " + arrayListPeople.size()
						+ "\n Klientow:" + arrayListClients.size() + "\n Adresow: " + arrayListAddresses.size());
			}
		});
		// listenery ekranu
		// logowania------------------------------------------------------------------------------
		buttonReturnToMainScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : loginScreenComponents)
					c.setVisible(false);
				for (Component c : mainScreenComponents)
					c.setVisible(true);
				textFieldLogin.setForeground(Color.GRAY);
				textFieldLogin.setFont(new Font("Times New Roman", Font.ITALIC, 14));
				textFieldLogin.setText("Login");
				passwordFieldPassword.setForeground(Color.GRAY);
				passwordFieldPassword.setFont(new Font("Times New Roman", Font.ITALIC, 14));
				passwordFieldPassword.setEchoChar((char) 0);
				passwordFieldPassword.setText("Haslo");
			}
		});

		textFieldLogin.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				textFieldLogin.setForeground(Color.BLACK);
				textFieldLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				if (textFieldLogin.getText().equals("Login"))
					textFieldLogin.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (textFieldLogin.getText().equals("")) {
					textFieldLogin.setForeground(Color.GRAY);
					textFieldLogin.setText("Login");
					textFieldLogin.setFont(new Font("Times New Roman", Font.ITALIC, 14));
				}
			}
		});

		passwordFieldPassword.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				passwordFieldPassword.setEchoChar('*');
				passwordFieldPassword.setForeground(Color.BLACK);
				passwordFieldPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				if (String.valueOf(passwordFieldPassword.getPassword()).equals("Haslo"))
					passwordFieldPassword.setText("");
			}

			public void focusLost(FocusEvent e) {
				if (String.valueOf(passwordFieldPassword.getPassword()).equals("")) {
					passwordFieldPassword.setEchoChar((char) 0);
					passwordFieldPassword.setForeground(Color.GRAY);
					passwordFieldPassword.setText("Haslo");
					passwordFieldPassword.setFont(new Font("Times New Roman", Font.ITALIC, 14));
				}
			}
		});

		buttonCreateAccount1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();
			}
		});

		buttonRemindPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remindPassword();
			}
		});

		buttonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn();
			}
		});

		checkBoxShowPassword.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (!passwordFieldPassword.getText().equals("Haslo"))
					if (checkBoxShowPassword.isSelected())
						passwordFieldPassword.setEchoChar((char) 0);
					else
						passwordFieldPassword.setEchoChar('*');
			}
		});

		passwordFieldPassword.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				checkBoxShowPassword.setSelected(false);
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		passwordFieldPassword.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				checkBoxShowPassword.setSelected(false);
			}
		});

		// listenery ekranu przegląania
		// sprzętu--------------------------------------------------------------------------------
		buttonReturnToMainScreen2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : itemBrowseScreenComponents)
					c.setVisible(false);
				for (Component c : mainScreenComponents)
					c.setVisible(true);
			}
		});

		buttonLogin1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginScreen();
			}
		});

		buttonCreateAccount2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();
			}
		});

		buttonToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toCart();
			}
		});

		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchInAssortment();
			}
		});

		buttonFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// filterAssortment();
				setupItemInfoScreen(null);

			}
		});

		// listenery ekranu informacji o sprzęcie-----------------------------
		buttonReturnToBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : itemInfoScreenComponents)
					c.setVisible(false);
				for (Component c : itemBrowseScreenComponents)
					c.setVisible(true);
			}
		});

		buttonLogin2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginScreen();
			}
		});

		buttonCreateAccount3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();
			}
		});

		buttonToCart2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toCart();
			}
		});

		buttonAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToCart();
			}
		});

		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				int[] parsedDate = parseDate(calendar.getDate());
				int length = 0;
				try {
					length = Integer.parseInt(textFieldlength.getText());
				} catch (Exception e) {
				}

				if (parsedDate[1] == selectedMonth)
					displayCalendar(parsedDate[0], parsedDate[1], parsedDate[2], length);
				else
					selectedMonth = parsedDate[1];
			}
		});

		comboBoxItemsOfModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displayCalendar(0, 0, 0, 0);
				} catch (Exception ex) {
				}
			}
		});

		// listenery ekranu akcji pracownika--------------------------------
		buttonReturnToMainScreen3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainScreenComponents)
					c.setVisible(true);
				for (Component c : loginScreenComponents)
					c.setVisible(false);
				for (Component c : itemBrowseScreenComponents)
					c.setVisible(false);
				for (Component c : itemInfoScreenComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(false);
				for (Component c : cartActionsComponents)
					c.setVisible(false);
			}
		});

		buttonLogOut.addActionListener(new ActionListener() {
			// ten listener sie rozni od funkcji loginScreen() tym
			// ze nie wyrzuca do ekranu logowania tylko do glownego
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainScreenComponents)
					c.setVisible(false);
				for (Component c : itemBrowseScreenComponents)
					c.setVisible(false);
				for (Component c : itemInfoScreenComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(false);
				for (Component c : cartActionsComponents)
					c.setVisible(false);
				for (Component c : mainScreenComponents)
					c.setVisible(true);

				loggedIn = false;
				labelLoggedAs.setText("");
				buttonLogin.setText("Zaloguj");
				buttonLogin1.setText("Zaloguj");
				buttonLogin2.setText("Zaloguj");
				buttonCreateAccount.setEnabled(true);
				buttonCreateAccount2.setEnabled(true);
				buttonCreateAccount3.setEnabled(true);
				buttonWorkerActions.setVisible(false);
			}
		});

		buttonManageAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManageDataAsWorker.removeAll();
				listOfDBContent = new ArrayList<JButton>();

				for (int i = 0; i < arrayListPeople.size(); i++) {
					boolean is_worker = false;
					for (int j = 0; j < arrayListWorkers.size(); j++) {
						if (arrayListPeople.get(i).getPersonID() == arrayListWorkers.get(j).getPersonID()
								&& arrayListPeople.get(i).getPersonID() != loggedID)
							is_worker = true;
					}

					if (!is_worker) {
						final Person p = arrayListPeople.get(i);
						JButton temp_button = new JButton(p.getLogin());
						temp_button.setPreferredSize(new Dimension(500, 30));
						temp_button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								editAccount(p);
							}
						});

						gridBagConstraintsWorkerActions.gridx = 1;
						gridBagConstraintsWorkerActions.gridy = listOfDBContent.size() + 2;

						listOfDBContent.add(temp_button);
						panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);
					}
				}
				JButton temp_button = new JButton("Dodaj nowe konto");
				temp_button.setPreferredSize(new Dimension(500, 30));
				temp_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createAccount();
					}
				});
				temp_button.setBackground(new Color(220, 255, 220));
				gridBagConstraintsWorkerActions.gridx = 1;
				gridBagConstraintsWorkerActions.gridy = 1;
				listOfDBContent.add(temp_button);
				panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);

				// dodalem to bo przyciski do kont nie chcialy sie pojawiac
				// a nie mam lepszego pomyslu jak to naprawic
				for (Component c : workerActionsComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(true);
			}
		});

		buttonBrowseOrders.addActionListener(new ActionListener() {
			// trzeba zrobic zaznaczanie tylko tych ktore dalej trwaja
			public void actionPerformed(ActionEvent e) {
				panelManageDataAsWorker.removeAll();
				listOfDBContent = new ArrayList<JButton>();

				for (int i = arrayListOrders.size() - 1; i >= 0; i--) {
					OrderHistory o = arrayListOrders.get(i);
					JButton temp_button = new JButton("Zamownienie numer: " + o.getOrderNumber());
					temp_button.setPreferredSize(new Dimension(500, 30));
					temp_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new ManageOrder(MainWindow.this, o);
						}
					});

					gridBagConstraintsWorkerActions.gridx = 1;
					gridBagConstraintsWorkerActions.gridy = i + 1;

					listOfDBContent.add(temp_button);
					panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);
				}

				// dodalem to bo przyciski do kont nie chcialy sie pojawiac
				// a nie mam lepszego pomyslu jak to naprawic
				for (Component c : workerActionsComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(true);
			}
		});

		buttonManageModels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManageDataAsWorker.removeAll();
				listOfDBContent = new ArrayList<JButton>();

				for (int i = 0; i < arrayListModels.size(); i++) {
					final Model m = arrayListModels.get(i);
					JButton temp_button = new JButton(
							"ID modelu: " + m.getModelID() + " Nazwa modelu: " + m.getModelName());
					temp_button.setPreferredSize(new Dimension(500, 30));
					temp_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							editModel(m);
						}
					});

					gridBagConstraintsWorkerActions.gridx = 1;
					gridBagConstraintsWorkerActions.gridy = i + 2;

					listOfDBContent.add(temp_button);
					panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);
				}
				JButton temp_button = new JButton("Dodaj nowy model");
				temp_button.setPreferredSize(new Dimension(500, 30));
				temp_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createModel();
					}
				});
				temp_button.setBackground(new Color(220, 255, 220));
				gridBagConstraintsWorkerActions.gridx = 1;
				gridBagConstraintsWorkerActions.gridy = 1;
				listOfDBContent.add(temp_button);
				panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);

				// dodalem to bo przyciski do kont nie chcialy sie pojawiac
				// a nie mam lepszego pomyslu jak to naprawic
				for (Component c : workerActionsComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(true);
			}
		});

		buttonManageEqupiment.addActionListener(new ActionListener() {
			// trzeba zrobic jakeis podswietlanie modeli uszkodzonych
			// albo cos takiego
			public void actionPerformed(ActionEvent e) {
				panelManageDataAsWorker.removeAll();
				listOfDBContent = new ArrayList<JButton>();

				for (int i = 0; i < arrayListAssortment.size(); i++) {
					final Assortment a = arrayListAssortment.get(i);
					JButton temp_button = new JButton("ID sprzetu: " + a.getItemID() + " ,liczba wypozyczen: "
							+ a.getLoansNumber() + " ,stan: " + a.getCondition());
					temp_button.setPreferredSize(new Dimension(500, 30));
					temp_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							editAssortment(a);
						}
					});

					gridBagConstraintsWorkerActions.gridx = 1;
					gridBagConstraintsWorkerActions.gridy = i + 2;

					listOfDBContent.add(temp_button);
					panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);
				}

				JButton temp_button = new JButton("Dodaj nowy przedmiot");
				temp_button.setPreferredSize(new Dimension(500, 30));
				temp_button.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						createAssortment();
					}
				});
				temp_button.setBackground(new Color(220, 255, 220));
				gridBagConstraintsWorkerActions.gridx = 1;
				gridBagConstraintsWorkerActions.gridy = 1;
				listOfDBContent.add(temp_button);
				panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);

				// dodalem to bo przyciski do kont nie chcialy sie pojawiac
				// a nie mam lepszego pomyslu jak to naprawic
				for (Component c : workerActionsComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(true);
			}
		});

		buttonManageMaintenances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManageDataAsWorker.removeAll();
				listOfDBContent = new ArrayList<JButton>();

				for (int i = 0; i < arrayListMaintenances.size(); i++) {
					MaintenanceHistory m = arrayListMaintenances.get(i);
					JButton temp_button = new JButton("Numer konserwacji: " + m.getMaintenanceNumber()
							+ " ,data konserwacji: " + m.getMaintenanceDate());
					temp_button.setPreferredSize(new Dimension(500, 30));
					temp_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new ManageMaintenanceRepair(false, arrayListAssortment, m, null);
						}
					});

					gridBagConstraintsWorkerActions.gridx = 1;
					gridBagConstraintsWorkerActions.gridy = i + 2;

					listOfDBContent.add(temp_button);
					panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);
				}

				JButton temp_button = new JButton("Dodaj nowa konserwacje");
				temp_button.setPreferredSize(new Dimension(500, 30));
				temp_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new ManageMaintenanceRepair(false, loggedID, arrayListMaintenances, arrayListRepairs,
								arrayListAssortment);
					}
				});
				temp_button.setBackground(new Color(220, 255, 220));
				gridBagConstraintsWorkerActions.gridx = 1;
				gridBagConstraintsWorkerActions.gridy = 1;
				listOfDBContent.add(temp_button);
				panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);

				// dodalem to bo przyciski do kont nie chcialy sie pojawiac
				// a nie mam lepszego pomyslu jak to naprawic
				for (Component c : workerActionsComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(true);
			}
		});

		buttonManageRepairs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelManageDataAsWorker.removeAll();
				listOfDBContent = new ArrayList<JButton>();

				for (int i = 0; i < arrayListRepairs.size(); i++) {
					RepairHistory r = arrayListRepairs.get(i);
					JButton temp_button = new JButton(
							"Numer konserwacji" + r.getRepairNumber() + " ,data konserwacji: " + r.getRepairDate());
					temp_button.setPreferredSize(new Dimension(500, 30));
					temp_button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new ManageMaintenanceRepair(true, arrayListAssortment, null, r);
						}
					});

					gridBagConstraintsWorkerActions.gridx = 1;
					gridBagConstraintsWorkerActions.gridy = i + 2;

					listOfDBContent.add(temp_button);
					panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);
				}
				
				JButton temp_button = new JButton("Dodaj nowa naprawe");
				temp_button.setPreferredSize(new Dimension(500, 30));
				temp_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new ManageMaintenanceRepair(true, loggedID, arrayListMaintenances, arrayListRepairs,
								arrayListAssortment);
					}
				});
				temp_button.setBackground(new Color(220, 255, 220));
				gridBagConstraintsWorkerActions.gridx = 1;
				gridBagConstraintsWorkerActions.gridy = 1;
				listOfDBContent.add(temp_button);
				panelManageDataAsWorker.add(temp_button, gridBagConstraintsWorkerActions);

				// dodalem to bo przyciski do kont nie chcialy sie pojawiac
				// a nie mam lepszego pomyslu jak to naprawic
				for (Component c : workerActionsComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(true);
			}
		});

		buttonAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageWorkerAccount(MainWindow.this, true);
			}
		});

		// listenery ekranu koszyka
		buttonReturnToMainScreen4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainScreenComponents)
					c.setVisible(true);
				for (Component c : loginScreenComponents)
					c.setVisible(false);
				for (Component c : itemBrowseScreenComponents)
					c.setVisible(false);
				for (Component c : itemInfoScreenComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(false);
				for (Component c : cartActionsComponents)
					c.setVisible(false);
			}
		});

		buttonReturnToBrowse2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : cartActionsComponents)
					c.setVisible(false);
				for (Component c : itemBrowseScreenComponents)
					c.setVisible(true);
			}
		});

		buttonLogin3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginScreen();
			}
		});

		buttonCreateAccount4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();
			}
		});

		buttonOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (arrayListCartItems.size() == 0)
					new Thread(new Runnable() {
						public void run() {
							try {
								labelRemoved.setText("Koszyk jest pusty!");
								Thread.sleep(3000);
								labelRemoved.setText("");
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}).start();
				else {
					int confirmed = 1;
					String[] objects = { "Tak", "Nie" };
					confirmed = JOptionPane.showOptionDialog(mainFrame,
							"Na pewno chcesz zlozyc zamowienie? Tej operacji nie da sie cofnac.",
							"Potwierdzenie zamowienia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							objects, "Tak");

					if (confirmed == 0) {
						Connect oracle = new Connect();
						int orderID = arrayListOrders.size() + 1;
						Date date = Calendar.getInstance().getTime();
						int total_cost = 0;						
						ArrayList<Integer> arrayListEquipmentID = new ArrayList<Integer>();
						ArrayList<Integer> arrayListEquipmentLoanLength = new ArrayList<Integer>();						
						while (arrayListCartItems.size() > 0)
							while (arrayListCartItems.size() > 0) {
								total_cost += arrayListModels.get(arrayListCartItems.get(0).getModelID() - 1)
										.getCostPerDay() * arrayListCartLength.get(0);
								
							
															
								arrayListEquipmentID.add(arrayListCartItems.get(0).getItemID());
								arrayListEquipmentLoanLength.add(arrayListCartLength.get(0));
								arrayListCartItems.get(0).getListDateOfOrder().add(arrayListCartDates.get(0));
								arrayListCartItems.get(0).getListLengthOfOrder().add(arrayListCartLength.get(0));
								oracle.db_connect();
								
								oracle.db_orderedEquipment(orderID,arrayListCartItems.get(0).getItemID() ,arrayListCartDates.get(0), new Date());
								oracle.db_disconnect();
					
								arrayListCartItems.remove(0);
								arrayListCartDates.remove(0);
								arrayListCartLength.remove(0);
							}
						

						total_cost = total_cost/100;
						oracle.db_connect();
						oracle.db_createOrder(orderID, date, loggedID, total_cost);
						oracle.db_disconnect();

						panelItems2.removeAll();
						panelItems2.repaint();

						arrayListOrders.add(new OrderHistory(orderID, loggedID, date, total_cost, arrayListEquipmentID,
								arrayListEquipmentLoanLength));

						new Thread(new Runnable() {
							public void run() {
								try {
									labelRemoved.setText("Zlozono zamowienie!");
									Thread.sleep(3000);
									labelRemoved.setText("");
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}).start();
					}
				}
			}
		});

		// listenery elementow widzianych/niewidzianych niezaleznie od ekranu
		buttonWorkerActions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : mainScreenComponents)
					c.setVisible(false);
				for (Component c : loginScreenComponents)
					c.setVisible(false);
				for (Component c : itemBrowseScreenComponents)
					c.setVisible(false);
				for (Component c : cartActionsComponents)
					c.setVisible(false);
				for (Component c : itemInfoScreenComponents)
					c.setVisible(false);
				for (Component c : workerActionsComponents)
					c.setVisible(true);

				panelManageDataAsWorker.removeAll();
			}
		});
	}

	private void createAccount() {
		new ManageClientAccount(this, true);
	}

	private void editAccount(Person p) {
		new ManageClientAccount(this, false, p);
	}

	private void createModel() {
		new ManageModel(arrayListModels, true);
	}

	private void editModel(Model m) {
		new ManageModel(arrayListModels, false, m, panelManageDataAsWorker);
	}

	private void createAssortment() {
		new ManageAssortment(arrayListAssortment, true);
	}

	private void editAssortment(Assortment a) {
		new ManageAssortment(arrayListAssortment, false, a, panelManageDataAsWorker);
	}

	private void remindPassword() {
		new RemindPassword(this);
	}

	private void loginScreen() {
		if (!loggedIn) {
			for (Component c : mainScreenComponents)
				c.setVisible(false);
			for (Component c : itemBrowseScreenComponents)
				c.setVisible(false);
			for (Component c : itemInfoScreenComponents)
				c.setVisible(false);
			for (Component c : cartActionsComponents)
				c.setVisible(false);
			for (Component c : workerActionsComponents)
				c.setVisible(false);
			for (Component c : cartActionsComponents)
				c.setVisible(false);
			for (Component c : loginScreenComponents)
				c.setVisible(true);
			buttonLogIn.requestFocus(); // tylko po ro zeby na textfieldach
										// byl szary tekst
		}

		loggedIn = false;
		labelLoggedAs.setText("");
		buttonLogin.setText("Zaloguj");
		buttonLogin1.setText("Zaloguj");
		buttonLogin2.setText("Zaloguj");
		buttonLogin3.setText("Zaloguj");
		buttonCreateAccount.setEnabled(true);
		buttonCreateAccount2.setEnabled(true);
		buttonCreateAccount3.setEnabled(true);
		buttonCreateAccount4.setEnabled(true);
		buttonWorkerActions.setVisible(false);
		buttonOrder.setEnabled(false);
	}

	private void logIn() {
		String login = textFieldLogin.getText();
		String password = String.valueOf(passwordFieldPassword.getPassword());
		boolean correct = false;
		int personID = 0;

		for (Person p : arrayListPeople) {
			if (p.getLogin().equals(login) && p.getPassword().equals(password)) {
				correct = true;
				personID = p.getPersonID();
				for (Worker w : arrayListWorkers)
					if (p.getPersonID() == w.getWorkerID()) {
						buttonWorkerActions.setVisible(true);
						if (w.isAdmin())
							buttonAddWorker.setEnabled(true);
						else
							buttonAddWorker.setEnabled(false);
						break;
					}
				break;
			}
		}
		if (!correct)
			new Thread(new Runnable() {
				public void run() {
					try {
						labelCorrectData.setText("Błędny login lub hasło!");
						Thread.sleep(3000);
						labelCorrectData.setText("");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		else {
			for (Component c : loginScreenComponents)
				c.setVisible(false);
			for (Component c : itemBrowseScreenComponents)
				c.setVisible(true);

			loggedIn = true;
			labelLoggedAs.setText("Zalogowano jako: " + login);
			buttonLogin.setText("Wyloguj");
			buttonLogin1.setText("Wyloguj");
			buttonLogin2.setText("Wyloguj");
			buttonLogin3.setText("Wyloguj");
			buttonCreateAccount.setEnabled(false);
			buttonCreateAccount2.setEnabled(false);
			buttonCreateAccount3.setEnabled(false);
			buttonCreateAccount4.setEnabled(false);
			buttonOrder.setEnabled(true);
			loggedID = personID;
		}
	}

	private void toCart() {
		for (Component c : mainScreenComponents)
			c.setVisible(false);
		for (Component c : itemBrowseScreenComponents)
			c.setVisible(false);
		for (Component c : itemInfoScreenComponents)
			c.setVisible(false);
		for (Component c : cartActionsComponents)
			c.setVisible(true);
		for (Component c : workerActionsComponents)
			c.setVisible(false);
		for (Component c : loginScreenComponents)
			c.setVisible(false);

		panelItems2.removeAll();

		for (int i = 0; i < arrayListCartItems.size(); i++) {
			int iterator = i;
			final Assortment a = arrayListCartItems.get(i);
			Model model = null;
			int[] parsedDate = null;

			for (Model m : arrayListModels)
				if (m.getModelID() == a.getModelID()) {
					model = m;
					break;
				}
			parsedDate = parseDate(arrayListCartDates.get(i));

			JLabel temp_label = new JLabel(
					"Nazwa: " + model.getModelName() + ", Data wypozyczenia: " + parsedDate[0] + "/" + parsedDate[1]
							+ "/" + parsedDate[2] + ", Dlugosc wypozyczenia: " + arrayListCartLength.get(i) + ", Cena:"
							+ arrayListCartLength.get(i) * model.getCostPerDay() / 100 + "zl.",
					SwingConstants.CENTER);
			temp_label.setPreferredSize(new Dimension(700, 30));
			gridBagConstraintsBrowse2.gridx = 1;
			gridBagConstraintsBrowse2.gridy = i + 1;
			panelItems2.add(temp_label, gridBagConstraintsBrowse2);

			JButton temp_button = new JButton("Usun");
			temp_button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable() {
						public void run() {
							try {
								labelRemoved.setText("Usunieto przedmiot z koszyka.");
								Thread.sleep(3000);
								labelRemoved.setText("");
							} catch (InterruptedException e) {
							}
						}
					}).start();
					arrayListCartItems.remove(a);
					arrayListCartDates.remove(arrayListCartDates.get(iterator));
					arrayListCartLength.remove(arrayListCartLength.get(iterator));

					for (Component c : cartActionsComponents)
						c.setVisible(false);
					for (Component c : cartActionsComponents)
						c.setVisible(true);

					toCart();
					return;
				}
			});
			gridBagConstraintsBrowse2.gridx = 2;
			gridBagConstraintsBrowse2.gridy = i + 1;
			panelItems2.add(temp_button, gridBagConstraintsBrowse2);
		}

		int total_cost = 0;
		for (int i = 0; i < arrayListCartLength.size(); i++) {
			Assortment assortment = arrayListCartItems.get(i);
			Model model = null;
			int length = arrayListCartLength.get(i);

			for (Model m : arrayListModels)
				if (m.getModelID() == assortment.getModelID()) {
					model = m;
					break;
				}
			total_cost += model.getCostPerDay() * length;
		}
		JLabel temp_label = new JLabel("Calkowity koszt: " + total_cost / 100 + "zl.", SwingConstants.CENTER);
		temp_label.setPreferredSize(new Dimension(500, 60));
		temp_label.setFont(new Font("Arial", Font.PLAIN, 20));
		gridBagConstraintsBrowse2.gridx = 1;
		gridBagConstraintsBrowse2.gridy = arrayListCartItems.size() + 1;
		panelItems2.add(temp_label, gridBagConstraintsBrowse2);
	}

	private void searchInAssortment() {
		JOptionPane.showMessageDialog(mainFrame, "Bedzie dodane");
		System.out.println(arrayListCartItems.size());
	}

	private void filterAssortment() {
		JOptionPane.showMessageDialog(mainFrame, "Bedzie dodane");
	}

	private void setupItemInfoScreen(Model model) {
		for (Component c : itemBrowseScreenComponents)
			c.setVisible(false);
		for (Component c : itemInfoScreenComponents)
			c.setVisible(true);

		selectedModel = model;

		labelItemName.setText(model.getModelName());
		textPaneItemDescription.setText(
				model.getModelName() + " jest to przedmiot producena "
						+ model.getProducer() + " który jest znanym dostawcą sprzętu sportowego i kosztuje jedyne "
						+ model.getCostPerDay() / 100 + " złotych dziennie. \n" + "Kaucja za zniszczenie wynosi: "
						+ model.getDamageDeposit() / 100 + " złotych.");

		// dodanie do comboBoxa wszystkich egzemplarzy danego modelu
		comboBoxItemsOfModel.removeAllItems();
		for (Assortment a : arrayListAssortment)
			if (a.getModelID() == selectedModel.getModelID())
				comboBoxItemsOfModel.addItem(comboBoxItemsOfModel.getItemCount() + 1);

		// tylko po to zeby od razu podswietlilo na czerwono juz zarezerwowane
		// terminy
		displayCalendar(0, 0, 0, 0);
	}

	public void displayCalendar(int day, int month, int year, int length) {
		ArrayList<Assortment> arrayList_items_of_this_model = new ArrayList<Assortment>();

		calendar.getDayChooser().removeDateEvaluator(evaluator);
		calendar.getDayChooser().removeDateEvaluator(evaluatorAllItems);
		// 2 evaluatory dla dwoch kolorow
		evaluator = new HighlightEvaluator();
		evaluator.setBackgroundColor(Color.GREEN);
		evaluatorAllItems = new HighlightEvaluator();
		evaluatorAllItems.setBackgroundColor(Color.RED);

		for (int i = day; i < day + length; i++) {
			evaluator.add(createDate(i, month, year));
		}

		for (Assortment a : arrayListAssortment)
			if (a.getModelID() == selectedModel.getModelID())
				arrayList_items_of_this_model.add(a);

		Assortment assortment = arrayList_items_of_this_model.get(comboBoxItemsOfModel.getSelectedIndex());

		// zaznaczenie wszystkich dat w ktorych egzemplarz jest zajety
		for (int i = 0; i < assortment.getListDateOfOrder().size(); i++) {
			Date date = assortment.getListDateOfOrder().get(i);
			int length_of_order = assortment.getListLengthOfOrder().get(i);
			int[] parsedDate = parseDate(date);

			for (int j = parsedDate[0]; j < parsedDate[0] + length_of_order; j++)
				evaluatorAllItems.add(createDate(j, parsedDate[1], parsedDate[2]));
		}

		calendar.getDayChooser().addDateEvaluator(evaluatorAllItems);
		calendar.getDayChooser().addDateEvaluator(evaluator);
		calendar.setCalendar(calendar.getCalendar());
	}

	private Date createDate(int d, int m, int y) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, y);
		c.set(Calendar.MONTH, m - 1);
		c.set(Calendar.DAY_OF_MONTH, d);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return (c.getTime());
	}

	private int[] parseDate(Date date) {
		String[] parts = date.toString().split(" ");
		int day = Integer.parseInt(parts[2]);
		int month;
		if (parts[1].equals("Jan"))
			month = 1;
		else if (parts[1].equals("Feb"))
			month = 2;
		else if (parts[1].equals("Mar"))
			month = 3;
		else if (parts[1].equals("Apr"))
			month = 4;
		else if (parts[1].equals("May"))
			month = 5;
		else if (parts[1].equals("Jun"))
			month = 6;
		else if (parts[1].equals("Jul"))
			month = 7;
		else if (parts[1].equals("Aug"))
			month = 8;
		else if (parts[1].equals("Oct"))
			month = 9;
		else if (parts[1].equals("Sep"))
			month = 10;
		else if (parts[1].equals("Nov"))
			month = 11;
		else
			month = 12;
		int year = Integer.parseInt(parts[5]);

		int[] parsedDate = new int[3];
		parsedDate[0] = day;
		parsedDate[1] = month;
		parsedDate[2] = year;

		return parsedDate;
	}

	private void addToCart() {
		if (!loggedIn) {
			new Thread(new Runnable() {
				public void run() {
					try {
						labelErrorDate.setText("Najpierw sie zaloguj!");
						Thread.sleep(3000);
						labelErrorDate.setText("");
					} catch (InterruptedException e) {
					}
				}
			}).start();
			return;
		}
		ArrayList<Assortment> arrayList_items_of_model = new ArrayList<Assortment>();
		ArrayList<Date> arrayList_all_dates_of_item = new ArrayList<Date>();

		// wyszukanie listy wszystkich egzemplarzy modelu
		for (Assortment a : arrayListAssortment)
			if (a.getModelID() == selectedModel.getModelID())
				arrayList_items_of_model.add(a);

		Assortment assortment = arrayList_items_of_model.get(comboBoxItemsOfModel.getSelectedIndex());

		Date date = calendar.getDate();
		int length = 0;
		try {
			length = Integer.parseInt(textFieldlength.getText());
			if (length == 0)
				length = Integer.parseInt("");
			textFieldlength.setText("");
		} catch (Exception ex) {
			new Thread(new Runnable() {
				public void run() {
					try {
						labelErrorDate.setText("<html>Podana dlugosc zamowienia <br> jest bledna</html>");
						Thread.sleep(3000);
						labelErrorDate.setText("");
					} catch (InterruptedException e) {
					}
				}
			}).start();
			textFieldlength.setText("");
			return;
		}

		// stworzenie listy wszystkich dat w ktorych dany egzemplarz jest
		// wypozyczony
		for (int i = 0; i < assortment.getListDateOfOrder().size(); i++) {
			for (int j = 0; j < assortment.getListLengthOfOrder().get(i); j++) {
				int[] parsedDate = parseDate(assortment.getListDateOfOrder().get(i));
				parsedDate[0] += j;
				arrayList_all_dates_of_item.add(createDate(parsedDate[0], parsedDate[1], parsedDate[2]));
			}
		}

		// sprawdzenie czy w podanych datach egzemplarz jest wolny
		// watki sa dla efektu(lepszy niz JOptionPane.messagedialog)
		for (int i = 0; i < length; i++) {
			int[] parsedDate = parseDate(calendar.getDate());
			parsedDate[0] += i;
			date = createDate(parsedDate[0], parsedDate[1], parsedDate[2]);
			for (Date d : arrayList_all_dates_of_item)
				if (d.compareTo(date) == 0) {
					new Thread(new Runnable() {
						public void run() {
							try {
								labelErrorDate
										.setText("<html>W podanym terminie sprzęt <br> jest już zarezerwowany!</html>");
								Thread.sleep(3000);
								labelErrorDate.setText("");
							} catch (InterruptedException e) {
							}
						}
					}).start();
					return;
				}
		}
		new Thread(new Runnable() {
			public void run() {
				try {
					labelErrorDate.setText("Pomyslnie dodano do koszyka!");
					Thread.sleep(3000);
					labelErrorDate.setText("");
				} catch (InterruptedException e) {
				}
			}
		}).start();
		arrayListCartItems.add(assortment);
		arrayListCartDates.add(calendar.getDate());
		arrayListCartLength.add(length);
	}

	public ArrayList<Client> getArrayListClients() {
		return arrayListClients;
	}

	public void setArrayListClients(ArrayList<Client> arrayListClients) {
		this.arrayListClients = arrayListClients;
	}

	public ArrayList<Worker> getArrayListWorkers() {
		return arrayListWorkers;
	}

	public void setArrayListWorkers(ArrayList<Worker> arrayListWorkers) {
		this.arrayListWorkers = arrayListWorkers;
	}

	public ArrayList<Person> getArrayListPeople() {
		return arrayListPeople;
	}

	public void setArrayListPeople(ArrayList<Person> arrayListPeople) {
		this.arrayListPeople = arrayListPeople;
	}

	public ArrayList<Account> getArrayListAccounts() {
		return arrayListAccounts;
	}

	public void setArrayListAccounts(ArrayList<Account> arrayListAccounts) {
		this.arrayListAccounts = arrayListAccounts;
	}

	public ArrayList<Address> getArrayListAddresses() {
		return arrayListAddresses;
	}

	public void setArrayListAddresses(ArrayList<Address> arrayListAddresses) {
		this.arrayListAddresses = arrayListAddresses;
	}

	public ArrayList<Assortment> getArrayListAssortment() {
		return arrayListAssortment;
	}

	public void setArrayListAssortment(ArrayList<Assortment> arrayListAssortment) {
		this.arrayListAssortment = arrayListAssortment;
	}

	public ArrayList<Model> getArrayListModels() {
		return arrayListModels;
	}

	public void setArrayListModels(ArrayList<Model> arrayListModels) {
		this.arrayListModels = arrayListModels;
	}

	public ArrayList<OrderHistory> getArrayListOrders() {
		return arrayListOrders;
	}

	public void setArrayListOrders(ArrayList<OrderHistory> arrayListOrders) {
		this.arrayListOrders = arrayListOrders;
	}

	public ArrayList<MaintenanceHistory> getArrayListMaintenances() {
		return arrayListMaintenances;
	}

	public void setArrayListMaintenances(ArrayList<MaintenanceHistory> arrayListMaintenances) {
		this.arrayListMaintenances = arrayListMaintenances;
	}

	public ArrayList<RepairHistory> getArrayListRepairs() {
		return arrayListRepairs;
	}

	public void setArrayListRepairs(ArrayList<RepairHistory> arrayListRepairs) {
		this.arrayListRepairs = arrayListRepairs;
	}

	public int getLoggedID() {
		return loggedID;
	}

	public void setLoggedID(int loggedID) {
		this.loggedID = loggedID;
	}

}
