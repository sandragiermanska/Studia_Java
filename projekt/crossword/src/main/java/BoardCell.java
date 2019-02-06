public class BoardCell {

    // Horiz/Wert  Start/Inner/End
//    private boolean isPossible[][] = new boolean[2][3];
    private String content;

    public BoardCell(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

//    public void setIsPossible(int x, int y, Board board) {
//        if (x == 0) {
//            isPossible[Direction.HORIZ.getValue()][Position.INNER.getValue()] = false;
//            isPossible[Direction.HORIZ.getValue()][Position.END.getValue()] = false;
//        } else if (x == board.getWidth() - 1) {
//            isPossible[Direction.HORIZ.getValue()][Position.INNER.getValue()] = false;
//            isPossible[Direction.HORIZ.getValue()][Position.START.getValue()] = false;
//        }
//        else {
//
//        }
//    }
//
//    public boolean[][] getIsPossible() {
//        return isPossible;
//    }
}
