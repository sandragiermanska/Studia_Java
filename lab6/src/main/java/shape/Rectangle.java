package shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Rectangle extends Shape {
    int a,b,posX,posY;
    String name;
    public Rectangle(String name, int a, int b, int posX, int posY) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void draw(Graphics graphics) {

        Graphics g = graphics;
        g.drawRect(posX, posY, a, b);
    }
}
