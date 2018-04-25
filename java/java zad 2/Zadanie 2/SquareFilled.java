import java.awt.Graphics;
/*
 *  Klasa umozliwia "narysowanie" kwadratu wypelnionego
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */



/** Klasa reprezentujaca wypelniony kwadrat, ktory jest rozszerzeniem klasy 'Square' */
class SquareFilled extends Square
{   
	/** Konstruktor  */
	public SquareFilled(float px, float py, float wid)
    { 
    	super(px,py, wid);
    }
  

	/** Metoda zwracajaca String z danymi opisujacymi figure - kwadrat wypelniony */
     public String toString()
    { 
    	return "Kwadrat wypelniony " + toStringXY() + " szerokosc: " + width + data();   
    }
    
     /** Metoda rysujaca wypelniony kwadrat
      * ustawia kolor */
     public void draw(Graphics g)
    { 
    	setColor(g);
    	g.fillRect((int)x, (int)y, (int)width, (int)width);
    }
}

