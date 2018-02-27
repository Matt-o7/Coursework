import java.awt.*;

public class SpeechScreen extends Screen {
    public SpeechScreen(ScreenManager sm) {
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
        sm.changeCurrentScreen(sm.menu);
    }

    @Override
    void select() {

    }


    @Override
    void onOff() {
        sm.changeCurrentScreen(sm.off);
    }
}
