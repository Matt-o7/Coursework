import javax.swing.*;
import java.awt.*;

class SatelliteScreen extends Screen {
    //Singleton initializer of SatelliteScreen
    private static SatelliteScreen satelliteInstance;

    private static JLabel labelting = new JLabel();;

    private SatelliteScreen(ScreenManager sm) {

        super(sm);
        setLayout(null);
    }

    static SatelliteScreen getInstance(){
        /*
         * Returns the single instance of KeyboardScreen
         *
         * @return the instance of KeyboardScreen
         */
        if(satelliteInstance == null){
            satelliteInstance = new SatelliteScreen(sm);
        }
        return satelliteInstance;
    }

    @Override
    void showScreen() {
        labelting.setText("");
        labelting.setText(OSXUblox7q.reader("/dev/cu.usbmodem1411"));
        labelting.setBounds(110, 180, 170, 300);
        labelting.setFont(new Font("Calibri", Font.BOLD, 18));

        add(labelting, BorderLayout.CENTER);
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



