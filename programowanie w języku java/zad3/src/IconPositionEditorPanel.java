
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;


public class IconPositionEditorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private PropertyEditorSupport editor;
	private Integer[] array;
	private NumberFormat fmt = NumberFormat.getNumberInstance();
	private JTextField valueField = new JTextField(12);
	private JButton valueButton = new JButton("Change");
	private JList<Integer> elementList = new JList<Integer>();
	private ListModel model = new ListModel();

	public IconPositionEditorPanel(PropertyEditorSupport ed) {
		editor = ed;
		setArray((Integer[]) ed.getValue());

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 100;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(valueField, gbc, 0, 1, 1, 1);
		gbc.fill = GridBagConstraints.NONE;
		add(valueButton, gbc, 1, 1, 1, 1);

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

		elementList.setModel((ListModel) model);
		elementList.setSelectedIndex(0);
	}

	public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		add(c, gbc);
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

	
}


class ListModel extends AbstractListModel<Integer> {

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
