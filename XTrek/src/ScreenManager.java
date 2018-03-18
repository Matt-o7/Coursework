import javax.swing.*;
import java.io.IOException;

/**
 * The ScreenManager class manages the different screens for the device
 *
 * @author Dennis Harrop, updated by Matt Humphrey, 2018
 */
public class ScreenManager extends JFrame {

    private JLabel background = new JLabel();
    private JButton onOffButton = new JButton();
    private JButton plusButton = new JButton();
    private JButton minusButton = new JButton();
    private JButton selectButton = new JButton();
    private JButton menuButton = new JButton();

    private Screen currentScreen;

    //region Object locations
    private final int FRAME_WIDTH = 366;
    private final int FRAME_HEIGHT = 635;

    //The location and sizes of the background image
    private final int BG_X = 0;
    private final int BG_Y = 0;
    private final int BG_WIDTH = 360;
    private final int BG_HEIGHT = 600;

    //The location and sizes of the off button
    private final int OFF_BTN_X = 234;
    private final int OFF_BTN_Y = 106;
    private final int OFF_BTN_WIDTH = 45;
    private final int OFF_BTN_HEIGHT = 45;

    //The location and sizes of the plus button
    private final int PLUS_BTN_X = 10;
    private final int PLUS_BTN_Y = 60;
    private final int PLUS_BTN_WIDTH = 30;
    private final int PLUS_BTN_HEIGHT = 55;

    //The location and sizes of the minus button
    private final int MINUS_BTN_X = 10;
    private final int MINUS_BTN_Y = 115;
    private final int MINUS_BTN_WIDTH = 30;
    private final int MINUS_BTN_HEIGHT = 55;

    //The location and sizes of the select button
    private final int SELECT_BTN_X = 10;
    private final int SELECT_BTN_Y = 190;
    private final int SELECT_BTN_WIDTH = 30;
    private final int SELECT_BTN_HEIGHT = 65;

    //The location and sizes of the menu button
    private final int MENU_BTN_X = 320;
    private final int MENU_BTN_Y = 70;
    private final int MENU_BTN_WIDTH = 30;
    private final int MENU_BTN_HEIGHT = 65;
    //endregion

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
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        background.setIcon(new ImageIcon(this.getClass().getResource("images/xtrek_on_template.png")));
        background.setBounds(BG_X, BG_Y, BG_WIDTH, BG_HEIGHT);
        add(background);


        //Creates the onOffButton
        onOffButton.setBounds(OFF_BTN_X, OFF_BTN_Y, OFF_BTN_WIDTH, OFF_BTN_HEIGHT);
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
        plusButton.setBounds(PLUS_BTN_X, PLUS_BTN_Y, PLUS_BTN_WIDTH, PLUS_BTN_HEIGHT);
        plusButton.setBorder(null);
        plusButton.addActionListener(e -> plusButtonPressed());
        plusButton.setOpaque(false);
        plusButton.setContentAreaFilled(false);
        add(plusButton);

        //Creates the minusButton
        minusButton.setBounds(MINUS_BTN_X, MINUS_BTN_Y, MINUS_BTN_WIDTH, MINUS_BTN_HEIGHT);
        minusButton.setBorder(null);
        minusButton.addActionListener(e -> minusButtonPressed());
        minusButton.setOpaque(false);
        minusButton.setContentAreaFilled(false);
        add(minusButton);

        //Creates the selectButton
        selectButton.setBounds(SELECT_BTN_X, SELECT_BTN_Y, SELECT_BTN_WIDTH, SELECT_BTN_HEIGHT);
        selectButton.setBorder(null);
        selectButton.addActionListener(e -> selectButtonPressed());
        selectButton.setOpaque(false);
        selectButton.setContentAreaFilled(false);
        add(selectButton);

        //Creates the menuButton
        menuButton.setBounds(MENU_BTN_X, MENU_BTN_Y, MENU_BTN_WIDTH, MENU_BTN_HEIGHT);
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
