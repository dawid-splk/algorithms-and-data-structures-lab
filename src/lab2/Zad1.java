package lab2;

public class Zad1 {

    public static void main(String[] args) {
        Zad1 test = new Zad1();

        OneWayLinkedList<Student> list1 = new OneWayLinkedList<>();
        list1.add(new Student("Dawid", "Spalek", 1));
        list1.add(new Student("Dawid", "Spalek", 1));
        list1.add(new Student("Dawid", "Spalek", 1));
        list1.add(new Student("Kacper", "Mikluszka", 7));
        list1.add(new Student("Jakub", "Dziarmaga", 3));
        list1.add(new Student("Michal", "Chudy", 5));
        list1.add(new Student("Kacper", "Mikluszka", 7));
        list1.add(new Student("Mateusz", "Spalek", 9));

        list1.viewList();
        System.out.println("\nUsuniecie pierwszych trzech studentow:");
        list1.removeStudentsUsingKey(new Student("Dawid", "Spalek", 1));
        list1.viewList();
        System.out.println("\nUsuniecie dwoch studentow ze srodka listy:");
        list1.removeStudentsUsingKey(new Student("Kacper", "Mikluszka", 7));
        list1.viewList();

    }
}
