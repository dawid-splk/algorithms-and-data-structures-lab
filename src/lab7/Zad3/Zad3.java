package lab7.Zad3;

import lab7.Student;

import java.util.Comparator;

public class Zad3 {

    public static void main(String[] args) {
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {    //do porownania obiektow sluzy tylko indeks
                return Integer.compare(o1.getIndex(), o2.getIndex());   //*jednak przy metodzie get wazne sa rowniez
            }                                                           //pozostale pola ze wzgledu na wklad w hashcode*
        };
        HashTable dict = new HashTable(5,comp);
        System.out.println("Array length at the beginning: " + dict.getTab().length + "\n");

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
        System.out.println("\nArray length at the end: " + dict.getTab().length + "\n");

        Student st1, st2;
        System.out.println(dict.contains(st1 = new Student("Michal", "Chudy", 5)));
        System.out.println("Index of the searched key: " + dict.get(st1));
        System.out.println(dict.contains(st2 = new Student("Mateusz", "Dworczyk", 11)));
        System.out.println("Index of the searched key: " + dict.get(st2) + " (the dictionary doesn't contain it.)");
    }

}
