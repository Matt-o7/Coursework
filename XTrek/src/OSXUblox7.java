import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.io.*;

/* 
 * OSX Ublox7 reader.
 * 
 * David Wakeling, 2018.
 */
public class OSXUblox7 {
  final static String FILE_NAME = "/dev/cu.usbmodem1411";
  /* final static String FILE_NAME = "/dev/cu.usbmodem1441"; */
  final static int    BUFF_SIZE = 1024;

  /*
   * Reader.
   */
  static void reader1(String fileName) {
    try {
      FileInputStream in = new FileInputStream( new File( fileName ) ); //reading bytes from the usb modem
      byte[] buffer      = new byte[ BUFF_SIZE ];
      String s = null;
      int    n;
                
      while ( (n = in.read( buffer )) > -1) {
          //System.out.println(n);
        s = new String( buffer, 0, n );
        //System.out.println(s.substring(4,6));

        if( s.contains("GLL")) {

            if (s.startsWith("$GPGLL")) {

                if ((s.charAt(7) == ',') && (s.charAt(10) == ',')) {
                    System.out.println("ERROR - Latitude and longitude cannot be found");
                    System.out.println("\n");

                } else if (s.charAt(7) != ',') {
                    if (s.charAt(14) == ',') {
                        System.out.println("ERROR - Longitude cannot be found");
                        System.out.println("Latitude is" + s.substring(8, 15));
                        System.out.println("\n");
                    }
                } else if (s.charAt(10) != ',') {
                    if (s.charAt(7) == ',') {
                        System.out.println("ERROR - Latitude cannot be found");
                        System.out.println(s.substring(10, 17));
                        System.out.println("\n");
                    }
                } else {
                    System.out.println("Latitude is" + s.substring(8, 15));
                    System.out.println("Longitude is" + s.substring(19, 25));
                    System.out.println("\n");
                }
            }
        }
      }

    } catch ( Exception ex ) {
      ex.printStackTrace();
    }
  }
}
