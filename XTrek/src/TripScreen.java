import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TripScreen extends Screen {

    public String trip_odem = "0.0";
    public String speed = "SHOW UP BITCH";
    public String minutes = "0";
    public String seconds = "0";

    JLabel screen = new JLabel();
    JLabel j_odem = new JLabel(trip_odem);
    JLabel j_speed = new JLabel(speed);
    JLabel j_time = new JLabel(minutes + " minutes " + seconds + "seconds");
    //public String moving_time = "";

    public TripScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);

        screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreen.png")));
        screen.setBounds(87, 224, 187, 232);

        j_odem.setBounds(117, 269, 130, 30);
        j_odem.setOpaque(true);
        j_odem.setText(trip_odem);


        j_speed.setBounds(117, 344, 130, 30);
        j_speed.setOpaque(true);
        j_speed.setText(speed);

        j_time.setBounds(117, 416, 130, 30);
        j_time.setOpaque(true);
        j_time.setText(minutes + " minutes " + seconds + "seconds");

        add(screen);
        add(j_odem);
        add(j_speed);
        add(j_time);


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

    void showScreen() {
        screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreen.png")));
        j_odem.setText(trip_odem);
        j_speed.setText(speed);
        j_time.setText(minutes + " minutes " + seconds + "seconds");
    }


}
