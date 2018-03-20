import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Dennis Harrop
 */
public class MapScreen extends Screen {
    private static MapScreen ms;

    private BufferedImage img;
    JLabel label;
    int zoom = 9;
    double lat = 50.735459, lon = -3.533207;
    int rot;
    String path;
    Thread test;

    private final int CENTER_X;
    private final int CENTER_Y;
    private final int DOT_DIAM = 10;
    private final int DOT_OFFSET_Y = 15;

    Location prev, cur;

    private static JSONParser parser = new JSONParser();

    static MapScreen getInstance() {
        //If the instance has not been created yet, then create a new one
        if (ms == null) {
            ms = new MapScreen(sm);
        }
        return ms;
    }

    ScheduledExecutorService executor;

    private MapScreen(ScreenManager sm) {
        super(sm);
        img = MapView.updateImage(lat, lon, zoom, "370x635", path);
        setBackground(new Color(163, 204, 255));
        label = new JLabel(new ImageIcon(img));
        add(label);
        CENTER_X = sm.getWidth() / 2;
        CENTER_Y = sm.getHeight() / 2;
    }

    Runnable updateMap = new Runnable() {
        @Override
        public void run() {
            if (prev.lat == cur.lat && prev.lng == cur.lng)
                return;
            img = MapView.updateImage(lat, lon, zoom, "370x635", path);
            label.setIcon(new ImageIcon(img));
            sm.repaint();
        }
    };

    @Override
    void showScreen() {
        img = MapView.updateImage(lat, lon, zoom, "370x635", path);
        label.setIcon(new ImageIcon(img));

        ArrayList<Step> steps = getSteps(Directions.ORIGIN, Directions.DESTINATION, Directions.REGION, Directions.MODE);
        path = getPolyLine(steps);
        img = MapView.updateImage(lat, lon, zoom, "370x635", path);

        // Testing direction speech output
        test = new Thread(() -> SpeechScreen.generateSpeechSound(steps.get(0).instruction, "english"));
        test.start();

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(updateMap, 0, 3, TimeUnit.SECONDS);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(CENTER_X - DOT_DIAM / 2, CENTER_Y + DOT_OFFSET_Y, DOT_DIAM, DOT_DIAM);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int w2 = CENTER_X + DOT_DIAM / 2;
        int h2 = CENTER_Y + DOT_OFFSET_Y;
        g2d.rotate(Math.toRadians(rot), w2, h2);
        super.paintComponent(g);
    }

    @Override
    public void plus() {
        if (zoom >= 21) {
            zoom = 21;
            return;
        }
        zoom++;
        img = MapView.updateImage(lat, lon, zoom, "370x635", path);
        label.setIcon(new ImageIcon(img));
    }

    @Override
    public void minus() {
        if (zoom < 1) {
            zoom = 1;
            return;
        }

        zoom--;
        img = MapView.updateImage(lat, lon, zoom, "370x635", path);
        label.setIcon(new ImageIcon(img));
    }

    @Override
    public void menu() {
        sm.changeCurrentScreen(MenuScreen.getInstance());
        executor.shutdown();
    }

    @Override
    public void select() {

    }

    @Override
    void onOff() {
        super.onOff();
        executor.shutdown();
    }

    public static ArrayList<Step> getSteps(String origin, String dest, String region, String mode) {
        String s = new String(Directions.readDirections(origin, dest, region, mode));

        ArrayList<Step> steps = new ArrayList<>();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = obj;
        JSONArray arr = (JSONArray) ((JSONObject) ((JSONArray) ((JSONObject) ((JSONArray) jsonObject.get("routes")).get(0)).get("legs")).get(0)).get("steps");

        String overview_polyline = (String) ((JSONObject) ((JSONObject) ((JSONArray) jsonObject.get("routes")).get(0)).get("overview_polyline")).get("points");
        int size = arr.size();

        for (int i = 0; i < size; i++) {
            JSONObject o = (JSONObject) arr.get(i);
            //Blah blah blah...
            JSONObject start_loc = (JSONObject) o.get("start_location");
            double s_lng = (double) start_loc.get("lng");
            double s_lat = (double) start_loc.get("lat");

            JSONObject end_loc = (JSONObject) o.get("start_location");
            double e_lng = (double) end_loc.get("lng");
            double e_lat = (double) end_loc.get("lat");

            String instructions = (String) o.get("html_instructions");
            Step step = new Step(s_lat, s_lng, e_lat, e_lng, instructions, overview_polyline);
            steps.add(step);

        }
        return steps;
    }

    public static String getPolyLine(ArrayList<Step> steps) {
        return steps.get(0).polyline;
    }
}

class Step {
    Location start_location;
    String instruction;
    String polyline;
    Location end_location;

    public Step(double s_lat, double s_lng, double e_lat, double e_lng, String instruction, String polyline) {
        start_location = new Location(s_lat, s_lng);
        end_location = new Location(e_lat, e_lng);
        this.instruction = instruction.replaceAll("\\<.*?>", "");
        this.polyline = polyline;
    }

    @Override
    public String toString() {
        return "Step{" +
                "start_location=" + start_location +
                ", instruction='" + instruction + '\'' +
                ", polyline='" + polyline + '\'' +
                ", end_location=" + end_location +
                '}';
    }
}

class Location {
    double lat, lng;

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}