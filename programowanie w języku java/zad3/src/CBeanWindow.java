import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class CBeanWindow extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	
	private static final int XPREFSIZE = 300;
	private static final int YPREFSIZE = 300;
	
	Button author = new Button();
	Button set = new Button();
	Button get = new Button();
	String author2 = "Przemyslaw Wojcinowicz";
	String name[] = new String[11];
	String oldName[] = new String[11];

	JTextField data = new JTextField();
	JTextField index = new JTextField();
	Label showData = new Label();
	Label oldData = new Label();
	CBeanWiazane bean = new CBeanWiazane();
	CBeanWiazane2 bean2 = new CBeanWiazane2();
	CBeanOgranicz ogranicz = new CBeanOgranicz();
	CBeanOgranicz2 ogranicz2 = new CBeanOgranicz2();

	public CBeanWindow() {

		JPanel authorPane = new JPanel();
		author.setLabel("Autor programu");
		bean.setText("Tu pojawi sie autor w pierwszym ziarenku");
		bean2.setText("Tu pojawi sie autor w drugim ziarenku");
		authorPane.setLayout(new BorderLayout());
		authorPane.add(author, BorderLayout.NORTH);
		authorPane.add(bean, BorderLayout.CENTER);
		authorPane.add(bean2, BorderLayout.SOUTH);
		bean.addPropertyChangeListener(bean2);
		author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bean.setAuthor(author2);
				bean.setText(author2);
			}
		});
		JPanel ograniczPane = new JPanel();
		set.setLabel("OK");
		ogranicz.setText("Tu wpisz zarobki");
		data.setText("Tu wpisz imie i nazwisko");
		index.setText("Tu wpisz numer tablicy");
		ograniczPane.setLayout(new BorderLayout());
		ograniczPane.add(ogranicz, BorderLayout.SOUTH);
		ograniczPane.add(set, BorderLayout.EAST);
		ograniczPane.add(data, BorderLayout.CENTER);
		ograniczPane.add(index, BorderLayout.NORTH);
		ogranicz.addVetoableChangeListener(ogranicz2);
		set.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int i;
				i = java.lang.Integer.parseInt(index.getText());
				try {
					ogranicz.setSalary(ogranicz.getText(), i);
					setData(i, data.getText());
				} catch (PropertyVetoException e1) {
					System.out.println("Minimalne wynagrodzenie to 2500 zl");
				}

			}
		});

		JPanel showPane = new JPanel();
		get.setLabel("Show");
		showData.setText("Tu pojawia sie aktualne dane");
		oldData.setText("Tu pojawia sie wczesniejsze dane");
		showPane.setLayout(new BorderLayout());
		showPane.add(get, BorderLayout.EAST);
		showPane.add(showData, BorderLayout.NORTH);
		showPane.add(oldData, BorderLayout.SOUTH);

		get.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData.setText(getData());
				oldData.setText(getOldData());
			}
		});

		addTab("Show", showPane);
		addTab("Set", ograniczPane);
		addTab("Author", authorPane);
	}

	public void setData(int index, String newData) {
		oldName[index] = name[index];
		name[index] = newData;
	}

	public String getData() {
		String data = " ";
		for (int i = 1; i < 11; i++) {
			if (name[i] != null) {
				data += (" " + i + " " + name[i] + " " + ogranicz.getSalary(i));
			}
		}
		return data;
	}

	public String getOldData() {
		String oldData = " ";
		for (int i = 1; i < 11; i++) {
			if (name[i] != null) {
				oldData += (" " + i + " " + oldName[i] + " ");
			}
		}

		return oldData;
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(XPREFSIZE, YPREFSIZE);
	}


}