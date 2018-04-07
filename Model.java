import java.awt.Color;
import java.util.Random;

public class Model {
	private Color[] clrArr = { Color.black, Color.blue, Color.red };
	private int playerAscore;
	private int playerBscore;

	protected Model() {
		// TODO Auto-generated constructor stub
		playerAscore = playerBscore = 0;
	}

	protected void incremntPlayerAscore() {
		playerAscore++;
	}

	protected void incremntPlayerBscore() {
		playerBscore++;
	}

	protected int getplayerAscore() {
		return playerAscore;
	}

	protected int getplayerBscore() {
		return playerBscore;

	}

	protected void setplayerAscore(int a) {
		playerAscore = a;
	}

	void setplayerBscore(int a) {
		playerBscore = a;
	}

	protected Shape updateRec(Shape shape) {
		shape.f_p_x += Shape.getXspeed();
		shape.s_p_x += Shape.getXspeed();
		shape.f_p_y += shape.Yspeed;
		shape.s_p_y += shape.Yspeed;
		if (shape.f_p_y > 760) {
			Random random = new Random();
			int randomNumber = random.nextInt(1000 - 10) + 10;
			shape.f_p_y = 0;
			shape.s_p_y = 10;
			shape.f_p_x = randomNumber;
			shape.s_p_x = randomNumber + 30;
			shape.shape_Color = clrArr[random.nextInt(1000) % clrArr.length];
		}
		return shape;
	}

	protected Person addRectoPlayer(Person p, Shape tmp, int type, int idx) {

		p.add(tmp, idx);
		if (type == 1)
			setplayerAscore(p.score);
		else
			setplayerBscore(p.score);

		return p;

	}

	protected boolean collisionDetect(int x1, int x2, int px1, int px2,
			Shape tmp, int height) {
		if (x1 >= px1 && x2 <= px2) {
			if (tmp.f_p_y + tmp.getHeight() + height == 750 || ( Math.abs(x1-px1) + Math.abs(x2-px2))==6  )  {
				return true;
			}
		}
		return false;
	}
}
