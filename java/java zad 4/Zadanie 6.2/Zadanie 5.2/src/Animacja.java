/* 
 *  Klasa Animacja
 *
 *  Przemys³aw Wojcinowicz 225943
 *  Wersja: 5 koñcowa
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Font;


class Animacja
extends JPanel {
	
     static final long serialVersionUID = 1L;
     int x;
     int y;
     int c;
     int size;
     Integer[] data;
     boolean bool;
    
     int[] xState = new int[8];
     int[] yState = new int[8];

   public  Animacja() 
    {
    	 
    }

    public void size(int s) {
        this.size = s;
    }


    public void drawStateWithColor(Graphics draw) {
      
    
        Graphics2D draft = (Graphics2D)draw;
        Stroke t = draft.getStroke();
        draft.setColor(Color.BLACK);
        draft.setColor(Color.lightGray);
        draft.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        draft.setStroke(new BasicStroke(1.0f));
        int sq = 20;
        int sm = 40;
        int z = 120;
        int z1 = 90;
        int z2 = 2;
        int vdr;
        int d;
   
        if (this.size > 0) {
        	
            draft.setColor(Color.ORANGE);
            String s = "";
            for (int i2 = this.size - 1; i2 >= 0; --i2) {
                s = i2 < this.data.length ? s + "|" + (char)this.data[i2].intValue() + "|" : s + "| |";
            }
            draft.setFont(new Font("Monospaced", 1, 20));
            draft.drawString(s, (this.getWidth() - this.size * sm) / z2, (this.getHeight() / z2) );
        }
        for (d = 0; d < this.y; ++d) {
        	vdr = this.xState[d] == 2 ? z : (this.xState[d] == 1 ? z1 : 0);
            draft.setColor(Color.CYAN);
            if (!this.bool && this.c == d) {
            	draft.setColor(Color.RED);
            }
            draft.fillOval(vdr, this.getHeight() / z2 - this.y * sq  + d * sm, sq, sq);
        }
        for (d = 0; d < this.x; ++d) {
        	vdr = this.yState[d] == 2 ? this.getWidth() - z - sq : (this.yState[d] == 1 ? this.getWidth() - z1 - sq : this.getWidth() - sq);
            draft.setColor(Color.YELLOW);
            if (this.bool && this.c == d) {
            	draft.setColor(Color.GREEN);
            }
            draft.fillOval(vdr, this.getHeight() / z2 - this.x * sq  + d * sm, sq, sq);
        }
        draft.setStroke(t);
    }
    

    public void state(Integer[] c, int lt, int ft, boolean r, int we, int hih) {
        this.data = c;
        this.x = lt;
        this.y = ft;
        this.bool = r;
        this.c = (we-1);
        if (this.bool) {
            this.xState[this.c] = hih;
        } else {
            this.yState[this.c] = hih;
        }
    }

    
    public void paintComponent(Graphics d) {
        super.paintComponent(d);
        this.drawStateWithColor(d);
    }
    
}

