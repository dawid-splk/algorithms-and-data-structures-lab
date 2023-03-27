package lab7.Zad2;

import lab7.Student;

import java.util.Comparator;

public class ArrayDictionary {

    private Student[] tab;
    private Comparator<Student> comp;
    private int size;

    public ArrayDictionary(int howManyStudentsPrimarly, Comparator<Student> comp){
        tab = new Student[(int) (1.5*howManyStudentsPrimarly)];
        this.comp = comp;
        size = 0;
    }

    public int get(Student st){
        int left = 0;
        int right = tab.length - 1;
        int middle;
        while(left <= right){
            middle=(left + right) / 2;
            if(tab[middle] == null){
                right = middle - 1;
            } else {
                int compValue = comp.compare(st, tab[middle]);
                if (compValue == 0)
                    return middle;
                if (compValue < 0)
                    right = middle - 1;
                else
                    left = middle + 1;
            }
        }
        return -1;
    }


    public void add(Student st){
        if(tab[0] == null){
            tab[0] = st;
            size++;
            return;
        }
        size++;
        checkIfArrayOverfilled();
        int i = 0;
        while(tab[i] != null){
            if(comp.compare(st, tab[i]) < 0){
                insertFix(st,i);
                return;
            }
            i++;
        }
        tab[i] = st;
    }

    private void insertFix(Student st, int index){
        Student temp = tab[index];
        Student tempNext = tab[index+1];

        tab[index] = st;
        while(tempNext != null){
            index++;
            tab[index] = temp;
            temp = tempNext;
            tempNext = tab[index+1];
        }
        tab[index+1] = temp;
    }

    private void checkIfArrayOverfilled(){
        if(size >= 0.6 * tab.length){
            Student[] temp = new Student[2*tab.length];
            System.arraycopy(tab,0,temp,0,size-1);
            tab = temp;
        }
    }

    public Student[] getTab() {
        return tab;
    }

    public void viewDictionary(){
        for (int i = 0; i < tab.length; i++) {
            if(tab[i] == null)
                return;
            System.out.println(tab[i]);
        }
    }

    public boolean contains(Student st){
        if(get(st) < 0) {
            return false;
        } else {
            return true;
        }
    }
}
