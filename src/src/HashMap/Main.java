package HashMap;

public class Main {

        public static void main(String[] args) {
            MyHashMap<Integer, String> hashMap = new MyHashMap<>();
            hashMap.put(1, "one");
            hashMap.put(2, "two");
            hashMap.put(3, "three");
            hashMap.clear();

            System.out.println(hashMap.size());
            System.out.println(hashMap);
        }
}
