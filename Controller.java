import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Controller {
	private static String DIR = "Rectangle.class";
	public static ViewActionListener actionListenr;
	public static ViewKeyListener keyListenr;
	private static Controller controller = null;
	private static Model theModel;
	private static View theView;
	private static includeClass loader = null;

	private static ReusablePool pool;

	protected static includeClass getloader() {
		return loader;
	}

	private Controller() throws Exception {
		// TODO Auto-generated constructor stub

		if (loader == null) {
			loader = new includeClass(DIR);
		}

		keyListenr = new ViewKeyListener();
		actionListenr = new ViewActionListener();
		theModel = new Model();
		theView = new View();
		pool = new ReusablePool(DIR);

	}

	public static Controller getInstance() throws Exception {
		if (controller == null) {
			controller = new Controller();
			theView.Con = loader.constructors;
		}
		return controller;
	}

	protected String getPlayerAscore() {
		// TODO Auto-generated method stub
		return String.valueOf(theModel.getplayerAscore());
	}

	protected String getPlayerBscore() {
		// TODO Auto-generated method stub

		return String.valueOf(theModel.getplayerBscore());
	}

	protected boolean gameIsEnded() {
		if (theModel.getplayerBscore() ==4  || theModel.getplayerAscore() == 4)
			return true;

		return false;

	}

	public static includeClass getLoader() {
		return loader;
	}

	public static void setLoader(includeClass loader) {
		Controller.loader = loader;
	}

	class ViewKeyListener implements KeyListener {
		private int mask;

		public ViewKeyListener() {
			// TODO Auto-generated constructor stub

			mask = 0;

		}

		int getType(KeyEvent e) {
			int c = e.getKeyCode();
			if (c == KeyEvent.VK_LEFT)
				return 0;
			if (c == KeyEvent.VK_RIGHT)
				return 1;
			if (c == KeyEvent.VK_A)
				return 2;
			if (c == KeyEvent.VK_D)
				return 3;
			return -1;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void keyPressed(KeyEvent e) {
			if (e != null) {
				if ((e.getKeyCode() == KeyEvent.VK_S)
						&& ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					// save kda :D
					try {
						ObjectOutputStream oos = new ObjectOutputStream(
								new FileOutputStream("A.txt"));
						oos.writeObject(View.getPerson(0));
						oos.writeObject(View.getPerson(1));
						oos.close();
						System.out.println("SAVED!!");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if ((e.getKeyCode() == KeyEvent.VK_L)
						&& ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					// da b2a el-load :D
					try {
						ObjectInputStream ois = new ObjectInputStream(
								new FileInputStream("A.txt"));
						Person a = (Person) ois.readObject();
						Person b = (Person) ois.readObject();
						theModel.setplayerAscore(a.score);
						theModel.setplayerBscore(b.score);
						View.setPerson(a, 0);
						View.setPerson(b, 1);
						// Model.setplayerBscore(Panel.PlayerB.score);
						ois.close();
						System.out.println("LOADED!!");

					} catch (Exception e1) {
						System.out.println("EX2!!");
					}
				}
				int c = getType(e);
				if (c == -1)
					return;
				mask |= (1 << c);
			}
			for (int i = 0; i < 4; i++) {
				if ((mask & (1 << i)) == 0)
					continue;
				if (i == 0) {
					theView.Window2Move(1, -2);
				} else if (i == 1) {
					theView.Window2Move(1, 2);
				} else if (i == 2) {
					theView.Window2Move(2, -2);
				} else {
					theView.Window2Move(2, 2);
				}
			}
		}

		public void keyReleased(KeyEvent e) {
			int c = getType(e);
			if (c == -1)
				return;
			mask ^= (1 << c);
		}

	}

	class ViewActionListener implements ActionListener {

		// @Override
		public ViewActionListener() {
			// TODO Auto-generated constructor stub

		}

		public void actionPerformed(ActionEvent e) {
			// // validate();

			if (gameIsEnded() == true) {

				if (theModel.getplayerAscore() > theModel.getplayerBscore()) {
					//System.out.println("Player A is the winner");
					try {
						theView.setPlayerAasTheWiner();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					try {
						theView.setPlayerBasTheWiner();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println("Player B is the winner");
				theView.getPanel().s.stop();
				theView.secondWindow.dispose();
				theView.firstWindow.dispose();

			}
			// System.out.println(theView.getWindow2Windowsize());
			for (int i = 0; i < theView.getnumberOfRec(); i++) {

				Shape tmp = theView.getPanel().arr.get(i);

				boolean ch1l = theModel.collisionDetect(tmp.f_p_x, tmp.s_p_x,
						theView.getPanel().PlayerA.x[0],
						theView.getPanel().PlayerA.x[0]
								+ theView.getPanel().PlayerA.w, tmp,
						theView.getPanel().PlayerA.getHeight(0));

				boolean ch2l = theModel.collisionDetect(tmp.f_p_x, tmp.s_p_x,
						theView.getPanel().PlayerB.x[0],
						theView.getPanel().PlayerB.x[0]
								+ theView.getPanel().PlayerB.w, tmp,
						theView.getPanel().PlayerB.getHeight(0));
				boolean ch1r = theModel.collisionDetect(tmp.f_p_x, tmp.s_p_x,
						theView.getPanel().PlayerA.x[1],
						theView.getPanel().PlayerA.x[1]
								+ theView.getPanel().PlayerA.w, tmp,
						theView.getPanel().PlayerA.getHeight(1));
				boolean ch2r = theModel.collisionDetect(tmp.f_p_x, tmp.s_p_x,
						theView.getPanel().PlayerB.x[1],
						theView.getPanel().PlayerB.x[1]
								+ theView.getPanel().PlayerB.w, tmp,
						theView.getPanel().PlayerB.getHeight(1));
				int pastscore;
				if (ch1l) {
					pastscore = theModel.getplayerAscore();
					theView.getPanel().PlayerA = theModel.addRectoPlayer(
							theView.getPanel().PlayerA, tmp, 1, 0);
					if (pastscore + 1 == theModel.getplayerAscore())
						pool.addTopool(theView.getPanel().PlayerA.getRemoverd());

				} else if (ch2l) {
					pastscore = theModel.getplayerBscore();
					theView.getPanel().PlayerB = theModel.addRectoPlayer(
							theView.getPanel().PlayerB, tmp, 2, 0);
					if (pastscore + 1 == theModel.getplayerBscore())
						pool.addTopool(theView.getPanel().PlayerB.getRemoverd());
				} else if (ch1r) {
					pastscore = theModel.getplayerAscore();
					theView.getPanel().PlayerA = theModel.addRectoPlayer(
							theView.getPanel().PlayerA, tmp, 1, 1);
					if (pastscore + 1 == theModel.getplayerAscore())
						pool.addTopool(theView.getPanel().PlayerA.getRemoverd());
				} else if (ch2r) {
					pastscore = theModel.getplayerBscore();
					theView.getPanel().PlayerB = theModel.addRectoPlayer(
							theView.getPanel().PlayerB, tmp, 2, 1);
					if (pastscore + 1 == theModel.getplayerBscore())
						pool.addTopool(theView.getPanel().PlayerB.getRemoverd());
				}
				if (ch1l || ch1r || ch2l || ch2r)
					theView.getPanel().arr.remove(i);
			}

			while (theView.getnumberOfRec() < theView.getAssumednumber()) {
				try {
					theView.getPanel().arr.add(pool.getInstane());
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// System.out.println(pool.reuseble.size());
			// TODO Auto-generated method stub
			for (int i = 0; i < theView.getPanel().arr.size(); i++) {
				theView.getPanel().arr.set(i,
						theModel.updateRec(theView.getPanel().arr.get(i)));

			}
			Controller.keyListenr.keyPressed(null);
		}
	}

}
