package shape;

import java.awt.*;

public class Rectangle extends Shape {
    private int width, height;
    private Point leftUpPoint;

    public Rectangle(String name, int a, int b, int posX, int posY) {
        this.name = name;
        this.width = a;
        this.height = b;
        this.leftUpPoint = new Point(posX, posY);
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics g = graphics;
        g.setColor(Color.GREEN);
        g.fillRect(leftUpPoint.x, leftUpPoint.y, width, height);
    }

    public boolean isInShape(Point cursor) {
        boolean result = false;
        if (cursor.x >= leftUpPoint.x && cursor.x <= leftUpPoint.x + width &&
                cursor.y >= leftUpPoint.y && cursor.y <= leftUpPoint.y + height) {
            result = true;
        }
        return result;
    }

    public void move(int xMove, int yMove) {
        leftUpPoint.x += xMove;
        leftUpPoint.y += yMove;
    }
}
