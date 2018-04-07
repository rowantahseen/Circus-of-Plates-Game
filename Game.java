import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Game {

	private static Controller theControllter = null;

	public static void main(String[] args) throws Exception {

		AudioPlayer02.main(null, "OST.wav");
		theControllter = Controller.getInstance();

	}

}
