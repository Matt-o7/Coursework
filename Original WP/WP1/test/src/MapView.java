import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MapView extends JFrame {

    final static String OUTPUT    = "output.png";  /* Ouput file        */
    final static String LATITUDE  = "50.7184";     /* Exeter, latitude  */
    final static String LONGITUDE = "-3.5339";     /* Exeter, longitude */
    final static String ZOOM      = "15";           /* 0 .. 21           */
    final static String SIZE      = "612x612";     /* Size              */

    static byte[] readData( String latitude
            , String longitude
            , String zoom
            , String size) {
        final String method = "GET";
        final String url
                = ( "https://maps.googleapis.com/maps/api/staticmap"
                + "?" + "center" + "=" + latitude + "," + longitude
                + "&" + "zoom"   + "=" + zoom
                + "&" + "size"   + "=" + size
        );
        final byte[] body
                = {};
        final String[][] headers
                = {};
        byte[] response = HttpConnect.httpConnect( method, url, headers, body );
        return response;
    }

    public static BufferedImage updateImage(final double lat,final double lon,int zoom, String size){
        String LAT = String.valueOf(lat);
        String LON = String.valueOf(lon);
        String ZOO = String.valueOf(zoom);

        final byte[] data = readData( LAT, LON, ZOO, size );
        BufferedImage img = null;
        try {
            img = ImageIO.read(new ByteArrayInputStream(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
//        writeData( OUTPUT, data );
    }

    /*
     * Write map data.
     */
    static void writeData( String file, byte[] data ) {
        try {
            OutputStream os = new FileOutputStream( file );
            os.write( data, 0, data.length );
            os.close();
        } catch ( IOException ex ) {
            ex.printStackTrace(); System.exit( 1 );
        }
    }

//    public static void main( String[] argv ) {
//        final byte[] data = readData( LATITUDE, LONGITUDE, ZOOM, SIZE );
//        writeData( OUTPUT, data );
//    }
}