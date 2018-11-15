package shape;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Program extends JFrame {

    public static void main(String [] argv) {
        Program program = new Program();
        MyPanel panel = new MyPanel();
        program.setSize(450, 450);
        program.setVisible(true);
        program.add(panel);

        program.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });

    }

}
