import javax.swing.*;
import java.awt.*;


/**
 * The class for the Menu screen
 *
 * @author Matt Humphrey, 2018
 */
class MenuScreen extends Screen {
    //Singleton initializer for MenuScreen
    private static MenuScreen menuInstance;

    /**
     * The enum Selection.
     * Allows program to loop through the possible selections on the menu screen
     */
    public enum Selection {
        whereTo {
            /**
             * Overridden method for prev to loop through back to the bottom selection
             *
             * @return the last enum
             */
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
            /**
             * Overridden method for next to loop through back to the top selection
             *
             * @return the first enum
             */
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

        /**
         * Abstract method, enum items overwrite to show the correct selected image on screen
         */
        abstract void updateImgs();

        /**
         * Selects the next item in the enum
         *
         * @return the next enum
         */
        Selection next() {
            return values()[ordinal() + 1];
        }

        /**
         * Selects the previous item in the enum
         *
         * @return the previous enum
         */
        Selection prev() {
            return values()[ordinal() - 1];
        }
    }

    //Creates the enum used for the menu button selection
    private Selection selectedItem;

    private static final JLabel whereToLabel = new JLabel();
    private static final JLabel tripComputerLabel = new JLabel();
    private static final JLabel mapLabel = new JLabel();
    private static final JLabel speechLabel = new JLabel();
    private static final JLabel satelliteLabel = new JLabel();
    private static final JLabel aboutLabel = new JLabel();

    //region Creates the images
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
    //endregion

    /**
     * Menu screen constructor, the menu screen is singleton so should only ever be ran once
     *
     * @param sm an instance of ScreenManager
     */
    private MenuScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);
        setBackground(Color.BLACK);

        //Gets all the images from the resource folder
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

    /**
     * Gets the instance of MenuScreen
     *
     * @return the instance of MenuScreen
     */
    static MenuScreen getInstance() {
        //If the instance has not been created yet, then create a new one
        if (menuInstance == null) {
            menuInstance = new MenuScreen(sm);
        }
        return menuInstance;
    }

    @Override
    void showScreen() {
        //Sets all of the menu item icons and reselect whereTo as default selected
        whereToLabel.setIcon(imgWhereTo_selected);
        tripComputerLabel.setIcon(imgTripComputer);
        mapLabel.setIcon(imgMap);
        speechLabel.setIcon(imgSpeech);
        satelliteLabel.setIcon(imgSatellite);
        aboutLabel.setIcon(imgAbout);

        selectedItem = Selection.whereTo;
    }

    /**
     * Overridden method for when the plus button is pressed on the menu screen
     * It will increment the selected item and update the images
     * {@inheritDoc}
     */
    @Override
    void plus() {
        selectedItem = selectedItem.next();
        selectedItem.updateImgs();
    }

    /**
     * Overridden method for when the minus button is pressed on the menu screen
     * It will decrement the selected item and update the images
     * {@inheritDoc}
     */
    @Override
    void minus() {
        selectedItem = selectedItem.prev();
        selectedItem.updateImgs();
    }

    /**
     * Overridden method for when the menu button is pressed on the menu screen
     * Menu button is disabled so nothing should happen
     * {@inheritDoc}
     */
   @Override
    void menu() {
        //Do nothing, Disabled
    }

    /**
     * Overridden method for when the select button is pressed on the menu screen
     * It will change the screen to the selected menu button
     * {@inheritDoc}
     */
    @Override
    void select() {
        // TODO Add this section in Selection enum to remove large switch case
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
