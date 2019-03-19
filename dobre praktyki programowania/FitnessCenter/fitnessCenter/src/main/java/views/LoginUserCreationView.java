package views;

import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jasiczek.fitnessCenter.MainWindow;

import controllers.LoginController;
import database.SQLConnection;

import java.awt.Font;

public class LoginUserCreationView
{

	private LoginController loginController;
	private SQLConnection sql = new SQLConnection();
	/** "Create account" dialog window */
	private JDialog dialogCreateAccount;
	/** Login label */
	private JLabel labelLogin;
	/** Password label */
	private JLabel labelPassword;
	/** Repeat password label */
	private JLabel labelRepeatPassword;
	/** Name label */
	private JLabel labelName;
	/** Lastname label */
	private JLabel labelLastName;
	/** Login text field */
	private JTextField textFieldLogin;
	/** Password text field */
	private JPasswordField passwordFieldPassword;
	/** Repeat password field */
	private JPasswordField passwordFieldRepeatPassword;
	/** Name text field */
	private JTextField textFieldName;
	/** Lastname text field */
	private JTextField textFieldLastName;
	/** "Show password" checkbox */
	private JCheckBox checkBoxShowPassword;
	/** Cancel button */
	private JButton buttonCancel;
	/** Create account button */
	private JButton buttonCreateClientAccount;
	
	public LoginUserCreationView(LoginController loginController)
	{
		this.loginController = loginController;	
		
		initComponents();
		initListeners();
		
		dialogCreateAccount.setVisible(true);

	}
	
	/**
	 * Method that loads window's components
	 */
	private void initComponents() 
	{
		dialogCreateAccount = new JDialog();
		dialogCreateAccount.setTitle("Create account panel");
		dialogCreateAccount.setSize(new Dimension(516, 421));
		dialogCreateAccount.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dialogCreateAccount.setResizable(false);
		dialogCreateAccount.setLocationRelativeTo(null);
		dialogCreateAccount.getContentPane().setLayout(null);
		dialogCreateAccount.setModalityType(ModalityType.MODELESS);
		

		labelLogin = new JLabel("Login:");
		labelLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelLogin.setBounds(60, 30, 108, 30);
		dialogCreateAccount.getContentPane().add(labelLogin);
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(191, 30, 150, 30);
		dialogCreateAccount.getContentPane().add(textFieldLogin);

		labelPassword = new JLabel("Password:");
		labelPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelPassword.setBounds(60, 73, 92, 30);
		dialogCreateAccount.getContentPane().add(labelPassword);
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(191, 73, 150, 30);
		passwordFieldPassword.setEchoChar('*');
		dialogCreateAccount.getContentPane().add(passwordFieldPassword);

		labelRepeatPassword = new JLabel("Repeat password:");
		labelRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelRepeatPassword.setBounds(60, 113, 122, 30);
		dialogCreateAccount.getContentPane().add(labelRepeatPassword);
		
		passwordFieldRepeatPassword = new JPasswordField();
		passwordFieldRepeatPassword.setBounds(191, 113, 150, 30);
		passwordFieldRepeatPassword.setEchoChar('*');
		dialogCreateAccount.getContentPane().add(passwordFieldRepeatPassword);
		
		checkBoxShowPassword =  new JCheckBox("Show password");
		checkBoxShowPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBoxShowPassword.setBounds(349, 78, 131, 20);
		dialogCreateAccount.getContentPane().add(checkBoxShowPassword);

		labelName = new JLabel("First name:");
		labelName.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelName.setBounds(60, 156, 108, 30);
		dialogCreateAccount.getContentPane().add(labelName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(191, 156, 150, 30);
		dialogCreateAccount.getContentPane().add(textFieldName);

		labelLastName = new JLabel("Last name:");
		labelLastName.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelLastName.setBounds(60, 199, 108, 30);
		dialogCreateAccount.getContentPane().add(labelLastName);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(191, 199, 150, 30);
		dialogCreateAccount.getContentPane().add(textFieldLastName);
	
		buttonCancel = new JButton("Cancel");
		buttonCancel.setBounds(302, 270, 108, 30);
		dialogCreateAccount.getContentPane().add(buttonCancel);

		buttonCreateClientAccount = new JButton("Create");
		buttonCreateClientAccount.setBounds(110, 270, 108, 30);
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
		
		buttonCreateClientAccount.addActionListener((e) ->{
			if(sql.createCustomer(textFieldName.getText(), textFieldLastName.getText(), textFieldLogin.getText(), String.valueOf(passwordFieldPassword.getPassword()), String.valueOf(passwordFieldRepeatPassword.getPassword()))) {
				loginController.returnToMainScreen();	
			}
		});
		
		buttonCancel.addActionListener((e) ->{
			loginController.returnToMainScreen();
		});
		
	}
	
	public void closeWindow()
	{
		dialogCreateAccount.setModalityType(ModalityType.MODELESS);
		dialogCreateAccount.dispose();
	}

}
