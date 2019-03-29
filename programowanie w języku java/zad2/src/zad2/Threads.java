package zad2;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Threads extends Thread {
	static List<Items> list;
	Map<Long, List<Items>> map;
	private static long value, weight;
	private long random_bean;
	SoftReference<List<Items>> reference;
	protected final Logger log = Logger.getLogger(getClass().getName());
	private int thread_index;
	int maxWeight = 20;
	static int numItems = 0;

	public static List<Items> generate_items(long bean) {
		numItems = (int) bean;
		List<Items> items = new LinkedList<Items>();
		for (int i = 0; i < bean; i++) {
			value = (i * bean) + 2;
			weight = (i * bean) + 1;
			items.add(new Items(i, value, weight));
		}
		return items;
	}

	public Threads(Map<Long, List<Items>> map, long random_bean, SoftReference<List<Items>> reference, int thread_index) {
		this.map = map;
		this.random_bean = random_bean;
		this.reference = reference;
		this.thread_index = thread_index;
	}

	public void run() {
		synchronized (map) {
			try {
				FileHandler fhandler = new FileHandler("log" + thread_index + "\\log" + thread_index + ".txt");
				SimpleFormatter sformatter = new SimpleFormatter();
				fhandler.setFormatter(sformatter);
				log.addHandler(fhandler);
			} catch (IOException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			} catch (SecurityException ex) {
				log.log(Level.SEVERE, ex.getMessage(), ex);
			}

			log.log(Level.INFO,
					"Sprawdzenie czy w pamieci znajduje sie instancja o podanym ziarnie. Index watku " + thread_index);
			if (map.containsKey(random_bean)) {
				list = map.get(random_bean);
				System.out.println("Wynik na liscie");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				log.log(Level.INFO, "Generuje instancje na podstawie podanego ziarna Index watku " + thread_index);
				reference = new SoftReference<List<Items>>(map.put(random_bean, generate_items(random_bean)));
				list = map.get(random_bean);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				log.log(Level.INFO, "Obliczam wynik Index watku " + thread_index);
				{
					int rand;
					Random r = new Random();
					rand = r.nextInt(2);
					rand = 0;
					if (rand == 0) {
						log.log(Level.INFO, "Rozwiazanie przy uzyciu Brute_force");
						try {
							Method bf = (MainMenu.classes.get(0).getMethod("startAlgorithm",
									new Class[] { int.class, int.class }));
							bf.invoke(null, maxWeight, numItems);
						} catch (NoSuchMethodException | SecurityException | IllegalArgumentException
								| IllegalAccessException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					if (rand == 1) {
						log.log(Level.INFO, "Rozwiazanie przy uzyciu Greedy");
						Greedy greedy = new Greedy(maxWeight, numItems);
						greedy.startAlgorithm();
					}

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

}
