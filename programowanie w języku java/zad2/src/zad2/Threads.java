package zad2;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
				
			}
		}
	}
}
