import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/*
 * Sound generation. David Wakeling, 2018.
 * Modifications: Philippe Roubert, 2018.
 */

public class Sound {
  /*
   * Play stream.
   */
  static void playStream( byte[] ba ) {

    try {
      AudioFormat format = new AudioFormat(16000.0f, 16, 1, true,  false);
      DataLine.Info  info = new DataLine.Info( SourceDataLine.class, format );
      SourceDataLine line = (SourceDataLine) AudioSystem.getLine( info );
      line.open( format );
      line.start();
      line.write( ba, 64, ba.length-512 );
    } catch ( Exception ex ) {
      System.out.println( "There was an issue with the given stream. Cannot read." );
    }
  }
}
