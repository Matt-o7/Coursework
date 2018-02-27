import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class AboutScreen extends Screen {
    JLabel aboutImage = new JLabel();

    AboutScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);
        setBackground(Color.BLACK);
    }

    @Override
    void showScreen() {
        try {
            aboutImage.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("images/about_image.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        aboutImage.setBounds(90, 185, 220, 300);
        add(aboutImage);
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


