import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class Template {

    private boolean deviceState = false;;
    JLabel background = new JLabel();
    JButton onOffButton = new JButton();
    JButton plusButton = new JButton();
    JButton minusButton = new JButton();
    JButton selectButton = new JButton();
    JButton menuButton = new JButton();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Template().frame();
            } catch (Exception ex)
            {
                System.out.println(ex);
            }
        });

    }

    public void frame() throws IOException {
        JFrame frame = new JFrame("Menu");
        frame.setSize(370, 635);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        background.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\xtrek_on_template.png"))));
        background.setBounds(0, 0, 360, 600);
        frame.add(background);



        //Creates the onOffButton
        onOffButton.setIcon(new ImageIcon(this.getClass().getResource("\\images\\onoff.png")));
        onOffButton.setBounds(234, 106, 45, 45);
        //onOffButton.setBorder(null);
        onOffButton.addActionListener(e -> {
            try {
                onOffPressed();

            } catch (Exception ex)
            {
                System.out.println(ex);}
        });
        frame.add(onOffButton);

        //Creates the plusButton
        plusButton.setBounds(10, 60, 30, 55);
        //plusButton.setBorder(null);
        plusButton.addActionListener(e -> plusButtonPressed());
        plusButton.setOpaque(false);
        plusButton.setContentAreaFilled(false);
        frame.add(plusButton);

        //Creates the minusButton
        minusButton.setBounds(10, 115, 30, 55);
        //minusButton.setBorder(null);
        minusButton.addActionListener(e -> minusButtonPressed());
        minusButton.setOpaque(false);
        minusButton.setContentAreaFilled(false);
        frame.add(minusButton);

        //Creates the selectButton
        selectButton.setBounds(10, 190, 30, 65);
        //selectButton.setBorder(null);
        selectButton.addActionListener(e -> selectButtonPressed());
        selectButton.setOpaque(false);
        selectButton.setContentAreaFilled(false);
        frame.add(selectButton);

        //Creates the menuButton
        menuButton.setBounds(320, 70, 30, 65);
        //menuButton.setBorder(null);
        menuButton.addActionListener(e -> menuButtonPressed());
        menuButton.setOpaque(false);
        menuButton.setContentAreaFilled(false);
        frame.add(menuButton);

        //plusButton.setEnabled(false);
        //minusButton.setEnabled(false);
        //selectButton.setEnabled(false);
        //menuButton.setEnabled(false);
        frame.setVisible(true);
    }

    public void onOffPressed(){
        System.out.println("On Off Pressed");

    }

    public void plusButtonPressed(){
        System.out.println("Plus Button Pressed");
    }

    public void minusButtonPressed(){
        System.out.println("Minus Button Pressed");
    }

    public void selectButtonPressed(){
        System.out.println("Select Button Pressed");
    }

    public void menuButtonPressed(){
        System.out.println("Menu Button Pressed");
    }
}