import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class MenuScreen extends Screen {
    private JLabel whereTo = new JLabel();
    private JLabel tripComputer = new JLabel();
    private JLabel map = new JLabel();
    private JLabel speech = new JLabel();
    private JLabel satellite = new JLabel();
    private JLabel about = new JLabel();

    private String selectedItem;

    private ImageIcon imgWhereTo = new ImageIcon(this.getClass().getResource("images/whereTo.png"));
    private ImageIcon imgWhereTo_selected = new ImageIcon(this.getClass().getResource("images/whereTo_selected.png"));
    private ImageIcon imgTripComputer = new ImageIcon(this.getClass().getResource("images/tripComputer.png"));
    private ImageIcon imgTripComputer_selected = new ImageIcon(this.getClass().getResource("images/tripComputer_selected.png"));

    private ImageIcon imgMap = new ImageIcon(this.getClass().getResource("images/map.png"));
    private ImageIcon imgMap_selected = new ImageIcon(this.getClass().getResource("images/map_selected.png"));
    private ImageIcon imgSpeech = new ImageIcon(this.getClass().getResource("images/speech.png"));
    private ImageIcon imgSpeech_selected = new ImageIcon(this.getClass().getResource("images/speech_selected.png"));

    private ImageIcon imgSatellite = new ImageIcon(this.getClass().getResource("images/satellite.png"));
    private ImageIcon imgSatellite_selected = new ImageIcon(this.getClass().getResource("images/satellite_selected.png"));
    private ImageIcon imgAbout = new ImageIcon(this.getClass().getResource("images/about.png"));
    private ImageIcon imgAbout_selected = new ImageIcon(this.getClass().getResource("images/about_selected.png"));

    MenuScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);
        setBackground(Color.BLACK);

        //Creates the whereTo image (Default Selected)
        whereTo.setIcon(imgWhereTo_selected);
        whereTo.setBounds(87, 224, 100, 72);
        add(whereTo);
        selectedItem = "whereTo";

        //Creates the tripComputer image
        tripComputer.setIcon(imgTripComputer);
        tripComputer.setBounds(182, 224, 90, 72);
        add(tripComputer);

        //Creates the map image
        map.setIcon(imgMap);
        map.setBounds(87, 301, 90, 72);
        add(map);

        //Creates the speech image
        speech.setIcon(imgSpeech);
        speech.setBounds(182, 301, 90, 72);
        add(speech);

        //Creates the satellite image
        satellite.setIcon(imgSatellite);
        satellite.setBounds(87, 378, 90, 72);
        add(satellite);

        //Creates the about image
        about.setIcon(imgAbout);
        about.setBounds(182, 378, 90, 72);
        add(about);
    }

    @Override
    void showScreen() {
        //Creates the whereTo image (Default Selected)
        whereTo.setIcon(imgWhereTo_selected);
        selectedItem = "whereTo";

        //Creates the tripComputer image
        tripComputer.setIcon(imgTripComputer);
        add(tripComputer);

        //Creates the map image
        map.setIcon(imgMap);
        add(map);

        //Creates the speech image
        speech.setIcon(imgSpeech);
        add(speech);

        //Creates the satellite image
        satellite.setIcon(imgSatellite);
        add(satellite);

        //Creates the about image
        about.setIcon(imgAbout);
        add(about);
    }

    @Override
    void plus() {
        switch (selectedItem) {
            case "whereTo": {
                whereTo.setIcon(imgWhereTo);
                tripComputer.setIcon(imgTripComputer_selected);
                selectedItem = "tripComputer";
                break;
            }
            case "tripComputer": {
                tripComputer.setIcon(imgTripComputer);
                map.setIcon(imgMap_selected);
                selectedItem = "map";
                break;
            }
            case "map": {
                map.setIcon(imgMap);
                speech.setIcon(imgSpeech_selected);
                selectedItem = "speech";
                break;
            }
            case "speech": {
                speech.setIcon(imgSpeech);
                satellite.setIcon(imgSatellite_selected);
                selectedItem = "satellite";
                break;
            }
            case "satellite": {
                satellite.setIcon(imgSatellite);
                about.setIcon(imgAbout_selected);
                selectedItem = "about";
                break;
            }
            case "about": {
                about.setIcon(imgAbout);
                whereTo.setIcon(imgWhereTo_selected);
                selectedItem = "whereTo";
                break;
            }
        }
    }

    @Override
    void minus() {
        switch (selectedItem) {
            case "about": {
                about.setIcon(imgAbout);
                satellite.setIcon(imgSatellite_selected);
                selectedItem = "satellite";
                break;
            }
            case "satellite": {
                satellite.setIcon(imgSatellite);
                speech.setIcon(imgSpeech_selected);
                selectedItem = "speech";
                break;
            }
            case "speech": {
                speech.setIcon(imgSpeech);
                map.setIcon(imgMap_selected);
                selectedItem = "map";
                break;
            }
            case "map": {
                map.setIcon(imgMap);
                tripComputer.setIcon(imgTripComputer_selected);
                selectedItem = "tripComputer";
                break;
            }
            case "tripComputer": {
                tripComputer.setIcon(imgTripComputer);
                whereTo.setIcon(imgWhereTo_selected);
                selectedItem = "whereTo";
                break;
            }
            case "whereTo": {
                whereTo.setIcon(imgWhereTo);
                about.setIcon(imgAbout_selected);
                selectedItem = "about";
                break;
            }
        }
    }

    @Override
    void menu() {
        //Do nothing, Disabled
    }

    @Override
    void select() {
        switch (selectedItem) {
            case "whereTo": {
                System.out.println("Where To clicked");
                //sm.changeCurrentScreen(sm.whereTo);
                break;
            }
            case "tripComputer": {
                System.out.println("Trip Computer clicked");
                //sm.changeCurrentScreen(sm.tripComp);
                break;
            }
            case "map": {
                System.out.println("Map clicked");
                sm.changeCurrentScreen(sm.map);
                break;
            }
            case "speech": {
                System.out.println("Speech clicked");
                //sm.changeCurrentScreen(sm.speech);
                break;
            }
            case "satellite": {
                System.out.println("Satellite clicked");
                sm.changeCurrentScreen(sm.satellite);
                break;
            }
            case "about": {
                System.out.println("About clicked");
                sm.changeCurrentScreen(sm.about);
                break;
            }
        }
    }
}
