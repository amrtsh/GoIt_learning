package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        System.out.println(queue.clear());
//        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
