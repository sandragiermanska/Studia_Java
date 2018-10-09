package lab2;

/**
 * Created by student on 2018-10-09.
 */
public class Punkt3D extends Punkt2D {
    protected double z;

    public Punkt3D (double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ () {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double distance (Punkt3D punkt) {
        return Math.sqrt(Math.pow(x - punkt.getX(),2) + Math.pow(y - punkt.getY(),2) + Math.pow(z - punkt.getZ(),2));
    }
    public void print () {
        System.out.println("("+x+","+y+","+z+")");
    }

}