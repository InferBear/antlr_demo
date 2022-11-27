package test.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Pools {

    final private int fixed ;

    final List<Runnable> taskList;

    class WorkThread extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    List<WorkThread> threadList;
    final Object lock = new Object();

    public Pools(int fixed) {
        this.fixed = fixed;
        taskList = new LinkedList<>();
    }

    private synchronized void submit(Runnable r) {
        taskList.add(r);
    }

    private synchronized Runnable pull() {
        if (taskList.size() == 0) {
            return null;
        }
        Runnable r = taskList.get(0);
        taskList.remove(0);
        return r;
    }
}
