package lab5.Sort;

import java.util.ArrayList;
import java.util.Comparator;

public class SelectSort<E> implements Sort<E> {

    public ArrayList<E> sort(ArrayList<E> list, Comparator<E> comp){

        int smallest;
        int swapCounter = 0;
        int compCounter = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            smallest = i;
            for (int j = i; j < list.size(); j++) {
                compCounter++;
                if(comp.compare(list.get(smallest), list.get(j)) > 0){
                    smallest = j;
                }
            }
            if(smallest != i) {
                E temp = list.get(smallest);
                list.set(smallest, list.get(i));
                list.set(i, temp);
                swapCounter++;
            }
        }

        System.out.println("Number of comparisons: " + compCounter);
        System.out.println("Number of swaps: " + swapCounter);
        return list;
    }
}
