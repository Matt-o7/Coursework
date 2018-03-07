import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuScreen extends Screen {
    private JLabel whereTo = new JLabel();
    private JLabel tripComputer = new JLabel();
    private JLabel map = new JLabel();
    private JLabel speech = new JLabel();
    private JLabel satellite = new JLabel();
    private JLabel about = new JLabel();

    private String selectedItem;

    public MenuScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);
        setBackground(Color.BLACK);

        //Creates the whereTo image (Default Selected)
        whereTo.setIcon(new ImageIcon(this.getClass().getResource("\\images\\whereTo_selected.png")));
        whereTo.setBounds(87, 224, 100, 72);
        add(whereTo);
        selectedItem = "whereTo";

        //Creates the tripComputer image
        tripComputer.setIcon(new ImageIcon(this.getClass().getResource("\\images\\tripComputer.png")));
        tripComputer.setBounds(182, 224, 90, 72);
        add(tripComputer);

        //Creates the map image
        map.setIcon(new ImageIcon(this.getClass().getResource("\\images\\map.png")));
        map.setBounds(87, 301, 90, 72);
        add(map);

        //Creates the speech image
        speech.setIcon(new ImageIcon(this.getClass().getResource("\\images\\speech.png")));
        speech.setBounds(182, 301, 90, 72);
        add(speech);

        //Creates the satellite image
        satellite.setIcon(new ImageIcon(this.getClass().getResource("\\images\\satellite.png")));
        satellite.setBounds(87, 378, 90, 72);
        add(satellite);

        //Creates the about image
        about.setIcon(new ImageIcon(this.getClass().getResource("\\images\\about.png")));
        about.setBounds(182, 378, 90, 72);
        add(about);
    }

    @Override
    void showScreen() {

    }

    @Override
    void plus() {
        try {
            switch (selectedItem) {
                case "whereTo": {
                    whereTo.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\whereTo.png"))));
                    tripComputer.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\tripComputer_selected.png"))));
                    selectedItem = "tripComputer";
                    break;
                }
                case "tripComputer": {
                    tripComputer.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\tripComputer.png"))));
                    map.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\map_selected.png"))));
                    selectedItem = "map";
                    break;
                }
                case "map": {
                    map.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\map.png"))));
                    speech.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\speech_selected.png"))));
                    selectedItem = "speech";
                    break;
                }
                case "speech": {
                    speech.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\speech.png"))));
                    satellite.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\satellite_selected.png"))));
                    selectedItem = "satellite";
                    break;
                }
                case "satellite": {
                    satellite.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\satellite.png"))));
                    about.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\about_selected.png"))));
                    selectedItem = "about";
                    break;
                }
                case "about": {
                    about.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\about.png"))));
                    whereTo.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\whereTo_selected.png"))));
                    selectedItem = "whereTo";
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void minus() {
        try {
            switch (selectedItem) {
                case "about": {
                    about.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\about.png"))));
                    satellite.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\satellite_selected.png"))));
                    selectedItem = "satellite";
                    break;
                }
                case "satellite": {
                    satellite.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\satellite.png"))));
                    speech.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\speech_selected.png"))));
                    selectedItem = "speech";
                    break;
                }
                case "speech": {
                    speech.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\speech.png"))));
                    map.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\map_selected.png"))));
                    selectedItem = "map";
                    break;
                }
                case "map": {
                    map.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\map.png"))));
                    tripComputer.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\tripComputer_selected.png"))));
                    selectedItem = "tripComputer";
                    break;
                }
                case "tripComputer": {
                    tripComputer.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\tripComputer.png"))));
                    whereTo.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\whereTo_selected.png"))));
                    selectedItem = "whereTo";
                    break;
                }
                case "whereTo": {
                    whereTo.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\whereTo.png"))));
                    about.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("\\images\\about_selected.png"))));
                    selectedItem = "about";
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                break;
            }
            case "tripComputer": {
                System.out.println("Trip Computer clicked");
                break;
            }
            case "map": {
                System.out.println("Map clicked");
                break;
            }
            case "speech": {
                System.out.println("Speech clicked");
                break;
            }
            case "satellite": {
                System.out.println("Satellite clicked");
                break;
            }
            case "about": {
                System.out.println("About clicked");
                break;
            }
        }
    }
}
