package lab2;

public class Main {

    public static void main(String[] args) {
        Punkt2D punkt1 = new Punkt2D(0,0);
        Punkt2D punkt2 = new Punkt2D(3,4);
        double dis = punkt1.distance(punkt2);
        System.out.println("dystans punktów 2D: " + dis);

        Punkt3D punkt3 = new Punkt3D(0,0,0);
        Punkt3D punkt4 = new Punkt3D(3,4,12);
        double dis2 = punkt3.distance(punkt4);
        System.out.println("dystans punktów 3D: " + dis2);
    }
}