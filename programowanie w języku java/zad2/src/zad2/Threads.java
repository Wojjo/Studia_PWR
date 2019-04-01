package zad2;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Threads extends Thread {
	static List<Items> list = new LinkedList<Items>();
	static List<Solution> result;
	Map<Long, List<Solution>> map;
	private static int value, weight;
	private long random_bean;
	SoftReference<List<Solution>> reference;
	protected final Logger log = Logger.getLogger(getClass().getName());
	private int thread_index;
	int maxWeight = 30;
	static int numItems = 15;

	public static List<Items> generate_items(long bean)
	{
		int a = (int) (bean+1)/10;
		for (int i = 0; i < 24; i++) {
			value = ((a/2) * i) + 1;
			weight = ((a/10) * i )+ 1;
			list.add(new Items(i, value, weight));
		}
		return list;
	}


	public Threads(Map<Long, List<Solution>> map, long random_bean, SoftReference<List<Solution>> reference, int thread_index) {
		this.map = map;
		this.random_bean = random_bean;
		this.reference = reference;
		this.thread_index = thread_index;
	}

	public void run() {
		synchronized (map) {
	
		
			log.log(Level.INFO,
					"Sprawdzenie czy w pamieci znajduje sie instancja o podanym ziarnie. Index watku " + thread_index + " ziarno " + random_bean);
			if (map.containsKey(random_bean)) {
				System.out.println("Wynik na liscie");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				log.log(Level.INFO, "Generuje instancje na podstawie podanego ziarna Index watku " + thread_index + " ziarno " + random_bean);
				generate_items(random_bean);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				log.log(Level.INFO, "Obliczam wynik Index watku " + thread_index + " " + random_bean);
				{
					int rand=0;
					Random r = new Random();
					rand = r.nextInt(MainMenu.classes.size());
								
					try {
						SoftReference<Method> m = new SoftReference <Method> (MainMenu.classes.get(rand).getMethod("startAlgorithm",
								new Class[] { int.class, int.class }));
						m.get().invoke(null, maxWeight, numItems);
						reference = new SoftReference<List<Solution>>(map.put(random_bean, result));	
						
						
					} catch (NoSuchMethodException | SecurityException | IllegalArgumentException
							| IllegalAccessException | InvocationTargetException e) {
						
						e.printStackTrace();
					}
					
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

}