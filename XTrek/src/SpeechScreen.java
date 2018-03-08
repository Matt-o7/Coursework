import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.nio.file.*;

public class SpeechScreen extends Screen {
    //Singleton initializer of SpeechScreen
    private static SpeechScreen speechInstance;

    JLabel  off = new JLabel();
    static JLabel  english = new JLabel();
    static JLabel  french = new JLabel();
    static JLabel  german = new JLabel();
    static JLabel  italian = new JLabel();
    static JLabel  spanish = new JLabel();

    JLabel  offO = new JLabel();
    JLabel  englishO = new JLabel();
    JLabel  frenchO = new JLabel();
    JLabel  germanO = new JLabel();
    JLabel  italianO = new JLabel();
    JLabel  spanishO = new JLabel();

    final static String KEY1 = "bc5d1a3f91ab43208f162ed2d2dd799c";
    final static String KEY2 = "e0d253267c6248ce875443df85049dd4";
    final static String FORMAT = "riff-16khz-16bit-mono-pcm";
    final static String token  = Speech.renewAccessToken( KEY1 );

    static byte[] speech;

    private SpeechScreen(ScreenManager sm) {
        super(sm);

        setLayout(null);







        System.out.println("Initialisation");
        //Initialises the sounds for each option
        speech = Speech.generateSpeech( token,  "English",   "en-US"
                , "Female", "(en-GB, Susan, Apollo)", FORMAT );
        Speech.writeData(speech, "res/audio/english.wav");

        speech = Speech.generateSpeech( token,  "Français",   "Fr-FR"
                , "Female", "(fr-FR, Julie, Apollo)", FORMAT );
        Speech.writeData(speech, "res/audio/french.wav");

        speech = Speech.generateSpeech( token,  "Deutsch",   "de-DE"
                , "Male", "(de-DE, Stefan, Apollo)", FORMAT );
        Speech.writeData(speech, "res/audio/german.wav");

        speech = Speech.generateSpeech( token,  "Italiano",   "it-IT"
                , "Male", "(it-IT, Cosimo, Apollo)", FORMAT );
        Speech.writeData(speech, "res/audio/italian.wav");

        speech = Speech.generateSpeech( token,  "Español",   "es-ES"
                , "Female", "(es-ES, Laura, Apollo)", FORMAT );
        Speech.writeData(speech, "res/audio/spanish.wav");

        //The location of the screen on the device
        int x = 87;
        int y = 224;
        int buttonWidth = 185;
        int buttonHeight = 38;

        //Creates each language option on the screen
        off.setIcon(new ImageIcon(this.getClass().getResource("images/off.png")));
        off.setBounds(x, y, buttonWidth, buttonHeight);
        add(off);

        english.setIcon(new ImageIcon(this.getClass().getResource("images/english.png")));
        english.setBounds(x, y+(1*38), buttonWidth, buttonHeight);
        add(english);

        french.setIcon(new ImageIcon(this.getClass().getResource("images/french.png")));
        french.setBounds(x, y+(2*38), buttonWidth, buttonHeight);
        add(french);

        german.setIcon(new ImageIcon(this.getClass().getResource("images/german.png")));
        german.setBounds(x, y+(3*38), buttonWidth, buttonHeight);
        add(german);

        italian.setIcon(new ImageIcon(this.getClass().getResource("images/italian.png")));
        italian.setBounds(x, y+(4*38), buttonWidth, buttonHeight);
        add(italian);

        spanish.setIcon(new ImageIcon(this.getClass().getResource("images/spanish.png")));
        spanish.setBounds(x, y+(5*38), buttonWidth, buttonHeight);
        add(spanish);

        offO.setIcon(new ImageIcon(this.getClass().getResource("images/offO.png")));
        offO.setBounds(x, y, buttonWidth, buttonHeight);
        add(offO);

        englishO.setIcon(new ImageIcon(this.getClass().getResource("images/englishO.png")));
        englishO.setBounds(x, y+(1*38), buttonWidth, buttonHeight);
        add(englishO);

        frenchO.setIcon(new ImageIcon(this.getClass().getResource("images/frenchO.png")));
        frenchO.setBounds(x, y+(2*38), buttonWidth, buttonHeight);
        add(frenchO);

        germanO.setIcon(new ImageIcon(this.getClass().getResource("images/germanO.png")));
        germanO.setBounds(x, y+(3*38), buttonWidth, buttonHeight);
        add(germanO);

        italianO.setIcon(new ImageIcon(this.getClass().getResource("images/italianO.png")));
        italianO.setBounds(x, y+(4*38), buttonWidth, buttonHeight);
        add(italianO);

        spanishO.setIcon(new ImageIcon(this.getClass().getResource("images/spanishO.png")));
        spanishO.setBounds(x, y+(5*38), buttonWidth, buttonHeight);
        add(spanishO);

        off.setVisible(false);
        //setVisible(true);


    }

    public static void generateSpeechSound(String toBeSaid, String language){

        if (language != "off" && toBeSaid.length() < 50) {
            String filePathString = "res/audio/" + toBeSaid + ".wav";
            Path path = Paths.get(filePathString);


            if (Files.exists(path)) {
                AudioInputStream stm = Sound.setupStream(filePathString);
                Sound.playStream(stm, Sound.readStream(stm));
            } else {
                if (language == "english") {
                    speech = Speech.generateSpeech(token, toBeSaid, "en-US"
                            , "Female", "(en-GB, Susan, Apollo)", FORMAT);
                } else if (language == "french") {
                    speech = Speech.generateSpeech(token, toBeSaid, "Fr-FR"
                            , "Female", "(fr-FR, Julie, Apollo)", FORMAT);
                } else if (language == "german") {
                    speech = Speech.generateSpeech(token, toBeSaid, "de-DE"
                            , "Male", "(de-DE, Stefan, Apollo)", FORMAT);
                } else if (language == "italian") {
                    speech = Speech.generateSpeech(token, toBeSaid, "it-IT"
                            , "Male", "(it-IT, Cosimo, Apollo)", FORMAT);
                } else if (language == "spanish") {
                    speech = Speech.generateSpeech(token, toBeSaid, "es-ES"
                            , "Female", "(es-ES, Laura, Apollo)", FORMAT);
                }
                Speech.writeData(speech, filePathString);
                AudioInputStream stm = Sound.setupStream(filePathString);
                Sound.playStream(stm, Sound.readStream(stm));
            }


        } else{
            System.out.println("Either speech is off or string is too long");
        }
    }

    public static String getLanguage(){
        if (english.isVisible() == false){
            return "english";
        }
        else if (french.isVisible() == false){
            return "french";
        }
        else if (german.isVisible() == false){
            return "german";
        }
        else if (italian.isVisible() == false){
            return "italian";
        }
        else if (spanish.isVisible() == false){
            return "spanish";
        }
        else {
            return "off";
        }
    }


    static SpeechScreen getInstance(){
        /*
         * Returns the single instance of SpeechScreen
         *
         * @return the instance of SpeechScreen
         */
        if(speechInstance == null){
            speechInstance = new SpeechScreen(sm);
        }
        return speechInstance;
    }

    @Override
    void showScreen() {
        setBackground(Color.BLACK);
    }

    @Override
    void plus() {


        //Makes the selection switch to the last one
        if (off.isVisible() == false) {
            off.setVisible(true);
            spanish.setVisible(false);
        }
        else if (spanish.isVisible() == false) {
            spanish.setVisible(true);
            italian.setVisible(false);
        }
        else if(italian.isVisible() == false) {
            italian.setVisible(true);
            german.setVisible(false);
        }
        else if(german.isVisible() == false) {
            german.setVisible(true);
            french.setVisible(false);
        }
        else if(french.isVisible() == false) {
            french.setVisible(true);
            english.setVisible(false);
        }
        else {
            english.setVisible(true);
            off.setVisible(false);
        }

    }

    @Override
    void minus() {

        //Makes the selection switch to the next one
        if (off.isVisible() == false) {
            off.setVisible(true);
            english.setVisible(false);
        }
        else if (english.isVisible() == false) {
            english.setVisible(true);
            french.setVisible(false);
        }
        else if(french.isVisible() == false) {
            french.setVisible(true);
            german.setVisible(false);
        }
        else if(german.isVisible() == false) {
            german.setVisible(true);
            italian.setVisible(false);
        }
        else if(italian.isVisible() == false) {
            italian.setVisible(true);
            spanish.setVisible(false);
        }
        else {
            off.setVisible(false);
            spanish.setVisible(true);
        }

    }

    @Override
    void select() {

        if (english.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "res/audio/english.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }
        else if (french.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "res/audio/french.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }
        else if (german.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "res/audio/german.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }
        else if (italian.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "res/audio/italian.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }
        else if (spanish.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "res/audio/spanish.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }
    }
}
