import java.awt.Graphics;
/*
 *  Klasa umozliwia "narysowanie" trojkata
 *
 *  Autor: Przemyslaw Wojcinowicz
 *   Data: 8.11.2016
 */


class Triangle extends Figure
{   
	private Point point1, point2, point3;
    
    /** Kontruktor */
    public Triangle(Point p1, Point p2, Point p3)
    {    
    	point1=p1; 
    	point2=p2; 
    	point3=p3;
    }
    
    /** Metoda pozwalajaca przesuwac trojkat */
    public void moveAllFigures(float dx, float dy)
    { 
    point1.moveAllFigures(dx,dy);
    point2.moveAllFigures(dx,dy);
    point3.moveAllFigures(dx,dy);
    }
    
    /** Metoda oblicza pole trojkata */
    public float field()
    {   
    	float a = (float)Math.sqrt( (point1.x-point2.x)*(point1.x-point2.x)+
                                    (point1.y-point2.y)*(point1.y-point2.y));
    	float b = (float)Math.sqrt( (point2.x-point3.x)*(point2.x-point3.x)+
                                    (point2.y-point3.y)*(point2.y-point3.y));
        float c = (float)Math.sqrt( (point1.x-point3.x)*(point1.x-point3.x)+
                                    (point1.y-point3.y)*(point1.y-point3.y));
        float p=(a+b+c)/2;

        return (float)Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    
    /** Metoda oblicza obwod trojkata */
    public float circuit()
    {   
    	float a = (float)Math.sqrt( (point1.x-point2.x)*(point1.x-point2.x)+
                                    (point1.y-point2.y)*(point1.y-point2.y));
        float b = (float)Math.sqrt( (point2.x-point3.x)*(point2.x-point3.x)+
                                    (point2.y-point3.y)*(point2.y-point3.y));
        float c = (float)Math.sqrt( (point1.x-point3.x)*(point1.x-point3.x)+
                                    (point1.y-point3.y)*(point1.y-point3.y));
        return a+b+c;
    }
    
    
    /** Metoda sprawdza czy kliknelismy wewnatrz trojkata */
    public boolean isInside(float px, float py)
    { 
    	float d1, d2, d3;
    	d1 = px*(point1.y-point2.y) + py*(point2.x-point1.x) + (point1.x*point2.y-point1.y*point2.x);
    	d2 = px*(point2.y-point3.y) + py*(point3.x-point2.x) + (point2.x*point3.y-point2.y*point3.x);
    	d3 = px*(point3.y-point1.y) + py*(point1.x-point3.x) + (point3.x*point1.y-point3.y*point1.x);
    	return ((d1<=0)&&(d2<=0)&&(d3<=0)) || ((d1>=0)&&(d2>=0)&&(d3>=0));
    }
        
    /** Metoda skalujaca - pozwala powiekszac lub pomniejszac trojkat */
    public void scaleAllFigures(float s)
    {   
    	Point sr1 = new Point((point1.x+point2.x+point3.x)/3,
                              (point1.y+point2.y+point3.y)/3);
    	point1.x*=s; point1.y*=s;
    	point2.x*=s; point2.y*=s;
    	point3.x*=s; point3.y*=s;
        Point sr2 = new Point((point1.x+point2.x+point3.x)/3,
                              (point1.y+point2.y+point3.y)/3);
        float dx=sr1.x-sr2.x;
        float dy=sr1.y-sr2.y;
        point1.moveAllFigures(dx,dy);
        point2.moveAllFigures(dx,dy);
        point3.moveAllFigures(dx,dy);
    }
    
    /** Metoda zwracajaca String z danymi opisujacymi figure - trojkat */
    public String toString()
    { 
    	return "Trojkat " + point1.toStringXY() + point2.toStringXY() + point3.toStringXY() + " " + data();
    }
    
    /** Metoda rysujaca trojkat */
    public void draw(Graphics g)
    {   
    	setColor(g);
        g.drawLine((int)point1.x, (int)point1.y, (int)point2.x, (int)point2.y);
        g.drawLine((int)point2.x, (int)point2.y, (int)point3.x, (int)point3.y);
        g.drawLine((int)point3.x, (int)point3.y, (int)point1.x, (int)point1.y);
    }


}