package lab8.Zad1_2_3;

public class Zad1_2_3 {

    public static void main(String[] args) throws InterruptedException {
        BST_Tree<Integer> testTree = new BST_Tree<>(Integer::compare);
        testTree.insert(5);
        testTree.insert(2);
        testTree.insert(9);
        testTree.insert(6);
        testTree.insert(3);
        testTree.insert(4);
        testTree.insert(1);
        testTree.insert(7);

        System.out.println("Poczatkowe drzewo BST:");
        testTree.print();

        testTree.insert(8);
        System.out.println("Drzewo po dodaniu elementu 8:");
        testTree.print();


        System.out.println("Element maksymalny: " + testTree.treeMaximum(testTree.root));
        System.out.println("Element minimalny: " + testTree.treeMinimum(testTree.root));
        int s = 4;
        System.out.println("Nastepnik elementu " + s + ": " + testTree.treeSuccessor(s));
        int p = 1;
        System.out.println("Porzednik elementu " + p + ": " + testTree.treePredecessor(p));
////        testTree.preOrder(testTree.root);
        System.out.println("Przeglad in order: ");
        testTree.inOrder(testTree.root);
////        testTree.postOrder(testTree.root);
        System.out.println("\nUsuniety element: " + testTree.delete(5, testTree.root));
        System.out.println("Drzewo po usunieciu elementu 5 - korzenia:");
        testTree.print();
        testTree.getCharacteristics(6, testTree.root);
    }
}
