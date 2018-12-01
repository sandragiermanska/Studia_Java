package shape;

import java.awt.*;

public class Square extends Shape{
    private int size;
    private Point leftUpPoint;

    public Square(String name, int size, int posX, int posY) {
        this.name = name;
        this.size = size;
        this.leftUpPoint = new Point(posX, posY);
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics g = graphics;
        g.setColor(Color.RED);
        g.fillRect(leftUpPoint.x, leftUpPoint.y, size, size);
    }

    public boolean isInShape(Point cursor) {
        boolean result = false;
        if (cursor.x >= leftUpPoint.x && cursor.x <= leftUpPoint.x + size &&
                cursor.y >= leftUpPoint.y && cursor.y <= leftUpPoint.y + size) {
            result = true;
        }
            return result;
    }

    public void move(int xMove, int yMove) {
        leftUpPoint.x += xMove;
        leftUpPoint.y += yMove;
    }
}
