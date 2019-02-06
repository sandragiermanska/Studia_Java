public abstract class Strategy {

    public abstract CwEntry findEntry(Crossword cw) throws Exception;

    public void updateBoard(Board b, CwEntry e) {
        String word = e.getWord();
        if (e.getDirection() == Direction.HORIZ) {
            for (int i = 0; i < word.length(); i++) {
                BoardCell cell = new BoardCell(String.valueOf(word.charAt(i)));
                b.setCell(e.getFirstPosX() + i, e.getFirstPosY(), cell);
            }
        } else {
            for (int i = 0; i < word.length(); i++) {
                BoardCell cell = new BoardCell(String.valueOf(word.charAt(i)));
                b.setCell(e.getFirstPosX(), e.getFirstPosY() + i, cell);
            }
        }
    }

}
