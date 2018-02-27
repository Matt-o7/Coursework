import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;

public class SpeechScreen extends Screen {

    JLabel  off = new JLabel();
    JLabel  english = new JLabel();
    JLabel  french = new JLabel();
    JLabel  german = new JLabel();
    JLabel  italian = new JLabel();
    JLabel  spanish = new JLabel();

    JLabel  offO = new JLabel();
    JLabel  englishO = new JLabel();
    JLabel  frenchO = new JLabel();
    JLabel  germanO = new JLabel();
    JLabel  italianO = new JLabel();
    JLabel  spanishO = new JLabel();

    public SpeechScreen(ScreenManager sm) {
        super(sm);

        setLayout(null);


        final String KEY1 = "bc5d1a3f91ab43208f162ed2d2dd799c";
        final String KEY2 = "e0d253267c6248ce875443df85049dd4";
        final String FORMAT = "riff-16khz-16bit-mono-pcm";

        byte[] speech;


        final String token  = Speech.renewAccessToken( KEY1 );



        //Initialises the sounds for each option
        speech = Speech.generateSpeech( token,  "English",   "en-US"
                , "Female", "(en-GB, Susan, Apollo)", FORMAT );
        Speech.writeData(speech, "english.wav");

        speech = Speech.generateSpeech( token,  "Français",   "Fr-FR"
                , "Female", "(fr-FR, Julie, Apollo)", FORMAT );
        Speech.writeData(speech, "french.wav");

        speech = Speech.generateSpeech( token,  "Deutsch",   "de-DE"
                , "Male", "(de-DE, Stefan, Apollo)", FORMAT );
        Speech.writeData(speech, "german.wav");

        speech = Speech.generateSpeech( token,  "Italiano",   "it-IT"
                , "Male", "(it-IT, Cosimo, Apollo)", FORMAT );
        Speech.writeData(speech, "italian.wav");

        speech = Speech.generateSpeech( token,  "Español",   "es-ES"
                , "Female", "(es-ES, Laura, Apollo)", FORMAT );
        Speech.writeData(speech, "spanish.wav");

        //The location of the screen on the device
        int x = 87;
        int y = 224;

        //Creates each language option on the screen
        off.setIcon(new ImageIcon(this.getClass().getResource("images/off.png")));
        off.setBounds(x, y, 185, 38);
        add(off);

        english.setIcon(new ImageIcon(this.getClass().getResource("images/english.png")));
        english.setBounds(x, y+(1*38), 185, 38);
        add(english);

        french.setIcon(new ImageIcon(this.getClass().getResource("images/french.png")));
        french.setBounds(x, y+(2*38), 185, 38);
        add(french);

        german.setIcon(new ImageIcon(this.getClass().getResource("images/german.png")));
        german.setBounds(x, y+(3*38), 185, 38);
        add(german);

        italian.setIcon(new ImageIcon(this.getClass().getResource("images/italian.png")));
        italian.setBounds(x, y+(4*38), 185, 38);
        add(italian);

        spanish.setIcon(new ImageIcon(this.getClass().getResource("images/spanish.png")));
        spanish.setBounds(x, y+(5*38), 185, 38);
        add(spanish);

        offO.setIcon(new ImageIcon(this.getClass().getResource("images/offO.png")));
        offO.setBounds(x, y, 185, 38);
        add(offO);

        englishO.setIcon(new ImageIcon(this.getClass().getResource("images/englishO.png")));
        englishO.setBounds(x, y+(1*38), 185, 38);
        add(englishO);

        frenchO.setIcon(new ImageIcon(this.getClass().getResource("images/frenchO.png")));
        frenchO.setBounds(x, y+(2*38), 185, 38);
        add(frenchO);

        germanO.setIcon(new ImageIcon(this.getClass().getResource("images/germanO.png")));
        germanO.setBounds(x, y+(3*38), 185, 38);
        add(germanO);

        italianO.setIcon(new ImageIcon(this.getClass().getResource("images/italianO.png")));
        italianO.setBounds(x, y+(4*38), 185, 38);
        add(italianO);

        spanishO.setIcon(new ImageIcon(this.getClass().getResource("images/spanishO.png")));
        spanishO.setBounds(x, y+(5*38), 185, 38);
        add(spanishO);


        //setVisible(true);

    }

    @Override
    void showScreen() {
        setBackground(Color.BLACK);
        off.setVisible(false);
        english.setVisible(true);
        french.setVisible(true);
        german.setVisible(true);
        italian.setVisible(true);
        spanish.setVisible(true);

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
    void menu() {
        sm.changeCurrentScreen(sm.menu);
    }

    @Override
    void select() {

        if (english.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "english.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }
        else if (french.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "french.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }
        else if (german.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "german.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }
        else if (italian.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "italian.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }
        else if (spanish.isVisible() == false){
            AudioInputStream stm = Sound.setupStream( "spanish.wav" );
            Sound.playStream( stm, Sound.readStream( stm ) );
        }

    }


    @Override
    void onOff() {
        sm.changeCurrentScreen(sm.off);
    }
}
