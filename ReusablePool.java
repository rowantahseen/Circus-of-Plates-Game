import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class ReusablePool {
	private Color[] clrArr = { Color.black, Color.blue, Color.red };
	private Random random;
	private static Shape lastRec;
	public ArrayList<Shape> reuseble;
	private static includeClass loader;

	public ReusablePool(String dIR) {
		random = new Random();
		loader = new includeClass();
		loader = new includeClass(dIR);
		reuseble = new ArrayList<>();
		lastRec = new Shape(0, 0, 0, 0, null, 0, 0);
	}

	public Shape getInstane() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		int randomNumber = random.nextInt(1000 - 10) + 10;
		int randomNumber2 = random.nextInt(100 - 10);
		if (reuseble.size() == 0) {

			return (Shape) (loader.constructors[0].newInstance(randomNumber,
					randomNumber2, randomNumber + 30, randomNumber2 + 10,
					clrArr[random.nextInt(1000) % clrArr.length], 0, 1));

		} else {
			lastRec = reuseble.get(reuseble.size() - 1);
			reuseble.remove(reuseble.size() - 1);
			lastRec = lastRec.randomupdate(randomNumber, randomNumber2);

			return lastRec;
		}
	}

	public void addTopool(ArrayList<Shape> arrayList) {
		for (int i = 0; i < arrayList.size(); i++)
			reuseble.add(arrayList.get(i));

		
	}
}
