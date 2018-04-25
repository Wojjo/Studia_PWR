import java.awt.Graphics;
/*
 *  Klasa umozliwia "narysowanie" kola wypelnionego
 *
 *  Autor: Przemyslaw Wojcinowicz
 *  Data: 8.11.2016
 */
/** Klasa reprezentujaca wypelnione kolo, ktory jest rozszerzeniem klasy Circle */
class CircleFilled extends Circle
{   

	
	/** Konstruktor  */
	public CircleFilled(float px, float py, float rad)
    { 
    	super(px,py,rad);
    }
    
    /** Metoda zwracajaca String z danymi opisujacymi figure - kolo wypelnione */
     public String toString()
    { 
    	return "Kolo wypelnione " + toStringXY() + " promien: " + radius + data();   
    }
    
    /** Metoda rysujaca wypelnione kolo
     * ustawia kolor */
     public void draw(Graphics g)
    { 
    	setColor(g);
      	g.fillOval((int)x, (int)y, (int)(radius), (int)(radius));
    }   
}