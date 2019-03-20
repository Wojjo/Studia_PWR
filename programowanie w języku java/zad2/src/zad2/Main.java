package zad2;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main
{
	public static void main(String[] args)
	{
		Random r = new Random();
		long bean = r.nextInt(3000) +1;
		float random_bean;
		ReferenceQueue reference_queue = new ReferenceQueue();
		Map<Long, List<Double>> map = new HashMap<Long, List<Double>>();
		SoftReference referencja = new SoftReference(map, reference_queue);
		
	}
}
