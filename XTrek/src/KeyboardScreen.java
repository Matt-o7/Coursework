import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class KeyboardScreen extends Screen {
    char selected = 'A';
    JLabel screen = new JLabel();

    public KeyboardScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);

        screen.setIcon(new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabetA.png")));
        screen.setBounds(87, 224, 187, 232);
        add(screen);
    }

    @Override
    void plus() {
        if (selected == ']' || selected == '%') {
        } else {
            selected++;
            if (selected == '\\') selected = ']';
            if (selected == ':') selected = '#';
            ImageIcon icon = new ImageIcon(getClass().getResource("images/keyboard/backgroundAlphabet" + selected + ".png"));
            System.out.println("images/backgroundAlphabet" + selected + ".png");
            screen.setIcon(icon);
        }
    }

    @Override
    void minus() {
        if (selected == 'A' || selected == '1') {
        } else {
            if (selected == ']') selected = '\\';
            if (selected == '#') selected = ':';
            selected--;
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabet" + selected + ".png"));
            screen.setIcon(icon);
        }
    }

    @Override
    void menu() {
        sm.changeCurrentScreen(sm.menu);
    }

    @Override
    void select() {
        System.out.print("Select Button Pressed on Button: ");
        if (selected == '[') {
            System.out.println("Space Bar");
        } else if (selected == ']') {
            selected = 49; //ASCI for 1
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabet" + selected + ".png"));
            screen.setIcon(icon);
        }
    }

    void showScreen() {
        selected = 'A';
        screen.setIcon(new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabetA.png")));
    }


}
