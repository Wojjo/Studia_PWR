import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Figure {
	
	float x,y;
	int rad = 25;
	public Circle(float dx, float dy, float rad)
	{
		x = dx;
		y = dy;
	}

	@Override
	public void moveAllFigures(float dx, float dy) {
		x += dx;
		y += dy;
		
	}

	@Override
	public void draw(Graphics g) {
		
		Color figure = new Color(255,242,0);
		g.setColor(figure);
		g.fillOval((int)x, (int)y, (int)rad, (int)rad);
		
	}

}
