public class App {

    public static void main(String[] args) {
        InteliCrosswordDB db = new InteliCrosswordDB("C:\\Users\\Sandra\\Desktop\\Studia\\III semestr\\PO_Java\\Repo\\projekt\\crossword\\src\\main\\resources\\cwdb.txt");
        Crossword crossword = new Crossword(30,30,db);
        try {
            crossword.generate(new AdvancedStrategy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Board board = crossword.getBoardCopy();
        printBoard(board);
    }

    public static void printBoard(Board board) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getCell(j,i).getContent() != null) System.out.print(board.getCell(j,i).getContent());
                else System.out.print("_");
            }
            System.out.println();
        }
    }

}
