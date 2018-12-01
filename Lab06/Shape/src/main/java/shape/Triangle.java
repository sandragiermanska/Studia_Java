package shape;

import java.awt.*;

public class Triangle extends Shape {
    private Point leftUpPoint, rightUpPoint, leftDownPoint;

    public Triangle(String name, int ax, int ay, int bx, int by, int cx, int cy) {
        this.name = name;
        this.leftUpPoint = new Point(ax, ay);
        this.rightUpPoint = new Point(bx, by);
        this.leftDownPoint = new Point(cx, cy);
    }

    @Override
    public void draw(Graphics graphics) {

        Graphics g = graphics;
        int[] xPosTable = new int[] {leftUpPoint.x, rightUpPoint.x, leftDownPoint.x};
        int[] yPosTable = new int[] {leftUpPoint.y, rightUpPoint.y, leftDownPoint.y};
        int n = 3;
        g.setColor(Color.PINK);
        g.fillPolygon(xPosTable, yPosTable, n);
    }

    public boolean isInShape(Point cursor) {
        boolean result = false;
        int a = (rightUpPoint.y - leftDownPoint.y) / (rightUpPoint.x - leftDownPoint.x);
        int b = (rightUpPoint.x*leftDownPoint.y - leftDownPoint.x*rightUpPoint.y)
                / (rightUpPoint.x - leftDownPoint.x);
        if (cursor.x >= leftUpPoint.x && cursor.y >= leftUpPoint.y &&
        cursor.y <= a * cursor.x + b) {
            result = true;
        }
        return result;
    }

    public void move(int xMove, int yMove) {
        rightUpPoint.x += xMove;
        leftDownPoint.x += xMove;
        leftUpPoint.x += xMove;
        rightUpPoint.y += yMove;
        leftDownPoint.y += yMove;
        leftUpPoint.y += yMove;
    }

}
