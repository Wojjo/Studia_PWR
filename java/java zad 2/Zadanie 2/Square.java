import java.awt.Graphics;

/*
 *  Klasa umozliwia "narysowanie" kwadratu
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */

class Square extends Point
{   
	protected float width;
    
    /** Kontruktor  */
    public Square(float px, float py, float wid)
    { 
    	super(px,py);
    	width = wid; 
    }
    
    /** Metoda umozliwiajaca przesuniecie kwadrat */
    public void moveAllFigures(float dx, float dy)
    { 
    	super.moveAllFigures(dx, dy);
    }
    
    /** Metoda skalujaca figure - kwadrat */
    public void scaleAllFigures(float w)
    { 
    	width = width*w;
    }
    
    /** Sprawdza czy kliknelismy wewnatrz figury - kwadratu*/
    public boolean isInside(float px, float py)
    { 
      	 return (x < px && px < x+width && y < py && py < y+width) ;
    } 
    
    /** Metoda oblicza pole kwadratu */
    public float field()
    { 
    	return (width*width); 
    }
    
    /** Metoda oblicza obwod kwadratu */
    public float circuit()
    { 
    	return (4*width); 
    }
    
    /** Metoda zwracajaca (String) dane opisujace figure - kwadrat */
    public String toString()
    { 
    	return "Kwadrat " + toStringXY() + " szerokosc: " + width + data();   
    }
    
    /** Metoda rysujaca figure - kwadrat
     * ustawia kolor  */
    public void draw(Graphics g)
    { 
    	//ustawienie kolorow
    	setColor(g);
      	//rysowanie poszczegolnych bokow
      	g.drawLine((int)x, (int)y, (int)(x+width), (int)y);
      	g.drawLine((int)(x+width), (int)y, (int)(x+width), (int)(y+width));
      	g.drawLine((int)(x+width), (int)(y+width), (int)x, (int)(y+width));
      	g.drawLine((int)x, (int)(y+width), (int)x, (int)y);
    }  
}

