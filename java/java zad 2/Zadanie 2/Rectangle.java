import java.awt.Graphics;
/*
 *  Klasa umozliwia "narysowanie" prostokata
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */



/** Klasa reprezentujaca prostokat - rozszerzenie klasy Point */
class Rectangle extends Point
{   
	//zmienne pomocznicze przechowujace szerokosc oraz wysokosc prostokata (a i b)
	protected float width, height;
    
    /** Konstruktor  */
    public Rectangle(float px, float py, float wid, float heig)
    { 
    	super(px,py);
    	width = wid; 
    	height = heig;
    }
    
    /** Metoda sprawdza czy kliknelismy wewnatrz prostokata */
    public boolean isInside(float px, float py)
    { 
      	 return (x<=px && px <= x+width && y <=py && py <= y+height) ;
    } 
    
    /** Metoda oblicza pole prostokata */
    public float field()
    { 
    	return (width*height); 
    }
    
    /** Metoda oblicza obwod prostokata */
    public float circuit()
    { 
    	return (2*width + 2*height); 
    }
    
    /** Metoda umozliwia przesuniecie figury - prostokata */
    public void moveAllFigures(float dx, float dy)
    { 
    	super.moveAllFigures(dx, dy);
    }
    
    /** Metoda skalujaca figure  */
    public void scaleAllFigures(float s)
    { 
    	width = width*s;
    	height = height*s;
    }
    
    /** Metoda zwracajaca (String) dane figury - prostokat */
    public String toString()
    { 
    	return "Prostokat " + toStringXY() + " szerokosc: " + width + " wysokosc: " + height + data();   
    }
    
    /** Metoda rysujaca prostokat */
    public void draw(Graphics g)
    { 
    	//ustawienie kolorow
    	setColor(g);
      	//rysowanie poszczegolnych bokow
      	g.drawLine((int)x, (int)y, (int)(x+width), (int)y);
      	g.drawLine((int)(x+width), (int)y, (int)(x+width), (int)(y+height));
      	g.drawLine((int)(x+width), (int)(y+height), (int)x, (int)(y+height));
      	g.drawLine((int)x, (int)(y+height), (int)x, (int)y);
    }
}
