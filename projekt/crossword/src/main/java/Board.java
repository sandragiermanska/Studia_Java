public class Board {

    protected BoardCell[][] board;

    public Board(int width, int height) {
        board = new BoardCell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new BoardCell(null);
            }
        }
    }

    public int getWidth() {
        return board.length;
    }

    public int getHeight() {
        return board[0].length;
    }

    public BoardCell getCell(int x, int y) {
        return board[x][y];
    }

    public void setCell(int x, int y, BoardCell c) {
        board[x][y] = c;
    }

    public String createPattern(int fromX, int fromY, int toX, int toY, Direction direction, Strategy s) throws Exception{
        String pattern;
        if(fromY == toY && direction == Direction.HORIZ) { //poziome
            int max = maxLengthBefore(fromX, fromY, direction, s);
            if (max == -1) return null;
            pattern = "^.{0,"+max+"}";

            for (int i = fromX; i <= toX; i++) {
                if (board[i][toY].getContent() == null) {
                    pattern += ".";
                } else {
                    pattern += board[i][toY].getContent();
                }
            }

            max = maxLengthAfter(toX,toY,direction, s);
            if (max == -1) return null;
            pattern += ".{0,"+max+"}$";

        } else if (fromX == toX && direction == Direction.VERT) { //pionowe
            int max = maxLengthBefore(fromY, fromX, direction, s);
            if (max == -1) return null;
            pattern = "^.{0," + max + "}";
            for (int i = fromY; i <= toY; i++) {
                if (board[toX][i].getContent() == null) {
                    pattern += ".";
                } else {
                    pattern += board[toX][i].getContent();
                }
            }
            max = maxLengthAfter(toY, toX, direction, s);
            if (max == -1) return null;

            pattern += ".{0," + max + "}$";
        } else {
            throw new Exception();
        }
        return pattern;
    }

    private int maxLengthBefore(int from, int numberOfLine, Direction direction, Strategy strategy) {
        int max = -1;
        if (direction == Direction.HORIZ) {
            for (int i = 0; i < from; i++) {
                if (strategy instanceof AdvancedStrategy &&
                        numberOfLine > 0 &&
                        board[i][numberOfLine - 1].getContent() == null && //czy nie ma nad
                        board[i][numberOfLine].getContent() == null && //czy tam nic nie ma
                        (numberOfLine == getHeight() || board[i][numberOfLine + 1].getContent() == null)) { //czy nie ma pod(o ile może być)
                    max++;
                } else if (strategy instanceof SimpleStrategy &&
                        board[i][numberOfLine].getContent() == null) { //czy tam nic nie ma
                    max++;
                } else {
                    max = -1; //liczymy od początku
                }
            }
        } else {
            for (int i = 0; i < from; i++) {
                if (strategy instanceof AdvancedStrategy &&
                        numberOfLine > 0 &&
                        board[numberOfLine - 1][i].getContent() == null && //czy nie ma z lewej
                        board[numberOfLine][i].getContent() == null && //czy tam nic nie ma
                        (numberOfLine == getWidth()|| board[numberOfLine + 1][i].getContent() == null)) { //czy nie ma z prawej(o ile może być)
                    max++;
                } else if (strategy instanceof SimpleStrategy &&
                        board[numberOfLine][i].getContent() == null) {
                    max++;
                } else {
                    max = -1; //liczymy od początku
                }
            }
        }
        return max;
    }

    private int maxLengthAfter(int to, int numberOfLine, Direction direction, Strategy strategy) {
        int max = 0;
        int width = getWidth();
        int height = getHeight();
        if (direction == Direction.HORIZ) {
            for (int i = to + 1; i < width; i++) {
                if (strategy instanceof AdvancedStrategy &&
                        numberOfLine > 0 &&
                        board[i][numberOfLine - 1].getContent() == null && //czy nie ma nad
                        board[i][numberOfLine].getContent() == null && //czy tam nic nie ma
                        (numberOfLine == getHeight() || board[i][numberOfLine + 1].getContent() == null)) { //czy nie ma pod(o ile może być)
                    max++;
                } else if (strategy instanceof SimpleStrategy &&
                        board[numberOfLine][i].getContent() == null) {
                    max++;
                } else {
                    break;
                }
            }
        } else {
            for (int i = to + 1; i < height; i++) {
                if (strategy instanceof AdvancedStrategy &&
                        numberOfLine > 0 &&
                        board[numberOfLine - 1][i].getContent() == null && //czy nie ma z lewej
                        board[numberOfLine][i].getContent() == null && //czy tam nic nie ma
                        (numberOfLine == getWidth() || board[numberOfLine + 1][i].getContent() == null)) { //czy nie ma z prawej(o ile może być)
                    max++;
                } else if (strategy instanceof SimpleStrategy &&
                        board[numberOfLine][i].getContent() == null) {
                    max++;
                } else {
                    break;
                }
            }
        }
        return max;
    }



}
