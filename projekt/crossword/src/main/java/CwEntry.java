public class CwEntry extends Entry {

    private int firstPosX;
    private int firstPosY;
    private Direction direction;
    private boolean visible = true;

    public CwEntry(String word, String clue) {
        super(word, clue);
    }

    public CwEntry(Entry entry, int x, int y, Direction d) {
        super(entry.getWord(), entry.getClue());
        firstPosX = x;
        firstPosY = y;
        direction = d;

    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setFirstPosX(int firstPosX) {
        this.firstPosX = firstPosX;
    }

    public void setFirstPosY(int firstPosY) {
        this.firstPosY = firstPosY;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getFirstPosX() {
        return firstPosX;
    }

    public int getFirstPosY() {
        return firstPosY;
    }

    public Direction getDirection() {
        return direction;
    }
}

