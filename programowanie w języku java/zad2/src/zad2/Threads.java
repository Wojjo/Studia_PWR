package zad2;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Threads extends Thread {
	static List<Items> list = new LinkedList<Items>();
	static List<Solution> result;
	private ArrayList<Thread> listOfThreads;
	Map<Long, Object> map;
	private static int value, weight;
	private Random random;
	private long seed;
	private boolean stop;
	SoftReference<List<Solution>> reference;
	protected final Logger log = Logger.getLogger(getClass().getName());
	int numberOfThreads = 3, maxWeight = 30;
	static int numItems = 15;

	public static List<Items> generate_items(long bean) {
		int a = (int) (bean + 1) / 10;
		for (int i = 0; i < 24; i++) {
			value = ((a / 2) * i) + 1;
			weight = ((a / 10) * i) + 1;
			list.add(new Items(i, value, weight));
		}
		return list;
	}

	public Threads(Map<Long, Object> map, SoftReference<List<Solution>> reference) {
		this.map = map;
		this.reference = reference;
		stop = false;
		runThreads();
	}

	public void runThreads() {
		final long[] totalReferences = new long[3];
		final long[] failReferences = new long[3];
		listOfThreads = new ArrayList<Thread>();
		random = new Random();

		map.put(0L, 0);
		map.put(1L, 0);
		
		for (int i = 0; i < numberOfThreads; i++) {
			final int index = i;
			listOfThreads.add(new Thread(new Runnable() {
				public void run() {
					while (!stop) {
						//System.gc();
						seed = Math.abs(random.nextLong() % 100);
						
						synchronized (map) {
							totalReferences[index] = Long.decode(String.valueOf(map.get(0L)));
							totalReferences[index]++;
							map.put(0L, totalReferences[index]);
							log.log(Level.INFO,
									"Sprawdzenie czy w pamieci znajduje sie instancja o podanym ziarnie. Index watku "
											+ index + " ziarno " + seed);
							if (map.containsKey(seed)) {
								System.out.println("Wynik na liscie");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							} else {
								log.log(Level.INFO, "Generuje instancje na podstawie podanego ziarna Index watku "
										+ index + " ziarno " + seed);
								failReferences[index] = Long.decode(String.valueOf(map.get(1L)));
								failReferences[index]++;
								map.put(1L, failReferences[index]);
								
								generate_items(seed);
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								log.log(Level.INFO, "Obliczam wynik Index watku " + index + " " + seed);
								{
									int rand = 0;
									Random r = new Random();
									rand = r.nextInt(MainMenu.classes.size());

									try {
										
									//	SoftReference<Class> c = new SoftReference<Class>(MainMenu.classes.get(rand));
									//	SoftReference<Object> o;
									//	o = new SoftReference<Object>(c.get().getConstructors()[0].newInstance(maxWeight, numItems));
										SoftReference<Method> m = new SoftReference<Method>(MainMenu.classes.get(rand)
												.getMethod("startAlgorithm", new Class[] { int.class, int.class }));
										m.get().invoke(null, maxWeight, numItems);
										reference =  (SoftReference<List<Solution>>) map.put(seed, result);

									} catch (NoSuchMethodException | SecurityException | IllegalArgumentException
											| IllegalAccessException | InvocationTargetException  e) {

										e.printStackTrace();
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
			}));
		}

		new Thread(new Runnable() {
			public void run() {
				for (Thread t : listOfThreads) {
					t.start();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (!stop) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (map)
					{
						totalReferences[0] = Long.decode(String.valueOf(map.get(0L)));
                        failReferences[0] = Long.decode(String.valueOf(map.get(1L)));

                        System.out.println("\n"
                                + "Liczba wszystkich odwolan: " + totalReferences[0] + "\n"
                                + "Liczba nieudanych odwolan: " + failReferences[0] + "\n");
                        
					}
				}
			}
		}).start();

	}
}