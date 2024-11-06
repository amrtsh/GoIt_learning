//Написати свій клас MyQueue як аналог класу Queue, який буде працювати за принципом FIFO (first-in-first-out).
//
//Можна робити за допомогою Node або використати масив.
//
//Методи
//
//add(Object value) додає елемент в кінець
//clear() очищає колекцію
//size() повертає розмір колекції
//peek() повертає перший елемент з черги
//poll() повертає перший елемент з черги і видаляє його з колекції


package Queue;

import java.util.Arrays;

public class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public void add(T data) {
        Node<T> new_node = new Node<T>(data);
        if (data == null) {
            throw new NullPointerException();
        }

        if (head == null) {
            head = tail = new_node;
            new_node.next = null;
        } else {
            tail.next = new_node;
            tail = new_node;
        }
        tail.next = null;
        size++;
    }

    public boolean clear() {
        if (isEmpty()) {
            return true;
        }
        Node<T> current = head;
        int counter = 0;
        while (counter < size ) {
            head.data = null;
            head.next = current;
            counter++;
        }
        size = 0;
        return isEmpty();
    }

    public int size() {
        return size;
    }

    public T peek(){
        T data = head.data;
        if(!isEmpty()) {
            return data;
        } else return null;
    }

    public T poll() {
        T data = head.data;
        Node<T> current = head;
        if(!isEmpty()) {
            head.data = null;
            head.next = current;
        } else return null;
        size--;
        return data;
    }

    public String toString() {
        Object[] array = new Object[size];
        Node<T> current = head;

        int index = 0;
        while (current != null) {
            array[index] = current.data;
            current = current.next;
            index++;
        }
        return Arrays.toString(array);
    }
}
