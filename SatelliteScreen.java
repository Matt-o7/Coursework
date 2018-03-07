import javax.swing.*;
import java.awt.*;

class SatelliteScreen extends Screen {

    public static JLabel labelting = new JLabel();;

    SatelliteScreen(ScreenManager sm) {

        super(sm);
        setLayout(null);
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
    void menu() {
        sm.changeCurrentScreen(sm.menu);
    }

    @Override
    void select() {

    }

}



