package zad2;

import java.lang.ref.SoftReference;

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
	private static ArrayList<Thread> listOfThreads;
	Map<Long, Object> map;
	private int value, weight;
	private Random random;
	static int seedRange = 200;
	private long seed;
	private boolean stop;
	SoftReference<List<Solution>> reference;
	protected final Logger log = Logger.getLogger(getClass().getName());
	static int numberOfThreads = 3;
	int maxWeight = 300;
	int numItems = 15;
	static int size = 100;
	static long total;
	static long fail, found;

	public List<Items> generate_items(long bean) {
		list = new LinkedList<Items>();
		int a = (int) (bean + 1) / 10;
		for (int i = 0; i < 24; i++) {
			value = ((a / 2) * i) + 1;
			weight = ((a / 5) * i) + 1;
			list.add(new Items(i, value, weight));
		}
		return list;
	}
	
	public void newResult(String items, int curBestWeight)
	{
		if(Threads.result.size() < Threads.size)
		{
		Threads.result.add(new Solution(items, curBestWeight));
		}else
		{
			Threads.result.remove(0);
			Threads.result.add(new Solution(items, curBestWeight));
		}

	}

	public synchronized static void changeSize(int x) {
		if (result != null && result.size() > 0) {
			ArrayList<Solution> tempResultList = new ArrayList<>();

			for (int i = 0; i < x; i++) {
				tempResultList.add(Threads.result.get(i));
			}
			Threads.size = x;
			Threads.result = tempResultList;
		} else
			Threads.size = x;
	}
	
	
	public synchronized static void changeNumber(int num)
	{
		listOfThreads.clear();
		numberOfThreads = num;
		Map<Long, Object> map = new HashMap<Long, Object>();
		SoftReference reference = new SoftReference(map);
		Threads thread = new Threads(map, reference);
		
	}
	

	public Threads(Map<Long, Object> map, SoftReference<List<Solution>> reference) {
		this.map = map;
		map.put(0L, 0);
		map.put(1L, 0);
		this.reference = reference;
		stop = false;
		runThreads();
	}
	

	public void runThreads() {

		final long[] totalReferences = new long[numberOfThreads];
		final long[] failReferences = new long[numberOfThreads];
		listOfThreads = new ArrayList<Thread>();
		random = new Random();
		SystemConfig.change = true;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(numberOfThreads);

		for (int i = 0; i < numberOfThreads; i++) {
			final int index = i;
			listOfThreads.add(new Thread(new Runnable() {
				public void run() {
					while (numberOfThreads>0) {

						seed = Math.abs(random.nextLong() % seedRange);

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
									rand = r.nextInt(2);
									if (rand == 0) {
										Brute_force bf = new Brute_force(maxWeight, numItems);
										bf.startAlgorithm();
									}
									if (rand == 1) {
										Greedy gr = new Greedy(maxWeight, numItems);
										gr.startAlgorithm(maxWeight, numItems);
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
			while (numberOfThreads>0) 
			{
				for (Thread t : listOfThreads) {
					t.start();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (numberOfThreads>0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (map) {
						totalReferences[0] = Long.decode(String.valueOf(map.get(0L)));
						failReferences[0] = Long.decode(String.valueOf(map.get(1L)));

						System.out.println("\n" + "Liczba wszystkich odwolan: " + totalReferences[0] + "\n"
								+ "Liczba nieudanych odwolan: " + failReferences[0] + "\n");
						total = totalReferences[0];
						fail = failReferences[0]; // nowe
						found = total - fail;
						//System.out.println("Procent nie trafionych: " + ((float)fail/(float)total)*100);

					}
				}
			}
		}).start();

	}
}