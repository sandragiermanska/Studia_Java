package lab9;

public class Chopstick {
    private int priority;
    private boolean isOccupied;

    Chopstick(int priority) {
        this.priority = priority;
        isOccupied = false;
    }

    void makeOccupied() {
        isOccupied = true;
    }

    void makeFree() {
        isOccupied = false;
    }

    int getPriority() {
        return priority;
    }

    boolean isOccupied() {
        return isOccupied;
    }
}
