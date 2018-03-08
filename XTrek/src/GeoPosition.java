import java.io.File;
import java.util.*;
import java.util.List;

/**
 * GPS OSXUblox7 reader.
 * 
 * Liem Pham, 2018.
 */
public class GeoPosition {
    /**
     * Reader.
     */

    private static final int LONGITUDE_IND = 1;
    private static final int LONGITUDE_DIR = 2;
    private static final int LATITUDE_IND = 3;
    private static final int LATITUDE_DIR = 4;


    static String reader(String fileName) {
        /**
         * Determines the longitude and latitude, depending
         * on whether your location is detectable using a OSXUblox7
         * dongle. Otherwise, two error messages will appear.
         *
         * @param fileName which is the usb device/OSXUblox7.
         *
         * @returns either the longitude and latitude of your location,
         *          or two error messages for two different scenarios.
         */

        //Variable containing the latitude and longitude, which will be returned.
        String coordinates = null;

        try {
            // Create a Scanner object to read input from the OSXUblox7 GPS dongle.
            File file = new File(fileName);
            Scanner in = new Scanner(file);

            // Keep reading input from the OSXUblox7 dongle until there's no input left to read.
            while (in.hasNext()) {

                // Read line by line.
                coordinates = in.nextLine();

                /* Only require the lines containing "$GPGLL" as
                 * they contain the longitude and latitude.
                 */
                if (coordinates.contains("$GPGLL")) {

                    // Store the target line into a list, so you can index each component for later use.
                    List<String> items = Arrays.asList(coordinates.split("\\s*,\\s*"));

                    //If the longitude is present and not an empty string, the latitude will be present also.
                    if (!items.get(1).equals("")) {

                        // Formatting the longitude and latitude more effectively as shown in the assessment document.
                        float lon = (Float.parseFloat(items.get(LONGITUDE_IND)) / 100);
                        float lat = (Float.parseFloat(items.get(LATITUDE_IND)) / 100);

                        /* The numbers before the decimal point are the longitude/latitude, and the
                         * numbers after the decimal point are the minutes, i.e 1649 = 16.49 minutes.
                         */
                        String longitude = String.format("%.4f", lon);
                        String latitude = String.format("%.4f", lat);

                        coordinates = "<HTML>" + longitude + " " + items.get(LONGITUDE_DIR) + "<br><br>" + latitude + " " + items.get(LATITUDE_DIR) + "</HTML>";
                        break;
                    } else { // Condition when the OSXUblox7 dongle cannot retrieve GPS information.
                        coordinates = "<HTML>POSITION<br>NOT<br>DETERMINED</HTML>";
                        break;
                    }
                }
            }
            return coordinates;

        } catch (Exception ex) { // Condition where the OSXUblox7 device isn't plugged into the correct USB port/at all.
            return "<HTML>GPS device<br>not inserted</HTML>";
        }
    }
}
