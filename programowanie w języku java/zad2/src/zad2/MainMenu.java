package zad2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainMenu {
	public static void menu() throws ClassNotFoundException, MalformedURLException {

		File file = new File("");
		URL[] urlsToLoadFrom = new URL[] { file.toURI().toURL() };
		URLClassLoader loader = new URLClassLoader(urlsToLoadFrom);
		LinkedList<Class> classes = new LinkedList<Class>();
		System.out.println("1. Zaladuj klasy");
		System.out.println("2. Rozwiaz problem plecakowy");
		System.out.println("3. Wyladuj klasy");
		System.out.println("0. Zakoncz");
		Scanner scan = new Scanner(System.in);
		int off = 1;
		int choose;
		while (off != 0) {
			choose = scan.nextInt();
			switch (choose) {
			case 1:
				classes.add(Class.forName("zad2.Brute_force", false, loader));
				classes.add(Class.forName("zad2.Greedy", false, loader));
				System.out.println("Klasy zaladowane");
				break;
			case 2:
				 try
				 {
				random();
				 } catch (Exception e) {
				 System.out.println("brak zaladownych klas");
				 }
				break;
			case 3:
				classes.remove(0);
				classes.remove(1);
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

	public static void random() {
		Random r = new Random();
		long bean = r.nextInt(18);
		long random_bean;
		ReferenceQueue reference_queue = new ReferenceQueue();
		Map<Long, List<Items>> map = new HashMap<Long, List<Items>>();
		SoftReference reference = new SoftReference(map, reference_queue);
		Threads[] threads = new Threads[3];

		while (true) {
			for (int i = 0; i < 2; i++) {
				random_bean = r.nextInt(18) + 1;
				threads[i] = new Threads(map, random_bean, reference, i);
				threads[i].start();
	
			}
		}

	}

}
