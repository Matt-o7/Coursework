import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapPanel extends Screen {
    private BufferedImage img;
    JLabel label;
    int zoom = 12;
    double lat = 50.737730, lon = -3.532626;

    public MapPanel(ScreenManager sm) {
        super(sm);
        img = MapView.updateImage(lat, lon, zoom, "370x635");
        setBackground(new Color(163,204,255));
        label = new JLabel(new ImageIcon(img));
        add(label);
    }

    @Override
    void showScreen() {
        img = MapView.updateImage(lat, lon, zoom, "370x635");
        label.setIcon(new ImageIcon(img));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval((int) g.getClipBounds().getWidth() / 2 - 5, (int) g.getClipBounds().getHeight() / 2 + 20, 10, 10);
    }

    @Override
    public void plus() {
        if(zoom >= 21){
            zoom = 21;
            return;
        }
        System.out.println("Map zooming in " + zoom);
        zoom++;
        img = MapView.updateImage(lat, lon, zoom, "370x635");
        label.setIcon(new ImageIcon(img));
    }

    @Override
    public void minus() {
        if(zoom < 1){
            zoom = 1;
            return;
        }
        System.out.println("Map zooming out");
        zoom--;
        img = MapView.updateImage(lat, lon, zoom, "370x635");
        label.setIcon(new ImageIcon(img));
    }

    @Override
    public void menu() {
        sm.changeCurrentScreen(sm.tm);
    }

    @Override
    public void select() {

    }

}
