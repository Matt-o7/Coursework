import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The TripScreen class manages the TripComputer page in the XTrek.
 *
 * @author Gabriel Copat, 2018
 */
public class TripScreen extends Screen {
    //Singleton initialiser of TripScreen
    private static TripScreen tripInstance;

    //Placeholders until other values come from other packages.
    public String trip_odem = "0.0";
    public String speed = "0.0";
    public String minutes = "0";
    public String seconds = "0";

    JLabel screen = new JLabel();
    JLabel j_odem = new JLabel(trip_odem);
    JLabel j_speed = new JLabel(speed + "m/h");
    JLabel j_time = new JLabel(minutes + " min " + seconds + "secs");
    //public String moving_time = "";

    private TripScreen(ScreenManager sm) {
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
        screen.add(j_odem);
        screen.add(j_speed);
        screen.add(j_time);


    }

    static TripScreen getInstance() {
        /*
         * Returns the single instance of TripScreen
         *
         * @return the instance of TripScreen
         */
        if (tripInstance == null) {
            tripInstance = new TripScreen(sm);
        }
        return tripInstance;
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

    void showScreen() {

    }


}
