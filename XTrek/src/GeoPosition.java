import java.io.File;
import java.util.*;
import java.util.List;

/**
 * GeoPosition implements methods to process and
 * format information regarding GPS correctly, and pass it
 * to other classes, as well as other parts of the application.
 *
 * @author Liem Pham.
 * @version 3.0.
 * @since 09/02/18.
 */

public class GeoPosition implements Runnable {

    /* The usb device/OSXUblox7 which processes GPS data and passes it into the program. */
    private static final String OSXUBLOX = "/dev/cu.usbmodem1411";

    /* Lines which begin with NMEA_MESSAGE contain the required GPS information. */
    private static final String NMEA_MESSAGE = "$GPGLL";

    /* Represents the offset of the longitude and latitude retrieved from the usb device/OSXUblox.
     * As the data retrieved by the usb device/OSXUblox7 is not 100% accurate.
     */
    private static final float LONGITUDE_OFFSET = (float) 0.295366;
    private static final float LATITUDE_OFFSET = (float) 0.213123;

    /* Reducing the format of the GPS data to be identical with the assessment document. */
    private static final String LONGLAT_FORMAT = "%.4f";

    /* Removing magic numbers and strings from deep within the code. */
    private static final int LONGITUDE_IND = 1;
    private static final int LONGITUDE_DIR = 2;
    private static final int LATITUDE_IND = 3;
    private static final int LATITUDE_DIR = 4;

    private static final String NO_POSITION = "S";
    private static final String NO_DEVICE = "D";

    private static final int THREAD_SLEEP = 1000;

    static List<String> retrieveData(String fileName) {
        /**
         * Determines the longitude and latitude, depending on whether your location is
         * detectable using a usb device/OSXUblox7 dongle and stores it inside a list.
         * Otherwise, two error messages will be stored in an individual list.
         *
         * @param fileName which is the usb device/OSXUblox7.
         * @return a list containing the longitude, direction of longitude, latitude
         *         and direction of latitude of your location. If not possible, retrieveData
         *         returns two lists containing strings representing error messages for
         *         two different scenarios.
         * @exception IOException if the usb device/OSXUblox7 cannot be read.
         * @see IOExcetpion.
         */

        try {
            /* Create a Scanner object to read input from the OSXUblox7 GPS dongle. */
            File file = new File(fileName);
            Scanner in = new Scanner(file);

            /* List to be returned containing either GPS data, or a respective error string. */
            List<String> listPosition = new ArrayList<String>();

            /* Continue searching through the usb device/OSXUblox7 input until potentially useful data appears. */
            while (in.hasNext()) {

                /* Scan the entire input, line-by-line. */
                String coordinates = in.nextLine();

                if (coordinates.startsWith(NMEA_MESSAGE)) {

                    /* Store the target line into a list, used when indexing each component later. */
                    List<String> listTemp = Arrays.asList(coordinates.split("\\s*,\\s*"));

                    /* If the longitude is present and not an empty string, the latitude will be present also. */
                    if (!listTemp.get(LONGITUDE_IND).equals("")) {

                        /* Formatting the longitude and latitude to be identical to that in the assessment document. */
                        float lon = ((Float.parseFloat(listTemp.get(LONGITUDE_IND)) / 100) + LONGITUDE_OFFSET);
                        float lat = ((Float.parseFloat(listTemp.get(LATITUDE_IND)) / 100) + LATITUDE_OFFSET);

                        /* The numbers before the decimal point are the longitude/latitude, and the
                         * numbers after the decimal point are the minutes, i.e 1649 = 16.49 minutes.
                         */
                        String longitude = String.format(LONGLAT_FORMAT, lon);
                        String latitude = String.format(LONGLAT_FORMAT, lat);

                        /* Adding the longitude, direction of longitude, latitude and direction of latitude to a list. */
                        listPosition.add(longitude);
                        listPosition.add(listTemp.get(LONGITUDE_DIR));
                        listPosition.add(latitude);
                        listPosition.add(listTemp.get(LATITUDE_DIR));
                        break;

                    } else { /* Condition when the OSXUblox7 dongle cannot retrieve GPS information. */
                        listPosition.add(NO_POSITION);
                        break;
                    }
                }
            }
            /* Return either a list containing useful GPS information or a string representing an error message. */
            return listPosition;

        } catch (Exception ex) { /* Condition where the OSXUblox7 device isn't plugged into the correct USB port/at all. */
            return Collections.singletonList(NO_DEVICE);
        }
    }

    @Override
    public void run() {
        /**
         * This function controls the operation of a thread and what data will be produced as a result
         * of the thread and where it will be stored.
         *
         * @param args Unused.
         * @return data which SatelliteScreen will utilise to display useful information to the user.
         * @exception InterruptedException due to the thread being stopped by another process/command.
         * @see InterruptedException.
         */

        while (true) {

            try {
                /* Storing the returned data from the retrieveData to a variable to be used in SatelliteScreen. */
                SatelliteScreen.getInstance().positionGeo = retrieveData(OSXUBLOX);
                /* Make the thread sleep after each iteration. */
                Thread.sleep(THREAD_SLEEP);

            } catch (InterruptedException e) { /* Sends a statement to the console stating the relevant error. */
                System.out.println("There has been an issue with gaining GPS data. Please retry entering the satellite section of this application.");
                break;
            }
        }
    }

}


