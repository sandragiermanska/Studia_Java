package shape;

import java.awt.*;

public class Square extends Shape{
    private int size, posX, posY;
    private String name;

    public Square(String name, int size, int posX, int posY) {
        this.name = name;
        this.size = size;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void draw(Graphics graphics) {

        Graphics g = graphics;
        g.drawRect(posX, posY, size, size);
    }
}
