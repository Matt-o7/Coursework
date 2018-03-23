import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;

/*
 * Speech generation using Microsoft Cognitive Services.
 *
 * See http://www.microsoft.com/cognitive-services/en-us/speech-api
 *
 * David Wakeling, 2018.
 *
 * Modified by Philippe Roubert, 2018.
 */
public class Speech {

    /*
     * Renew an access token --- they expire after 10 minutes.
     */
    static String renewAccessToken(String key1) {
        final String method = "POST";
        final String url =
                "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
        final byte[] body = {};
        final String[][] headers
                = {{"Ocp-Apim-Subscription-Key", key1}
                , {"Content-Length", String.valueOf(body.length)}
        };
        byte[] response = HttpConnect.httpConnect(method, url, headers, body);
        return new String(response);
    }

    /*
     * Synthesize speech.
     */
    static byte[] generateSpeech(String token, String text
            , String lang, String gender
            , String artist, String format) {
        final String method = "POST";
        final String url = "https://speech.platform.bing.com/synthesize";
        final byte[] body
                = ("<speak version='1.0' xml:lang='en-us'>"
                + "<voice xml:lang='" + lang + "' "
                + "xml:gender='" + gender + "' "
                + "name='Microsoft Server Speech Text to Speech Voice "
                + artist + "'>"
                + text
                + "</voice></speak>").getBytes();
        final String[][] headers
                = {{"Content-Type", "application/ssml+xml"}
                , {"Content-Length", String.valueOf(body.length)}
                , {"Authorization", "Bearer " + token}
                , {"X-Microsoft-OutputFormat", format}
        };
        byte[] response = HttpConnect.httpConnect(method, url, headers, body);
        return response;
    }
}
