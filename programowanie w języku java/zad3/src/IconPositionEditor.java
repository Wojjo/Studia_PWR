
import java.awt.*;
import java.beans.*;
import java.util.StringTokenizer;


public class IconPositionEditor extends PropertyEditorSupport {
	public Component getCustomEditor() {
		return new IconPositionEditorPanel(this);
	}

	public boolean supportsCustomEditor() {
		return true;
	}

	public String getJavaInitializationString() {
		Integer[] iconPosition = (Integer[]) getValue();

		StringBuffer s = new StringBuffer();
		s.append("new Integer[]{");
		for (int i = 0; i < iconPosition.length - 1; i++) {
			s.append(iconPosition[i]);
			s.append(",");
		}
		s.append(iconPosition[iconPosition.length - 1]);
		s.append("}");
		return s.toString();
	}

	public boolean isPaintable() {
		return false;
	}

	public String getAsText() {
		Integer[] iconPosition = (Integer[]) getValue();
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < iconPosition.length - 1; i++) {
			s.append(iconPosition[i]);
			s.append(" ");
		}
		s.append(iconPosition[iconPosition.length - 1]);
		return s.toString();
	}

	public void setAsText(String s) {
		StringTokenizer tokenizer = new StringTokenizer(s);
		int i = 0;
		Integer[] iconPosition = new Integer[tokenizer.countTokens()];
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			try {
				System.out.println(token);
				iconPosition[i] = Integer.parseInt(token);
				i++;
			} catch (NumberFormatException exception) {
			}
		}
		setValue(iconPosition);

	}
	
}
