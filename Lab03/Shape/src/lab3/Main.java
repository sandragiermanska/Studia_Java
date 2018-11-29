package lab3;

public class Main {

    public static void main(String[] args) {

        Rectangle rectangle = new Rectangle("prostokąt", 5, 20);
        Circle circle = new Circle("koło", 5);
        Square square = new Square("kwadrat", 5);
        Triangle triangle = new Triangle("trójkąt", 5);

        rectangle.draw();
        circle.draw();
        square.draw();
        triangle.draw();
    }
}
