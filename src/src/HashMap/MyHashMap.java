package HashMap;

//Написати свій клас MyHashMap як аналог класу HashMap.
//
//Потрібно робити за допомогою однозв'язної Node.
//
//Не може зберігати дві ноди з однаковими ключами.
//
//Методи
//
//put(Object key, Object value) додає пару ключ + значення
//remove(Object key) видаляє пару за ключем
//clear() очищає колекцію
//size() повертає розмір колекції
//get(Object key) повертає значення (Object value) за ключем


import java.util.Arrays;

public class MyHashMap<K, V> {

    static class Node<K, V> {
        private K key;
        private V value;
        Node<K, V> next;

        public K getKey() {
            return this.key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public V getValue() {
            return this.value;
        }


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private int capacity = 16;
    private int size = 0;
    private Node<K, V>[] bucket;


    public MyHashMap() {
        bucket = new Node[capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private void rehash() {
        capacity *= 2;
        Node<K, V>[] oldBucket = bucket; // Save the current bucket array
        bucket = new Node[capacity]; // Create a new bucket array with the updated capacity
        size = 0; // Reset size (will be recalculated during reinsertion)

        for (Node<K, V> head : oldBucket) {
            while (head != null) {
                put(head.getKey(), head.getValue());
                head = head.next;
            }
        }
    }

    public void put(K key, V value) {
        Node<K, V> node = bucket[hash(key)];

        if (size == capacity) {
            rehash();
        }

        if (node == null) {
            bucket[hash(key)] = new Node<>(key, value);
            size++;
            return;
        }

        while (node != null) {
            if (node.getKey().equals(key)) {
                node.setValue(value);
                return;
            }
            if (node.next == null) {
                node.next = new Node<>(key, value);
                size++;
                return;
            }
            node = node.next;
        }
    }

    public void remove(K key) {
        Node<K, V> node = bucket[hash(key)];

        if (node == null) {
            throw new IllegalArgumentException("Key not found in hash table: " + key);
        }
        // Case 2: The head node contains the key
        if (node.getKey().equals(key)) {
            bucket[hash(key)] = node.next;
            size--;
            return;
        }

        // Case 3: Traverse the chain to find and remove the node
        while (node.next != null) {
            if (node.next.getKey().equals(key)) {
                node.next = node.next.next;
                size--;
                return;
            }
            node = node.next;
        }

        throw new IllegalArgumentException("Key not found in hash table: " + key);
    }

    public void clear() {
        Arrays.fill(bucket, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        Node<K, V> node = bucket[hash(key)];
        while (node != null) {
            if (node.getKey().equals(key)) {
                V value = node.getValue();
                return value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<K, V> node : bucket) {
            while (node != null) {
                sb.append("\nKey: " + node.getKey() + ", Value: " + node.getValue());
                node = node.next;
            }

        } return sb.toString();
    }

}
