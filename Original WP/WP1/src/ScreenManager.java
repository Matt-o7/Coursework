import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ScreenManager extends JFrame {

    public JLabel background = new JLabel();
    public JButton onOffButton = new JButton();
    public JButton plusButton = new JButton();
    public JButton minusButton = new JButton();
    public JButton selectButton = new JButton();
    public JButton menuButton = new JButton();

    private Screen currentScreen;

//    public MapPanel mp = new MapPanel(this);
    public TempOff to = new TempOff(this);
    public MenuScreen tm = new MenuScreen(this);

    public static void main(String[] args) {
        try {
            new ScreenManager().frame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void frame() throws IOException {
        setSize(366, 635);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        background.setIcon(new ImageIcon(this.getClass().getResource("images/xtrek_on_template.png")));
        background.setBounds(0, 0, 360, 600);
        add(background);


        //Creates the onOffButton
        onOffButton.setIcon(new ImageIcon(this.getClass().getResource("images/onoff.png")));
        onOffButton.setBounds(234, 106, 45, 45);
        //onOffButton.setBorder(null);
        onOffButton.addActionListener(e -> {
            try {
                onOffPressed();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
        add(onOffButton);

        //Creates the plusButton
        plusButton.setBounds(10, 60, 30, 55);
        //plusButton.setBorder(null);
        plusButton.addActionListener(e -> plusButtonPressed());
        plusButton.setOpaque(false);
        plusButton.setContentAreaFilled(false);
        add(plusButton);

        //Creates the minusButton
        minusButton.setBounds(10, 115, 30, 55);
        //minusButton.setBorder(null);
        minusButton.addActionListener(e -> minusButtonPressed());
        minusButton.setOpaque(false);
        minusButton.setContentAreaFilled(false);
        add(minusButton);

        //Creates the selectButton
        selectButton.setBounds(10, 190, 30, 65);
        //selectButton.setBorder(null);
        selectButton.addActionListener(e -> selectButtonPressed());
        selectButton.setOpaque(false);
        selectButton.setContentAreaFilled(false);
        add(selectButton);

        //Creates the menuButton
        menuButton.setBounds(320, 70, 30, 65);
        //menuButton.setBorder(null);
        menuButton.addActionListener(e -> menuButtonPressed());
        menuButton.setOpaque(false);
        menuButton.setContentAreaFilled(false);
        add(menuButton);


        changeCurrentScreen(to);
        setVisible(true);
    }


    public void changeCurrentScreen(Screen next) {
        if (currentScreen != null) {
            remove(currentScreen);
        }
        add(next);
        next.setVisible(true);
        next.showScreen();
        validate();
        currentScreen = next;
    }


    public void onOffPressed() {
        //System.out.println("On Off Pressed");
        currentScreen.onOff();
        repaint();
    }

    public void plusButtonPressed() {
        //System.out.println("Plus Button Pressed");
        currentScreen.plus();
        repaint();
    }

    public void minusButtonPressed() {
        //System.out.println("Minus Button Pressed");
        currentScreen.minus();
        repaint();
    }

    public void selectButtonPressed() {
        //System.out.println("Select Button Pressed");
        currentScreen.select();
        repaint();
    }

    public void menuButtonPressed() {
        //System.out.println("Menu Button Pressed");
        currentScreen.menu();
        repaint();
    }
}

abstract class Screen extends JPanel {
    protected ScreenManager sm;

    public Screen(ScreenManager sm) {
        this.sm = sm;
    }

    abstract void showScreen();

    abstract void plus();

    abstract void minus();

    abstract void menu();

    abstract void select();

    void onOff() {
        sm.changeCurrentScreen(sm.to);
    }
}
