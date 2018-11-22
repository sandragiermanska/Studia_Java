package shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


public class MyPanel extends JPanel implements MouseListener, MouseMotionListener {

    private Circle circle;
    private Rectangle rect;
    private Square square;
    private Triangle triangle;

    private Point pointWhenWasPressed = new Point();

    public MyPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        circle = new Circle("koło", 100, 20,20);
        rect = new Rectangle("prostokąt", 50, 100, 20, 200);
        square = new Square("kwadrat", 20, 200, 20);
        triangle = new Triangle("trójkąt", 200,200,300,200,200,300);
    }

    @Override
    public void paint(Graphics arg0) {
        super.paint(arg0);
        circle.draw(arg0);
        rect.draw(arg0);
        square.draw(arg0);
        triangle.draw(arg0);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int moveX, moveY;
        if(triangle.isInShape(pointWhenWasPressed)) {
            moveX = e.getX() - pointWhenWasPressed.x;
            moveY = e.getY() - pointWhenWasPressed.y;
            triangle.move(moveX, moveY);
            repaint();
        }
        else if(circle.isInShape(pointWhenWasPressed)) {
            moveX = e.getX() - pointWhenWasPressed.x;
            moveY = e.getY() - pointWhenWasPressed.y;
            circle.move(moveX, moveY);
            repaint();
        }
        else if(square.isInShape(pointWhenWasPressed)) {
            moveX = e.getX() - pointWhenWasPressed.x;
            moveY = e.getY() - pointWhenWasPressed.y;
            square.move(moveX, moveY);
            repaint();
        }
        else if(rect.isInShape(pointWhenWasPressed)) {
            moveX = e.getX() - pointWhenWasPressed.x;
            moveY = e.getY() - pointWhenWasPressed.y;
            rect.move(moveX, moveY);
            repaint();
        }
        pointWhenWasPressed.setLocation(e.getX(), e.getY());

    }

    @Override
    public void mouseMoved(MouseEvent e) { }
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        pointWhenWasPressed.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) { }
}
