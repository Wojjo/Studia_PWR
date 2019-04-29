
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.event.*;

public class CBeanStartCustomizer extends JTabbedPane implements Customizer {

	private static final long serialVersionUID = 1L;
	private static final int XPREFSIZE = 200;
	private static final int YPREFSIZE = 120;
	private CBeanStart bean;
	private PropertyEditor colorEditor;
	private JButton setTitlePositionButton = new JButton("Set title position");
	private JButton setIconPositionButton = new JButton("Set icon position");
	private JButton getTitleButton = new JButton("Get info");
	private JButton getColorButton = new JButton("Get info");
	private JTextField titleField;
	private JTextArea titlePositionField;
	private JTextField iconPositionField;

	public CBeanStartCustomizer() {
		bean = new CBeanStart();
		JPanel colorPane = new JPanel();
		colorPane.setLayout(new BorderLayout());
		iconPositionField = new JTextField();
		iconPositionField.setText("Enter icon position");
		//
		colorEditor = PropertyEditorManager.findEditor(Color.class);
		colorEditor.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				setIconColor((Color) colorEditor.getValue());
			}
		});
		colorPane.add(iconPositionField, BorderLayout.NORTH);
		colorPane.add(getColorButton, BorderLayout.EAST);
		colorPane.add(setIconPositionButton, BorderLayout.WEST);

		setIconPositionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setDataIconPosition(iconPositionField.getText());
			}
		});

		getColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println(bean.getIconColor());
				Integer[] iconPosition = bean.getIconPosition();
				;
				for (int i = 0; i < iconPosition.length; i++)
					System.out.println(iconPosition[i]);
			}
		});
		colorPane.add(colorEditor.getCustomEditor(), BorderLayout.CENTER);
		JPanel titlePane = new JPanel();
		titlePane.setLayout(new BorderLayout());
		titleField = new JTextField();
		titlePositionField = new JTextArea();
		titleField.setText("Enter title");
		titlePositionField = new JTextArea();
		titlePositionField.setText("Enter title position");
		titlePane.add(getTitleButton, BorderLayout.EAST);
		titlePane.add(setTitlePositionButton, BorderLayout.WEST);
		titlePane.add(titlePositionField, BorderLayout.SOUTH);
		titlePane.add(titleField, BorderLayout.NORTH);
		//
		titleField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent evt) {
				setTitle(titleField.getText());
			}

			public void insertUpdate(DocumentEvent evt) {
				setTitle(titleField.getText());
			}

			public void removeUpdate(DocumentEvent evt) {
				setTitle(titleField.getText());
			}
		});

		setTitlePositionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setDataTitlePosition(titlePositionField.getText());

			}
		});

		getTitleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Integer[] titlePosition = bean.getTitlePosition();
				;
				System.out.println(bean.getTitle());
				for (int i = 0; i < titlePosition.length; i++)
					System.out.println(titlePosition[i]);

			}
		});

		addTab("Icon", colorPane);
		addTab("Title", titlePane);

		addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				validate();
			}
		});
	}

	// metoda pobiera dane oddzielone spacja z pola tekstowego o pozycji tytulu 
	public void setDataTitlePosition(String s) {
		StringTokenizer tokenizer = new StringTokenizer(s);

		int i = 0;
		Integer[] values = new Integer[tokenizer.countTokens()];
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			try {
				values[i] = Integer.parseInt(token);
				i++;
			} catch (NumberFormatException exception) {
			}
		}
		setTitlePosition(values);
	}
	// metoda pobiera dane oddzielone spacja z pola tekstowego o pozycji ikony 
	public void setDataIconPosition(String s) {
		StringTokenizer tokenizer = new StringTokenizer(s);

		int i = 0;
		Integer[] values = new Integer[tokenizer.countTokens()];
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			try {
				values[i] = Integer.parseInt(token);
				i++;
			} catch (NumberFormatException exception) {
			}
		}
		setIconPosition(values);
	}
	// metoda ustawia tytul
	public void setTitle(String newValue) {
		if (bean == null)
			return;
		String oldValue = bean.getTitle();
		bean.setTitle(newValue);
		firePropertyChange("title", oldValue, newValue);
	}
	// metoda ustawia pozycje tytulu
	public void setTitlePosition(Integer[] newValue) {
		if (bean == null)
			return;
		Integer[] oldValue = bean.getTitlePosition();
		bean.setTitlePosition(newValue);
		firePropertyChange("titlePosition", oldValue, newValue);
	}

	// metoda ustawia pozycje ikony
	public void setIconPosition(Integer[] newValue) {
		if (bean == null)
			return;
		Integer[] oldValue = bean.getIconPosition();
		bean.setIconPosition(newValue);
		firePropertyChange("iconPosition", oldValue, newValue);
	}
	// metoda ustawia kolor ikony
	public void setIconColor(Color newValue) {
		if (bean == null) {
			System.out.println(bean);
			return;
		}
		Color oldValue = bean.getIconColor();
		bean.setIconColor(newValue);
		firePropertyChange("iconColor", oldValue, newValue);
	}

	public void setObject(Object obj) {
		bean = (CBeanStart) obj;

		Integer[] titlePosition = bean.getTitlePosition();
		for (int i = 0; i < titlePosition.length; i++) {

			(titlePositionField).append(titlePosition[i] + "\n");

		}

		titleField.setText(bean.getTitle());
		colorEditor.setValue(bean.getIconColor());
	}

	public Dimension getPreferredSize() {
		return new Dimension(XPREFSIZE, YPREFSIZE);
	}

}
