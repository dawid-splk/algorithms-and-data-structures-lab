package lab4.Zad1;

public class Zad1 {

    public static void main(String[] args) throws FullStackException {

        FixedSizeArrayStack<Integer> stack = new FixedSizeArrayStack<>(5);

        stack.push(1);
        System.out.println(stack.pop());    //pop 1
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());    //pop 3
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());    //pop 5
        System.out.println(stack.pop());    //pop 4
        System.out.println(stack.pop());    //pop 2

//        System.out.println(stack.pop());    //EmptyStackException

        stack.push(200);
        stack.push(200);
        stack.push(200);
        stack.push(200);
        stack.push(200);
        stack.push(403);              //FullStackException

    }
}
