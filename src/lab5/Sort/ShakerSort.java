package lab5.Sort;

import java.util.ArrayList;
import java.util.Comparator;

public class ShakerSort<E> implements Sort<E>{

    public ArrayList<E> sort(ArrayList<E> list, Comparator<E> comp){

        boolean alreadySorted = false;
        boolean goRight = true;
        int rightIndex = list.size() - 1;
        int leftIndex = 0;
        int lastSwap = 0;

        int compCounter = 0;
        int swapCounter = 0;

        for (int i = 0; i < list.size() - 1 && !alreadySorted; i++) {
            alreadySorted = true;
            if(goRight){
                for (int j = leftIndex; j < rightIndex; j++) {
                    compCounter++;
                    if(comp.compare(list.get(j), list.get(j+1)) > 0){
                        swap(list, j, j+1);
                        lastSwap = j;
                        alreadySorted = false;
                        swapCounter++;
                    }
                }
                rightIndex = lastSwap;

            } else {
                for (int j = rightIndex; j > leftIndex; j--) {
                    compCounter++;
                    if (comp.compare(list.get(j), list.get(j - 1)) < 0) {
                        swap(list, j, j - 1);
                        lastSwap = j;
                        alreadySorted = false;
                        swapCounter++;
                    }
                }
                leftIndex = lastSwap;
            }
            goRight = i % 2 == 1;
        }
        System.out.println("Number of comparisons: " + compCounter);
        System.out.println("Number of swaps: " + swapCounter);
        return list;
    }

    public void swap(ArrayList<E> list, int left, int right){
        E temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }
}
