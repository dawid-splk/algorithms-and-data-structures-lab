package lab2;

import java.util.Iterator;

public class Zad2 {


    public static void main(String[] args) {
        Zad2 test = new Zad2();

        OneWayLinkedList<Student> list1 = new OneWayLinkedList<>();
        OneWayLinkedList<Student> list2 = new OneWayLinkedList<>();
        list1.add(new Student("Dawid", "Spalek", 1));
        list1.add(new Student("Jakub", "Dziarmaga", 2));
        list1.add(new Student("Michal", "Chudy", 5));
        list1.add(new Student("Kacper", "Mikluszka", 7));
        list1.add(new Student("Mateusz", "Spalek", 9));

        System.out.println("\nLaczenie list, w tym jednej pustej:");
        System.out.println("\nZawartosc pierwszej listy: ");
        list1.viewList();
        System.out.println("\nZawartosc drugiej - dluzszej - listy: ");
        list2.viewList();
        System.out.println("\nWynikowa lista: ");
        test.uniteLists(list1, list2).viewList();

        list2.add(new Student("Lukasz", "Nowak", 3));
        list2.add(new Student("Karolina", "Michalska", 4));
        list2.add(new Student("Natalia", "Grobek", 6));
        list2.add(new Student("Rafal", "Kozlik", 8));
        list2.add(new Student("Julia", "Kowalska", 10));
        list2.add(new Student("Julia", "Nowak", 11));

        System.out.println("\nLaczenie dwoch niepustych list roznej dlugosci:");
        System.out.println("\nZawartosc pierwszej listy: ");
        list1.viewList();
        System.out.println("\nZawartosc drugiej - dluzszej - listy: ");
        list2.viewList();
        System.out.println("\nWynikowa lista: ");
        test.uniteLists(list1, list2).viewList();
    }

    public OneWayLinkedList<Student> uniteLists(OneWayLinkedList<Student> list1, OneWayLinkedList<Student> list2) {
        Iterator<Student> iter1 = list1.iterator();
        Iterator<Student> iter2 = list2.iterator();
        OneWayLinkedList<Student> newList = new OneWayLinkedList<>();

        Student s1;
        Student s2;
        if(iter1.hasNext())
            s1 = iter1.next();
        else
            s1 = null;
        if(iter2.hasNext())
            s2 = iter2.next();
        else
            s2 = null;


        boolean whichIterReachedEnd = true;

        try {
            if(s1 == null && s2 == null)
                return newList;
            if(s1 == null){
                newList.addAll(list2);
                return newList;
            }
            if(s2 == null){
                newList.addAll(list1);
                return newList;
            }


            while(true){
                if (s1.getNrIndeksu() < s2.getNrIndeksu()) {
                    newList.add(s1);
                    whichIterReachedEnd = true;
                    s1 = iter1.next();
                } else {
                    newList.add(s2);
                    whichIterReachedEnd = false;
                    s2 = iter2.next();
                }
            }
        } catch (Exception e) {
            if(whichIterReachedEnd){
                while(iter2.hasNext()){
                    newList.add(s2);
                    s2 = iter2.next();
                }
                newList.add(s2);
            } else {
                newList.add(s1);
                while(iter1.hasNext()){
                    newList.add(s1);
                    s1 = iter1.next();
                }
                newList.add(s1);
            }
        }

    return newList;
    }
}
