package zad2;

import java.net.MalformedURLException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;

public class Main
{
	public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InterruptedException, MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException
	{
		MainMenu.menu();
		
	}
}
