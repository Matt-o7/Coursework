import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MapView extends JFrame {

    static byte[] readData(String latitude
            , String longitude
            , String zoom
            , String size
            , String path) {
        final String method = "GET";
        final String url
                = ("https://maps.googleapis.com/maps/api/staticmap"
                + "?" + "center" + "=" + latitude + "," + longitude
                + "&" + "zoom" + "=" + zoom
                + "&" + "size" + "=" + size
                + "&" + "path" + "=enc%3A" + path
        );
        final byte[] body
                = {};
        final String[][] headers
                = {};
        byte[] response = HttpConnect.httpConnect(method, url, headers, body);
        return response;
    }

    public static BufferedImage updateImage(final double lat, final double lon, int zoom, String size, String path) {
        String LAT = String.valueOf(lat);
        String LON = String.valueOf(lon);
        String ZOO = String.valueOf(zoom);

        final byte[] data = readData(LAT, LON, ZOO, size, path);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}