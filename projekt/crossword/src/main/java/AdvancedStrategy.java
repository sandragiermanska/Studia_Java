import java.util.Iterator;
import java.util.Random;

public class AdvancedStrategy extends Strategy {

    private Direction direction = Direction.HORIZ;

    @Override
    public CwEntry findEntry(Crossword cw) throws Exception {
        Iterator<CwEntry> iter = cw.getROEntryIter();
        CwEntry entry = null;
        Board b = cw.getBoardCopy();
        if (!iter.hasNext()) { //znajdowanie pierwszego hasła
            entry = findFirstEntry(cw);
        } else {
            //znajdowanie kolejnego hasła
            int whichTry = 0;
            Entry e = null;
            while (whichTry < 30) {
                Point point = crossPoint(cw);
                while (!canBeCrossPoint(point, b)) {
                    point = crossPoint(cw);
                }
                char atCrossPoint = b.getCell(point.getX(), point.getY()).getContent().charAt(0);
                Interval interval = findMaxFromToInterval(point, b);
                String pattern;
                if (direction == Direction.HORIZ) {
                    pattern = b.createPattern(interval.getFrom(), point.getY(), interval.getTo(), point.getY(), direction, this);
                    if (pattern != null) {
                        e = cw.getCwDB().getRandom(pattern);
                        update(e, interval, point.getX());
                    }
                    whichTry++;
                } else {
                    pattern = b.createPattern(point.getX(), interval.getFrom(), point.getX(), interval.getTo(), direction, this);
                    if (pattern != null) {
                        e = cw.getCwDB().getRandom(pattern);
                        update(e, interval, point.getY());
                    }
                    whichTry++;
                }
                if (e != null) {
                    Point startPoint = startPoint(point, e.getWord(), atCrossPoint);
                    if (!cw.contains(e.getWord())) {
                        entry = new CwEntry(e, startPoint.getX(), startPoint.getY(), direction);
                        break;
                    }
                }
            }
        }
        return entry;
    }

    private CwEntry findFirstEntry(Crossword cw) {
        Board board = cw.getBoardCopy();

        Random random = new Random();
        int width = board.getWidth();
        Entry entry;
        if (width < 12) {
            entry = cw.getCwDB().getRandom(width - 2);
        } else {
            entry = cw.getCwDB().getRandom();
        }

        //jedynki bo miejsce na numerek pytania
        int posX = random.nextInt((board.getWidth() - entry.getWord().length())/2) + 1;
        int posY = random.nextInt(board.getHeight()/2) + 1;

        return new CwEntry(entry, posX, posY, Direction.HORIZ);
    }

    private Point crossPoint(Crossword cw) {
        Iterator<CwEntry> iter = cw.getROEntryIter();
        Random random = new Random();
        int sizeOfCurrentDictionary = 0;
        while (iter.hasNext()) {
            sizeOfCurrentDictionary++;
            iter.next();
        }
        CwEntry previousEntry = cw.getEntry(random.nextInt(sizeOfCurrentDictionary));
        String previousWord = previousEntry.getWord();
        int x, y;
        if (previousEntry.getDirection() == Direction.HORIZ) {
            x = random.nextInt(previousWord.length()) + previousEntry.getFirstPosX();
            y = previousEntry.getFirstPosY();
            direction = Direction.VERT;
        } else {
            x = previousEntry.getFirstPosX();
            y = random.nextInt(previousWord.length()) + previousEntry.getFirstPosY();
            direction = Direction.HORIZ;
        }
        return new Point(x,y);
    }

    private boolean canBeCrossPoint(Point point, Board board) {
        if (direction == Direction.HORIZ) {
            if (board.getCell(point.getX() - 1,point.getY()).getContent() == null &&
                    board.getCell(point.getX() + 1, point.getY()).getContent() == null) {
                return true;
            }
        } else {
            if (board.getCell(point.getX(),point.getY() - 1).getContent() == null &&
                    board.getCell(point.getX(), point.getY() + 1).getContent() == null) {
                return true;
            }
        }
        return false;
    }

    private Interval findMaxFromToInterval(Point point, Board b) {
        int size, numberOfLine;
        int from = -1;
        int to = -1;
        if (direction == Direction.HORIZ) {
            size = b.getWidth();
            numberOfLine = point.getY();
            from = to = point.getX();
            for (int i = point.getX() - 1; i > 0; i--) {
                if (b.getCell(i, numberOfLine - 1).getContent() != null ||
                    (numberOfLine != size - 1 && b.getCell(i, numberOfLine + 1).getContent() != null)) {
                    break;
                }
                if (b.getCell(i, numberOfLine).getContent() != null) {
                    from = i;
                }
            }
            for (int i = point.getX() + 1; i < size; i++) {
                if (b.getCell(i, numberOfLine - 1).getContent() != null ||
                        (numberOfLine != size -1 && b.getCell(i, numberOfLine + 1).getContent() != null)) {
                    break;
                }
                if (b.getCell(i, numberOfLine).getContent() != null) {
                    to = i;
                }
            }

        } else {
            size = b.getHeight();
            numberOfLine = point.getX();
            from = to = point.getY();
            for (int i = point.getY() - 1; i > 0; i--) {
                if (b.getCell(numberOfLine - 1, i).getContent() != null ||
                        (numberOfLine + 1 < size && b.getCell(numberOfLine + 1, i).getContent() != null)) {
                    break;
                }
                if (b.getCell(numberOfLine, i).getContent() != null) {
                    from = i;
                }
            }
            for (int i = point.getY() + 1; i < size; i++) {
                if (b.getCell(numberOfLine - 1, i).getContent() != null ||
                        (numberOfLine + 1 < size && b.getCell(numberOfLine + 1, i).getContent() != null)) {
                    break;
                }
                if (b.getCell(numberOfLine, i).getContent() != null) {
                    to = i;
                }
            }
        }
        return new Interval(from, to);
    }

    private void update (Entry entry, Interval interval, int crossValue) {
        if (entry == null) {
            if (interval.getFrom() < crossValue && interval.getTo() > crossValue + 1) {
                Random random = new Random();
                boolean deleteFrom = random.nextBoolean();
                if (deleteFrom) {
                    interval.setFromOneMore();
                } else {
                    interval.setToOneLess();
                }
            } else if (interval.getFrom() < crossValue) {
                interval.setFromOneMore();
            } else if (interval.getTo() > crossValue) {
                interval.setToOneLess();
            }
        }
    }

    private Point startPoint(Point crossPoint, String word, char atCrossPoint) {
        Point result = new Point(0,0);
        int positionOfCrossInWord = word.indexOf(atCrossPoint);
        if (direction == Direction.HORIZ) {
            result.setX(crossPoint.getX() - positionOfCrossInWord);
            result.setY(crossPoint.getY());
        } else {
            result.setX(crossPoint.getX());
            result.setY(crossPoint.getY() - positionOfCrossInWord);
        }
        return result;
    }
}
