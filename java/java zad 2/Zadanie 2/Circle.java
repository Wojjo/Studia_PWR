/*
 *  Klasa umozliwia "narysowanie" kola
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */


import java.awt.Graphics;



class Circle extends Point
{   
	/**zmienna reprezentujaca promien */
	protected float radius;
    
    /** Konstruktor  */
    public Circle(float px, float py, float rad)
    { 
    	super(px,py);
    	radius = rad; 
    }
    /** Sprawdza czy kliknelismy wewnatrz figury*/
    public boolean isInside(float px, float py)
    { 
    	 return (x<px && px < x+radius && y <py && py < y+radius);
    } 
    
    /** Metoda oblicza pole kola */
    public float field()
    { 
    	return ( (float) (Math.PI*radius*radius )); 
    }
    
    /** Ta metoda oblicza obwod kola */
    public float circuit()
    { 
    	return ((float)(2*Math.PI*radius)); 
    }
    
    /** Metoda pozwala przesunac figure */
    public void moveAllFigures(float dx, float dy)
    { 
    	super.moveAllFigures(dx, dy);
    }
    
    /** Metoda skalujaca */
    public void scaleAllFigures(float sc)
    { 
    	radius = radius*sc;
    }
    
    /** Metoda zwracajaca String z danymi opisujacymi figure */
    public String toString()
    { 
    	return "Kolo " + toStringXY() + " promien: " + radius + data();   
    }
    
    /** Metoda rysujaca figure - kolo
     * ustawia kolor  */
    public void draw(Graphics g)
    { 
    	setColor(g);
      	g.drawOval((int)x, (int)y, (int)(radius), (int)(radius));
    }   
}