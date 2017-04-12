package stc5.srez1;

import java.util.Random;

/**
 * Created by sergey on 12.04.17.
 */
public class RandomGenerator implements Runnable {
    Thread t;
    private volatile SyncData data;
    private Random random;
    private int currentSize = 0;
    private boolean keepExecute;
    private int size;

    public RandomGenerator(SyncData data) {
        t = new Thread(this, "randomGeneratorThread");
        this.data = data;
        random = new Random();
        keepExecute = true;
        size = 100;
        t.start();
    }

    public void run() {
        while(keepExecute) {
            synchronized (data) {
                int randomNumber = random.nextInt(size);
                data.addNumber(randomNumber);
                currentSize = data.getSize();

//                System.out.print(randomNumber + " ");
                data.updateSeconds();

                if (currentSize >= size) {
                    data.stopExecute();
                    keepExecute = data.canExecute();
                }
                data.notifyAll();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
