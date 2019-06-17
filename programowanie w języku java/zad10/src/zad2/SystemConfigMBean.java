package zad2;

public interface SystemConfigMBean {

    public void setThreadCount(int numberOfThreads);
    public int getThreadCount();

    public void setMapSize(int size);
    public int getMapSize();

    public void setSeedRange(int range);
    public int getSeedRange();

    public String displayInfo();
}