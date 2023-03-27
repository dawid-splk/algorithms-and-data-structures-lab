package lab4.Zad3;

public class Zad3 {

    public static void main(String[] args) {
        SinkingStack<Integer> sinkingStack = new SinkingStack<>(5);

        sinkingStack.push(1);
        sinkingStack.push(2);
        sinkingStack.push(3);
        sinkingStack.push(4);
        sinkingStack.push(5);
        sinkingStack.push(6);

        System.out.println("Integer stack with size: " + sinkingStack.size());
        System.out.println(sinkingStack.pop());     //pop 6
        System.out.println(sinkingStack.pop());     //pop 5
        System.out.println(sinkingStack.pop());     //pop 4
        System.out.println(sinkingStack.pop());     //pop 3
        System.out.println(sinkingStack.pop());     //pop 2
//        System.out.println(sinkingStack.pop());     //EmptyStackException pops out

        SinkingStack<String> stringStack = new SinkingStack<>(1);
        stringStack.push("World");
        stringStack.push("Hello");
        System.out.println("\n\nString stack with size: " + stringStack.size());

        System.out.println(stringStack.pop());      //pop Hello
        System.out.println(stringStack.pop());      //EmptyStackException

    }
}
