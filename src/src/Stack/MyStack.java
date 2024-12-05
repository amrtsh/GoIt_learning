package Stack;

//Написати свій клас MyStack як аналог класу Stack,
// який працює за принципом LIFO (last-in-first-out).
//
//        Можна робити за допомогою Node або використати масив.
//
//        Методи
//
//        push(Object value) додає елемент в кінець
//        remove(int index) видаляє елемент за індексом
//        clear() очищає колекцію
//        size() повертає розмір колекції
//        peek() повертає перший елемент стеку
//        pop() повертає перший елемент стеку та видаляє його з колекції


public class MyStack<T> {
    private Node<T> head;
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
            head = new_node;
            new_node.next = null;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new_node;
            current.next.next = null;
        }
        size++;
    }

    public void remove(int index) {
        Node<T> current = head;
        int counter = 0;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        else if (index == 0) { // Removing the head
            head = head.next; // Update head to the next node
        }

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;

    size--;
    }


    public void clear() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack already empty!");
        }
        Node<T> current = head;
        int counter = 0;
        while (counter < size) {
            current.data = null;
            current = current.next;
            counter++;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public T peek(){
        if(!isEmpty()) {
            return head.data;
        } else return null;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        T data = head.data;
        remove(0);
        size--;
        return data;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stack is empty!";
        }

        StringBuilder sb = new StringBuilder();
        Node<T> current = head;

        sb.append("Stack: ");
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }

        return sb.toString();
    }

}
