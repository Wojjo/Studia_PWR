package zad2;

public class SystemConfig implements SystemConfigMBean {
	static boolean change = false;
	int i=0;
	int free=0, used;
	public SystemConfig(int numberOfThreads, int size) {
		setThreadCount(numberOfThreads);
		setMapSize(size);
	}

	@Override
	public void setThreadCount(int numberOfThreads) {
		Threads.numberOfThreads = numberOfThreads;
		if (change == true) {
			Threads.changeNumber(numberOfThreads);
		}
	}

	@Override
	public int getThreadCount() {
		return Threads.numberOfThreads;
	}

	@Override
	public void setMapSize(int size) {
		Threads.changeSize(size);

	}

	@Override
	public int getMapSize() {
		return Threads.size;
	}



	@Override
	public String displayInfo() {
		i = Threads.result.size();
		free = Threads.size - i;
		
		String threads = "Number of threads is " + getThreadCount() + "\n";
		String all = "There were " + Threads.total + " results" + "\n";
		String notfound = "There was: " + Threads.fail + "not founds" + "\n";
		String procent = "There was " + ((float)Threads.fail /(float) Threads.total)*100 + "% of misses" + "\n";
		return threads + size + all + notfound + procent;
	}
}
