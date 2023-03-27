package lab4.Zad2;

public class Zad2 {

    public static void main(String[] args) {
        UnlimitedStack<Integer> stack = new UnlimitedStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Popping 3: " + stack.pop());    //pop 3
        System.out.println("Popping 2: " + stack.pop());    //pop 2
        stack.push(4);
        stack.push(5);
        System.out.println("Popping 5: " + stack.pop());    //pop 5
        System.out.println("Popping 4: " + stack.pop());    //pop 4
        System.out.println("Popping 1: " + stack.pop());    //pop 1
        System.out.println("Trying to pop when stack's empty: ");
        System.out.println(stack.pop());    //EmptyStackException
    }
}
