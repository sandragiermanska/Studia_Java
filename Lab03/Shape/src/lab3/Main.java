package lab3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArrayList<Shape> shapes = new ArrayList<Shape>();
        boolean end = false;
        int option = 0;

        while (!end) try {
            System.out.println("1\tDodaj kwadrat");
            System.out.println("2\tDodaj prostokąt");
            System.out.println("3\tDodaj trójkąt");
            System.out.println("4\tDodaj koło");
            System.out.println("5\tRysuj");
            System.out.println("6\tWyjdź");

            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);

            option = Integer.parseInt(bfr.readLine());
            String name;
            int x, y;


            switch (option) {
                case 1:
                    System.out.println("Podaj nazwę");
                    name = bfr.readLine();
                    System.out.println("Podaj szerokość");
                    x = Integer.parseInt(bfr.readLine());
                    shapes.add(new Square(name, x));
                    break;
                case 2:
                    System.out.println("Podaj nazwę");
                    name = bfr.readLine();
                    System.out.println("Podaj szerokość");
                    x = Integer.parseInt(bfr.readLine());
                    System.out.println("Podaj wysokość");
                    y = Integer.parseInt(bfr.readLine());
                    shapes.add(new Rectangle(name, y, x));
                    break;
                case 3:
                    System.out.println("Podaj nazwę");
                    name = bfr.readLine();
                    System.out.println("Podaj wysokość");
                    x = Integer.parseInt(bfr.readLine());
                    shapes.add(new Triangle(name, x));
                    break;
                case 4:
                    System.out.println("Podaj nazwę");
                    name = bfr.readLine();
                    System.out.println("Podaj długość promienia");
                    x = Integer.parseInt(bfr.readLine());
                    shapes.add(new Circle(name, x));
                    break;
                case 5:
                    for (int i = 0; i < shapes.size(); i++) {
                        System.out.println(shapes.get(i).name);
                        shapes.get(i).draw();
                        System.out.println();
                    }
                    break;
                case 6:
                    end = true;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }

//        Rectangle rectangle = new Rectangle("prostokąt", 5, 20);
//        Circle circle = new Circle("koło", 5);
//        Square square = new Square("kwadrat", 5);
//        Triangle triangle = new Triangle("trójkąt", 5);

//        rectangle.draw();
//        circle.draw();
//        square.draw();
//        triangle.draw();
    }
}
