import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Dennis Harrop
 */
public class MapScreen extends Screen implements KeyListener {
    private static MapScreen ms;

    private BufferedImage img;
    JLabel label;
    int zoom = 9;
    double lat = 50.7371369, lng = -3.5351475;
    Location prev = new Location(lat, lng);
    int rot;
    String path;
    Thread test;

    private final int CENTER_X;
    private final int CENTER_Y;
    private final int DOT_DIAM = 10;
    private final int DOT_OFFSET_Y = 15;

    public ArrayList<Step> steps = new ArrayList<>();

    private static JSONParser parser = new JSONParser();

    static MapScreen getInstance() {
        //If the instance has not been created yet, then create a new one
        if (ms == null) {
            ms = new MapScreen(sm);
        }
        return ms;
    }

    ScheduledExecutorService executor;

    public void setDestination() {
        steps = getSteps(lat + "," + lng, KeyboardScreen.getInstance().output, Directions.REGION, Directions.MODE);
        path = getPolyLine(steps);
        img = MapView.updateImage(lat, lng, zoom, "370x635", path);
        System.out.println("Distance: " + getDistance() + " miles\nDuration: " + getDuration() + " mins");

    }

    private MapScreen(ScreenManager sm) {
        super(sm);
        img = MapView.updateImage(lat, lng, zoom, "370x635", path);
        setBackground(new Color(163, 204, 255));
        label = new JLabel(new ImageIcon(img));
        add(label);
        CENTER_X = sm.getWidth() / 2;
        CENTER_Y = sm.getHeight() / 2;

        sm.addKeyListener(this);
        sm.setFocusable(true);
        sm.requestFocus();
    }

    Runnable updateMap = new Runnable() {
        @Override
        public void run() {
            if (prev.lat == lat && prev.lng == lng) {
                return;
            } else {
//                updateRot();
                prev.lng = lng;
                prev.lat = lat;
            }
            if (SatelliteScreen.getInstance().positionGeo.size() >= 4) {
                lng = Double.valueOf(SatelliteScreen.getInstance().positionGeo.get(1));
                if (!SatelliteScreen.getInstance().positionGeo.get(0).toUpperCase().equals("NORTH"))
                    lng *= -1;
                lat = Double.valueOf(SatelliteScreen.getInstance().positionGeo.get(3));
                if (!SatelliteScreen.getInstance().positionGeo.get(2).toUpperCase().equals("EAST"))
                    lng *= -1;
            } else {
//                lng = 0;
//                lat = 0;
            }
            img = MapView.updateImage(lat, lng, zoom, "370x635", path);
            label.setIcon(new ImageIcon(img));
            for (Step s : steps) {
                double x1 = s.start_location.lng;
                double x2 = lng;

                double y1 = s.start_location.lat;
                double y2 = lat;

                double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));


                if (distance < 0.0005 && !s.said) {
                    test = new Thread(() -> SpeechScreen.generateSpeechSound(s.instruction, SpeechScreen.getLanguage()));
                    test.start();
                    s.said = true;
                    break;
                }

            }
            sm.repaint();
        }
    };

    private void updateRot() {
        Location cur = new Location(lat, lng);
        double dx = cur.lat - prev.lat;
        double dy = prev.lng - cur.lng;

        rot = (int) Math.toDegrees(Math.atan2(dy, dx));
    }

    @Override
    void showScreen() {
        img = MapView.updateImage(lat, lng, zoom, "370x635", path);
        label.setIcon(new ImageIcon(img));

        if (KeyboardScreen.getInstance().output != "")
            setDestination();

        // Testing direction speech output
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(updateMap, 0, 1, TimeUnit.SECONDS);

        addKeyListener(this);
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
        img = MapView.updateImage(lat, lng, zoom, "370x635", path);
        label.setIcon(new ImageIcon(img));
        sm.requestFocus();
    }

    @Override
    public void minus() {
        if (zoom < 1) {
            zoom = 1;
            return;
        }

        zoom--;
        img = MapView.updateImage(lat, lng, zoom, "370x635", path);
        label.setIcon(new ImageIcon(img));
        sm.requestFocus();
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

            JSONObject start_loc = (JSONObject) o.get("start_location");
            double s_lng = (double) start_loc.get("lng");
            double s_lat = (double) start_loc.get("lat");

            JSONObject end_loc = (JSONObject) o.get("start_location");
            double e_lng = (double) end_loc.get("lng");
            double e_lat = (double) end_loc.get("lat");

            long distance = (long) ((JSONObject) o.get("distance")).get("value");
            int duration = Integer.valueOf(((JSONObject) o.get("distance")).get("value").toString());
            String instructions = (String) o.get("html_instructions");

            Step step = new Step(s_lat, s_lng, e_lat, e_lng, instructions, overview_polyline, distance, duration);
            steps.add(step);

        }
        return steps;
    }

    public static String getPolyLine(ArrayList<Step> steps) {
        return steps.get(0).polyline;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            lat += 0.0001;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            lat -= 0.0001;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            lng -= 0.0001;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            lng += 0.0001;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public double getDistance() {
        long temp = 0;
        for (Step s : steps) temp += s.distance;
        return temp * 0.00062137;
    }

    public double getDuration() {
        long temp = 0;
        for (Step s : steps) temp += s.duration;
        return temp / 60;
    }
}

class Step {
    Location start_location;
    String instruction;
    String polyline;
    long distance;
    int duration;
    Location end_location;
    boolean said = false;

    public Step(double s_lat, double s_lng, double e_lat, double e_lng, String instruction, String polyline, long distance, int duration) {
        start_location = new Location(s_lat, s_lng);
        end_location = new Location(e_lat, e_lng);
        this.instruction = instruction.replaceAll("\\<.*?>", "");
        this.polyline = polyline;
        this.distance = distance;
        this.duration = duration;
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
    public boolean equals(Object obj) {
        return (lat == ((Location) obj).lat && lng == ((Location) obj).lng);
    }
}