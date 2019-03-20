package zad2;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Start {
	
	public void random() {
		Random r = new Random();
		long bean = r.nextInt(3000) + 1;
		long random_bean;
		ReferenceQueue reference_queue = new ReferenceQueue();
		Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();
		SoftReference reference = new SoftReference(map, reference_queue);
		Threads[] threads = new Threads[5];

		while (true) {
			for (int i = 0; i < 5; i++) {
				random_bean = r.nextInt(3000) + 1;
				threads[i] = new Threads(map, random_bean, reference, i);
				threads[i].start();
			}
		}

	}

}
