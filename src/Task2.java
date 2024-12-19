package src;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Task2 {

    int n = 15;
    AtomicInteger start = new AtomicInteger(1);
    BlockingQueue<Object> blockingQueue = new LinkedBlockingDeque<>(n);
    ExecutorService executorService = Executors.newFixedThreadPool(4);  // Thread pool to manage threads A, B, C, D

    public void fizz() {
        int value = start.get();
        boolean isDivisible = (value % 3 == 0);
        try {
            if (isDivisible) {
                blockingQueue.put("fizz");
            } else blockingQueue.put(value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void buzz() {
        int value = start.get();
        boolean isDivisible = (value % 5 == 0);
        try {
            if (isDivisible) {
                blockingQueue.put("buzz");
            } else blockingQueue.put(value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void fizzbuzz() {
        int value = start.get();
        boolean isDivisible = (value % 3 == 0 && value % 5 == 0);
        try {
            if (isDivisible) {
                blockingQueue.put("fizzbuzz");
            } else blockingQueue.put(value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void number() {
        try {
            Object result = blockingQueue.take();
            System.out.print(result + ", ");  // Output the element from the queue
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


//    public void runTask() {
//        // Start a loop to iterate from 1 to n
//        while (start.get() <= n) {
//            Thread A = new Thread(this::fizz);
//            Thread B = new Thread(this::buzz);
//            Thread C = new Thread(this::fizzbuzz);
//            Thread D = new Thread(this::number);
//
//            A.start();
//            B.start();
//            C.start();
//            D.start();
//
//            // Wait for threads to finish processing the current value
//            try {
//                A.join();
//                B.join();
//                C.join();
//                D.join();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//            start.incrementAndGet();
//        }
//        System.out.println(list);
//    }
//
//    public static void main(String[] args) {
//        Task2 task = new Task2();
//        task.runTask();
//    }
//
//}

    public void runTask() {
        // Start a loop to iterate from 1 to n
        while (start.get() <= n) {
            // Increment the counter before starting tasks to ensure each task works with the correct value
            start.incrementAndGet();

            // Start each thread after incrementing the start value
            executorService.submit(this::fizz);
            executorService.submit(this::buzz);
            executorService.submit(this::fizzbuzz);
            executorService.submit(this::number);

            // Sleep for a short period to allow all threads to process the current number
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        executorService.shutdown();
    }

    // Main method to run the task
    public static void main(String[] args) {
        Task2 task = new Task2();
        task.runTask();
    }
}
