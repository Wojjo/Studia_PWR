
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class CBeanStart extends JPanel
{
	
	private static final int XPREFSIZE = 300;
	private static final int YPREFSIZE = 300;
	private String title = "Bean Company";
	private Integer[] titlePosition = { 80, 80 };
	private Integer[] iconPosition = { 50, 25, 200, 100 };
	private boolean inverse;
	private Color color = Color.red;

	public CBeanStart() {
	}

	private static final long serialVersionUID = 1L;

	// metoda rysuje ikone i tytul
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(inverse ? Color.black : color);
		Ellipse2D elipse = new Ellipse2D.Double(iconPosition[0], iconPosition[1], iconPosition[2], iconPosition[3]);
		g2.fill(elipse);
		g2.draw(elipse);
		
		g2.setColor(Color.WHITE);
		Font titleFont = new Font("SansSerif", Font.BOLD, 20);
		g2.setFont(titleFont);
		g2.drawString(title, titlePosition[0], titlePosition[1]);

	}
	
	/**
	 * Metoda setTitle - ustawia tytu³ (nazwe firmy)
	 * @param t tytu³
	 */
	public void setTitle(String t) {
		title = t;
	}
	
	/**
	 * Metoda getTitle - zwraca tytu³ (nazwe firmy)
	 * @return tytu³
	 */

	public String getTitle() {
		return title;
	}


	/**
	 * Metoda setInverse.
	 * 
	 * @param b wartosc true - ikona ma czarny kolor
	 * 			wartosc false - ikona ma czerwony kolor
	 */

	public void setInverse(boolean b) {
		inverse = b;
	}
	
	/**
	 * Metoda isInverse.
	 * 
	 * @return  wartosc true - ikona ma czarny kolor
	 * 			wartosc false - ikona ma czerwony kolor
	 */

	public boolean isInverse() {
		return inverse;
	}

	// metoda ustawia pozycje ikony
	
	public void setIconPosition(Integer[] v) {
		iconPosition = new Integer[v.length];
		for(int i=0; i<v.length;i++)
			iconPosition[i]=new Integer(v[i]);
	}

	// metoda zwraca pozycje ikony
	public Integer[] getIconPosition() {
		return iconPosition;
	}
	
	// metoda ustawia dany parametr w pozycji ikony
	public void setIconPosition(int i, Integer value) {
		if (0 <= i && i < iconPosition.length)
			iconPosition[i] = value;
	}

	// metoda zwraca dany parametr pozycji ikony
	public Integer getIconPosition(int i) {
		if (0 <= i && i < iconPosition.length)
			return iconPosition[i];
		return 0;
	}

	// metoda ustawia pozycje tytulu
	public void setTitlePosition(Integer[] v) {
		titlePosition = new Integer[v.length];
		for(int i=0; i<v.length;i++)
			titlePosition[i]=new Integer(v[i]);
	}

	// metoda zwraca pozycje tytulu
	public Integer[] getTitlePosition() {
		return titlePosition;
	}

	// metoda ustawia dany parametr pozycji tytulu
	public void setTitlePosition(int i, Integer value) {
		if (0 <= i && i < titlePosition.length)
			titlePosition[i] = value;
	}

	// metoda zwraca dany parametr pozycji tytulu
	public Integer getTitlePosition(int i) {
		if (0 <= i && i < titlePosition.length)
			return titlePosition[i];
		return 0;
	}
	// metoda ustawia kolor ikony
	public void setIconColor(Color c) {
		color = c;
	}
	// metoda zwraca kolor ikony
	public Color getIconColor() {
		return color;
	}
	// metoda ustawia rozmiar okna
	public Dimension getPreferredSize() {
		return new Dimension(XPREFSIZE, YPREFSIZE);
	}

}
