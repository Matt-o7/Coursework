import javax.swing.*;
import java.awt.*;

/**
 * SatelliteScreen, which is a screen displaying
 * information regarding GPS.
 *
 * Liem Pham, 2018.
 */

class SatelliteScreen extends Screen {

    //Singleton initializer of SatelliteScreen.
    private static SatelliteScreen satelliteInstance;

    //Where the GPS information will be displayed.
    //Resets the text from a previous access of the SatelliteScreen.
    private static JLabel position = new JLabel("");

    //Removes "Magic Numbers" from deep within code.
    private final static String GEOPOSITION = "/dev/cu.usbmodem1411";
    private final int CO_X = 110;
    private final int CO_Y = 180;
    private final int CO_W = 170;
    private final int CO_H = 300;
    private final int F_S = 18;

    private SatelliteScreen(ScreenManager sm) {
        /**
         * Determines the base details of the screen
         * when the ScreenManager calls SatelliteScreen.
         *
         * @param sm, which is of type ScreenManager.
         *
         * @returns the base of the SatelliteScreen which will be
         *         displayed when called by ScreenManager.
         */

        super(sm);
        setLayout(null);
    }

    static SatelliteScreen getInstance() {
        /**
         * Returns the single instance of KeyboardScreen
         *
         * @return the instance of KeyboardScreen
         */

        if(satelliteInstance == null) {
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
         * @return the entire SatelliteScreen required.
         */

        //Gets the longitude and latitude from a method in GeoPosition.java.
        position.setText(GeoPosition.reader(GEOPOSITION));

        position.setBounds(CO_X, CO_Y, CO_W, CO_H);
        position.setFont(new Font("Calibri", Font.BOLD, F_S));

        add(position, BorderLayout.CENTER);
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


}



