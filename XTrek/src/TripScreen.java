import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * The TripScreen class manages the TripComputer page in the XTrek.
 *
 * @author Gabriel Copat, 2018
 */
public class TripScreen extends Screen {
    //Singleton initialiser of TripScreen
    private static TripScreen tripInstance;

    private final int TEXT_WIDTH = 130;
    private final int TEXT_HEIGHT = 30;
    
    

    private double trip_odem = MapScreen.getDistance();
    private double duration = MapScreen.getDistance();
    
    private String odem = Double.toString(  Math.round(trip_odem * 100) / 100);
    private String time = Double.toString(  Math.round(duration * 100) / 100);
    private String speed = Double.toString( Math.round( (trip_odem/(duration*60)) * 100) / 100); //
    
    
    JLabel screen = new JLabel();
    JLabel j_odem = new JLabel(odem + " miles");
    JLabel j_speed = new JLabel(speed + " m/h");
    JLabel j_time = new JLabel(time + " mins");

    private TripScreen(ScreenManager sm) {
        super(sm);
        setLayout(null);

        switch (SpeechScreen.getLanguage()) {
        case "english":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreen.png")));
            break;
        case "french":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreenFrench.png")));
            break;
        case "german":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreenGerman.png")));
            break;
        case "italian":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreenItalian.png")));
            break;
        case "spanish":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreenSpanish.png")));
            break;
        default:
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreen.png")));
    }
        screen.setBounds(87, 224, 187, 232);
        
        j_odem.setBounds(117, 269, TEXT_WIDTH, TEXT_HEIGHT);
        
        j_speed.setBounds(117, 344, TEXT_WIDTH, TEXT_HEIGHT);
        
        j_time.setBounds(117, 416, TEXT_WIDTH, TEXT_HEIGHT);
    
        add(j_odem);
        add(j_speed);
        add(j_time);
        add(screen);
       


    }

    static TripScreen getInstance(){
        /*
         * Returns the single instance of TripScreen
         *
         * @return the instance of TripScreen
         */
        if(tripInstance == null){
            tripInstance = new TripScreen(sm);
        }
        return tripInstance;
    }

    @Override
    void plus() {

    }

    @Override
    void minus() {

    }

    @Override
    void select() {

    }

    void showScreen() {
    	trip_odem = MapScreen.getDistance();
    	duration = MapScreen.getDistance();
       
    	odem = Double.toString(  Math.round(trip_odem * 100) / 100);
        time = Double.toString(  Math.round(duration * 100) / 100);
        speed = Double.toString( Math.round( (trip_odem/(duration*60)) * 100) / 100); //
    	
    	switch (SpeechScreen.getLanguage()) {
        case "english":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreen.png")));
            break;
        case "french":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreenFrench.png")));
            break;
        case "german":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreenGerman.png")));
            break;
        case "italian":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreenItalian.png")));
            break;
        case "spanish":
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreenSpanish.png")));
            break;
        default:
        	screen.setIcon(new ImageIcon(getClass().getResource("/images/tripComputerScreen.png")));
    }
        screen.setBounds(87, 224, 187, 232);
        
        j_odem.setBounds(117, 269, TEXT_WIDTH, TEXT_HEIGHT);
        
        j_speed.setBounds(117, 344, TEXT_WIDTH, TEXT_HEIGHT);
        
        j_time.setBounds(117, 416, TEXT_WIDTH, TEXT_HEIGHT);
    
        add(j_odem);
        add(j_speed);
        add(j_time);
        add(screen);
    }


}
