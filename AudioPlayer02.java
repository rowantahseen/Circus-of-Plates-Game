

import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

public class AudioPlayer02 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AudioFormat audioFormat;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;

	public static void main(String args[], String sound) {
		new AudioPlayer02(sound);
	}

	public AudioPlayer02(String sound) {
		playAudio(sound);
		playAudio(sound);
	}

	private void playAudio(String sound) {
		try {
			File soundFile = new File(sound);
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			audioFormat = audioInputStream.getFormat();
			DataLine.Info dataLineInfo = new DataLine.Info(
					SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			new PlayThread().start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	class PlayThread extends Thread {
		byte tempBuffer[] = new byte[10000];

		public void run() {
			try {
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();
				int cnt;
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (cnt > 0) {
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
				}
				sourceDataLine.drain();
				sourceDataLine.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
