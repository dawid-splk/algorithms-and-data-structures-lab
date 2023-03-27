package lab5.Sort;

import java.util.ArrayList;
import java.util.Comparator;

public class InsertSort<E> implements Sort<E> {

    public ArrayList<E> sort(ArrayList<E> list, Comparator<E> comp){

        int moveCounter = 0;
        int compCounter = 0;
        for (int i = 1; i < list.size(); i++) {
            E value = list.get(i);
            E temp;
            int j;
            for (j = i; j > 0 && comp.compare(value, temp = list.get(j - 1)) < 0; j--) {
                list.set(j, temp);
                moveCounter++;
                compCounter++;
            }
            if(j != 0)
                compCounter++;
            list.set(j, value);
            moveCounter++;

        }
        System.out.println("Number of comparisons: " + compCounter);
        System.out.println("Number of reallocations: " + moveCounter);
        return list;
    }
}
