package stc5.srez1;


import java.util.Date;

/**
 * Created by sergey on 12.04.17.
 */
public class Printer implements  Runnable {
    Thread t;

    SyncData data;
    int currentSize;

    public Printer(SyncData data) {
        this.data = data;
        t = new Thread(this, "printer thread");
        t.start();
    }

    public void run() {
        while(true) {
            synchronized (data) {
                currentSize = data.getSize();

                if (data.getSeconds() % 5 == 0)
                    System.out.println("[" + new Date() + "] Printer. Size of data " + currentSize);

                if (!data.canExecute())
                    break;
                try {
                    data.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
