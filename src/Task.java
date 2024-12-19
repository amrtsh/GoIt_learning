package src;

//Напишіть програму, яка кожну секунду відображає на екрані дані про час,
// що минув від моменту запуску програми.
//
//Другий потік цієї ж програми кожні 5 секунд виводить повідомлення Минуло 5 секунд.

import java.security.PublicKey;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Task {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public void run() {
        final long start = System.currentTimeMillis();
        Runnable firstTask = () -> {
            long elapsedTime = (System.currentTimeMillis() - start) / 1000;
            System.out.println("Elapsed time: " + elapsedTime);
        };
        scheduler.scheduleAtFixedRate(firstTask, 0, 1, SECONDS);


        Runnable secondTask = () ->
                System.out.println("Минуло 5 секунд.");
        scheduler.scheduleAtFixedRate(secondTask, 5, 5, SECONDS);
    }

    ;

    public static void main(String[] args) {
        Task task = new Task();
        task.run();
    }
}
