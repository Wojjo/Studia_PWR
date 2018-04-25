import java.awt.Color;
import java.awt.Graphics;

/*
 *  Klasa Figure
 *  umozliwia skalowanie, zaznaczanie, przesuwanie, zmiana kolorow figur
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */

abstract class Figure{

		private boolean selected = false;
		private boolean green = false;
		private boolean blue = false;
		private boolean yellow = false;
		private boolean red = false;
		
		/** Metoda okreslajaca czy figura jest zaznaczona  */
		public boolean isSelected()
		{ 
			return selected; 
		}
		   
	    /** Metoda powodujaca zaznaczenie lub odznaczenie figury */
	    public void select(boolean z)
	    { 
	    	selected = z; 
	    }
	    
	    /** Metoda odznaczajaca figure */
	    public void unselect()
	    { 
	    	selected = false; 
	    }
	    
	    /** Metoda zaznaczajaca odwrotnie */
	    public void converselySelect()
	    { 
	    	selected = !selected; 
	    }
	    
	    
	    /** Metoda zaznaczajaca figure */
	    public void select()
	    { 
	    	selected = true; 
	    }
	    
	    /** Metoda zaznaczajaca, kolor zielony */
	    public void gre()
	    { 
	    	green = true; 
	    }
	    
	    /** Metoda zaznaczajaca, kolor niebieski */
	    public void blu()
	    { 
	    	blue = true; 
	    }
	    
	    /** Metoda zaznaczajaca, kolor zolty */
	    public void yel()
	    { 
	    	yellow = true; 
	    }
	    
	    /** Metoda zaznaczajaca, kolor czerwony */
	    public void red()
	    { 
	    	red = true; 
	    }
	 
	    /** 
	     * Metoda okreslajaca czy miejsce, w ktore kliknelismy jest w wewnatrz figury 
	     * @param zmienne px, py sa to wpolrzedne punktu klikniecia
	     * @return informacja czy punkt klikniecia znajduje sie wewnatrz figury
	     */
	    public abstract boolean isInside(float px, float py);
	    
	    /** Metoda okreslajaca czy miejsce, w ktore kliknelismy jest w wewnatrz figury */
	    public boolean isInside(int px, int py)
	    { 
	    	return isInside((float)px, (float)py); 
	    }
	   
	    /** 
	     * Metoda oblicza pole
	     */
	    public abstract float field();
	    
	    /** 
	     * Metoda oblicza obwod 
	     */
	    public abstract float circuit(); 
	    
	    /** 
	     * Metoda przesuwajaca figure 
	     * @param zmienne dx, dy sa to wspolrzedne o ktore ma zostac przesunieta figura
	     */
	    public abstract void moveAllFigures(float dx, float dy);  
	    
	    /** 
	     * Metoda skalujaca figure 
	     * @param float s - wspolczynnik skalowania
	     */
	    public abstract void scaleAllFigures(float s);
	    
	    /** 
	     * Metoda z danymi figury
	     */
	    protected String data()
	    { 
	    	String s = "  Pole: " + field() + "  Obwod: " + circuit();
	      	if (isSelected()) s = s + " ZAZNACZONA"; 
	      	return s;
	    }
	
	    
	    /** 
	     * Metoda ustawiajaca kolor figury 
	     */
	    protected void setColor(Graphics g)    
	    { 
	        Color figure = new Color(98, 98, 98);
	        Color figuraSelected = new Color(192, 41, 38);
	    	Color figureGreen = new Color(57, 181, 74);
	    	Color figureBlue = new Color (57, 103, 181);
	    	Color figureYellow = new Color (255, 242, 0);
	    	Color figureRed = new Color (236, 3, 3);
	    	if (selected) g.setColor(figuraSelected); 
	    	else g.setColor(figure);
	    	if (green) g.setColor(figureGreen);
	    	if (green && selected) g.setColor(figuraSelected);
	    	if (blue) g.setColor(figureBlue);
	    	if (blue && selected) g.setColor(figuraSelected);
	    	if (yellow) g.setColor(figureYellow);
	    	if (yellow && selected) g.setColor(figuraSelected);
	    	if (red) g.setColor(figureRed);
	    	if (red && selected) g.setColor(figuraSelected);  	 
	    }

	    /** 
	     * Metoda rysujaca figure 
	     */
	    public abstract void draw(Graphics s);
	     
	  
}