package zad2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.Scanner;
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

	public static void menu() throws ClassNotFoundException, MalformedURLException, InterruptedException {

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
				load_classes();
				break;
			case 2:
				if (classes.size() > 0) {
				random();
				}
				else
				{
					System.out.println("Najpierw zaladuj klasy");
				}
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

	private static void load_classes() throws MalformedURLException, ClassNotFoundException {
		URL url = new URL(
				"file:C:\\Users\\pwojc\\OneDrive\\Dokumenty\\GitHub\\Studia_PWR\\programowanie w jêzyku java\\zad2\\algorytmy");
		urlsToLoadFrom = new URL[] { url };
		loader = new URLClassLoader(urlsToLoadFrom);
		classes = new LinkedList<Class>();
		File dir = new File(
				"C:\\Users\\pwojc\\OneDrive\\Dokumenty\\GitHub\\Studia_PWR\\programowanie w jêzyku java\\zad2\\algorytmy\\zad2");
		File[] directListing = dir.listFiles();
		System.out.println("Klasy zaladowane ");
		System.out.println("Dostepne algorytmy: ");
		if (directListing != null) {
			for (File child : directListing) {
				String childName = child.getName();
				String className = childName.replace(".class", "");
				System.out.println(className);
				String classPackage = "zad2.";
				String fullClassName = classPackage.concat(className);
				cla = loader.loadClass(fullClassName);
				methods = cla.getMethods();
				classes.add(cla);

			}
		}

	}

	public static void close() {
		System.out.println("Aby zakonczyc wybierz 0");
		int i = 1;
		Scanner scan = new Scanner(System.in);
		i = scan.nextInt();
		if (i == 0) {
			System.out.println("Zakonczono");
			System.exit(0);
		} else {
			close();
		}

	}

	public static void random() throws InterruptedException {
		Random r = new Random();
		int licznik=0, j=0;
		long random_bean;
		ReferenceQueue reference_queue = new ReferenceQueue();
		Map<Long, List<Solution>> map = new HashMap<Long, List<Solution>>();
		SoftReference reference = new SoftReference(map, reference_queue);
		Threads[] threads = new Threads[3];
		while (true) {
			for (int i = 0; i < 3; i++) {
				random_bean = r.nextInt(20) + 1;
				threads[i] = new Threads(map, random_bean, reference, i);
				threads[i].start();
				//	reference = (SoftReference) reference_queue.remove();
				//	reference.clear();
				//	licznik++;

			}
		//	j++;
		
		//System.out.println(j);
		//Thread.sleep(1000);

			}
		}

	

}
