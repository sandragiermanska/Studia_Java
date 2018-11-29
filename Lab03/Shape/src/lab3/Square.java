package lab3;

public class Square extends Shape{
    private int size;
    public Square(String name, int size) {
        this.name = name;
        this.size = size;
    }
    @Override
    public void draw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("()");
            }
            System.out.println();
        }
    }
}
