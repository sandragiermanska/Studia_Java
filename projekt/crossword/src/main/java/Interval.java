public class Interval {

    private int from;
    private int to;

    public Interval(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public void setFromOneMore() {
        from++;
    }

    public void setToOneLess() {
        to--;
    }
}
