
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Shape implements  Observer, Serializable {
	/**
	 * 
	 */
	public static final long serialVersionUID = 7772374349528434937L;
	public static int Xspeed = 0;
	public Color shape_Color = Color.BLACK;
	public int shape_Strock;
	// public BasicStroke shapeStrock;
	public int gettype;
	public int f_p_x; // first point x
	public int f_p_y; // first point y
	public int s_p_x; // second point x
	public int s_p_y; // second point y
	public int py;
	public int Yspeed = 1;

	 public Shape() {
		// TODO Auto-generated constructor stub
	}

	public Shape(int x1, int y1, int x2, int y2, Color c, int r, int g) {

		shape_Color = c;
		shape_Strock = 3;
		// shapeStrock = new BasicStroke(shape_Strock);
		f_p_x = x1;
		f_p_y = y1;
		s_p_x = x2;
		s_p_y = y2;
		Xspeed = r;
		Yspeed = g;

	}

 

	public void setgettype(int a) {
		gettype = a;
	}

	public int getgettype() {
		return gettype;
	}

	public void setf_p_x(int a) {
		f_p_x = a;
	}

	public void setf_p_y(int b) {
		f_p_y = b;
	}

	public void sets_p_x(int a) {
		s_p_x = a;
	}

	public void sets_p_y(int a) {
		s_p_y = a;
	}

	public void setshape_Color(int s) {
		shape_Color = new Color(s);
	}

	public int getshape_Color() {
		return shape_Color.getRGB();
	}

	public int getf_p_x() {
		return f_p_x;
	}

	public int getf_p_y() {
		return f_p_y;
	}

	public int gets_p_x() {
		return s_p_x;
	}

	public int gets_p_y() {
		return s_p_y;
	}

	public void setStrock(int x) {
		if (x > 1)
			shape_Strock = x;
	}

	public int getStrock() {
		return shape_Strock;
	}

	public Shape getShape() {
		return new Shape(f_p_x, f_p_y, s_p_x, s_p_y, shape_Color, Yspeed, Yspeed);
	}

	public boolean isSelected(int x, int y) {
		return false;
	}

	public Color getColor() {
		return shape_Color;
	}

	// public void setStrock(BasicStroke s) {
	// shapeStrock = s;
	// }

	public Shape[] getHolders() {
		return null;
	}

	public void notifyShapes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int value) {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Shape randomupdate(int randomNumber, int randomNumber2) {
		// TODO Auto-generated method stub
		return getShape();
	}

	public static int getXspeed() {
		return Xspeed;
	}

	public static void setXspeed(int xspeed) {
		Xspeed = xspeed;
	}

}
