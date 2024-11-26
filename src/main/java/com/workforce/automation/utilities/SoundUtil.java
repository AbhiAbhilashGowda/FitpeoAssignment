package com.workforce.automation.utilities;

import javax.sound.sampled.*;

import java.io.IOException;
import java.io.InputStream;


public class SoundUtil {

	public static void playFailureSound() {
		try {
			
            // Load the sound file using the ClassLoader
            InputStream soundFileInputStream = SoundUtil.class.getResourceAsStream("src/main/resources/file_example_WAV_1MG.wav");


			if (soundFileInputStream != null) {

				// Create an AudioInputStream
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFileInputStream);

				// Get the AudioFormat from the AudioInputStream
				AudioFormat audioFormat = audioInputStream.getFormat();

				// Create a DataLine.Info object for the SourceDataLine (the audio output
				// device)
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

				// Open the audio output device
				SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();

				// Create a buffer for reading from the input stream and writing to the output
				// device
				byte[] buffer = new byte[4096];
				int bytesRead;

				// Read from the input stream and write to the output device
				while ((bytesRead = audioInputStream.read(buffer)) != -1) {
					sourceDataLine.write(buffer, 0, bytesRead);
				}

				// Close the input stream and output device
				audioInputStream.close();
				sourceDataLine.drain();
				sourceDataLine.close();
			} else {
				System.err.println("Sound file not found.");
			}

		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
			System.err.println("Error playing sound: " + e.getMessage());
		}
	}
}
