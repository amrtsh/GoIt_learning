package ArrayList;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<T> {

    private int capacity = 2;
    private T[] array = (T[]) new Object[capacity];
    private int size = 0;

    public void add(T value) {
        if (capacity <= size) {
            capacity += capacity / 2;
            array = Arrays.copyOf(array, capacity);
        }
        array[size] = value;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
    }

    public void clear() {
        size = 0;
        Arrays.fill(array, null);
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public String toString() {
        Object[] resultArray = Arrays.stream(array)
                .filter(Objects::nonNull)
                .toArray();
        return Arrays.toString(resultArray);
    }
}
