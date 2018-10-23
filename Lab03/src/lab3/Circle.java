package lab3;

public class Circle extends Shape{

    int size;
    public Circle(String name, int size) {
        this.name = name;
        this.size = size;
    }
    
    @Override
    public void draw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size*2; j++) {
                if (j < size-i || j > size+i) {
                    System.out.print("  ");
                }
                else {
                    System.out.print("()");
                }
            }
            System.out.println();
        }
        for (int i = size; i >= 0; i--) {
            for (int j = 0; j < size*2+1; j++) {
                if (j < size-i || j > size+i) {
                    System.out.print("  ");
                }
                else {
                    System.out.print("()");
                }
            }
            System.out.println();
        }
    }
}
