package lab9;

public class Main {

    public static void main(String[] args) {

        Chopstick chopstick1 = new Chopstick(1);
        Chopstick chopstick2 = new Chopstick(2);
        Chopstick chopstick3 = new Chopstick(3);
        Chopstick chopstick4 = new Chopstick(4);
        Chopstick chopstick5 = new Chopstick(5);

        Philosopher philosopher1 = new Philosopher(chopstick1, chopstick2);
        Philosopher philosopher2 = new Philosopher(chopstick2, chopstick3);
        Philosopher philosopher3 = new Philosopher(chopstick3, chopstick4);
        Philosopher philosopher4 = new Philosopher(chopstick4, chopstick5);
        Philosopher philosopher5 = new Philosopher(chopstick5, chopstick1);

        Thread t1 = new Thread(philosopher1);
        Thread t2 = new Thread(philosopher2);
        Thread t3 = new Thread(philosopher3);
        Thread t4 = new Thread(philosopher4);
        Thread t5 = new Thread(philosopher5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
