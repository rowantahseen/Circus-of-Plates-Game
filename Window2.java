import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class Window2 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panel window;
	private int sz = 20;
	public static Constructor<?>[] Constractors = new Constructor[55];

	public Window2() throws Exception {
		// TODO Auto-generated constructor stub
		setShapes(View.Con);
		window = new Panel();

		setTitle("Circus of Plates Game");

		setSize(1303, 773);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(window);
		setVisible(true);

	}

	int getsz() {
		return sz;
	}

	public Panel getwindow() {
		return window;
	}

	class Panel extends JPanel {

		private static final long serialVersionUID = 1L;

		protected ArrayList<Shape> arr;

		protected Timer s;
		protected Person PlayerA;
		protected Person PlayerB;

		protected JLabel playerAscore = new JLabel(String.valueOf(0),
				new ImageIcon("Player1.png"), JLabel.LEFT);
		protected JLabel playerBscore = new JLabel(String.valueOf(0),
				new ImageIcon("Player2.png"), JLabel.RIGHT);
		protected Random random = new Random();

		protected Color[] clrArr = { Color.black, Color.blue, Color.red };

		public Panel() throws Exception {

			s = new Timer(10, Controller.actionListenr);
			arr = new ArrayList<>();

			setBackground(Color.white);
			playerAscore.setHorizontalTextPosition(JLabel.CENTER);
			playerAscore.setVerticalTextPosition(JLabel.BOTTOM);
			playerAscore.setFont(new Font("Serif", Font.PLAIN, 25));
			this.add(playerAscore);

			playerBscore.setHorizontalTextPosition(JLabel.CENTER);
			playerBscore.setVerticalTextPosition(JLabel.BOTTOM);
			playerBscore.setFont(new Font("Serif", Font.PLAIN, 25));
			this.add(playerAscore);

			this.add(playerBscore);

			for (int i = 0; i < sz; i++) {
				int randomNumber = random.nextInt(1000 - 10) + 10;
				int randomNumber2 = random.nextInt(1000 - 10);
				arr.add((Shape) Constractors[0].newInstance(randomNumber,
						randomNumber2, randomNumber + 30, randomNumber2 + 7,
						clrArr[random.nextInt(1000) % clrArr.length], 0, 1));
			}

			addKeyListener(Controller.keyListenr);
			PlayerA = new Person(300, 742, "clown.PNG");
			PlayerB = new Person(800, 742, "clown.PNG");

			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			s.start();
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			ImageIcon q = new ImageIcon("Tent2.png");
			q.paintIcon(this, g, 0, 0);

			PlayerA.draw(g);
			PlayerB.draw(g);

			for (int i = 0; i < arr.size(); i++)
				arr.get(i).draw(g);

			playerAscore.setText(String.valueOf(PlayerA.score));
			playerBscore.setText(String.valueOf(PlayerB.score));
			playerAscore.setForeground(Color.white);
			playerBscore.setForeground(Color.white);
			

			repaint();

		}

	}

	public void setShapes(Constructor<?>[] q) {
		// TODO Auto-generated method stub
		// System.out.println("222");
		// System.out.println(q.length);
		Constractors = new Constructor[q.length];
		int o = 0;
		for (Constructor x : q) {
			// System.out.println(x);
			Constractors[o++] = x;
		}

	}

}
