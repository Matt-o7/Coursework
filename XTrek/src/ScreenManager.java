import javax.swing.*;
import java.io.IOException;

/**
 * The ScreenManager class manages the different screens for the device
 */
public class ScreenManager extends JFrame {

    private JLabel background = new JLabel();
    private JButton onOffButton = new JButton();
    private JButton plusButton = new JButton();
    private JButton minusButton = new JButton();
    private JButton selectButton = new JButton();
    private JButton menuButton = new JButton();

    private Screen currentScreen;

    private final int frameWidth = 366;
    private final int frameHeight = 635;

    //The location and sizes of the background image
    private final int bgX = 0;
    private final int bgY = 0;
    private final int bgWidth = 360;
    private final int bgHeight = 600;

    //The location and sizes of the off button
    private final int offBtnX = 234;
    private final int offBtnY = 106;
    private final int offBtnWidth = 45;
    private final int offBtnHeight = 45;

    //The location and sizes of the plus button
    private final int plusBtnX = 10;
    private final int plusBtnY = 60;
    private final int plusBtnWidth = 30;
    private final int plusBtnHeight = 55;

    //The location and sizes of the minus button
    private final int minusBtnX = 10;
    private final int minusBtnY = 115;
    private final int minusBtnWidth = 30;
    private final int minusBtnHeight = 55;

    private final int selectBtnX = 10;
    private final int selectBtnY = 190;
    private final int selectBtnWidth = 30;
    private final int selectBtnHeight = 65;

    //The location and sizes of the menu button
    private final int menuBtnX = 320;
    private final int menuBtnY = 70;
    private final int menuBtnWidth = 30;
    private final int menuBtnHeight = 65;

    /**
     * Creates the off screen (blank starting screen)
     */
    OffScreen off = new OffScreen(this);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            new ScreenManager().frame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame and buttons for the XTrek
     *
     * @throws IOException Exception will be thrown when a resource from one of the screens may not be found
     */
    private void frame() throws IOException {
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        background.setIcon(new ImageIcon(this.getClass().getResource("images/xtrek_on_template.png")));
        background.setBounds(bgX, bgY, bgWidth, bgHeight);
        add(background);


        //Creates the onOffButton
        onOffButton.setBounds(offBtnX, offBtnY, offBtnWidth, offBtnHeight);
        onOffButton.setBorder(null);
        onOffButton.addActionListener(e -> {
            try {
                onOffPressed();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        onOffButton.setOpaque(false);
        onOffButton.setContentAreaFilled(false);
        add(onOffButton);

        //Creates the plusButton
        plusButton.setBounds(plusBtnX, plusBtnY, plusBtnWidth, plusBtnHeight);
        plusButton.setBorder(null);
        plusButton.addActionListener(e -> plusButtonPressed());
        plusButton.setOpaque(false);
        plusButton.setContentAreaFilled(false);
        add(plusButton);

        //Creates the minusButton
        minusButton.setBounds(minusBtnX, minusBtnY, minusBtnWidth, minusBtnHeight);
        minusButton.setBorder(null);
        minusButton.addActionListener(e -> minusButtonPressed());
        minusButton.setOpaque(false);
        minusButton.setContentAreaFilled(false);
        add(minusButton);

        //Creates the selectButton
        selectButton.setBounds(selectBtnX, selectBtnY, selectBtnWidth,selectBtnHeight);
        selectButton.setBorder(null);
        selectButton.addActionListener(e -> selectButtonPressed());
        selectButton.setOpaque(false);
        selectButton.setContentAreaFilled(false);
        add(selectButton);

        //Creates the menuButton
        menuButton.setBounds(menuBtnX, menuBtnY, menuBtnWidth, menuBtnHeight);
        menuButton.setBorder(null);
        menuButton.addActionListener(e -> menuButtonPressed());
        menuButton.setOpaque(false);
        menuButton.setContentAreaFilled(false);
        add(menuButton);


        changeCurrentScreen(off);
        setVisible(true);
    }


    /**
     * Change current screen.
     *
     * @param next the screen to change to
     */
    void changeCurrentScreen(Screen next) {
        if (currentScreen != null) {
            remove(currentScreen);
        }
        add(next);
        next.setVisible(true);
        next.showScreen();
        validate();
        currentScreen = next;
    }

    private void onOffPressed() {
        currentScreen.onOff();
        repaint();
    }

    private void plusButtonPressed() {
        currentScreen.plus();
        repaint();
    }

    private void minusButtonPressed() {
        currentScreen.minus();
        repaint();
    }

    private void selectButtonPressed() {
        currentScreen.select();
        repaint();
    }

    private void menuButtonPressed() {
        currentScreen.menu();
        repaint();
    }
}

/**
 * Screen class, difference screens of the XTrek extend and
 * overwrite methods to edit functionality for the specific screen
 */
abstract class Screen extends JPanel {
    static ScreenManager sm;

    /**
     * Instantiates a new Screen.
     *
     * @param sm the screen manager
     */
    Screen(ScreenManager sm) {
        Screen.sm = sm;
    }


    abstract void showScreen();
    abstract void plus();
    abstract void minus();
    abstract void select();

    /**
     * Menu button returns the device to the menu screen
     */
    void menu() {
        sm.changeCurrentScreen(MenuScreen.getInstance());
    }

    /**
     * Turns the device on and off
     */
    void onOff() {
        sm.changeCurrentScreen(sm.off);
    }
}
