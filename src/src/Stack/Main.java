package Stack;


public class Main {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.remove(0);

//        stack.clear();
        System.out.println(stack);
    }
}
