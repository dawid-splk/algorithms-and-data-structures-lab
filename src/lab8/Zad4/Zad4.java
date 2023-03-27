package lab8.Zad4;

import java.util.Comparator;

public class Zad4 {

    public static void main(String[] args) {
        Comparator<Integer> comp = Integer::compare;
        RB_Tree<Integer> tree = new RB_Tree<>(comp);

        tree.colorInsert(5);
        tree.colorInsert(2);
        System.out.println("\nPoczatkowe drzewo RB: ");
        tree.print();
        tree.colorInsert(10);
        System.out.println("Drzewo po dodaniu 10:  (I przypadek: rodzic czarny)");
        tree.print();
        tree.colorInsert(6);
        System.out.println("Drzewo po dodaniu 6:  (II przypadek: rodzic czerwony, stryjek czerwony");
        tree.print();
        tree.colorInsert(7);
        System.out.println("Drzewo po dodaniu 7:  (III przypadek: rodzic czerwony, stryjek (czyli null) czarny)");
        tree.print();
        tree.colorInsert(3);
        tree.colorInsert(4);
        tree.colorInsert(1);
        tree.colorInsert(9);
        tree.colorInsert(8);

        System.out.println("\nKoncowe drzewo RB: ");
        tree.print();

        System.out.println("Przeglad drzewa pre-order: ");
        tree.preOrder(tree.root);
    }
}
