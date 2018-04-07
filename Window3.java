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

class Window3 extends JFrame {
	/**
	 * 
	 */

	public Window3(String title) throws Exception {

		Windows3Panel window = new Windows3Panel(title);

		setTitle("Circus of Plates Game");

		setSize(500, 500);

		this.add(window);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// this.dispose();

	}

	class Windows3Panel extends JPanel {

		public Windows3Panel(String str) throws Exception {

			JLabel x = new JLabel(str);
			this.add(x);

			setFocusable(true);
			setFocusTraversalKeysEnabled(false);

		}

	}

}
