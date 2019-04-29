
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.lang.reflect.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;


public class IconPositionEditorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public IconPositionEditorPanel(PropertyEditorSupport ed) {
		editor = ed;
		setArray((Integer[]) ed.getValue());

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 100;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		add(sizeField, gbc, 0, 0, 1, 1);
		add(valueField, gbc, 0, 1, 1, 1);

		gbc.fill = GridBagConstraints.NONE;

		add(sizeButton, gbc, 1, 0, 1, 1);
		add(valueButton, gbc, 1, 1, 1, 1);

		sizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				changeSize();
			}
		});

		valueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				changeValue();
			}
		});

		gbc.weighty = 100;
		gbc.fill = GridBagConstraints.BOTH;

		add(new JScrollPane(elementList), gbc, 0, 2, 2, 1);

		elementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		elementList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int i = elementList.getSelectedIndex();
				if (i < 0)
					return;
				valueField.setText("" + array[i]);
			}
		});

		elementList.setModel((DoubleArrayListModel) model);
		elementList.setSelectedIndex(0);
	}

	public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		add(c, gbc);
	}

	
	public void changeSize() {
		fmt.setParseIntegerOnly(true);
		int s = 0;
		try {
			s = fmt.parse(sizeField.getText()).intValue();
			if (s < 0)
				throw new ParseException("Out of bounds", 0);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "" + e, "Input Error", JOptionPane.WARNING_MESSAGE);
			sizeField.requestFocus();
			return;
		}
		if (s == array.length)
			return;
		setArray((Integer[]) arrayGrow(array, s));
		editor.setValue(array);
		editor.firePropertyChange();
	}

	
	public void changeValue() {
		int v = 0;
		fmt.setParseIntegerOnly(false);
		try {
			v = fmt.parse(valueField.getText()).intValue();
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "" + e, "Input Error", JOptionPane.WARNING_MESSAGE);
			valueField.requestFocus();
			return;
		}
		int currentIndex = elementList.getSelectedIndex();
		setArray(currentIndex, v);
		editor.firePropertyChange();
	}

	
	public void setArray(Integer[] v) {
		if (v == null)
			array = new Integer[0];
		else
			array = v;
		model.setArray(array);
		sizeField.setText("" + array.length);
		if (array.length > 0) {
			valueField.setText("" + array[0]);
			elementList.setSelectedIndex(0);
		} else
			valueField.setText("");
	}

	
	public Integer[] getArray() {
		return (Integer[]) array.clone();
	}

	
	public void setArray(int i, int value) {
		if (0 <= i && i < array.length) {
			model.setValue(i, value);
			elementList.setSelectedIndex(i);
			valueField.setText("" + value);
		}
	}

	
	public Integer getArray(int i) {
		if (0 <= i && i < array.length)
			return array[i];
		return 0;
	}

	
	private static Object arrayGrow(Object a, int newLength) {
		Class<? extends Object> cl = a.getClass();
		if (!cl.isArray())
			return null;
		Class<?> componentType = a.getClass().getComponentType();
		int length = Array.getLength(a);

		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
		return newArray;
	}

	private PropertyEditorSupport editor;
	private Integer[] array;
	private NumberFormat fmt = NumberFormat.getNumberInstance();
	private JTextField sizeField = new JTextField(4);
	private JTextField valueField = new JTextField(12);
	private JButton sizeButton = new JButton("Resize");
	private JButton valueButton = new JButton("Change");
	private JList<Integer> elementList = new JList<Integer>();
	private DoubleArrayListModel model = new DoubleArrayListModel();
}


class DoubleArrayListModel extends AbstractListModel<Integer> {

	private static final long serialVersionUID = 1L;

	public int getSize() {
		return array.length;
	}

	public Integer getElementAt(int i) {
		return  array[i];
	}

	public void setArray(Integer[] a) {
		int oldLength = array == null ? 0 : array.length;
		array = a;
		int newLength = array == null ? 0 : array.length;
		if (oldLength > 0)
			fireIntervalRemoved(this, 0, oldLength);
		if (newLength > 0)
			fireIntervalAdded(this, 0, newLength);
	}

	public void setValue(int i, Integer value) {
		array[i] = value;
		fireContentsChanged(this, i, i);
	}

	private Integer[] array;
}
