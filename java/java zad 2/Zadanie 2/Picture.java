
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JPanel;
import java.util.*;
/*
 *  Klasa Picture
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */

class Picture extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{

	private static final long serialVersionUID = 1L;
/**Metoda wyswietla dane autora i date powstania programu*/
     
	Vector<Figure> figures = new Vector<Figure>();
	   public String autor()
	{ 
	   	String s = "Przemyslaw Wojcinowicz \n"
	   			+ "225943"
	   			+"8.11.2016";
	   	return s;
	}

	/**Prywatne zmienne pomocnicze przechowujace obecna pozycje myszki */
		private int mouseX, mouseY;

		/** Metoda rysujaca komponent - figure */ 
	    public void paintComponent(Graphics g)
	{   
	  	super.paintComponent(g);
	    for (Figure f : figures) f.draw(g);
	}

    /**Metoda umozliwiajaca dodanie figury*/
    void addFigure(Figure fig)
    { for (Figure f : figures)
    { 
    	f.unselect();
    }
      fig.select();
      figures.add(fig);
  	repaint();
    }
    
    
    /** Metoda ustawia kolor zielony */

	public void setColorGreen()
	{
		for (Figure f : figures)
    	{ 
    		if (f.isSelected()) f.gre(); 
    	}
    	repaint();
	}
	/** Metoda ustawia kolor niebieski */
	public void setColorBlue()
	{
		for (Figure f : figures)
    	{ 
    		if (f.isSelected()) f.blu(); 
    	}
    	repaint();
	}
	/** Metoda ustawia kolor zolty */
	public void setColorYellow()
	{
		for (Figure f : figures)
    	{ 
    		if (f.isSelected()) f.yel(); 
    	}
    	repaint();
	}
	/** Metoda ustawia kolor czerwony */
	public void setColorRed()
	{
		for (Figure f : figures)
    	{ 
    		if (f.isSelected()) f.red(); 
    	}
    	repaint();
	}
    

    
    /** Metoda umozliwiajaca dodanie punktu */
    public void addPoint()
    { 
    	
    	float x, y;
    	Random random = new Random();
    	x = random.nextFloat()*getWidth();
    	y = random.nextFloat()*getHeight();
    	addFigure(new Point(x, y));
    }
    
    /** Metoda umozliwiajaca dodanie kwadratu */ 
    public void addjSquare()
    { 
    	float x, y, szer;
    	Random random = new Random();
    	x = random.nextFloat()*getWidth();
    	y = random.nextFloat()*getHeight();
    	szer = random.nextFloat()*getWidth();
    	addFigure(new Square(x,y,szer));
    }
    
    /** Metoda umozliwiajaca dodanie trojkata */    
    public void addTriangle()
    { 
    	float x1, y1, x2, y2, x3, y3;
    	Random random = new Random();
    	x1 = random.nextFloat()*getWidth();
    	y1 = random.nextFloat()*getHeight();
    	x2 = random.nextFloat()*getWidth();
    	y2 = random.nextFloat()*getHeight();
    	x3 = random.nextFloat()*getWidth();
    	y3 = random.nextFloat()*getHeight();
    	addFigure(new Triangle(new Point(x1,y1),new Point(x2,y2), new Point(x3,y3)));
    }
   
     
    /** Metoda umozliwiajaca dodanie wypelnionego kwadratu */ 
    public void addjSquareFilled()
    { 
    	
    	float x, y, szer;
    	Random random = new Random();
    	x = random.nextFloat()*getWidth();
    	y = random.nextFloat()*getHeight();
    	szer = random.nextFloat()*getWidth();
    	addFigure(new SquareFilled(x,y,szer));
    }
    
    /** Metoda umozliwiajaca dodanie prostokata */ 
    public void addRectangle()
    { 
    	float x, y, szer, wys;
    	Random random = new Random();
    	x = random.nextFloat()*getWidth();
    	y = random.nextFloat()*getHeight();
    	szer = random.nextFloat()*getWidth();
    	wys = random.nextFloat()*getHeight();
    	addFigure(new Rectangle(x,y,szer,wys));
    }
    
    /** Metoda umozliwiajaca dodanie wypelnionego prostokata */ 
    public void addRectangleFilled()
    { 
    	float x, y, szer, wys;
    	Random random = new Random();
    	x = random.nextFloat()*getWidth();
    	y = random.nextFloat()*getHeight();
    	szer = random.nextFloat()*getWidth();
    	wys = random.nextFloat()*getHeight();
    	addFigure(new RectangleFilled(x,y,szer,wys));
    }
    
    /** Metoda umozliwiajaca dodanie kola */ 
    public void addCircle()
    { 
    	float x, y, prom;
    	Random random = new Random();
    	x = random.nextFloat()*getWidth();
    	y = random.nextFloat()*getHeight();
    	prom = random.nextFloat()*getWidth();
    	addFigure(new Circle(x,y,prom));
    }
    
    /** Metoda umozliwiajaca dodanie wypelnionego kola */ 
    public void addCircleFilled()
    { 
    	float x, y, prom;
    	Random random = new Random();
    	x = random.nextFloat()*getWidth();
    	y = random.nextFloat()*getHeight();
    	prom = random.nextFloat()*getWidth();
    	addFigure(new CircleFilled(x,y,prom));
    }

    
    

    /** Metoda przesuwajaca figury */
    void moveAllFigures(float dx, float dy){
    	for (Figure f : figures)
    	{
    		if (f.isSelected()) f.moveAllFigures(dx,dy);
    	}
        repaint();
    }
    /** Metoda skalujaca figury */
    void scaleAllFigures(float s){
    	for (Figure f : figures)
        	{ if (f.isSelected()) f.scaleAllFigures(s);
        	}
          repaint();
    }
    
    public String drawAll()
    { 
    	String s = "Rysunek: \n\n";
    	for (Figure f : figures)
      	{ 
      		s += f.toString() + "\n"; 
      	}
      	return s;
    }
    
    

    /** 
     * Metoda zwracajaca String z opisem dzialania poszczegolnych klawiszy funkcyjnych
     * @return string s zwarca opisane klawisze
     */ 
    public String functionKeys()
    { 
    	String s = "Klawisze funkcyjne: \n\n";
    	s += "Program obsluguje nastepujace klawisze: \n";
    	s += "Strzalka 'lewo' - przesuniecie figury w lewo \n";
    	s += "Klawisz SHIFT + 'lewo' - szybkie przesuniecie figury w lewo \n";
    	s += "Strzalka 'prawo' - przesuniecie figury w prawo \n";
    	s += "Klawisz SHIFT + 'prawo' - szybkie przesuniecie figury w prawo \n";
    	s += "Strzalka 'gora' - przesuniecie figury w gore \n";
    	s += "Klawisz SHIFT + 'gora' - szybkie przesuniecie figury w gore \n";
    	s += "Strzalka 'dol' - przesuniecie figury w dol \n";
    	s += "Klawisz SHIFT + 'dol' - szybkie przesuniecie figury w dol \n";
    	s += "Klawisz '=' - powiekszenie zaznaczonej figury \n";
    	s += "Klawisz '-' - pomniejszenie zaznaczonej figury \n";
    	s += "Klawisz 'delete' - usuniecie zaznaczonej figury \n";
    	s += "Klawisz 'z' - zmiana koloru figury na zielony\n";
    	s += "Klawisz 'n' - zmiana koloru figury na niebieski\n";
    	s += "Klawisz 'y' - zmiana koloru figury na zolty\n";
    	s += "Klawisz 'c' - zmiana koloru figury na czerwony\n\n";
    	s += "Klawisz 'p' - dodanie punktu \n";
    	s += "Klawisz 't' - dodanie trojkata \n";
    	s += "Klawisz 'k' - dodanie kwadratu \n";
    	s += "Klawisz 'K' - dodanie wypelnionego kwadratu \n";
    	s += "Klawisz 'r' - dodanie prostokata \n";
    	s += "Klawisz 'R' - dodanie wypelnionego prostokata \n";
    	s += "Klawisz 's' - dodanie kola \n";
    	s += "Klawisz 'S' - dodanie wypelnionego kola \n";
    	s += "Klawisze '0...9' - zaznaczenie figury o danym numerze \n";
    	s += "Klawisz ALT + '0...9' - zmiana zaznaczenia figury o danym numerze \n";
    	
    	s += "Obsluga myszki: \n\n";
    	s += "Klikniecie na figurze - zaznaczenie jej \n";
    	s += "Klawisz ALT + klikniecie na figurze - zmiana zaznaczenia \n";
    	s += "Klikniecie na figurze i przeciagniecie - zmiana polozenia figury";
    	return s;
    }
    
    


    public String toString(){
        String str = "Rysunek { ";
        
        for(Figure f : figures)
        {
            str+=f.toString() +"\n         ";
        str+="}";
        }
        return str;
    }

	

    public void keyPressed (KeyEvent evt)
    {  int dist;
       if (evt.isShiftDown()) 
    	   dist = 10;
           else dist = 1;
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_Z : setColorGreen(); break;
     	case KeyEvent.VK_N : setColorBlue(); break;
     	case KeyEvent.VK_Y : setColorYellow(); break;
     	case KeyEvent.VK_C : setColorRed(); break;
     	case KeyEvent.VK_LEFT : moveAllFigures(-dist, 0); break;
        case KeyEvent.VK_RIGHT: moveAllFigures( dist, 0); break;
        case KeyEvent.VK_UP   : moveAllFigures(0, -dist); break;
        case KeyEvent.VK_DOWN : moveAllFigures(0,  dist); break;
        case KeyEvent.VK_EQUALS : scaleAllFigures(1.1f); break;
        case KeyEvent.VK_MINUS : scaleAllFigures(0.9f); break;
		case KeyEvent.VK_DELETE:
			Iterator<Figure> i = figures.iterator();
			while (i.hasNext()) {
				Figure f = i.next();
				if (f.isSelected()) {
					i.remove();
				}
			}
			repaint();
			break;
		}
    }

   public void keyReleased (KeyEvent evt)
   {  }

 
   public void keyTyped (KeyEvent evt)
   { 
   	char znak = evt.getKeyChar(); 
   	switch(znak)
   	{ 
   		case 'p': addPoint(); break;
   		case 't': addTriangle(); break;
   		case 'k': addjSquare(); break;
   		case 'K': addjSquareFilled(); break;
   		case 'r': addRectangle(); break;
   		case 'R': addRectangleFilled(); break;
   		case 's': addCircle(); break;
   		case 'S': addCircleFilled(); break;
     }
   
     int nr = (int)znak-48; 
     if (nr >= 0 && nr < figures.size())
     { 
     		if (evt.isAltDown()==false) for(Figure f : figures ) f.unselect(); 
     		figures.get(nr).converselySelect(); 
     		repaint();
     } 
   }  



   public void mouseClicked(MouseEvent e)
   { int px = e.getX();
     int py = e.getY();
     for (Figure f : figures)
       { if (e.isAltDown()==false) f.unselect();
         if (f.isInside(px,py)) f.select( !f.isSelected() );
       }
     repaint();
   }


   /** Metoda umozliwiajaca najechania na figure */ 
   public void mouseEntered(MouseEvent e) 
   { }

   /** Metoda umozliwiajaca opuszczenia obszaru figury */ 
   public void mouseExited(MouseEvent e) 
   { }

   /** Metoda umozliwiajaca wcisniecia przyciskow myszki */ 
   public void mousePressed(MouseEvent e)  
   { 
   
   	mouseX = e.getX();
   	mouseY = e.getY();
   }

   /** Metoda umozliwiajaca zwolnienia przyciskow myszki*/ 
   public void mouseReleased(MouseEvent e) 
   { }
   
   /** Metoda umozliwiajaca przesuwania figury myszka */ 
   public void mouseDragged(MouseEvent e) 
   { 
   	
   	int newMouseX = e.getX();
       int newMouseY = e.getY();
		float x = 0, y = 0;
		
       
       x += newMouseX - mouseX;
       y += newMouseY - mouseY;

      
       moveAllFigures(x,y);
       
       
       mouseX = newMouseX;
       mouseY = newMouseY; 
       repaint();
   }
   	
   /** Metoda umozliwiajaca przesuwania myszki */ 
   public void mouseMoved(MouseEvent e)  
   { }
}
