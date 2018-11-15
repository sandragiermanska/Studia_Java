package shape;

import java.awt.*;

public class Triangle extends Shape {

    int size, posX, posY;
    public Triangle(String name, int size, int posX, int posY) {
        this.name = name;
        this.size = size;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void draw(Graphics graphics) {

        Graphics g = graphics;
        int[] xPosTable = new int[] {posX};
        int[] yPosTable = new int[] {posY};
        int n = 3;
        g.drawPolygon(xPosTable, yPosTable, n);
    }
}
