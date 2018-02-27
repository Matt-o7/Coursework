import javax.swing.*;
import java.io.IOException;

public class ScreenManager extends JFrame {

    private JLabel background = new JLabel();
    private JButton onOffButton = new JButton();
    private JButton plusButton = new JButton();
    private JButton minusButton = new JButton();
    private JButton selectButton = new JButton();
    private JButton menuButton = new JButton();

    private Screen currentScreen;

    OffScreen off = new OffScreen(this);
    MenuScreen menu = new MenuScreen(this);
    KeyboardScreen whereTo = new KeyboardScreen(this);
    MapScreen map = new MapScreen(this);
    SatelliteScreen satellite = new SatelliteScreen(this);
    SpeechScreen speech = new SpeechScreen(this);
    AboutScreen about = new AboutScreen(this);

    public static void main(String[] args) {
        try {
            new ScreenManager().frame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void frame() throws IOException {
        setSize(366, 635);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        background.setIcon(new ImageIcon(this.getClass().getResource("images/xtrek_on_template.png")));
        background.setBounds(0, 0, 360, 600);
        add(background);


        //Creates the onOffButton
        onOffButton.setBounds(234, 106, 45, 45);
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
        plusButton.setBounds(10, 60, 30, 55);
        plusButton.setBorder(null);
        plusButton.addActionListener(e -> plusButtonPressed());
        plusButton.setOpaque(false);
        plusButton.setContentAreaFilled(false);
        add(plusButton);

        //Creates the minusButton
        minusButton.setBounds(10, 115, 30, 55);
        minusButton.setBorder(null);
        minusButton.addActionListener(e -> minusButtonPressed());
        minusButton.setOpaque(false);
        minusButton.setContentAreaFilled(false);
        add(minusButton);

        //Creates the selectButton
        selectButton.setBounds(10, 190, 30, 65);
        selectButton.setBorder(null);
        selectButton.addActionListener(e -> selectButtonPressed());
        selectButton.setOpaque(false);
        selectButton.setContentAreaFilled(false);
        add(selectButton);

        //Creates the menuButton
        menuButton.setBounds(320, 70, 30, 65);
        menuButton.setBorder(null);
        menuButton.addActionListener(e -> menuButtonPressed());
        menuButton.setOpaque(false);
        menuButton.setContentAreaFilled(false);
        add(menuButton);


        changeCurrentScreen(off);
        setVisible(true);
    }


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
        //System.out.println("On Off Pressed");
        currentScreen.onOff();
        repaint();
    }

    private void plusButtonPressed() {
        //System.out.println("Plus Button Pressed");
        currentScreen.plus();
        repaint();
    }

    private void minusButtonPressed() {
        //System.out.println("Minus Button Pressed");
        currentScreen.minus();
        repaint();
    }

    private void selectButtonPressed() {
        //System.out.println("Select Button Pressed");
        currentScreen.select();
        repaint();
    }

    private void menuButtonPressed() {
        //System.out.println("Menu Button Pressed");
        currentScreen.menu();
        repaint();
    }
}

abstract class Screen extends JPanel {
    ScreenManager sm;

    Screen(ScreenManager sm) {
        this.sm = sm;
    }

    abstract void showScreen();

    abstract void plus();

    abstract void minus();

    abstract void menu();

    abstract void select();

    void onOff() {
        sm.changeCurrentScreen(sm.off);
    }
}
