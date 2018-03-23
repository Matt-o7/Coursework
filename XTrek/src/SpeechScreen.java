import javax.swing.*;
import java.awt.*;

/**
 * Speech Screen Work Package 4:
 * @author Philippe Roubert
 * Singleton:
 * @author Matt Humphrey
 */


public class SpeechScreen extends Screen{
    //Singleton initializer of SpeechScreen
    private static SpeechScreen speechInstance;

    private JLabel off = new JLabel();
    private static JLabel english = new JLabel();
    private static JLabel french = new JLabel();
    private static JLabel german = new JLabel();
    private static JLabel italian = new JLabel();
    private static JLabel spanish = new JLabel();

    private static boolean hasInitialised = false;
    private final static String KEY1 = "bc5d1a3f91ab43208f162ed2d2dd799c";
    private final static String FORMAT = "riff-16khz-16bit-mono-pcm";
    private static String token = Speech.renewAccessToken(KEY1);



    private static byte[] speech;

    private SpeechScreen(ScreenManager sm) {
        super(sm);

        setLayout(null);

        (new TokenRenewer()).start();

        //Initialises the sounds for each option
        hasInitialised = true;

        //The location of the screen on the device
        final int x = 87;
        final int y = 224;
        final int buttonWidth = 185;
        final int buttonHeight = 38;

        //Creates each language option on the screen
        off.setIcon(new ImageIcon(this.getClass().getResource("images/off.png")));
        off.setBounds(x, y, buttonWidth, buttonHeight);
        add(off);

        english.setIcon(new ImageIcon(this.getClass().getResource("images/english.png")));
        english.setBounds(x, y + (buttonHeight), buttonWidth, buttonHeight);
        add(english);

        french.setIcon(new ImageIcon(this.getClass().getResource("images/french.png")));
        french.setBounds(x, y + (2 * buttonHeight), buttonWidth, buttonHeight);
        add(french);

        german.setIcon(new ImageIcon(this.getClass().getResource("images/german.png")));
        german.setBounds(x, y + (3 * buttonHeight), buttonWidth, buttonHeight);
        add(german);

        italian.setIcon(new ImageIcon(this.getClass().getResource("images/italian.png")));
        italian.setBounds(x, y + (4 * buttonHeight), buttonWidth, buttonHeight);
        add(italian);

        spanish.setIcon(new ImageIcon(this.getClass().getResource("images/spanish.png")));
        spanish.setBounds(x, y + (5 * buttonHeight), buttonWidth, buttonHeight);
        add(spanish);

        JLabel offO = new JLabel();
        offO.setIcon(new ImageIcon(this.getClass().getResource("images/offO.png")));
        offO.setBounds(x, y, buttonWidth, buttonHeight);
        add(offO);

        JLabel englishO = new JLabel();
        englishO.setIcon(new ImageIcon(this.getClass().getResource("images/englishO.png")));
        englishO.setBounds(x, y + (buttonHeight), buttonWidth, buttonHeight);
        add(englishO);

        JLabel frenchO = new JLabel();
        frenchO.setIcon(new ImageIcon(this.getClass().getResource("images/frenchO.png")));
        frenchO.setBounds(x, y + (2 * buttonHeight), buttonWidth, buttonHeight);
        add(frenchO);

        JLabel germanO = new JLabel();
        germanO.setIcon(new ImageIcon(this.getClass().getResource("images/germanO.png")));
        germanO.setBounds(x, y + (3 * buttonHeight), buttonWidth, buttonHeight);
        add(germanO);

        JLabel italianO = new JLabel();
        italianO.setIcon(new ImageIcon(this.getClass().getResource("images/italianO.png")));
        italianO.setBounds(x, y + (4 * buttonHeight), buttonWidth, buttonHeight);
        add(italianO);

        JLabel spanishO = new JLabel();
        spanishO.setIcon(new ImageIcon(this.getClass().getResource("images/spanishO.png")));
        spanishO.setBounds(x, y + (5 * buttonHeight), buttonWidth, buttonHeight);
        add(spanishO);

        off.setVisible(false);
        //setVisible(true);




    }

    /**
     * Generates the sound of the speech from a given String and reads it out loud
     * @param toBeSaid The given String to be said out loud
     * @param language The language in which it should be read
     */
    public static void generateSpeechSound(String toBeSaid, String language) {

        String[] words = toBeSaid.split("\\s+");
        toBeSaid = "";
        for (int i = 0; i < words.length; i++) {

            words[i] = abbreviation(words[i]);
            toBeSaid = toBeSaid + " " + words[i];
        }


        if (!language.equals("off")) {
            String filePathString = "res/audio/" + "command" + ".wav";

            switch (language) {
                case "english":
                    speech = Speech.generateSpeech(token, toBeSaid, "en-US"
                            , "Female", "(en-GB, Susan, Apollo)", FORMAT);
                    break;
                case "french":
                    speech = Speech.generateSpeech(token, toBeSaid, "Fr-FR"
                            , "Female", "(fr-FR, Julie, Apollo)", FORMAT);
                    break;
                case "german":
                    speech = Speech.generateSpeech(token, toBeSaid, "de-DE"
                            , "Male", "(de-DE, Stefan, Apollo)", FORMAT);
                    break;
                case "italian":
                    speech = Speech.generateSpeech(token, toBeSaid, "it-IT"
                            , "Male", "(it-IT, Cosimo, Apollo)", FORMAT);
                    break;
                case "spanish":
                    speech = Speech.generateSpeech(token, toBeSaid, "es-ES"
                            , "Female", "(es-ES, Laura, Apollo)", FORMAT);
                    break;
            }
            Sound.playStream(speech);
        }

    }

    /**
     * Gets the currently selected language
     *
     * @return a String of the selected language
     */
    public static String getLanguage() {
        if (hasInitialised) {
            if (!english.isVisible()) {
                return "english";
            } else if (!french.isVisible()) {
                return "french";
            } else if (!german.isVisible()) {
                return "german";
            } else if (!italian.isVisible()) {
                return "italian";
            } else if (!spanish.isVisible()) {
                return "spanish";
            } else {
                return "off";
            }
        } else {
            return "off";
        }
    }

    /**
     * Changes an abbreviation to its full word
     *
     * @param word Is the given abbreviation
     * @return A String of the full word
     */
    private static String abbreviation(String word){
        switch(word){
            case "Rd": return "Road";
            case "Dr": return "Drive";
            case "Ln": return "Lane";
            case "Aly": return "Alley";
            case "Arc": return "Arcade";
            case "Ave": return "Avenue";
            case "Bch": return "Beach";
            case "Blvd": return "Boulevard";
            case "Brg": return "Bridge";
            case "Byp": return "Bypass";
            case "Ctr": return "Center";
            case "Fld": return "Field";
            case "Fwy": return "Freeway";
            case "Hwy": return "Highway";
            case "Mt": return "Mount";
            case "Mtwy": return "Motorway";
            case "Sq": return "Square";
            case "St": return "Street";
            default: return word;
        }
    }

    public class TokenRenewer extends Thread{
        public void run(){
            token = Speech.renewAccessToken(KEY1);
            try {
                Thread.sleep(1000 * 60 * 9); // 1000 milliseconds * 60 seconds * 9 minutes
                run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Returns the single instance of SpeechScreen
     *
     * @return the instance of SpeechScreen
     */
    static SpeechScreen getInstance() {
        if (speechInstance == null) {
            speechInstance = new SpeechScreen(sm);
        }
        return speechInstance;
    }

    /**
     * Called when the SpeechScreen shows on the XTrek Screen
     */
    @Override
    void showScreen() {
        setBackground(Color.BLACK);
    }

    /**
     * Highlights the previous language
     */
    @Override
    void plus() {

        if (!off.isVisible()) {
            off.setVisible(true);
            spanish.setVisible(false);
        } else if (!spanish.isVisible()) {
            spanish.setVisible(true);
            italian.setVisible(false);
        } else if (!italian.isVisible()) {
            italian.setVisible(true);
            german.setVisible(false);
        } else if (!german.isVisible()) {
            german.setVisible(true);
            french.setVisible(false);
        } else if (!french.isVisible()) {
            french.setVisible(true);
            english.setVisible(false);
        } else {
            english.setVisible(true);
            off.setVisible(false);
        }

    }

    /**
     * Highlights the next language
     */
    @Override
    void minus() {

        if (!off.isVisible()) {
            off.setVisible(true);
            english.setVisible(false);
        } else if (!english.isVisible()) {
            english.setVisible(true);
            french.setVisible(false);
        } else if (!french.isVisible()) {
            french.setVisible(true);
            german.setVisible(false);
        } else if (!german.isVisible()) {
            german.setVisible(true);
            italian.setVisible(false);
        } else if (!italian.isVisible()) {
            italian.setVisible(true);
            spanish.setVisible(false);
        } else {
            off.setVisible(false);
            spanish.setVisible(true);
        }

    }

    /**
     * Selects the highlighted language
     */
    @Override
    void select() {

        if (!english.isVisible()) {
            generateSpeechSound("English", "english");
        } else if (!french.isVisible()) {
            generateSpeechSound("Français", "french");
        } else if (!german.isVisible()) {
            generateSpeechSound("Deutsch", "german");
        } else if (!italian.isVisible()) {
            generateSpeechSound("Italiano", "italian");
        } else if (!spanish.isVisible()) {
            generateSpeechSound("Español", "spanish");
        }
    }
}