import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

class SatelliteScreen extends Screen {

    private JTextArea textArea;

    SatelliteScreen(ScreenManager sm) {
        super(sm);
    }

    @Override
    void showScreen() {
        textArea = new JTextArea(0, 1);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord (false);
        textArea.setEditable(false);
        textArea.setBounds(88,225,183,224);
        add(textArea);

        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);

        printLog();
    }

    private void printLog() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long start = System.currentTimeMillis();
                    long end = start + 60*100; // 60 seconds * 1000 ms/sec
                    while (System.currentTimeMillis() < end) {
                        OSXUblox7.reader1("/dev/cu.usbmodem1411");
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    void plus() {

    }

    @Override
    void minus() {

    }

    @Override
    void menu() {
        sm.changeCurrentScreen(sm.menu);
    }

    @Override
    void select() {

    }


}

class CustomOutputStream extends OutputStream {
    private JTextArea textArea;

    CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}


