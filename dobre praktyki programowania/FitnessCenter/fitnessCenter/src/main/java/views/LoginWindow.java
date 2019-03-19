package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


import controllers.LoginController;

public class LoginWindow
{
	private LoginController loginController;
	
	/** Main frame */
	private JFrame mainFrame;
	/** Logo */
	private JLabel labelLogo;
	/** Client login button */
	private JButton buttonClientLogin;
	/** Employee login button */
	private JButton buttonWorkerLogin;
	/** Create account button */
	private JButton buttonCreateAccount;
	/** Exit button */
	private JButton buttonExit;
	private JButton btnInfo;
	private JLabel fixLabelInfo;
	
	public LoginWindow(LoginController loginController)
	{
		this.loginController = loginController;
		
//		initVariables();
		initComponents();
		initListeners(); 
		mainFrame.setVisible(true);
	}
	
	/**
	 * Method that loads window's components
	 */
	private void initComponents()
	{
		mainFrame = new JFrame("Fitness Center");
		mainFrame.setSize(new Dimension(650, 720));
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.getContentPane().setLayout(null);
		
		labelLogo = new JLabel();
		labelLogo.setIcon(new ImageIcon(this.getClass().getResource("/Logo.png")));
		labelLogo.setBounds(156, 50, 320, 320);
		mainFrame.getContentPane().add(labelLogo);
			
		
		buttonWorkerLogin = new JButton("Log in as employee");
		buttonWorkerLogin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonWorkerLogin.setBounds(165, 397, 300, 40);
		mainFrame.getContentPane().add(buttonWorkerLogin);
		
		buttonClientLogin = new JButton("Log in as customer");
		buttonClientLogin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonClientLogin.setBounds(165, 447, 300, 40);
		mainFrame.getContentPane().add(buttonClientLogin);
		
		buttonCreateAccount = new JButton("Create account");
		buttonCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonCreateAccount.setBounds(165, 497, 300, 40);
		mainFrame.getContentPane().add(buttonCreateAccount);
		
		buttonExit = new JButton("Exit");
		buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 19));
		buttonExit.setBounds(165, 550, 300, 40);
		mainFrame.getContentPane().add(buttonExit);
		
		btnInfo = new JButton("");
		btnInfo.setIcon(new ImageIcon(LoginWindow.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		btnInfo.setBounds(12, 633, 43, 40);
		mainFrame.getContentPane().add(btnInfo);
		

		
	}
	
	private void initListeners()
	{
		buttonWorkerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.loginScreenAsWorker();
			}
		});
		
		buttonClientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController.loginScreenAsClient();
			}
		});
		
		buttonCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 loginController.loginScreenCreateUser();

				 loginController.createAccount();
			}
		});
		
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnInfo.addActionListener((e) ->{
			JDialog dialog = new JDialog();
			
			fixLabelInfo = new JLabel("<html>USER GUIDE<br/>1. Main menu consits of four buttons."
					+ "<br/>1.1 Button number one is responsibe for Admin log in"
					+ "<br/>1.2 Button number two is responsibe for User log in"
					+ "<br>1.3 Button number three is responsible for new user registration"
					+ "<br>1.4 Button number four is responsible for safely exiting the application"
					+ "<br>2. After clicking log in button you are redirected to a form where you are "
					+ "<br> asked to fill in you password and login after that to proceed "
					+ "<br> you will be prompted with success/failure of you login "
					+ "<br> after succesful log in you will be transported to corresping user/admin panel"
					+ "<br>3.In registration form you are required to fill in all the information"
					+ "not doing so will fail registration process   </html>");
			fixLabelInfo.setBounds(28, 28, 217, 273);
			mainFrame.getContentPane().add(fixLabelInfo);
			
			
			dialog.setBounds(600, 450, 600, 300);
			dialog.getContentPane().add(fixLabelInfo);
			dialog.setVisible(true);
		});
	}
	
	public void setVisible(boolean b)
	{
		mainFrame.setVisible(b);
	}
	
	public void closeWindow()
	{
		mainFrame.dispose();
	}
}
