package shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle extends Shape{

    int size;
    String name;
    int posX;
    int posY;

    public Circle(String name, int size, int posX, int posY) {
        this.name = name;
        this.size = size;
        this.posX = posX;
        this.posY = posY;
    }
    
    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        Ellipse2D e = new Ellipse2D.Double(posX, posY, size, size);
        graphics2D.draw(e);

        GradientPaint gp = new GradientPaint(posX-size/2, posY-size/2, Color.red,posX+size/2, posY+size/2, Color.blue, false);
        // Fill with a gradient.
        graphics2D.setPaint(gp);
        graphics2D.fill(e);

        graphics2D.draw(e);

    }
}
