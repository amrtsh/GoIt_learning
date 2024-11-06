package ArrayList;

public class Main {
    public static void main(String[] args) {

        MyArrayList<String> myArrayList = new MyArrayList<>();
        myArrayList.add("Alina");
        myArrayList.add("Mike");
        myArrayList.add("John");
        myArrayList.add("Frank");
        myArrayList.add("Lim");
        myArrayList.remove(3);
        System.out.println(myArrayList);
    }
}
