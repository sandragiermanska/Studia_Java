package lab2;
/**
 * Created by student on 2018-10-09.
 */
public class Punkt2D {
    protected double x, y;

    public Punkt2D (double x_, double y_) {
        x = x_;
        y = y_;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance (Punkt2D punkt) {
        return Math.sqrt(Math.pow(x - punkt.getX(),2) + Math.pow(y - punkt.getY(),2));
    }
}