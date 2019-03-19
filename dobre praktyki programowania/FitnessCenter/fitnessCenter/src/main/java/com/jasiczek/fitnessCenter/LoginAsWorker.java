package com.jasiczek.fitnessCenter;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.AdminPanelController;
@Deprecated
public class LoginAsWorker
{
	private JFrame logInFrame;
	
	// elementy ekranu startowego aplikacji
	JLabel labelLogo;
	JLabel labelPassword;
	JLabel labelCorrectData;
	
	JTextField textFieldLogin;
	JPasswordField passwordFieldPassword;
	
	JCheckBox checkBoxShowPassword;
	
	JButton buttonLogIn;
	JButton buttonRemindPassword;
	JButton buttonReturnToMainScreen;

	
	public LoginAsWorker()
	{
		
		initComponents(); // tylko tworzenie i dodawanie elementów do okna
		initListeners(); // tworzenie i obsługa listenerów,
		logInFrame.setVisible(true);
		
	}
	

	private void initComponents()
	{
		logInFrame = new JFrame("Logowanie");
		logInFrame.setSize(new Dimension(950, 620));
		logInFrame.setResizable(false);
		logInFrame.setLayout(null);
		
		labelLogo = new JLabel();
		labelLogo.setIcon(new ImageIcon(this.getClass().getResource("/login.png")));
		labelLogo.setBounds(150, 50, 320, 320);
		logInFrame.add(labelLogo);
			
		textFieldLogin = new JTextField("Login");
		textFieldLogin.setBounds(500, 150, 300, 40);
		textFieldLogin.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		textFieldLogin.setForeground(Color.GRAY);
		logInFrame.add(textFieldLogin);
				
		passwordFieldPassword = new JPasswordField("Haslo");
		passwordFieldPassword.setBounds(500, 210, 300, 40);
		passwordFieldPassword.setFont(new Font("Times New Roman", Font.ITALIC, 14));
	//	passwordFieldPassword.setEchoChar((char) 0);
		passwordFieldPassword.setForeground(Color.GRAY);
		logInFrame.add(passwordFieldPassword);
		
		checkBoxShowPassword = new JCheckBox("Pokaż hasło");
		checkBoxShowPassword.setBounds(810, 210, 300, 40);
		logInFrame.add(checkBoxShowPassword);
		
		buttonLogIn = new JButton("Zaloguj się");
		buttonLogIn.setBounds(450, 300, 100, 40);
		logInFrame.add(buttonLogIn);

		buttonReturnToMainScreen = new JButton("Powrót do ekranu głównego");
		buttonReturnToMainScreen.setBounds(570, 300, 200, 40);
		logInFrame.add(buttonReturnToMainScreen);
		
		buttonLogIn.addActionListener((e) ->{
		//	AdminPanelController adminControler = new AdminPanelController();
			System.out.println("we are here");
		});
		

		
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
	}	
	

}
