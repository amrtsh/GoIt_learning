package src;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {

    private final int end = 15;
    private final AtomicInteger start = new AtomicInteger(1);  // Лічильник
    private final BlockingQueue<Object> blockingQueue = new LinkedBlockingDeque<>(end);  // Черга
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);


    public void fizz() {
        try {
            int value = start.get();
            if (value % 3 == 0 && value % 5 != 0) {
                blockingQueue.put("fizz");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void buzz() {
        try {
            int value = start.get();
            if (value % 5 == 0 && value % 3 != 0) {
                blockingQueue.put("buzz");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void fizzbuzz() {
        try {
            int value = start.get();
            if (value % 3 == 0 && value % 5 == 0) {
                blockingQueue.put("fizzbuzz");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void number() {
        try {
            int value = start.get();
            if (value % 3 != 0 && value % 5 != 0) {
                blockingQueue.put(value);
            }
            System.out.print(blockingQueue.take() + ", ");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void runTask() {
        while (start.get() <= end) {
            executorService.submit(this::fizz);
            executorService.submit(this::buzz);
            executorService.submit(this::fizzbuzz);
            executorService.submit(this::number);

            if (start.get() > end) break;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            start.incrementAndGet();
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        Task2 task = new Task2();
        task.runTask();
    }
}
