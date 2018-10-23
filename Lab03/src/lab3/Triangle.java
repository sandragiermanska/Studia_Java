package lab3;

public class Triangle extends Shape {

    int size;
    public Triangle(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void draw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size*2; j++) {
                if (j < size-i || j > size+i) {
                    System.out.print(" ");
                }
                else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}
