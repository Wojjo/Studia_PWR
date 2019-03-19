package com.jasiczek.fitnessCenter;

import java.awt.EventQueue;

import controllers.AdminPanelController;
import controllers.LoginController;
import controllers.UserPanelController;
import models.Worker;


/**
 * Hello world!
 *
 */
public class App 
{

	private  AdminPanelController adminController;
	public static final String ADMIN = "ADMIN";
	private  UserPanelController userController;
	public static final String USER = "USER";
	private  LoginController loginController;
	public static final String LOGIN = "LOGIN";


	
	
    public static void main( String[] args )
    {
    	
//		EventQueue.invokeLater(new Runnable() {
//			public void run()
//			{
//				try
//				{
//			        loginController = new LoginController();
//				//	window.mainFrame.setVisible(true);
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//				}
//			}
//		});

    	
    	App app = new App();
    	app.switchWindow(App.LOGIN, null);
		

    }
    
    public void switchWindow(String window, Object obj)
    {
    	switch(window)
    	{
	    	case ADMIN:
	    	{
	    		if(loginController != null)
	    			loginController.closeWindows();
	    		adminController = new AdminPanelController(this, (Worker)obj);
	    		break;
	    	}
	    	case USER:
	    	{
	    		if(loginController != null)
	    			loginController.closeWindows();
	    		userController = new UserPanelController(this);
	    		break;
	    	}
	    	case LOGIN:
	    	{
	    		if(adminController != null)
	    			adminController.closeWindows();
	    		if(userController != null)
	    			userController.closeWindows();
	    		 loginController = new LoginController(this);
	    		break;
	    	}
	    	default:
	    	{
	    		System.out.println("error while switching windows: "+window);
	    		break;
	    	}
    	}

    	

    }
    
//    public void switch()
//    {
//    
//    }
}
