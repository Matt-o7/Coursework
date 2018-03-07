import java.io.File;
import java.util.*;
import java.util.List;
/* 
 * OSX Ublox7 reader.
 * 
 * David Wakeling, 2018.
 */
public class OSXUblox7q {
    final static String FILE_NAME = "/dev/cu.usbmodem1411";
    /* final static String FILE_NAME = "/dev/cu.usbmodem1441"; */
    /*
     * Reader.
     */
    static String reader(String fileName) {

        String longitude = null;

        try {
            File file = new File(fileName);
            Scanner in = null;
            in = new Scanner(file);

            while (in.hasNext()) {
                longitude = in.nextLine();

                if (longitude.contains("$GPGLL")) {
                    List<String> items = Arrays.asList(longitude.split("\\s*,\\s*"));

                    System.out.println(longitude);

                    items.get(2).replaceFirst("^0+(?!$)", "");

                    if (!items.get(1).equals("")) {
                        longitude = "<HTML>" + items.get(1) + " " + items.get(2) + "<br><br>" + items.get(3) + " " + items.get(4) + "</HTML>";
                        break;

                    } else {
                        longitude = "<HTML>POSITION<br>NOT<br>DETERMINED</HTML>";
                        break;
                    }

                }
            }
            return longitude;

        } catch (Exception ex) {
            return "<HTML>GPS device<br>not inserted</HTML>";
        }
    }

  /*public static void main( String[] argv ) {
    reader( FILE_NAME );
  }*/
}
