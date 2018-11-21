package shape;

import java.awt.*;

public class Triangle extends Shape {
    String name;
    int ax, ay, bx, by, cx, cy;
    public Triangle(String name, int ax, int ay, int bx, int by, int cx, int cy) {
        this.name = name;
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.cx = cx;
        this.cy = cy;
    }

    @Override
    public void draw(Graphics graphics) {

        Graphics g = graphics;
        int[] xPosTable = new int[] {ax, bx, cx};
        int[] yPosTable = new int[] {ay, by, cy};
        int n = 3;
        g.drawPolygon(xPosTable, yPosTable, n);
    }
}
