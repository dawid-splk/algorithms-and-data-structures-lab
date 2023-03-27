package lab7.Zad3;

import lab7.Student;

import java.util.Comparator;
import java.util.Random;

public class HashTable {

    private Student[] tab;
    private Comparator<Student> comp;
    private int size;

    public HashTable(int howManyStudentsPrimarly, Comparator<Student> comp){
        tab = new Student[(int) (1.5*howManyStudentsPrimarly)];
        size = 0;
        this.comp = comp;
    }

    public int get(Student st){
        int pos = st.hashCode() % tab.length;
        int i = 1;
        while(tab[pos] != null){
            if(comp.compare(st, tab[pos]) == 0){
                return pos;
            } else {
                pos = (int) ((pos + (Math.pow(-1,i-1)*Math.pow((i+1)/2,2)))% tab.length);
                i++;
            }
        }
        return -1;
    }

    public void add(Student st){
        size++;
        checkIfTableOverfilled();
        addUsingHashFunction(st,tab);
    }

    public boolean contains(Student st){
        if(get(st) < 0) {
            return false;
        } else {
            return true;
        }
    }

    private void checkIfTableOverfilled(){
        if(size >= 0.5 * tab.length){
            Student[] temp = new Student[2 * tab.length];
            for (int i = 0; i < tab.length; i++) {
                if(tab[i] != null)
                    addUsingHashFunction(tab[i], temp);
            }
            tab = temp;
        }
    }

    private void addUsingHashFunction(Student st, Student[] array){
        int pos = st.hashCode() % array.length;
        int i = 1;
        while(array[pos] != null){
            pos = (int) ((pos + (Math.pow(-1,i-1)*Math.pow((i+1)/2,2)))% array.length);
            i++;
        }
        if(array[pos] != null)
            throw new UnknownError();
        array[pos] = st;
    }

    public Student[] getTab() {
        return tab;
    }

    public void viewDictionary(){
        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i]);
        }
    }
}
