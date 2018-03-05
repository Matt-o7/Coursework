import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TripScreen extends Screen {
    JLabel screen = new JLabel();

    public TripScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);

        screen.setIcon(new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabetA.png")));
        screen.setBounds(87, 224, 187, 232);
        add(screen);
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
        screen.setIcon(new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabetA.png")));
    }


}
