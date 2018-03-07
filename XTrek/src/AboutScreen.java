import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class AboutScreen extends Screen {
    //Singleton initializer of AboutScreen
    private static AboutScreen aboutInstance;

    JLabel aboutImage = new JLabel();

    private AboutScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);
        setBackground(Color.BLACK);
    }

    static AboutScreen getInstance(){
        /*
         * Returns the single instance of AboutScreen
         *
         * @return the instance of AboutScreen
         */
        if(aboutInstance == null){
            aboutInstance = new AboutScreen(sm);
        }
        return aboutInstance;
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
    void select() {

    }
}


