import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * AboutScreen, the screen displaying
 * information about the device.
 *
 * Liem Pham, 2018.
 */

class AboutScreen extends Screen {

    //Singleton initializer of AboutScreen.
    private static AboutScreen aboutInstance;

    //Where the About image will be displayed.
    JLabel aboutImage = new JLabel();

    //Removes "Magic Numbers" from deep within code.
    private final String ABOUT_PICTURE = "images/about_image.png";
    private final int CO_X = 90;
    private final int CO_Y = 185;
    private final int CO_W = 220;
    private final int CO_H = 300;

    private AboutScreen(ScreenManager sm) {
        /**
         * Determines the base details of the screen
         * when the ScreenManager calls AboutScreen.
         *
         * @param sm, type ScreenManager.
         *
         * @returns the base of the AboutScreen which will be
         *         displayed when called by ScreenManager.
         */

        super(sm);
        setLayout(null);
        setBackground(Color.BLACK);
    }


    static AboutScreen getInstance(){
        /**
         * Returns the single instance of AboutScreen
         *
         * @return the instance of AboutScreen
         */

        if(aboutInstance == null){
            aboutInstance = new AboutScreen(sm);
        }
        return aboutInstance;
    }


    @Override
    void showScreen() {
        /**
         * Returns the appropriate screen when the "about"
         * tab is selected on the home screen.
         *
         * @return the entire AboutScreen required.
         */

        //Attempts to retrieve the image stored in the images folder, inside the resources root.
        try {
            aboutImage.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(ABOUT_PICTURE))));
            aboutImage.setBounds(CO_X, CO_Y, CO_W, CO_H);
            add(aboutImage);

            //If the image cannot be retrieved.
        } catch (IOException e) {
            e.printStackTrace();
        }
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


