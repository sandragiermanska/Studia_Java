public enum Position {

    START(0), INNER(1), END(2);

    private int value;

    Position(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
