public enum Direction {
    HORIZ(0), VERT(1);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}