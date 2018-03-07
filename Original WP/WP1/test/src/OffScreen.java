import java.awt.*;

public class OffScreen extends Screen {
    public OffScreen(ScreenManager sm) {
        super(sm);

    }

    @Override
    void showScreen() {
        setBackground(Color.BLACK);
    }

    @Override
    void plus() {

    }

    @Override
    void minus() {

    }

    @Override
    void menu() {

    }

    @Override
    void select() {

    }


    @Override
    void onOff() {
        sm.changeCurrentScreen(sm.tm);
    }
}
