package Stack;

//Написати свій клас MyStack як аналог класу Stack, який працює за принципом LIFO (last-in-first-out).
//
//Можна робити за допомогою Node або використати масив.
//
//Методи
//
//push(Object value) додає елемент в кінець
//remove(int index) видаляє елемент за індексом
//clear() очищає колекцію
//size() повертає розмір колекції
//peek() повертає перший елемент стеку
//pop() повертає перший елемент стеку та видаляє його з колекції


import java.util.Arrays;

public class MyStack<T> {
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

    public boolean isEmpty() {
        return (size == 0);
    }

    public void push(T data) {
        Node<T> new_node = new Node<T>(data);
        if (data == null) {
            throw new NullPointerException();
        }

        if (head == null) {
            head = tail = new_node;
            new_node.next = null;
        } else {
            new_node.next = head;
            head = new_node;
        }
        size++;
    }

    public void remove(int index) {
        Node<T> current = head;
        int counter = 0;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        while (counter != index) {
            current = current.next;
            counter++;
        }
        current.next = current.next.next;
        size--;
    }


    public void clear() {
        if (isEmpty()) {
            return;
        }
        Node<T> current = head;
        int counter = 0;
        while (counter < size) {
            head.data = null;
            head.next = current;
            counter++;
        }
        size = 0;
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

    public T pop() {
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
