package shape;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    @Override
    public void paint(Graphics arg0) {

        Circle circle = new Circle("koło", 100, 20,20);
        Rectangle rect = new Rectangle("prostokąt", 50, 100, 20, 200);
        Square square = new Square("kwadrat", 20, 200, 20);
        circle.draw(arg0);
        rect.draw(arg0);
        square.draw(arg0);
    }
}
