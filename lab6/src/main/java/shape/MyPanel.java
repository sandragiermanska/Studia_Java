package shape;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    Circle circle;
    Rectangle rect;
    Square square;
    Triangle triangle;

    MyPanel() {
        circle = new Circle("koło", 100, 20,20);
        rect = new Rectangle("prostokąt", 50, 100, 20, 200);
        square = new Square("kwadrat", 20, 200, 20);
        triangle = new Triangle("trójkąt", 200,200,400,200,300,300);
    }

    @Override
    public void paint(Graphics arg0) {
        circle.draw(arg0);
        rect.draw(arg0);
        square.draw(arg0);
        triangle.draw(arg0);
    }
}
