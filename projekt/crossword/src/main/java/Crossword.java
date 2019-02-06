import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;


public class Crossword {

    private LinkedList<CwEntry> entries;
    private Board b;
    private InteliCrosswordDB cwdb;
    private final long ID = -1;

    Crossword(int width, int height, InteliCrosswordDB cwdb) {
        entries = new LinkedList<>();
        b = new Board(width, height);
        this.cwdb = cwdb;
    }

    public Iterator<CwEntry> getROEntryIter() {
        return Collections.unmodifiableList(entries).iterator();
    }

    public Board getBoardCopy() {
        Board newBoard = new Board(b.getWidth(), b.getHeight());
        newBoard.board = b.board.clone();
        return newBoard;
    }

    public InteliCrosswordDB getCwDB() {
        return cwdb;
    }

    public CwEntry getEntry(int index) {
        return entries.get(index);
    }

    public void setCwDB(InteliCrosswordDB cwdb) {
        this.cwdb = cwdb;
    }

    public boolean contains(String word) {
        for (Entry entry : entries) {
            if (entry.getWord().equals(word)) {
                return true;
            }
        }
        return false;
    }

    public final void addCrosswordEntry(CwEntry cwEntry, Strategy s) {
        entries.add(cwEntry);
        s.updateBoard(b, cwEntry);
    }

    public final void generate(Strategy s) throws Exception {
        CwEntry e = null;
        while((e = s.findEntry(this)) != null) {
            addCrosswordEntry(e, s);
        }
    }
}
