import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

public class Picture extends JPanel implements KeyListener, MouseListener, MouseMotionListener  {

	
	
	private static final long serialVersionUID = 1L;
	
	Vector<Figure> figures = new Vector<Figure>();
	
	private int x1,y1;
	
	public String autor()
	{
		String s;
		
		s = "Przemyslaw Wojcinowicz \n"
				+"225943";
		return s;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (Figure f: figures)
			f.draw(g);
		
		
	}
	
	public void addFigure(Figure f)
	{
		figures.add(f);
		repaint();
	}
	
	public void moveAllFigures(float dx, float dy)
	{
		for(Figure f : figures)
		{
			f.moveAllFigures(dx, dy);
		}
		repaint();
	}
	
	public void addCircle()
	{
		float x,y,prom;
		Random rand = new Random();
		x = rand.nextFloat()*getWidth();
		y = rand.nextFloat()*getHeight();
		prom = 25;
		addFigure(new Circle(x,y,prom));
	}
	
	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int newX = e.getX(), newY= e.getY();
		float x = 0, y=0;
		
		x += newX -x1;
		y += newY -y1;
		
		moveAllFigures(x,y);
		
		x1 = newX;
		y1 = newY;
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		addCircle();
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	figures.removeAllElements();
		repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int dist = 10;
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT : moveAllFigures(-dist, 0); break;
		case KeyEvent.VK_RIGHT : moveAllFigures(dist, 0); break;
		case KeyEvent.VK_DOWN : moveAllFigures(0, dist); break;
		case KeyEvent.VK_UP : moveAllFigures(0, -dist); break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
