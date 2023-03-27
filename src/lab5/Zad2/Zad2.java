package lab5.Zad2;

import lab5.Sort.SelectSort;
import lab5.Sort.Sort;

import java.util.ArrayList;
import java.util.Comparator;

public class Zad2 {

    ArrayList<Student> studentList = new ArrayList<>();
    Sort<Student> wayOfSorting;

    public static void main(String[] args) {
        Zad2 test = new Zad2(new SelectSort<>());

        test.wayOfSorting.sort(test.studentList, Comparator.naturalOrder());    //komparator naturalny

        System.out.println("\nAfter using natural comparator: ");
        for (Student s : test.studentList) {
            System.out.println(s.toString());
        }

        test.wayOfSorting.sort(test.studentList, new Comparator<Student>() {    //komparator prosty
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getName().compareTo(o2.getName()), 0);
            }
        });

        System.out.println("\nAfter using simple comparator: ");
        for (Student s : test.studentList) {
            System.out.println(s.toString());
        }


        test.wayOfSorting.sort(test.studentList, new Comparator<Student>() {    //komparator zlozony
            @Override
            public int compare(Student o1, Student o2) {
                int result;
                result = Integer.compare(o1.getSurname().compareTo(o2.getSurname()), 0);
                if(result != 0){
                    return result;
                } else {
                    return Integer.compare(o1.getName().compareTo(o2.getName()), 0);
                }
            }
        });

        System.out.println("\nAfter using complex comparator: ");
        for (Student s : test.studentList) {
            System.out.println(s.toString());
        }
    }

    public Zad2(Sort<Student> howToSort){
        createList();
        wayOfSorting = howToSort;

        System.out.println("Data to operate on:");
        for (Student s: studentList) {
            System.out.println(s.toString());
        }
    }

    public void createList(){
        studentList.add(new Student("Lukasz", "Nowak", 2));
        studentList.add(new Student("Michal", "Chudy", 5));
        studentList.add(new Student("Jakub", "Dziarmaga", 3));
        studentList.add(new Student("Karolina", "Michalska", 4));
        studentList.add(new Student("Natalia", "Grobek", 6));
        studentList.add(new Student("Mateusz", "Spalek", 9));
        studentList.add(new Student("Kacper", "Mikluszka", 7));
        studentList.add(new Student("Rafal", "Kozlik", 8));
        studentList.add(new Student("Julia", "Kowalska", 10));
        studentList.add(new Student("Dawid", "Spalek", 1));
    }
}
