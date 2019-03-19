package com.jasiczek.fitnessCenter;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//import models.Client;
//import models.Worker;


@Deprecated
public class MainWindow
{
	private JFrame mainFrame;
	private boolean loggedIn;
	private int loggedID;
//	
//	ArrayList<Client> arrayListClients;
//	ArrayList<Worker> arrayListWorkers;
	
	// elementy ekranu startowego aplikacji
	JLabel labelLogo;
	JButton buttonClientLogin;
	JButton buttonWorkerLogin;
	JButton buttonCreateAccount;
	JButton buttonExit;
	
	public MainWindow()
	{
		initVariables();
		initComponents(); // tylko tworzenie i dodawanie elementów do okna
		initListeners(); // tworzenie i obsługa listenerów,
		mainFrame.setVisible(true);
		
	}
	
	private void initVariables()
	{
		loggedIn = false;
//		arrayListClients = new ArrayList<Client>();
//		arrayListWorkers = new ArrayList<Worker>();
	}
	
	private void initComponents()
	{
		mainFrame = new JFrame("Fitness Center");
		mainFrame.setSize(new Dimension(650, 720));
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		
		labelLogo = new JLabel();
		labelLogo.setIcon(new ImageIcon(this.getClass().getResource("/Logo.png")));
		labelLogo.setBounds(150, 50, 320, 320);
		mainFrame.add(labelLogo);
			
		
		buttonWorkerLogin = new JButton("Zaloguj się jako pracownik");
		buttonWorkerLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonWorkerLogin.setBounds(150, 400, 300, 40);
		mainFrame.add(buttonWorkerLogin);
		
		buttonClientLogin = new JButton("Zaloguj się jako klient");
		buttonClientLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonClientLogin.setBounds(150, 450, 300, 40);
		mainFrame.add(buttonClientLogin);
		
		buttonCreateAccount = new JButton("Załóż konto");
		buttonCreateAccount.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonCreateAccount.setBounds(150, 500, 300, 40);
		mainFrame.add(buttonCreateAccount);
		
		buttonExit = new JButton("Wyjście");
		buttonExit.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		buttonExit.setBounds(150, 550, 300, 40);
		mainFrame.add(buttonExit);
		
	}
	
	private void initListeners()
	{
		buttonWorkerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginAsWorker();
			}
		});
		
		buttonClientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginAsClient();
			}
		});
		
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 createAccount();
			}
		});
		
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void createAccount()
	{
		new CreateAccount(this);
	}
	

	
	
	

}
