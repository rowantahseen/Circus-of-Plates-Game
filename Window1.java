

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Window1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8883110140142094009L;

	public Window1() throws IOException {
		// TODO Auto-generated constructor stub
		MenuPane a = new MenuPane();
		this.add(a);
		setTitle("Circus of Plates Game");
		setSize(1300, 773);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 

	 
	}

	class MenuPane extends JPanel {

	 

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MenuPane() {
			setLayout(new GridBagLayout());

			JButton singleplayerButton = new JButton();
			singleplayerButton.setIcon(new ImageIcon("Start.png"));
			singleplayerButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					try {
						View.InitilaizeWindow2();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// TODO Auto-generated method stub

				}
			});
			singleplayerButton.setBorder(BorderFactory.createEmptyBorder());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.ipadx = 1;
			gbc.ipady = 1;

			add(singleplayerButton, gbc);

		}

		protected void paintComponent(Graphics g) {
			ImageIcon i = new ImageIcon("MAB.png");
			i.paintIcon(this, g, 0, 0);

		}
	}

}
