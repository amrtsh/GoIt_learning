package LinkedList;

//Напишіть свій клас MyLinkedList як аналог класу LinkedList.
//
//Не можна використовувати масив. Кожний елемент повинен бути окремим об'єктом-посередником (Node - нода),
// що зберігає посилання на попередній та наступний елемент колекції (двозв'язний список).
//
//Методи
//
//add(Object value) додає елемент в кінець
//remove(int index) видаляє елемент із вказаним індексом
//clear() очищає колекцію
//size() повертає розмір колекції
//get(int index) повертає елемент за індексом

import java.util.Arrays;

public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    /* Linked list Node*/
    static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public void add(T data) {
        Node<T> new_node = new Node<T>(data);
        if (head == null) {
            head = tail = new_node;
            new_node.next = new_node.prev = null;
        } else {
            tail.next = new_node;
            tail.next.prev = tail;
            tail = new_node;
        }
        tail.next = null;
        size++;
    }

    public void remove(int index) {
        Node<T> current;
        int counter;
        final int distance = size / 2;

        if (index >= 0 && index < size) {
            if (size == 1) {
                head = tail = null;
                size--;
                return;
            }
            if (index == 0) {
                head.next = head;
                head.prev = null;
                size--;
                return;
            }
            if (index == size - 1) {
                tail = tail.prev;
                tail.next = null;
                size--;
                return;
            }

            if (index < distance || index == distance) {
                current = head;
                counter = 0;
                while (counter != index) {
                    current = current.next;
                    counter++;
                }
                connectionChange(current);
            } else {
                current = tail;
                counter = size - 1;
                while (counter != index) {
                    current = current.prev;
                    counter--;
                }
                connectionChange(current);
            }
            size--;
        } else throw new IndexOutOfBoundsException();
    }


    private void connectionChange(Node<T> node) {

        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.data = null;
    }

    public void clear() {
        head.data = tail.data = null;
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public Node<T> get(int index) {
        Node<T> current;
        int counter;
        final int distance = size / 2;

        if (index >= 0 && index < size) {
            if (index == 0) {
                return head;
            }
            if (index == size - 1) {
                return tail;
            }
            if (index < distance || index == distance) {
                current = head;
                counter = 0;
                while (counter != index) {
                    current = current.next;
                    counter++;
                }
                return current;
            } else {
                current = tail;
                counter = size - 1;
                while (counter != index) {
                    current = current.prev;
                    counter--;
                }
                return current;
            }
        } throw new IndexOutOfBoundsException();
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