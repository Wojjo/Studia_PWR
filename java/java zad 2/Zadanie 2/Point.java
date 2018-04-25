import java.awt.Graphics;

/*
 *  Klasa umozliwia "narysowanie" punktu
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */

class Point extends Figure
{   
	protected float x, y;
    
    /** Konstruktor  */
    public Point(float px, float py)
    { 
    	x=px; 
    	y=py; 
    }
    
   
    public boolean isInside(float px, float py)
    { 
      	return (Math.sqrt((x-px)*(x-px) + (y-py)*(y-py))<=6);
    } 
    
    /** Metoda obliczajaca pole */
    public float field()
    { 
    	return 0; 
    }
    
    /** Metoda obliczajaca obwod */
    public float circuit()
    { 
    	return 0; 
    } 
    
    /** Metoda przesuwa punkt o dane wartosci */
    public void moveAllFigures(float dx, float dy)
    { 
    	x+=dx; 
    	y+=dy; 
    }
    
    /**  
  	 * Deklaracja funkcji skalujacej figury, w tym przypadku pusta 
  	 * (Nie mozna powiekszyc punktu, bo powstanie kolo)
     */
    public void scaleAllFigures(float s)
    {  }
    
    /** Metoda zwracajaca (String) dane ze wspolrzednymi punktow x, y  */
    protected String toStringXY()
    { 
    	return "(" + x + " , " + y + ")"; 
    }
    
    /** Metoda zwracajaca (String) dane opisujacymi punkt */
    public String toString()
    { 
    	return "Punkt " + toStringXY() + data();   
    }
    
    /** Metoda rysujaca punkt */
    public void draw(Graphics g)
    { 
    	//ustawiamy kolor - domyslny szary, w momencie zaznaczenia - bordowy
    	setColor(g);
    	//rysujemy punkt
    	g.fillOval((int)(x-3),(int)(y-3),6,6); 
    }

	

	
   
}
