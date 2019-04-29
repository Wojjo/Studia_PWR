
import java.awt.*;
import java.beans.*;
import java.util.StringTokenizer;


public class TitlePositionEditor extends PropertyEditorSupport {
	public Component getCustomEditor() {
		return new TitlePositionEditorPanel(this);
	}

	public boolean supportsCustomEditor() {
		return true;
	}

	public String getJavaInitializationString() {
		Integer[] titlePosition = (Integer[]) getValue();

		StringBuffer s = new StringBuffer();
		s.append("new Integer[]{");
		for (int i = 0; i < titlePosition.length - 1; i++) {
			s.append(titlePosition[i]);
			s.append(",");
		}
		s.append(titlePosition[titlePosition.length - 1]);
		s.append("}");
		return s.toString();
	}

	public boolean isPaintable() {
		return false;
	}

	public String getAsText() {
		Integer[] titlePosition = (Integer[]) getValue();
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < titlePosition.length - 1; i++) {
			s.append(titlePosition[i]);
			s.append(" ");
		}
		s.append(titlePosition[titlePosition.length - 1]);
		return s.toString();
	}

	public void setAsText(String s) {
		StringTokenizer tokenizer = new StringTokenizer(s);
		int i = 0;
		Integer[] titlePosition = new Integer[tokenizer.countTokens()];
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			try {
				System.out.println(token);
				titlePosition[i] = Integer.parseInt(token);
				i++;
			} catch (NumberFormatException exception) {
			}
		}
		setValue(titlePosition);

	}
	
}

