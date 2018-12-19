package lab9;

public class Philosopher implements Runnable{
    private Chopstick left;
    private Chopstick right;
    private boolean isEaten;

    Philosopher(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
        isEaten = false;
    }

    @Override
    public void run() {
        while (!isEaten) {
            tryEat();
        }
        System.out.println("Filozof" + left.getPriority() + ": Zjadłem");
    }

    private void tryEat() {
        Chopstick first, second;
        if (left.getPriority() > right.getPriority()) {
            first = left;
            second = right;
        } else {
            first = right;
            second = left;
        }
        try {
            if (!first.isOccupied()) {
                takeAChopstick(first);
                if (!second.isOccupied()) {
                    takeAChopstick(second);
                    eat(first, second);
                } else {

                    Thread.sleep(2000);

                    if (!second.isOccupied()) {
                        takeAChopstick(second);
                        eat(first, second);
                    } else {
                        putAChopstick(first);
                    }
                }
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat(Chopstick first, Chopstick second) {
        try {
            Thread.sleep(1000);
            isEaten = true;
            putAChopstick(second);
            putAChopstick(first);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void takeAChopstick(Chopstick chopstick) {
        chopstick.makeOccupied();
        System.out.println("Filozof" + left.getPriority() + ": Wziąłem pałeczkę" + chopstick.getPriority());
    }

    private void putAChopstick(Chopstick chopstick) {
        chopstick.makeFree();
        System.out.println("Filozof" + left.getPriority() + ": Odłożyłem pałeczkę" + chopstick.getPriority());
    }
}
