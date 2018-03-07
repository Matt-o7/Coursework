import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class KeyboardScreen extends Screen {
    //Singleton initializer of KeyboardScreen
    private static KeyboardScreen keyboardInstance;

    private char selected = 'A';
    public String output = "";
    JLabel screen = new JLabel();
    JLabel display = new JLabel(output);

    private KeyboardScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);

        screen.setIcon(new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabetA.png")));
        screen.setBounds(87, 224, 187, 232);
        display.setText("");
        display.setBounds(97, 232, 166, 18);
        display.setOpaque(true);
        add(display);
        add(screen);
    }

    static KeyboardScreen getInstance(){
        /*
         * Returns the single instance of KeyboardScreen
         *
         * @return the instance of KeyboardScreen
         */
        if(keyboardInstance == null){
            keyboardInstance = new KeyboardScreen(sm);
        }
        return keyboardInstance;
    }

    @Override
    void plus() {
        if (selected == ']' || selected == '%') {
        } else {
            selected++;
            if (selected == '\\') selected = ']';
            if (selected == ':') selected = '#';
            ImageIcon icon = new ImageIcon(getClass().getResource("images/keyboard/backgroundAlphabet" + selected + ".png"));
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
    void select() {
        System.out.print("Select Button Pressed on Button: ");
        if (selected == '[') {
            //Space Bar
            output = output + " ";
        } else if (selected == ']') {
            //Change Keyboard to numerals
            selected = 49; //ASCI for 1
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabet" + selected + ".png"));
            screen.setIcon(icon);
        }else if (selected == '$') {
            //Change Keyboard to Alphabet
            selected = 'A';
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabet" + selected + ".png"));
            screen.setIcon(icon);
        }else if (selected == '%'){
            //Make output remove last digit
            if (output.length() != 0) {
                output = output.substring(0, output.length() - 1);
            }
        }else if (selected == '#') {
            //Outputs 0
            output = output + '0';
            ;

        }else{
            //Digit pressed
            output = output + selected;

        }
        display.setText(output);
    }

    void showScreen() {
        selected = 'A';
        screen.setIcon(new ImageIcon(getClass().getResource("/images/keyboard/backgroundAlphabetA.png")));
        display.setText("");
    }


}
