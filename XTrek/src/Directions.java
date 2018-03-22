import java.net.URLEncoder;

/*
 * Directions using the Google Maps APIs.
 *
 * See https://developers.google.com/maps/documentation/directions/intro
 *
 * David Wakeling, 2018.
 */
public class Directions {
    final static String ORIGIN = "The Forum, Exeter University";
    final static String DESTINATION = "Cathedral Green, Exeter";
    final static String REGION = "uk";
    final static String MODE = "walking"; /* "driving" */

    /*
     * Read directions.
     */
    static byte[] readDirections(String origin
            , String destination
            , String region
            , String mode) {
        try {
            final String encDestination = URLEncoder.encode(DESTINATION, "UTF-8");
            final String method = "GET";
            final String lang;
            switch (SpeechScreen.getLanguage()) {
                case "english":
                    lang = "en";
                    break;
                case "french":
                    lang = "fr";
                    break;
                case "german":
                    lang = "de";
                    break;
                case "italian":
                    lang = "it";
                    break;
                case "spanish":
                    lang = "es";
                    break;
                default:
                    lang = "en";
            }
            final String url
                    = ("https://maps.googleapis.com/maps/api/directions/json"
                    + "?" + "origin" + "=" + origin
                    + "&" + "destination" + "=" + encDestination
                    + "&" + "region" + "=" + region
                    + "&" + "mode" + "=" + mode
                    + "&" + "key" + "=" + "AIzaSyDDbfI4zefWjAZ5NpNdFLGPhbw1YJjKVIo"
                    + "&language=" + lang
            );
            final byte[] body
                    = {};
            final String[][] headers
                    = {};
            byte[] response = HttpConnect.httpConnect(method, url, headers, body);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
