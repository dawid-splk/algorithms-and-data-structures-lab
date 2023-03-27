package lab4.Zad4;

public class Zad4 {

    public static void main(String[] args) {
        DynamicSizeStack<Integer> dynamicStack = new DynamicSizeStack<>(4);
        dynamicStack.push(1);
        dynamicStack.push(12);
        System.out.println("\nPushing " + (dynamicStack.getLastIndex()+1) + "/" + dynamicStack.getSize());
        dynamicStack.push(123);
        System.out.println("New size: " + dynamicStack.getSize() + "\t should print 8");

        dynamicStack.push(1234);
        dynamicStack.push(12345);
        System.out.println("\nPushing " + (dynamicStack.getLastIndex()-1) + "/" + dynamicStack.getSize());
        dynamicStack.push(123456);
        System.out.println("New size: " + dynamicStack.getSize() + "\t should print 16\n");

        System.out.println(dynamicStack.pop());
        System.out.println("Popping. Elements left: " + (dynamicStack.getLastIndex()-1) + "/" + dynamicStack.getSize());
        System.out.println(dynamicStack.pop());
        System.out.println("New size: " + dynamicStack.getSize() + "   should print 8\n");

        System.out.println(dynamicStack.pop());
        System.out.println("Popping. Elements left: " + (dynamicStack.getLastIndex()-1) + "/" + dynamicStack.getSize());
        System.out.println(dynamicStack.pop());
        System.out.println("New size: " + dynamicStack.getSize() + "   should print 4\n");

        System.out.println("Popping. Elements left: " + (dynamicStack.getLastIndex()-1) + "/" + dynamicStack.getSize());
        System.out.println(dynamicStack.pop());
        System.out.println("New size: " + dynamicStack.getSize() + "   should print 2\n");

        System.out.println("Popping. Elements left: " + (dynamicStack.getLastIndex()-1) + "/" + dynamicStack.getSize());
        System.out.println(dynamicStack.pop());
        System.out.println("New size: " + dynamicStack.getSize() + "   should print 1\n");

        System.out.println("\nPushing " + (dynamicStack.getLastIndex()+1) + "/" + dynamicStack.getSize());
        dynamicStack.push(404);
        System.out.println("New size: " + dynamicStack.getSize() + "   should print 2\n");
        System.out.println("Final pop: " + dynamicStack.pop());

    }
}
