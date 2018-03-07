import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class KeyboardScreen extends Screen {
	 char selected = 'A';
	 Screen currentScreen;
	 JLabel screen = new JLabel();
	 
	 public KeyboardScreen(ScreenManager sm) { 
		 super(sm);
	     setLayout(null);
	     setBackground(Color.BLACK);
	 }
	@Override
	void plus() {
		if(selected == ']' || selected == '%') {
    	}else{
	    	selected++;
	    	if (selected=='\\') selected = ']';
	    	if (selected==':') selected = '#';
	    	ImageIcon icon = new ImageIcon("res/images/backgroundAlphabet" + selected + ".png");
	    	icon.getImage().flush();
	    	screen.setIcon( icon );
	    	System.out.println("Plus Button Pressed");
	        currentScreen.plus();
    	}
	}
	@Override
	void minus() {
		if(selected == 'A' || selected == '1') {
    	}else{
	    	if (selected==']') selected = '\\';
	    	if (selected=='#') selected = ':';
	    	selected--;
	    	ImageIcon icon = new ImageIcon("res/images/backgroundAlphabet" + selected + ".png");
	    	icon.getImage().flush();
	    	screen.setIcon( icon );
	    	System.out.println("Minus Button Pressed");
	        currentScreen.menu();
    	}
	}
	
	@Override
	void menu() {
		
	}
	
	@Override
	void select() {
		System.out.print("Select Button Pressed on Button: ");
    	if (selected=='[') {
    		System.out.println("Space Bar");
    	}else if(selected==']') {
    		selected = 49; //ASCI for 1
        	ImageIcon icon = new ImageIcon("res/images/backgroundAlphabet" + selected + ".png");
        	icon.getImage().flush();
        	screen.setIcon( icon );;
    	}
    	System.out.println(selected);
        currentScreen.select();
    }
	void showScreen() {
		
	}
	
	
	
}
