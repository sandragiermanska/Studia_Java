package lab3;

public class Rectangle extends Shape {
    int a,b;
    public Rectangle(String name, int a, int b) {
        this.name = name;
        this.a = a;
        this.b = b;
    }
    @Override
    public void draw() {
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }
}
