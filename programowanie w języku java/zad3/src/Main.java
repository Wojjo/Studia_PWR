import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main {

	public JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	 void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CBeanStart beanStart = new CBeanStart();
		frame.getContentPane().add(beanStart, BorderLayout.WEST);
		
		
		
		
		
		
		
		
	
	}

}
