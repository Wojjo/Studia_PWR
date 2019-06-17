package zad2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.Scanner;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import java.lang.management.ManagementFactory;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainMenu extends Thread {
	public static LinkedList<Class> classes = new LinkedList<Class>();
	static URL[] urlsToLoadFrom;
	static URLClassLoader loader;
	static Class cla;
	static Method[] methods;

	public static void menu()
			throws ClassNotFoundException, MalformedURLException, InterruptedException, MalformedObjectNameException,
			InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {

		System.out.println("2. Rozwiaz problem plecakowy");
		System.out.println("0. Zakoncz");
		Scanner scan = new Scanner(System.in);
		int off = 1;
		int choose;
		while (off != 0) {
			choose = scan.nextInt();
			switch (choose) {
			case 1:

				break;
			case 2:
				random();
				break;
			case 3:
				if (classes.size() > 0) {
					while (classes.size() != 0) {
						classes.remove();
					}
					System.out.println("Klasy zosta³y wy³adowane");
				} else {
					System.out.println("Brak klas");
				}

				break;
			case 0:
				off = 0;
				break;

			default:
				System.out.println("Wybierz dostepna opjce");
				choose = scan.nextInt();
			}
		}

	}

	public static void random() throws InterruptedException, MalformedObjectNameException,
			InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {

		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		SystemConfig mBean = new SystemConfig(Threads.numberOfThreads, Threads.size);
		ObjectName name = new ObjectName("lab2:type=SystemConfig");
		mbs.registerMBean(mBean, name);

		Map<Long, Object> map = new HashMap<Long, Object>();
		SoftReference reference = new SoftReference(map);
		Threads thread = new Threads(map, reference);

		

	}

}
