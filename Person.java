
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8474978885918972554L;
	public  ArrayList<Shape>[] al;
	private ArrayList<Integer>[] dp;
	private ArrayList<Shape> removedRec;
	public int w;
	private int[] h;
	private int y;
	public int[] x;
	public int score;

	// private BufferedImage bi;

	@SuppressWarnings("unchecked")
	Person(int x, int y, String filename) throws Exception {
		dp = new ArrayList[2];
		al = new ArrayList[2];
		h = new int[2];
		removedRec = new ArrayList<Shape>();
		this.x = new int[2];
		score = 0;

		this.x[0] = x;
		this.x[1] = x + 150;
		this.y = y;
		w = 45;
		h[0] = h[1] = 10;
		for (int i = 0; i < 2; i++) {
			dp[i] = new ArrayList<>();
			al[i] = new ArrayList<>();
		}
		dp[0].add(160);
		dp[1].add(105);
	}

	public int getHeight(int idx) {
		return dp[idx].get(dp[idx].size() - 1);
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.BLACK);

		// g2.drawImage(bi, x[0], y - bi.getHeight(), null);
		try {
			BufferedImage bi = ImageIO.read(((new File("clown.PNG")).toURI())
					.toURL());
			g2.drawImage(bi, x[0], y - bi.getHeight(), null);
		} catch (Exception e) {

		}
		g2.fillRect(x[0], 590, w, h[0]);
		g2.fillRect(x[1], 645, w, h[1]);
		for (int j = 0; j < al.length; j++) {
			for (int i = 0; i < al[j].size(); i++) {
				al[j].get(i).draw(g);
			}

		}
	}

	public void add(Shape tmp, int idx) {
		tmp.py = 750 - getHeight(idx) - tmp.getHeight();
		al[idx].add((Shape) tmp);
		dp[idx].add(getHeight(idx) + tmp.getHeight());
		if (al[idx].size() > 2) {
			boolean done = true;
			Color c = al[idx].get(al[idx].size() - 1).shape_Color;
			for (int i = al[idx].size() - 2; i >= al[idx].size() - 3 && done; i--)
				done &= al[idx].get(i).shape_Color.equals(c);
			if (done) {

				for (int i = 0; i < 3; i++) {

					removedRec.add((Shape) al[idx].get(al[idx].size() - 1));
					al[idx].remove(al[idx].size() - 1);
					dp[idx].remove(dp[idx].size() - 1);
				}

				score++;

			}
		}
	}

	public void move(int dx) {
		if (x[0] + dx + w < 1303 && x[0] + dx > -1 && x[1] + dx + w < 1303) {
			x[0] += dx;
			x[1] += dx;
		}
		notifyShapes();
	}

	public void notifyShapes() {
		// TODO Auto-generated method stub

		for (int j = 0; j < al.length; j++) {
			for (int i = 0; i < al[j].size(); i++) {
				al[j].get(i).update(x[j]);
			}
		}
	}

	public ArrayList<Shape> getRemoverd() {
		// TODO Auto-generated method stub
		ArrayList<Shape> temp = new ArrayList<>(removedRec);
		removedRec.clear();

		return temp;
	}

}
