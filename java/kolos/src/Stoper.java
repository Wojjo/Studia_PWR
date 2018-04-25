
public class Stoper 
{
	private long startTime = 0;
	private boolean running = true;
	
	public void start()
	{
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}
	
	public void reset()
	{
		startTime = 0;
		start();
	}
	
	public long timeSec()
	{
		long sec; 
		sec = ((System.currentTimeMillis() - startTime)/1000);
		return sec;
	}
	
	public String toString()
	{
		return timeSec() + " s";
	}

}
