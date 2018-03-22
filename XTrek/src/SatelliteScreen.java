import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SatelliteScreen implements methods to display and
 * format information regarding GPS to the user interface.
 *
 * @author Liem Pham.
 * @version 3.0.
 * @since 09/02/18.
 */

class SatelliteScreen extends Screen {

    /* Singleton initialiser of SatelliteScreen. */
    private static SatelliteScreen satelliteInstance;

    /* Creates the Thread to be utilised in retrieving GPS data. */
    Thread oneSatellite;

    private static final String NO_DATA = "N";

    /* Initialise array containing a string indicating no GPS data has been retrieved yet */
    List<String> positionGeo = new ArrayList<>(Arrays.asList(NO_DATA));

    /* Displays information including GPS to the user.
     * Uses two JLabels and specified by the client.
     */
    public static JLabel positionOne = new JLabel("");
    private static JLabel positionTwo = new JLabel("");

    /* Removing magic numbers from deep within the code. */
    private final int CO_X = 110;
    private final int CO_Y = 180;
    private final int CO_W = 170;
    private final int CO_H = 300;
    private final int F_S = 18;
    private final int FF_S = 10;

    private final int N_ONEX = 103;
    private final int N_TWOX = 113;
    private final int N_TWOY = 200;

    private final int S_TWOX = 118;
    private final int D_TWOX = 135;

    private final int DE_ONEX = 130;
    private final int DE_ONEY = 160;
    private final int DE_TWOX = 140;

    private final int FIRST_ELEM = 0;
    private final int SECOND_ELEM = 1;
    private final int THIRD_ELEM = 2;
    private final int FOURTH_ELEM = 3;


    private SatelliteScreen(ScreenManager sm) {
        /**
         * Determines the base details of the screen
         * when the ScreenManager calls SatelliteScreen.
         *
         * @param sm, which is of type ScreenManager.
         * @return the base of the SatelliteScreen which will be
         *         displayed when called by ScreenManager.
         * @exception ClassNotFoundException where GeoPosition or Thread classes cannot be found.
         * @see ClassNotFoundException.
         */

        super(sm);

        /* Prevents from using any specific layout (i.e grid). */
        setLayout(null);

        /* Initialises and starts the thread which will retrieve GPS data consistently. */
        oneSatellite = new Thread(new GeoPosition());
        oneSatellite.start();

    }


    static SatelliteScreen getInstance() {
        /**
         * Returns the single instance of SatelliteScreen.
         *
         * @param args Unused.
         * @return the instance of SatelliteScreen.
         * @exception ClassNotFoundException since instance production may not be available
         * @see ClassNotFoundException
         */

        /* Ensuring only one instance is made. */
        if (satelliteInstance == null) {
            satelliteInstance = new SatelliteScreen(sm);
        }

        return satelliteInstance;

    }

    @Override
    void showScreen() {
        /**
         * Returns the appropriate screen when the "Satellite"
         * tab is selected on the home screen.
         *
         * @param args Unused.
         * @return the entire SatelliteScreen required, containing information (i.e GPS) which the user may use.
         * @exception IOExcetion since the displayPosition method may not return anything.
         * @see IOException.
         */

        /* Calls a function which will determine which information is to be displayed to the user. */
        displayPosition(positionOne, positionTwo);

        /* Adds the two JLabels to the screen so they are visible to the user. */
        add(positionOne, BorderLayout.CENTER);
        add(positionTwo, BorderLayout.CENTER);
    }


    @Override
    void plus() {
    }


    @Override
    void minus() {
    }


    @Override
    void select() {
    }


    void displayPosition(JLabel positionOne, JLabel positionTwo) {
        /**
         * Processes and determines what information the GPS device has returned, and apply
         * the data, allowing the data to be displayed a certain way depending on the contents of the data.
         *
         * @param positionOne a JLabel containing GPS data, which will be displayed to the user.
         * @param positionTwo a JLabel containing GPS data, which will be displayed to the user.
         * @return two JLabels, each containing the data which will be displayed to the user through the user interface.
         * @exception IOException on input error.
         * @see IOException
         */

        /* Switch used instead of if else to optimise program by controlling execution of statements.
         * By determining the contents of a list.
         */
        switch (positionGeo.get(FIRST_ELEM)) {

            /* If no GPS data has been retrieved and processed yet. */
            case "N":
                positionOne.setText("PROCESSING, PLEASE GO BACK");
                positionTwo.setText("TO MENU AND TRY AGAIN");
                positionOne.setBounds(N_ONEX, CO_Y, CO_W, CO_H);
                positionTwo.setBounds(N_TWOX, N_TWOY, CO_W, CO_H);
                positionOne.setFont(new Font("Calibri", Font.BOLD, FF_S));
                positionTwo.setFont(new Font("Calibri", Font.BOLD, FF_S));
                break;

            /* If the GPS dongle has not been able to retrieve longitude and latitude values. */
            case "S":
                positionOne.setText("POSITION NOT");
                positionTwo.setText("DETERMINED");
                positionOne.setBounds(CO_X, CO_Y, CO_W, CO_H);
                positionTwo.setBounds(S_TWOX, N_TWOY, CO_W, CO_H);
                positionOne.setFont(new Font("Calibri", Font.BOLD, F_S));
                positionTwo.setFont(new Font("Calibri", Font.BOLD, F_S));
                break;

            /* Handles the case when the OSXUblox or GPS device isn't inserted. */
            case "D":
                positionOne.setText("NO GPS DEVICE");
                positionTwo.setText("INSERTED");
                positionOne.setBounds(CO_X, CO_Y, CO_W, CO_H);
                positionTwo.setBounds(D_TWOX, N_TWOY, CO_W, CO_H);
                positionOne.setFont(new Font("Calibri", Font.BOLD, F_S));
                positionTwo.setFont(new Font("Calibri", Font.BOLD, F_S));
                break;

            /* Occurs when GPS data, including longitude and latitude values, has been successfully retrieved and process. */
            default:
                /* Display the GPS data in a format identical to the assessment document. (i.e aligned to the right). */
                positionOne.setText(positionGeo.get(FIRST_ELEM) + " " + (positionGeo.get(SECOND_ELEM)));
                positionTwo.setText(positionGeo.get(THIRD_ELEM) + " " + (positionGeo.get(FOURTH_ELEM)));
                positionOne.setBounds(DE_ONEX, DE_ONEY, CO_W, CO_H);
                positionTwo.setBounds(DE_TWOX, N_TWOY, CO_W, CO_H);
                positionOne.setFont(new Font("Calibri", Font.BOLD, F_S));
                positionTwo.setFont(new Font("Calibri", Font.BOLD, F_S));
                break;

        }
    }

}



