
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Rectangle extends Shape {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public int center_x;
	public int center_y;
	public int px, py;
	int Xspeed, Yspeed;
	 

 

	public Rectangle(int x1, int y1, int x2, int y2, Color c, int r, int g) {
		super(x1, y1, x2, y2, c, r, g);

	}

	public void draw(Graphics g) {
		super.draw(g);
		checkRecDim();
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(this.shape_Color);
		g2.setStroke(new BasicStroke(shape_Strock));
		g2.fillRect(px, py, getWidth(), getHeight());
	}

	public void checkRecDim() {
		px = f_p_x;
		py = f_p_y;
	}

	public int getWidth() {
		return Math.abs(f_p_x - s_p_x);
	}

	public int getHeight() {
		super.getHeight();
		return Math.abs(f_p_y - s_p_y);
	}

	public boolean isSelected(int x, int y) {
		center_x = (f_p_x + s_p_x) / 2;
		center_y = (f_p_y + s_p_y) / 2;
		int newWight = Math.abs(center_x - x);
		int newHeight = Math.abs(center_y - y);
		if (newHeight <= getHeight() / 2 && newWight <= getWidth() / 2)
			return true;
		return false;
	}

	@Override
	public Shape getShape() {
		return this;
	}

	@Override
	public void update(int value) {
		// TODO Auto-generated method stub
		int tmpW = getWidth();
		f_p_x = value + 10;
		s_p_x = value + tmpW + 10;
	}

	public Rectangle randomupdate(int a, int b) {

		// TODO Auto-generated method stub
		super.randomupdate(a, b);
		f_p_x = (a);
		f_p_y = (b);
		s_p_x = (a + 30);
		s_p_y = (b + 10);
		return (Rectangle) getShape();
	}

}


