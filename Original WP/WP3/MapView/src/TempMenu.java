import javax.swing.*;
import java.awt.*;

public class TempMenu extends Screen {
    public TempMenu(ScreenManager sm) {
        super(sm);
    }

    @Override
    void showScreen() {
        setBackground(new Color(75,75,75));
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
        sm.changeCurrentScreen(sm.mp);
    }

}
