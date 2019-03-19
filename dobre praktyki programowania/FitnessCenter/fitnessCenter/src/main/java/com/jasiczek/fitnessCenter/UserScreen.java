package com.jasiczek.fitnessCenter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

@Deprecated
public class UserScreen implements ActionListener {

	private LoginAsClient loginAsClient;

	JDialog dialogClientAccount;
	// elementy widoczne po zalogowaniu
	private List<Component> itemBrowseScreenComponents;
	JLabel labelLogo1;
	JButton buttonToCart;
	JButton buttonChooseType1;
	JButton buttonChooseType2;
	JButton buttonReturnToMainScreen;
	JPanel panelItems; // tutaj zaczynają się elementy scrollowalnego panelu
	GridBagConstraints gridBagConstraintsBrowse;
	JScrollPane scrollPaneItemPanel;

	// elementy widoczne po wyborze treningu (siłowy, fitness)
	private List<Component> itemInfoScreenComponents;
	JButton buttonReturnToBrowse;
	JButton buttonToCart2;
	JButton buttonAddToCart;
	JTextPane textPaneItemDescription;

	
	/**
	 * @wbp.parser.constructor
	 */
	public UserScreen(LoginAsClient loginAsClient) {
		this.loginAsClient = loginAsClient;
		initVariables();
		initComponents();
		initListeners();
	
		for (Component c : itemBrowseScreenComponents)
			c.setVisible(true);
		for (Component c : itemInfoScreenComponents)
			c.setVisible(false);
		
		dialogClientAccount.setVisible(true);

	}

	public UserScreen() {
		initVariables();
		initComponents();
		initListeners();
		
		for (Component c : itemBrowseScreenComponents)
			c.setVisible(true);
		for (Component c : itemInfoScreenComponents)
			c.setVisible(false);

		dialogClientAccount.setVisible(true);

	}
	
	private void initVariables()
	{
		itemBrowseScreenComponents = new ArrayList<Component>();
		itemInfoScreenComponents = new ArrayList<Component>();
	}

	private void initComponents() {
		dialogClientAccount = new JDialog();
		dialogClientAccount.setTitle("Zalogowany");
		dialogClientAccount.setSize(new Dimension(1280, 720));
		dialogClientAccount.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogClientAccount.setResizable(false);
		dialogClientAccount.setModal(true);

		dialogClientAccount.setLayout(null);

		dialogClientAccount.getContentPane().setLayout(null);


		// ELEMENTY EKRANU WYBORU TYPU ZAJĘĆ
		// ------------------------------------------------------------------------------------

		buttonReturnToMainScreen = new JButton("Powrót do ekranu głównego");
		buttonReturnToMainScreen.setBounds(50, 20, 200, 30);

		dialogClientAccount.add(buttonReturnToMainScreen);

		dialogClientAccount.getContentPane().add(buttonReturnToMainScreen);

		itemBrowseScreenComponents.add(buttonReturnToMainScreen);
		
		buttonToCart = new JButton("Koszyk");
		buttonToCart.setBounds(1050, 150, 150, 30);

		dialogClientAccount.add(buttonToCart);

		dialogClientAccount.getContentPane().add(buttonToCart);

		itemBrowseScreenComponents.add(buttonToCart);
		
		panelItems = new JPanel();
		panelItems.setBackground(Color.LIGHT_GRAY);
		panelItems.setLayout(new GridBagLayout());
		gridBagConstraintsBrowse = new GridBagConstraints();

		scrollPaneItemPanel = new JScrollPane(panelItems);
		scrollPaneItemPanel.setBounds(50, 200, 1170, 450);

		dialogClientAccount.add(scrollPaneItemPanel);

		dialogClientAccount.getContentPane().add(scrollPaneItemPanel);

		itemBrowseScreenComponents.add(scrollPaneItemPanel);
		
		
		buttonChooseType1 = new JButton();
		buttonChooseType1.setIcon(new ImageIcon(this.getClass().getResource("/wybor1.png")));
		buttonChooseType1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));

		buttonChooseType1.setBounds(100, 60, 150, 100);
		dialogClientAccount.add(buttonChooseType1);

		buttonChooseType1.setBounds(372, 60, 150, 100);
		dialogClientAccount.getContentPane().add(buttonChooseType1);

		itemBrowseScreenComponents.add(buttonChooseType1);
		
		buttonChooseType2 = new JButton();
		buttonChooseType2.setIcon(new ImageIcon(this.getClass().getResource("/wybor2.png")));
		buttonChooseType2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));

		buttonChooseType2.setBounds(300, 60, 150, 100);
		dialogClientAccount.add(buttonChooseType2);

		buttonChooseType2.setBounds(583, 60, 150, 100);
		dialogClientAccount.getContentPane().add(buttonChooseType2);

		itemBrowseScreenComponents.add(buttonChooseType2);
		
		// ELEMENTY

		buttonReturnToBrowse = new JButton("Powrót wyboru kategorii zajęć");

		buttonReturnToBrowse.setBounds(50, 50, 250, 30);
		dialogClientAccount.add(buttonReturnToBrowse);
		itemInfoScreenComponents.add(buttonReturnToBrowse);

		buttonToCart2 = new JButton("Koszyk");
		buttonToCart2.setBounds(50, 90, 150, 30);
		dialogClientAccount.add(buttonToCart2);
		itemInfoScreenComponents.add(buttonToCart2);

		buttonAddToCart = new JButton("Zapisz mnie");
		buttonAddToCart.setBounds(50, 130, 150, 30);
		dialogClientAccount.add(buttonAddToCart);

		buttonReturnToBrowse.setBounds(50, 64, 250, 30);
		dialogClientAccount.getContentPane().add(buttonReturnToBrowse);
		itemInfoScreenComponents.add(buttonReturnToBrowse);

		buttonToCart2 = new JButton("Dodaj środki");
		buttonToCart2.setBounds(50, 107, 150, 30);
		dialogClientAccount.getContentPane().add(buttonToCart2);
		itemInfoScreenComponents.add(buttonToCart2);

		buttonAddToCart = new JButton("Zapisz mnie");
		buttonAddToCart.setBounds(50, 150, 150, 30);
		dialogClientAccount.getContentPane().add(buttonAddToCart);

		itemInfoScreenComponents.add(buttonAddToCart);

		textPaneItemDescription = new JTextPane();
		textPaneItemDescription.setBounds(50, 370, 1170, 300);
		textPaneItemDescription.setBackground(Color.LIGHT_GRAY);
		textPaneItemDescription.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		//textPaneItemDescription.setEnabled(false);

		dialogClientAccount.add(textPaneItemDescription);

		dialogClientAccount.getContentPane().add(textPaneItemDescription);

		itemInfoScreenComponents.add(textPaneItemDescription);


	}

	private void initListeners() {

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == buttonToCart2) {
//			Przekazać do panelu customerId
//			AddingFundsPanel addingFundsPanel = new AddingFundsPanel(customerId);
//			addingFundsPanel.setVisible(true);
//			addingFundsPanel.setLocationRelativeTo(null);
		}
		
	}


}
