package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.app;

import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;

public class HighlightTest {

	public static class HighlightEvaluator implements IDateEvaluator {

		private final List<Date> list = new ArrayList<>();
		private Color backgroundColor;

		public HighlightEvaluator(){
			backgroundColor = Color.black;
		}
		
		public void add(Date date) {
			list.add(date);
		}

		public void removeAllDates() {
			while (list.size() > 0)
				list.remove(0);
		}

		public boolean isSpecial(Date date) {
			return list.contains(date);
		}

		public Color getSpecialForegroundColor() {
			return Color.black;
		}

		public Color getSpecialBackroundColor() {
			return backgroundColor;
		}

		public String getSpecialTooltip() {
			return "Highlighted event.";
		}

		public boolean isInvalid(Date date) {
			return false;
		}

		public Color getInvalidForegroundColor() {
			return null;
		}

		public Color getInvalidBackroundColor() {
			return null;
		}

		public String getInvalidTooltip() {
			return null;
		}

		public void setBounds(int i, int j, int k, int l) {
			// TODO Auto-generated method stub
		}
		
		public void setBackgroundColor(Color c){
			backgroundColor = c;
		}
	}

	// a - od, b - do, m - miesiac
	public static void displayCalendar(int a, int b, int m) {
		JFrame f = new JFrame("Calendar");
		int i;
		HighlightEvaluator evaluator = new HighlightEvaluator();
		for (i = a; i <= b; i++) {
			evaluator.add(createDate(i, m));
		}
		JCalendar jc = new JCalendar();
		jc.getDayChooser().addDateEvaluator(evaluator);
		jc.setCalendar(jc.getCalendar());
		f.add(jc);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	private static Date createDate(int d, int m) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, m - 1);
		c.set(Calendar.DAY_OF_MONTH, d);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return (c.getTime());
	}

}