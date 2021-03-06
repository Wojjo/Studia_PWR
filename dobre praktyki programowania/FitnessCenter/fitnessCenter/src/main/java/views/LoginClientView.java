package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jasiczek.fitnessCenter.CreateAccount;

import controllers.LoginController;
import database.SQLConnection;

public class LoginClientView
{
	
	private LoginController loginController;
	private SQLConnection sql = new SQLConnection();
	private JFrame logInFrame;
	
	/** Logo */
	JLabel labelLogo;
	JLabel labelCorrectData;
	
	/** Login text field */
	JTextField textFieldLogin;
	/** Password text field */
	JPasswordField passwordFieldPassword;
	/** "Show password" checkbox */
	JCheckBox checkBoxShowPassword;
	/** Login button */
	JButton buttonLogIn;
	/** "Remind password" button */
	JButton buttonRemindPassword;
	/** Return button */
	JButton buttonReturnToMainScreen;

	
	public LoginClientView(LoginController loginController)
	{
		this.loginController = loginController;
		
		initComponents(); 
		initListeners(); 
		logInFrame.setVisible(true);
		
	}
	
	/**
	 * Method that loads window's components
	 */
	private void initComponents()
	{
		logInFrame = new JFrame("Logowanie");
		logInFrame.setTitle("Login panel");
		logInFrame.setSize(new Dimension(885, 528));
		logInFrame.setResizable(false);
		logInFrame.getContentPane().setLayout(null);
		logInFrame.setLocationRelativeTo(null);
		logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		labelLogo = new JLabel();
		labelLogo.setIcon(new ImageIcon(this.getClass().getResource("/login.png")));
		labelLogo.setBounds(93, 56, 320, 320);
		logInFrame.getContentPane().add(labelLogo);
			
		textFieldLogin = new JTextField("Login");
		textFieldLogin.setBounds(425, 150, 300, 40);
		textFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldLogin.setForeground(Color.BLACK);
		logInFrame.getContentPane().add(textFieldLogin);
				
		passwordFieldPassword = new JPasswordField("Password");
		passwordFieldPassword.setBounds(425, 210, 300, 40);
		passwordFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordFieldPassword.setForeground(Color.BLACK);
		logInFrame.getContentPane().add(passwordFieldPassword);
		
		checkBoxShowPassword = new JCheckBox("Show password");
		checkBoxShowPassword.setBounds(733, 210, 137, 40);
		logInFrame.getContentPane().add(checkBoxShowPassword);
		
		buttonLogIn = new JButton("Login");
		buttonLogIn.setBounds(503, 263, 137, 31);
		logInFrame.getContentPane().add(buttonLogIn);
						
		buttonReturnToMainScreen = new JButton("Return to the main screen");
		buttonReturnToMainScreen.setBounds(653, 449, 214, 31);
		logInFrame.getContentPane().add(buttonReturnToMainScreen);
		
	}
	
	private void initListeners()
	{
		checkBoxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBoxShowPassword.isSelected()) {
					passwordFieldPassword.setEchoChar((char) 0);
					
				} else {
					passwordFieldPassword.setEchoChar('*');
					
				}
			}
		});
			
//		buttonCreateAccount.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			//	new CreateAccount();
//			}
//		});
		
		/**
		 * Log-in method
		 */
		buttonLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sql.customerLogin(textFieldLogin.getText(), String.valueOf(passwordFieldPassword.getPassword())) > 0) {
					loginController.loggedClient();
				}
			}
		});
		
		buttonReturnToMainScreen.addActionListener((e) ->{
			loginController.returnToMainScreen();
		});
		
		
	}
	
	public void closeWindow()
	{
		logInFrame.dispose();
	}

}
