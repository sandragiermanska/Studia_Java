import java.util.Iterator;

public class SimpleStrategy extends Strategy {

    @Override
    public CwEntry findEntry(Crossword cw) throws Exception {
        Iterator<CwEntry> iter = cw.getROEntryIter();
        CwEntry entry = null;
        if (!iter.hasNext()) {
            int size = cw.getBoardCopy().getHeight();
            Entry e = null;
            while (e == null) {
                e = cw.getCwDB().getRandom(size - 1);
                size--;
                if (size == 0) throw new Exception();
            }
            entry = new CwEntry(e.getWord(), e.getClue());
            entry.setDirection(Direction.VERT);
            entry.setFirstPosX(cw.getBoardCopy().getWidth() / 2);
            entry.setFirstPosY(1);
            entry.setVisible(false);
        } else {
            CwEntry password = iter.next();
            int i = 1;
            while (iter.hasNext()) {
                i++;
                iter.next();
            }
            if (i == password.getWord().length() + 1) return null;
            int x = cw.getBoardCopy().getWidth() / 2;
            try {
                Entry e = null;
                do {
                    String pattern = cw.getBoardCopy().createPattern(x, i, x, i, Direction.HORIZ, this);
                    if (pattern != null) {
                        e = cw.getCwDB().getRandom(pattern);
                    }
                } while (e == null || cw.contains(e.getWord()));
                entry = new CwEntry(e.getWord(), e.getClue());
                String word = entry.getWord();
                int j;
                for (j = 0; j < word.length(); j++) {
                    if (word.charAt(j) == password.getWord().charAt(i - 1)) {
                        if (word.length() - j <= cw.getBoardCopy().getWidth() - x) {
                            break;
                        }
                    }
                }
                entry.setFirstPosX(x - j);
                entry.setFirstPosY(i);
                entry.setDirection(Direction.HORIZ);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entry;
    }

}