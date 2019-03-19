package controllers;

import com.jasiczek.fitnessCenter.App;

import models.Worker;
import views.LoginClientView;
import views.LoginUserCreationView;
import views.LoginWindow;
import views.LoginWorkerView;
/**
 * Login controller class
 *
 */
public class LoginController
{
	private boolean loggedIn;
	private int loggedID;
	
	private LoginWindow loginView;
	
	private LoginWorkerView workerLogin;
	
	private LoginClientView clientLogin;
	
	private LoginUserCreationView userCreation;
	
	private App app;
	
	/**
	 * Constructor
	 * @param app
	 */
	public LoginController(App app)
	{
		this.app = app;
		loginView = new LoginWindow(this);
	}
	
	/**
	 * User register window
	 */
	public void loginScreenCreateUser()
	{
		loginView.setVisible(false);
		userCreation = new LoginUserCreationView(this);
	}
	
	public void createAccount()
	{
		
	}
	
	/**
	 * Employee login window
	 */
	public void loginScreenAsWorker()
	{
		loginView.setVisible(false);
		workerLogin = new LoginWorkerView(this);
	}
	
	/**
	 * Client login window
	 */
	public void loginScreenAsClient()
	{
		loginView.setVisible(false);
		clientLogin = new LoginClientView(this);
	}
	
	/**
	 * Method that redirects logged client to application
	 */
	public void loggedClient()
	{
		app.switchWindow(App.USER,null);
	}
	
	/**
         * Method that redirects logged employee to application
         */
	public void loggedWorker(Worker worker)
	{
		app.switchWindow(App.ADMIN, worker);
	}
	
	public void returnToMainScreen()
	{
		loginView.setVisible(true);
		if(workerLogin != null)
			workerLogin.closeWindow();
		if(clientLogin != null)
			clientLogin.closeWindow();
		if(userCreation != null)
			userCreation.closeWindow();
	}
	
	public void closeWindows()
	{
		if(workerLogin != null)
			workerLogin.closeWindow();
		if(clientLogin != null)
			clientLogin.closeWindow();
		loginView.closeWindow();
	}

}


