package lab7.Zad4;

import lab7.Student;

import java.util.Comparator;

public class Zad4 {

    public static void main(String[] args) {
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {    //do porownania obiektow sluzy tylko indeks
                return Integer.compare(o1.getIndex(), o2.getIndex());   //*jednak przy metodzie get wazne sa rowniez
            }                                                           //pozostale pola ze wzgledu na wklad w hashcode*
        };
        HashTableWithLinkedLists dict = new HashTableWithLinkedLists(1,comp);
        System.out.println("Array length: " + dict.getTab().length + "\n");


        dict.add(new Student("Michal", "Chudy", 5));
        dict.add(new Student("Rafal", "Kozlik", 8));
        dict.add(new Student("Natalia", "Grobek", 6));
        dict.add(new Student("Kacper", "Mikluszka", 7));
        dict.add(new Student("Lukasz", "Nowak", 2));
        dict.add(new Student("Mateusz", "Spalek", 9));
        dict.add(new Student("Jakub", "Dziarmaga", 3));
        dict.add(new Student("Karolina", "Michalska", 4));
        dict.add(new Student("Dawid", "Spalek", 1));

        dict.viewDictionary();

        System.out.println("Array length: " + dict.getTab().length + " (dynamic resizing)\n");

        System.out.println(dict.contains(new Student("Michal", "Chudy", 5)));
        System.out.println(dict.contains(new Student("Karolina", "Michalska", 4)));
        System.out.println(dict.contains(new Student("Mateusz", "Dworczyk", 11)));
    }
}
