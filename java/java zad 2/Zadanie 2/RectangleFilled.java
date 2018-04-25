import java.awt.Graphics;
/*
 *  Klasa umozliwia "narysowanie" prostokata wypelnionego
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */

/** Klasa reprezentujaca wypelniony prostokat, ktory jest rozszerzeniem klasy Rectangle */
class RectangleFilled extends Rectangle
{   
	/** Konstruktor  */
    public RectangleFilled(float px, float py, float wid, float heig)
    { 
    	super(px,py, wid, heig);
    }
    
    /** Metoda zwracajaca (String) dane figury - wypelniony prostokat */
    @Override public String toString()
    { 
    	return "Prostokat wypelniony " + toStringXY() + " szerokosc: " + width + " wysokosc: " + height + data();   
    }
    
    /** Metoda rysujaca wypelniony prostokat
     * ustawia kolor */
    @Override public void draw(Graphics g)
    { 
    	setColor(g);
    	g.fillRect((int)x, (int)y, (int)width, (int)height);
    }   
}
