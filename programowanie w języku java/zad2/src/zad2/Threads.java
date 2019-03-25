package zad2;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Threads extends Thread {
	private List<Long> list;
	Map<Long, List<Long>> map;
	private long sum;
	private long random_bean;
	SoftReference reference;
	protected final Logger log = Logger.getLogger(getClass().getName());
	private int thread_index;

	public static List<Long> generate_items(long bean) {
		List<Long> items = new LinkedList<Long>();
		for (int i = 0; i < bean; i++) {
			long item = (i * bean) + 1;
			items.add(item);
		}
		return items;
	}

	public Threads(Map<Long, List<Long>> map, long random_bean, SoftReference reference, int thread_index) {
		this.map = map;
		this.random_bean = random_bean;
		this.reference = reference;
		this.thread_index = thread_index;
	}

	public void run() {
		synchronized (map) 
		{
			try
			{
				FileHandler fhandler = new FileHandler("log" + thread_index + "\\log" + thread_index + ".txt");
				SimpleFormatter sformatter = new SimpleFormatter();
				fhandler.setFormatter(sformatter);
				log.addHandler(fhandler);
			}
			catch(IOException e)
			{
				log.log(Level.SEVERE, e.getMessage(), e);
			}
			
			log.log(Level.INFO, "Sprawdzenie czy w pamieci znajduje sie instancja o podanym ziarnie");
			if (map.containsKey(random_bean))
			{
				list = map.get(random_bean);
			} 
			else
			{
				log.log(Level.INFO, "Generuje instancje na podstawie podanego ziarna");
				reference = new SoftReference(map.put(random_bean,  generate_items(random_bean)));
				list = map.get(random_bean);
			}
			log.log(Level.INFO, "Obliczam wynik ");
			{
				
			}
		}
	}
}
