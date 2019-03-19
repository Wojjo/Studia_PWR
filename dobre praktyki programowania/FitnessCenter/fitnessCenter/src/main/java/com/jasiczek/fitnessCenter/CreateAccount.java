package com.jasiczek.fitnessCenter;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
@Deprecated
public class CreateAccount 
{
	private MainWindow mainWindow;
	
	JDialog dialogCreateAccount;
	JLabel labelLogin;
	JTextField textFieldLogin;
	JLabel labelPassword;
	JPasswordField passwordFieldPassword;
	JLabel labelRepeatPassword;
	JPasswordField passwordFieldRepeatPassword;
	JLabel labelShowPassword;
	JCheckBox checkBoxShowPassword;
	JLabel labelSecurityQuestion;
	JTextField textFieldSecurityQuestion;
	JLabel labelSecurityAnswer;
	JTextField textFieldSecurityAnswer;
	JLabel labelName;
	JTextField textFieldName;
	JLabel labelLastName;
	JTextField textFieldLastName;
	JLabel labelBirthDate;
	JTextField textFieldBirthDate;
	JLabel labelPhoneNumber;
	JTextField textFieldPhoneNumber;
	JLabel labelCity;
	JTextField textFieldCity;
	JLabel labelPostalCode;
	JTextField textFieldPostalCode;
	JLabel labelStreet;
	JTextField textFieldStreet;
	JLabel labelHouseNumber;
	JTextField textFieldHouseNumber;
	JLabel labelFlatNumber;
	JTextField textFieldFlatNumber;
	JLabel labelRequiredFields;
	JButton buttonCancel;
	JButton buttonCreateClientAccount;
	

	

	/**
	 * @wbp.parser.constructor
	 */

	public CreateAccount(MainWindow mainWindow)
	{
		this.mainWindow = mainWindow;
		
		initComponents();
		initListeners();
		
		dialogCreateAccount.setVisible(true);
		
	}
	public CreateAccount()
	{
			
		initComponents();
		initListeners();
		
		dialogCreateAccount.setVisible(true);
		
	}
	
	private void initComponents() 
	{
		dialogCreateAccount = new JDialog();
		dialogCreateAccount.setTitle("Tworzenie konta");

		dialogCreateAccount.setSize(new Dimension(500, 450));
		dialogCreateAccount.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogCreateAccount.setResizable(false);
		dialogCreateAccount.setModal(true);
		dialogCreateAccount.setLayout(null);

		dialogCreateAccount.setSize(new Dimension(505, 426));
		dialogCreateAccount.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogCreateAccount.setResizable(false);
		dialogCreateAccount.setModal(true);
		dialogCreateAccount.getContentPane().setLayout(null);

		

		labelLogin = new JLabel("Login:");
		labelLogin.setBounds(30, 30, 150, 30);

		dialogCreateAccount.add(labelLogin);
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(180, 30, 150, 30);
		dialogCreateAccount.add(textFieldLogin);

		labelPassword = new JLabel("Hasło:");
		labelPassword.setBounds(30, 70, 150, 30);
		dialogCreateAccount.add(labelPassword);
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(180, 70, 150, 30);
		passwordFieldPassword.setEchoChar('*');
		dialogCreateAccount.add(passwordFieldPassword);

		labelRepeatPassword = new JLabel("Powtórz hasło:");
		labelRepeatPassword.setBounds(30, 110, 150, 30);
		dialogCreateAccount.add(labelRepeatPassword);
		passwordFieldRepeatPassword = new JPasswordField();
		passwordFieldRepeatPassword.setBounds(180, 110, 150, 30);
		passwordFieldRepeatPassword.setEchoChar('*');
		dialogCreateAccount.add(passwordFieldRepeatPassword);

		labelShowPassword = new JLabel("Pokaz haslo:");
		labelShowPassword.setBounds(350, 70, 100, 30);
		dialogCreateAccount.add(labelShowPassword);
		checkBoxShowPassword = new JCheckBox();
		checkBoxShowPassword.setBounds(440, 75, 20, 20);
		dialogCreateAccount.add(checkBoxShowPassword);

		labelName = new JLabel("Imie:");
		labelName.setBounds(30, 150, 150, 30);
		dialogCreateAccount.add(labelName);
		textFieldName = new JTextField();
		textFieldName.setBounds(180, 150, 150, 30);
		dialogCreateAccount.add(textFieldName);

		labelLastName = new JLabel("Nazwisko:");
		labelLastName.setBounds(30, 190, 150, 30);
		dialogCreateAccount.add(labelLastName);
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(180, 190, 150, 30);
		dialogCreateAccount.add(textFieldLastName);

		dialogCreateAccount.getContentPane().add(labelLogin);
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(180, 30, 150, 30);
		dialogCreateAccount.getContentPane().add(textFieldLogin);

		labelPassword = new JLabel("Hasło:");
		labelPassword.setBounds(30, 70, 150, 30);
		dialogCreateAccount.getContentPane().add(labelPassword);
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(180, 70, 150, 30);
		passwordFieldPassword.setEchoChar('*');
		dialogCreateAccount.getContentPane().add(passwordFieldPassword);

		labelRepeatPassword = new JLabel("Powtórz hasło:");
		labelRepeatPassword.setBounds(30, 110, 150, 30);
		dialogCreateAccount.getContentPane().add(labelRepeatPassword);
		passwordFieldRepeatPassword = new JPasswordField();
		passwordFieldRepeatPassword.setBounds(180, 110, 150, 30);
		passwordFieldRepeatPassword.setEchoChar('*');
		dialogCreateAccount.getContentPane().add(passwordFieldRepeatPassword);

		labelShowPassword = new JLabel("Pokaz haslo:");
		labelShowPassword.setBounds(338, 70, 100, 30);
		dialogCreateAccount.getContentPane().add(labelShowPassword);
		checkBoxShowPassword = new JCheckBox();
		checkBoxShowPassword.setBounds(411, 70, 43, 30);
		dialogCreateAccount.getContentPane().add(checkBoxShowPassword);

		labelName = new JLabel("Imie:");
		labelName.setBounds(30, 150, 150, 30);
		dialogCreateAccount.getContentPane().add(labelName);
		textFieldName = new JTextField();
		textFieldName.setBounds(180, 150, 150, 30);
		dialogCreateAccount.getContentPane().add(textFieldName);

		labelLastName = new JLabel("Nazwisko:");
		labelLastName.setBounds(30, 190, 150, 30);
		dialogCreateAccount.getContentPane().add(labelLastName);
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(180, 190, 150, 30);
		dialogCreateAccount.getContentPane().add(textFieldLastName);


		
	
		buttonCancel = new JButton("Anuluj");

		buttonCancel.setBounds(250, 270, 80, 30);
		dialogCreateAccount.add(buttonCancel);

		buttonCreateClientAccount = new JButton("Utworz");
		buttonCreateClientAccount.setBounds(130, 270, 80, 30);
		dialogCreateAccount.add(buttonCreateClientAccount);

		buttonCancel.setBounds(283, 249, 80, 30);
		dialogCreateAccount.getContentPane().add(buttonCancel);

		buttonCreateClientAccount = new JButton("Utworz");
		buttonCreateClientAccount.setBounds(136, 249, 80, 30);
		dialogCreateAccount.getContentPane().add(buttonCreateClientAccount);

		
		
	}

	private void initListeners() 
	{
		checkBoxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBoxShowPassword.isSelected()) {
					passwordFieldPassword.setEchoChar((char) 0);
					passwordFieldRepeatPassword.setEchoChar((char) 0);
				} else {
					passwordFieldPassword.setEchoChar('*');
					passwordFieldRepeatPassword.setEchoChar('*');
				}
			}
		});
		
	}

	
	

	
}




