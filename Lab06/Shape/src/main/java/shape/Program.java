package shape;

import javax.swing.*;

public class Program extends JFrame {

    private MyPanel panel = new MyPanel();

    public static void main(String [] argv) {
        new Program();
    }

    public Program() {
        super("Shape");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
    }
}
