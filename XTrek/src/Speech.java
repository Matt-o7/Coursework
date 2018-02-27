import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
/*
 * Speech generation using Microsoft Cognitive Services.
 *
 * See http://www.microsoft.com/cognitive-services/en-us/speech-api
 *
 * David Wakeling, 2018.
 */
public class Speech {
  final static String KEY1 = "bc5d1a3f91ab43208f162ed2d2dd799c";
  final static String KEY2 = "e0d253267c6248ce875443df85049dd4";

  final static String TEXT   = "English";
  final static String LANG   = "en-US";
  final static String GENDER = "Female";
  final static String ARTIST = "(en-GB, Susan, Apollo)";
  // final static String ARTIST = "(en-AU, HayleyRUS)";
  // final static String ARTIST = "(en-CA, Linda)";
  // final static String ARTIST = "(en-US, ZiraRUS)";
  final static String OUTPUT = "output.wav";
  final static String FORMAT = "riff-16khz-16bit-mono-pcm";

  /*
   * Renew an access token --- they expire after 10 minutes.
   */
  static String renewAccessToken( String key1 ) {
    final String method = "POST";
    final String url = 
      "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
    final byte[] body = {}; 
    final String[][] headers
      = { { "Ocp-Apim-Subscription-Key", key1                          }
        , { "Content-Length"           , String.valueOf( body.length ) }
        };
    byte[] response = HttpConnect.httpConnect( method, url, headers, body );
    return new String( response ); 
  }

  /*
   * Synthesize speech.
   */
  static byte[] generateSpeech( String token,  String text
                              , String lang,   String gender
                              , String artist, String format ) {
    final String method = "POST";
    final String url = "https://speech.platform.bing.com/synthesize";
    final byte[] body
      = ( "<speak version='1.0' xml:lang='en-us'>"
        + "<voice xml:lang='" + lang   + "' "
        + "xml:gender='"      + gender + "' "
        + "name='Microsoft Server Speech Text to Speech Voice "
        + artist + "'>"
        + text
        + "</voice></speak>" ).getBytes(); 
    final String[][] headers
      = { { "Content-Type"             , "application/ssml+xml"        }
        , { "Content-Length"           , String.valueOf( body.length ) }
        , { "Authorization"            , "Bearer " + token             }
        , { "X-Microsoft-OutputFormat" , format                        }
        };
    byte[] response = HttpConnect.httpConnect( method, url, headers, body );
    return response;
  } 

  /*
   * Write data to file.
   */
  static void writeData( byte[] buffer, String name ) {
    try {
      File             file = new File( name );
      FileOutputStream fos  = new FileOutputStream( file );
      DataOutputStream dos  = new DataOutputStream( fos ); 
      dos.write( buffer );
      dos.flush();
      dos.close();
    } catch ( Exception ex ) {
      System.out.println( ex ); System.exit( 1 ); return;
    }
  }

  /*
   * Generate speech.
   */
  public static void main( String[] argv ) {
    final String token  = renewAccessToken( KEY1 );
    final byte[] speech = generateSpeech( token,  TEXT,   LANG
                                        , GENDER, ARTIST, FORMAT );
    writeData( speech, OUTPUT );
  }
}
