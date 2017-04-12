package stc5.srez1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergey on 12.04.17.
 */
public class SyncData {
    Set<Integer> numbers;
    private volatile int seconds;
    private volatile boolean keepExecute;

    public SyncData() {
        numbers = new HashSet<>(10);
        seconds = -1;
        keepExecute = true;
    }

    public void addNumber(int value) {
        numbers.add(value);
    }

    public int getSize() {
        return numbers.size();
    }

    public void updateSeconds() {
        seconds++;
    }

    public int getSeconds() {
        return  seconds;
    }

    public boolean canExecute() {
        return keepExecute;
    }

    public void stopExecute() {
        keepExecute = false;
    }
}
