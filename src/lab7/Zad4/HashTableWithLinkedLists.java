package lab7.Zad4;

import lab7.Student;

import java.util.Comparator;
import java.util.Iterator;

public class HashTableWithLinkedLists {

    private OneWayLinkedList<Student>[] tab;
    private Comparator<Student> comp;
    private int maxListSize;

    @SuppressWarnings("unchecked")
    public HashTableWithLinkedLists(int howManyStudentsPrimarly, Comparator<Student> comp){
        tab = (OneWayLinkedList<Student>[]) new OneWayLinkedList[2*howManyStudentsPrimarly];  //wywali sie czy nie??
        maxListSize = 0;
        this.comp = comp;
    }

    public int get(Student st){
        int hc = st.hashCode() % tab.length;
        if(tab[hc] != null){
            int idx = tab[hc].indexOf(st);
            if((idx != -1)) {
                System.out.println("Index of the searched object: " + hc + "/" + idx);
                return idx;
            }
        }
        System.out.println("Dictionary doesn't contain searched element.");
        return -1;
    }

    public void add(Student st){
        checkIfTableOverfilled();
        addUsingHashFunction(st, tab);
    }

    public boolean contains(Student st){
        if(get(st) < 0) {
            return false;
        } else {
            return true;
        }
    }

    private void addUsingHashFunction(Student st, OneWayLinkedList<Student>[] temp){
        int hc = st.hashCode() % temp.length;
        if(temp[hc] == null) {
            temp[hc] = new OneWayLinkedList<>();
        }
        temp[hc].add(st);

        int size;
        if((size = temp[hc].size()) > maxListSize){
            maxListSize = size;
        }
    }


    @SuppressWarnings("unchecked")
    private void checkIfTableOverfilled(){
        int pos;
        if(maxListSize >= 0.8 * tab.length){
            maxListSize = 0;
            OneWayLinkedList<Student>[] temp = (OneWayLinkedList<Student>[]) new OneWayLinkedList[2* tab.length];;
            for (int i = 0; i < tab.length; i++) {
                if(tab[i] != null) {
                    Iterator<Student> iter = tab[i].iterator();
                    while(iter.hasNext()){
                        Student st = iter.next();
                        addUsingHashFunction(st, temp);
                    }
                }
            }
            tab = temp;
        }
    }

    public OneWayLinkedList<Student>[] getTab() {
        return tab;
    }

    public void viewDictionary(){
        for (int i = 0; i < tab.length; i++) {
            if(tab[i] != null) {
                tab[i].viewList();
                System.out.print("\n");
            } else {
                System.out.println("Empty list");
            }
        }
        System.out.print("\n");
    }

    public int getMaxListSize() {
        return maxListSize;
    }
}
