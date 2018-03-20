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

            @Override
            void openSelected() {
                sm.changeCurrentScreen(KeyboardScreen.getInstance());
            }
        },
        tripComputer {
            @Override
            void updateImgs() {
                tripComputerLabel.setIcon(imgTripComputer_selected);
                whereToLabel.setIcon(imgWhereTo);
                mapLabel.setIcon(imgMap);
            }

            @Override
            void openSelected() {
                sm.changeCurrentScreen(TripScreen.getInstance());
            }
        },
        map {
            @Override
            void updateImgs() {
                mapLabel.setIcon(imgMap_selected);
                tripComputerLabel.setIcon(imgTripComputer);
                speechLabel.setIcon(imgSpeech);
            }

            @Override
            void openSelected() {
                sm.changeCurrentScreen(MapScreen.getInstance());
            }
        },
        speech {
            @Override
            void updateImgs() {
                speechLabel.setIcon(imgSpeech_selected);
                mapLabel.setIcon(imgMap);
                satelliteLabel.setIcon(imgSatellite);
            }

            @Override
            void openSelected() {
                sm.changeCurrentScreen(SpeechScreen.getInstance());
            }
        },
        satellite {
            @Override
            void updateImgs() {
                satelliteLabel.setIcon(imgSatellite_selected);
                speechLabel.setIcon(imgSpeech);
                aboutLabel.setIcon(imgAbout);
            }

            @Override
            void openSelected() {
                sm.changeCurrentScreen(SatelliteScreen.getInstance());
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

            @Override
            void openSelected() {
                sm.changeCurrentScreen(AboutScreen.getInstance());
            }
        };

        /**
         * Abstract method, enum items overwrite to show the correct PATH_SELECTED image on screen
         */
        abstract void updateImgs();

        /**
         * Abstract method, opens the currently selected item
         */
        abstract void openSelected();

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

    //region Creates the Labels and Images
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
    //endregion

    //region Create the the resource paths
    private static final String PATH = "images/menus/";
    private static final String PATH_SELECTED = "_selected";
    private static final String PATH_PNG = ".png";

    private static final String PATH_WHERE_TO = "/whereTo" + PATH_PNG;
    private static final String PATH_WHERE_TO_SELECTED = "/whereTo" + PATH_SELECTED + PATH_PNG;

    private static final String PATH_TRIP_COMP = "/tripComputer" + PATH_PNG;
    private static final String PATH_TRIP_COMP_SELECTED = "/tripComputer" + PATH_SELECTED + PATH_PNG;

    private static final String PATH_MAP = "/map" + PATH_PNG;
    private static final String PATH_MAP_SELECTED = "/map" + PATH_SELECTED + PATH_PNG;

    private static final String PATH_SPEECH = "/speech" + PATH_PNG;
    private static final String PATH_SPEECH_SELECTED = "/speech" + PATH_SELECTED + PATH_PNG;

    private static final String PATH_SATELLITE = "/satellite" + PATH_PNG;
    private static final String PATH_SATELLITE_SELECTED = "/satellite" + PATH_SELECTED + PATH_PNG;

    private static final String PATH_ABOUT = "/about" + PATH_PNG;
    private static final String PATH_ABOUT_SELECTED = "/about" + PATH_SELECTED + PATH_PNG;
    //endregion

    //region Label bounds
    private static final int LABEL_WIDTH = 90;
    private static final int LABEL_HEIGHT = 72;

    private static final int LEFT_X = 87;
    private static final int RIGHT_X = 182;

    private static final int TOP_ROW_Y = 224;
    private static final int MIDDLE_ROW_Y = 301;
    private static final int BOTTOM_ROW_Y = 378;
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

        //Positions and adds the menu icon images
        whereToLabel.setBounds(LEFT_X, TOP_ROW_Y, LABEL_WIDTH, LABEL_HEIGHT);
        add(whereToLabel);

        tripComputerLabel.setBounds(RIGHT_X, TOP_ROW_Y, LABEL_WIDTH, LABEL_HEIGHT);
        add(tripComputerLabel);

        mapLabel.setBounds(LEFT_X, MIDDLE_ROW_Y, LABEL_WIDTH, LABEL_HEIGHT);
        add(mapLabel);

        speechLabel.setBounds(RIGHT_X, MIDDLE_ROW_Y, LABEL_WIDTH, LABEL_HEIGHT);
        add(speechLabel);

        satelliteLabel.setBounds(LEFT_X, BOTTOM_ROW_Y, LABEL_WIDTH, LABEL_HEIGHT);
        add(satelliteLabel);

        aboutLabel.setBounds(RIGHT_X, BOTTOM_ROW_Y, LABEL_WIDTH, LABEL_HEIGHT);
        add(aboutLabel);

        SatelliteScreen.getInstance();
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

    /**
     * Changes the icon languages depending on the currently PATH_SELECTED language
     */
    private void updateLang() {
        String selectedLang;

        //Changes the selected language language
        switch (SpeechScreen.getLanguage()) {
            case "english":
                selectedLang = "english";
                break;
            case "french":
                selectedLang = "french";
                break;
            case "german":
                selectedLang = "german";
                break;
            case "italian":
                selectedLang = "italian";
                break;
            case "spanish":
                selectedLang = "spanish";
                break;
            default:
                selectedLang = "english";
        }

        //The path to the language folder
        String thisPath = PATH + selectedLang;

        //Sets all the images from the resource folder to the correct language
        imgWhereTo = new ImageIcon(getClass().getResource(thisPath + PATH_WHERE_TO));
        imgWhereTo_selected = new ImageIcon(getClass().getResource(thisPath + PATH_WHERE_TO_SELECTED));

        imgTripComputer = new ImageIcon(getClass().getResource(thisPath + PATH_TRIP_COMP));
        imgTripComputer_selected = new ImageIcon(getClass().getResource(thisPath + PATH_TRIP_COMP_SELECTED));

        imgMap = new ImageIcon(getClass().getResource(thisPath + PATH_MAP));
        imgMap_selected = new ImageIcon(getClass().getResource(thisPath + PATH_MAP_SELECTED));

        imgSpeech = new ImageIcon(getClass().getResource(thisPath + PATH_SPEECH));
        imgSpeech_selected = new ImageIcon(getClass().getResource(thisPath + PATH_SPEECH_SELECTED));

        imgSatellite = new ImageIcon(getClass().getResource(thisPath + PATH_SATELLITE));
        imgSatellite_selected = new ImageIcon(getClass().getResource(thisPath + PATH_SATELLITE_SELECTED));

        imgAbout = new ImageIcon(getClass().getResource(thisPath + PATH_ABOUT));
        imgAbout_selected = new ImageIcon(getClass().getResource(thisPath + PATH_ABOUT_SELECTED));
    }

    @Override
    void showScreen() {
        updateLang();

        //Sets all of the menu item icons and reselect whereTo as default PATH_SELECTED
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
     * It will increment the PATH_SELECTED item and update the images
     * {@inheritDoc}
     */
    @Override
    void plus() {
        selectedItem = selectedItem.next();
        selectedItem.updateImgs();
    }

    /**
     * Overridden method for when the minus button is pressed on the menu screen
     * It will decrement the PATH_SELECTED item and update the images
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
     * It will change the screen to the PATH_SELECTED menu button
     * {@inheritDoc}
     */
    @Override
    void select() {
        selectedItem.openSelected();
    }
}
