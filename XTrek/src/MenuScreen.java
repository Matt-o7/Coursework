import javax.swing.*;
import java.awt.*;


class MenuScreen extends Screen {
    //Singleton initializer for MenuScreen
    private static MenuScreen menuInstance;

    public enum Selection {
        whereTo {
            @Override
            public Selection prev() {
                return values()[values().length - 1];
            }

            @Override
            void updateImgs() {
                whereToLabel.setIcon(imgWhereTo_selected);
                tripComputerLabel.setIcon(imgTripComputer);
                aboutLabel.setIcon(imgAbout);
            }
        },
        tripComputer {
            @Override
            void updateImgs() {
                tripComputerLabel.setIcon(imgTripComputer_selected);
                whereToLabel.setIcon(imgWhereTo);
                mapLabel.setIcon(imgMap);
            }
        },
        map {
            @Override
            void updateImgs() {
                mapLabel.setIcon(imgMap_selected);
                tripComputerLabel.setIcon(imgTripComputer);
                speechLabel.setIcon(imgSpeech);
            }
        },
        speech {
            @Override
            void updateImgs() {
                speechLabel.setIcon(imgSpeech_selected);
                mapLabel.setIcon(imgMap);
                satelliteLabel.setIcon(imgSatellite);
            }
        },
        satellite {
            @Override
            void updateImgs() {
                satelliteLabel.setIcon(imgSatellite_selected);
                speechLabel.setIcon(imgSpeech);
                aboutLabel.setIcon(imgAbout);
            }
        },
        about {
            @Override
            public Selection next() {
                return values()[0];
            }

            @Override
            void updateImgs() {
                aboutLabel.setIcon(imgAbout_selected);
                satelliteLabel.setIcon(imgSatellite);
                whereToLabel.setIcon(imgWhereTo);
            }
        };

        abstract void updateImgs();

        Selection next() {
            return values()[ordinal() + 1];
        }

        Selection prev() {
            return values()[ordinal() - 1];
        }
    }

    private Selection selectedItem;

    private static JLabel whereToLabel = new JLabel();
    private static JLabel tripComputerLabel = new JLabel();
    private static JLabel mapLabel = new JLabel();
    private static JLabel speechLabel = new JLabel();
    private static JLabel satelliteLabel = new JLabel();
    private static JLabel aboutLabel = new JLabel();

    private static ImageIcon imgWhereTo;
    private static ImageIcon imgWhereTo_selected;

    private static ImageIcon imgTripComputer;
    private static ImageIcon imgTripComputer_selected;

    private static ImageIcon imgMap;
    private static ImageIcon imgMap_selected;

    private static ImageIcon imgSpeech;
    private static ImageIcon imgSpeech_selected;

    private static ImageIcon imgSatellite;
    private static ImageIcon imgSatellite_selected;

    private static ImageIcon imgAbout;
    private static ImageIcon imgAbout_selected;

    private MenuScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);
        setBackground(Color.BLACK);
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


        //Positions and adds the menu icon images
        whereToLabel.setBounds(87, 224, 100, 72);
        add(whereToLabel);

        tripComputerLabel.setBounds(182, 224, 90, 72);
        add(tripComputerLabel);

        mapLabel.setBounds(87, 301, 90, 72);
        add(mapLabel);

        speechLabel.setBounds(182, 301, 90, 72);
        add(speechLabel);

        satelliteLabel.setBounds(87, 378, 90, 72);
        add(satelliteLabel);

        aboutLabel.setBounds(182, 378, 90, 72);
        add(aboutLabel);
    }

    static MenuScreen getInstance() {
        if (menuInstance == null) {
            menuInstance = new MenuScreen(sm);
        }
        return menuInstance;
    }

    @Override
    void showScreen() {
        //Sets all of the menu item icons and reselect whereTo as default selected
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
        selectedItem = selectedItem.next();
        selectedItem.updateImgs();
    }

    @Override
    void minus() {
        selectedItem = selectedItem.prev();
        selectedItem.updateImgs();
    }

    @Override
    void menu() {
        //Do nothing, Disabled
    }

    @Override
    void select() {
        switch (selectedItem) {
            case whereTo: {
                sm.changeCurrentScreen(KeyboardScreen.getInstance());
                break;
            }
            case tripComputer: {
                sm.changeCurrentScreen(TripScreen.getInstance());
                break;
            }
            case map: {
                sm.changeCurrentScreen(MapScreen.getInstance());
                break;
            }
            case speech: {
                sm.changeCurrentScreen(SpeechScreen.getInstance());
                break;
            }
            case satellite: {
                sm.changeCurrentScreen(SatelliteScreen.getInstance());
                break;
            }
            case about: {
                sm.changeCurrentScreen(AboutScreen.getInstance());
                break;
            }
        }
    }
}
