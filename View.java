import java.lang.reflect.Constructor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View {

	public static  Window1 firstWindow;
	public static Window2 secondWindow;
	public static Window3 thirdWindow;
	
	public static Constructor<?>[] Con;

	public View() throws Exception {

		setFirstWindow(new Window1());
		Con = new Constructor[4];

	}

	static void InitilaizeWindow2() throws Exception {

		secondWindow = new Window2();

	}

	protected void Window2Move(int playernumber, int value) {
		// TODO Auto-generated method stub
		if (playernumber == 1) {

			secondWindow.getwindow().PlayerA.move(value);
		} else {
			secondWindow.getwindow().PlayerB.move(value);
		}
	}

	protected int getnumberOfRec() {
		return secondWindow.getwindow().arr.size();
	}

	public Window2.Panel getPanel() {
		// TODO Auto-generated method stub
		return secondWindow.getwindow();
	}

	public int getAssumednumber() {
		return secondWindow.getsz();
	}

	public static Window2 getSecondWindow() {
		return secondWindow;
	}

	static Person getPerson(int i) {
		return i == 0 ? secondWindow.getwindow().PlayerA : secondWindow
				.getwindow().PlayerB;
	}

	static void setPerson(Person x, int i) {
		if (i == 0)
			secondWindow.getwindow().PlayerA = x;
		else
			secondWindow.getwindow().PlayerB = x;
	}

	public Window1 getFirstWindow() {
		return firstWindow;
	}

	public void setFirstWindow(Window1 firstWindow) {
		this.firstWindow = firstWindow;
	}

	public void setPlayerAasTheWiner() throws Exception {
		// TODO Auto-generated method stub
		thirdWindow = new Window3("Player A is the Winner ");

	}

	public void setPlayerBasTheWiner() throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		thirdWindow = new Window3("Player B is the Winner ");

		// secondWindow.dispose();
	}
}
