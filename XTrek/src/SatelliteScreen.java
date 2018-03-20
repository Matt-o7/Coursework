import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * SatelliteScreen, which is a screen displaying
 * information regarding GPS.
 *
 * Liem Pham, 2018.
 */

class SatelliteScreen extends Screen {

    //Singleton initializer of SatelliteScreen.
    private static SatelliteScreen satelliteInstance;

    List<String> matty;

    //Where the GPS information will be displayed.
    //Resets the text from a previous access of the SatelliteScreen.
    private static JLabel positionOne = new JLabel("");
    private static JLabel positionTwo = new JLabel("");
    private static JLabel aboutImage = new JLabel();

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

        System.out.println("Created");

        new Thread(new GeoPosition()).start();


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


        //System.out.println(GeoPosition.reader(GEOPOSITION));
        //System.out.println(GeoPosition.reader(GEOPOSITION));

        //Use one instance of GeoPosition everytime satellite screen is selected

        final List<String> POSITIONGEO = GeoPosition.reader(GEOPOSITION);

        if ((POSITIONGEO).get(0) == "S") {
            positionOne.setText("POSITION NOT");
            positionTwo.setText("DETERMINED");
            positionOne.setBounds(CO_X, CO_Y, CO_W, CO_H);
            positionTwo.setBounds(118, 200, CO_W, CO_H);

        } else if (POSITIONGEO.get(0) == "D") {
            positionOne.setText("NO GPS DEVICE");
            positionTwo.setText("INSERTED");
            positionOne.setBounds(CO_X, CO_Y, CO_W, CO_H);
            positionTwo.setBounds(135, 200, CO_W, CO_H);

        } else {
            positionOne.setText(POSITIONGEO.get(0) + " " + POSITIONGEO.get(1));
            positionTwo.setText(POSITIONGEO.get(2) + " " + POSITIONGEO.get(3));
            positionOne.setBounds(130, 160, CO_W, CO_H);
            positionTwo.setBounds(140, 200, CO_W, CO_H);
        }

        positionOne.setFont(new Font("Calibri", Font.BOLD, F_S));
        positionTwo.setFont(new Font("Calibri", Font.BOLD, F_S));
        add(positionOne, BorderLayout.CENTER);
        add(positionTwo, BorderLayout.CENTER);
        /*
        ImageIcon loading = new ImageIcon("images/ajax-loader.gif");

        JLabel hello1 = new JLabel("loading... ", loading, JLabel.CENTER);
        //add(hello1);
        hello1.setBounds(CO_X, CO_Y, CO_W, CO_H);
        add(hello1, BorderLayout.CENTER);*/

    }

        @Override
    void plus() {

    }


    @Override
    void minus() {

    }


    @Override
    void select() {
        System.out.println("HELLO WORLD");

    }


}



