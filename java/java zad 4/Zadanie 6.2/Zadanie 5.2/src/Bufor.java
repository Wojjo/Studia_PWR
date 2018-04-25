/* 
 *  Klasa Bufor
 *
 *  Przemys³aw Wojcinowicz 225943
 *  Wersja: 4 koñcowa
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.JTextArea;

class Bufor {
	 JTextArea txt;
     Animacja wiz;
	 BlockingQueue<Integer> content;
     int konsument;
     int producent;
     int size;
    

    public Bufor(JTextArea tx, Animacja visio, int leng, int x, int y) 
    {
    	
    	this.txt = tx;
        this.wiz = visio;
        this.size = leng;
        
        this.content = new ArrayBlockingQueue<Integer>(leng, true);
    	this.wiz.size(leng);
    	
    	this.konsument = x;
        this.producent = y;
       
    }

    public synchronized void synch(int wh) {
        this.txt.append("Konsument  #  " + wh + ": Chce cos skonsumowac.\n");
        this.txt.setCaretPosition(this.txt.getDocument().getLength());
        while (this.content.isEmpty()) 
        	
        {
        	
            this.txt.append("Konsument  #  " + wh + ": Bufor pusty, czekam.\n");
            this.txt.setCaretPosition(this.txt.getDocument().getLength());
            this.wiz.state(this.content.toArray(new Integer[0]), this.konsument, this.producent, true, wh, 10);
            this.wiz.repaint();
            try 
            {
                Thread.sleep(50);
            }
            
            catch (InterruptedException b) 
            {
                
            }
            
            try
            {
                this.wait();
            }
            
            catch (InterruptedException bb) 
            {
            	
            } 
            
        }
        
        this.wiz.state(this.content.toArray(new Integer[0]), this.konsument, this.producent, true, wh, 9);
        this.wiz.repaint();
        try 
        
        {
            Thread.sleep(150);
            char t = (char)this.content.take().intValue();
            this.txt.append("Konsument #" + wh + ": Zabieram " + t + " \n");
            this.txt.setCaretPosition(this.txt.getDocument().getLength());
        }
        
        catch (InterruptedException c) 
        {
            
        }
        
        this.wiz.state(this.content.toArray(new Integer[0]), this.konsument, this.producent, true, wh, 8);
        this.wiz.repaint();
        this.txt.append("Konsument #  " + wh + ": Konsumuje \n");
        this.txt.setCaretPosition(this.txt.getDocument().getLength());
        this.notifyAll();
    }

    public synchronized void put(int who, char txt) 
    {
        this.txt.append("Producent #  " + who + ": Chce oddac " + txt + "\n");
        this.txt.setCaretPosition(this.txt.getDocument().getLength());
        while (this.content.size() == this.size) {
            this.txt.append("Producent #  " + who + ": Bufor jest zajety, czekam \n");
            this.txt.setCaretPosition(this.txt.getDocument().getLength());
            this.wiz.state(this.content.toArray(new Integer[0]), this.konsument, this.producent, false, who, 10);
            this.wiz.repaint();
            try 
            {
                Thread.sleep(50);
                this.wait();
            }
            catch (InterruptedException c) {}
        }
        try {
            this.content.put(Integer.valueOf(txt));
        }
        catch (InterruptedException a) 
        {
            
        }
        this.wiz.state(this.content.toArray(new Integer[0]), this.konsument, this.producent, false, who, 9);
        this.wiz.repaint();
        try {
            Thread.sleep(150);
        }
        catch (InterruptedException aa) 
        {
          
        }
        this.txt.append("Producent #  " + who + ": Oddalem " + txt + "\n");
        this.txt.setCaretPosition(this.txt.getDocument().getLength());
        this.wiz.state(this.content.toArray(new Integer[0]), this.konsument, this.producent, false, who, 8);
        this.wiz.repaint();
        this.txt.append("Producent #  " + who + ": Produkuje \n");
        this.txt.setCaretPosition(this.txt.getDocument().getLength());
        this.notifyAll();
    }
}

