import javax.swing.*;
import java.awt.*;


class MenuScreen extends Screen {
    public enum Selection {
        whereTo,
        tripComputer,
        map,
        speech,
        satellite,
        about;

//        int selected = 0;
//        private Selection vals[] = values();
//        public Selection getSelected(){
//            return vals[selected];
//        }
//        public Selection next()
//        {
//            return vals[(this.ordinal()+1) % vals.length];
//        }
    }
    private JLabel whereToLabel = new JLabel();
    private JLabel tripComputerLabel = new JLabel();
    private JLabel mapLabel = new JLabel();
    private JLabel speechLabel = new JLabel();
    private JLabel satelliteLabel = new JLabel();
    private JLabel aboutLabel = new JLabel();

    Selection selectedItem = Selection.whereTo;

    private ImageIcon imgWhereTo;
    private ImageIcon imgWhereTo_selected;
    private ImageIcon imgTripComputer;
    private ImageIcon imgTripComputer_selected;

    private ImageIcon imgMap;
    private ImageIcon imgMap_selected;
    private ImageIcon imgSpeech;
    private ImageIcon imgSpeech_selected;

    private ImageIcon imgSatellite;
    private ImageIcon imgSatellite_selected;
    private ImageIcon imgAbout;
    private ImageIcon imgAbout_selected;

    MenuScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);
        setBackground(Color.BLACK);
        try {
            imgWhereTo = new ImageIcon(getClass().getResource("images/whereTo.png"));
            imgWhereTo_selected = new ImageIcon(getClass().getResource("images/whereTo_selected.png"));
            imgTripComputer = new ImageIcon(getClass().getResource("images/tripComputer.png"));
            imgTripComputer_selected = new ImageIcon(getClass().getResource("images/tripComputer_selected.png"));
            imgMap = new ImageIcon(getClass().getResource("images/map.png"));
            imgMap_selected = new ImageIcon(getClass().getResource("images/map_selected.png"));
            imgSpeech = new ImageIcon(getClass().getResource("images/speech.png"));
            imgSpeech_selected = new ImageIcon(getClass().getResource("images/speech_selected.png"));
            imgSatellite = new ImageIcon(getClass().getResource("images/satellite.png"));
            imgSatellite_selected = new ImageIcon(getClass().getResource("images/satellite_selected.png"));
            imgAbout = new ImageIcon(getClass().getResource("images/about.png"));
            imgAbout_selected = new ImageIcon(getClass().getResource("images/about_selected.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Creates the whereToLabel image (Default Selected)
        whereToLabel.setIcon(imgWhereTo_selected);
        whereToLabel.setBounds(87, 224, 100, 72);
        add(whereToLabel);
        selectedItem = Selection.whereTo;

        //Creates the tripComputerLabel image
        tripComputerLabel.setIcon(imgTripComputer);
        tripComputerLabel.setBounds(182, 224, 90, 72);
        add(tripComputerLabel);

        //Creates the mapLabel image
        mapLabel.setIcon(imgMap);
        mapLabel.setBounds(87, 301, 90, 72);
        add(mapLabel);

        //Creates the speechLabel image
        speechLabel.setIcon(imgSpeech);
        speechLabel.setBounds(182, 301, 90, 72);
        add(speechLabel);

        //Creates the satelliteLabel image
        satelliteLabel.setIcon(imgSatellite);
        satelliteLabel.setBounds(87, 378, 90, 72);
        add(satelliteLabel);

        //Creates the aboutLabel image
        aboutLabel.setIcon(imgAbout);
        aboutLabel.setBounds(182, 378, 90, 72);
        add(aboutLabel);
    }

    @Override
    void showScreen() {
        whereToLabel.setIcon(imgWhereTo_selected);
        selectedItem = Selection.whereTo;
        tripComputerLabel.setIcon(imgTripComputer);
        mapLabel.setIcon(imgMap);
        speechLabel.setIcon(imgSpeech);
        satelliteLabel.setIcon(imgSatellite);
        aboutLabel.setIcon(imgAbout);
    }

    @Override
    void plus() {
        switch (selectedItem) {
            case whereTo: {
                whereToLabel.setIcon(imgWhereTo);
                tripComputerLabel.setIcon(imgTripComputer_selected);
                selectedItem = Selection.tripComputer;
                break;
            }
            case tripComputer: {
                tripComputerLabel.setIcon(imgTripComputer);
                mapLabel.setIcon(imgMap_selected);
                selectedItem = Selection.map;
                break;
            }
            case map: {
                mapLabel.setIcon(imgMap);
                speechLabel.setIcon(imgSpeech_selected);
                selectedItem = Selection.speech;
                break;
            }
            case speech: {
                speechLabel.setIcon(imgSpeech);
                satelliteLabel.setIcon(imgSatellite_selected);
                selectedItem = Selection.satellite;
                break;
            }
            case satellite: {
                satelliteLabel.setIcon(imgSatellite);
                aboutLabel.setIcon(imgAbout_selected);
                selectedItem = Selection.about;
                break;
            }
            case about: {
                aboutLabel.setIcon(imgAbout);
                whereToLabel.setIcon(imgWhereTo_selected);
                selectedItem = Selection.whereTo;
                break;
            }
        }
    }

    @Override
    void minus() {
        switch (selectedItem) {
            case about: {
                aboutLabel.setIcon(imgAbout);
                satelliteLabel.setIcon(imgSatellite_selected);
                selectedItem = Selection.satellite;
                break;
            }
            case satellite: {
                satelliteLabel.setIcon(imgSatellite);
                speechLabel.setIcon(imgSpeech_selected);
                selectedItem = Selection.speech;
                break;
            }
            case speech: {
                speechLabel.setIcon(imgSpeech);
                mapLabel.setIcon(imgMap_selected);
                selectedItem = Selection.map;
                break;
            }
            case map: {
                mapLabel.setIcon(imgMap);
                tripComputerLabel.setIcon(imgTripComputer_selected);
                selectedItem = Selection.tripComputer;
                break;
            }
            case tripComputer: {
                tripComputerLabel.setIcon(imgTripComputer);
                whereToLabel.setIcon(imgWhereTo_selected);
                selectedItem = Selection.whereTo;
                break;
            }
            case whereTo: {
                whereToLabel.setIcon(imgWhereTo);
                aboutLabel.setIcon(imgAbout_selected);
                selectedItem = Selection.about;
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
            case whereTo: {
                sm.changeCurrentScreen(sm.whereTo);
                break;
            }
            case tripComputer: {
                sm.changeCurrentScreen(sm.tripComp);
                break;
            }
            case map: {
                sm.changeCurrentScreen(sm.map);
                break;
            }
            case speech: {
                sm.changeCurrentScreen(sm.speech);
                break;
            }
            case satellite: {
                sm.changeCurrentScreen(sm.satellite);
                break;
            }
            case about: {
                sm.changeCurrentScreen(sm.about);
                break;
            }
        }
    }
}
