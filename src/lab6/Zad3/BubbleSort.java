package lab6.Zad3;

import java.util.ArrayList;
import java.util.Comparator;

public class BubbleSort<E> {

    int swapCounter = 0;
    int compCounter = 0;

    public ArrayList<E> sort(ArrayList<E> list, Comparator<E> comp){

        boolean alreadySorted = true;
        int bound = list.size() - 1;

        for (int j = 0; j < list.size() - 1; j++) {
            alreadySorted = true;
            for (int i = 0; i < bound; i++) {
                if (comp.compare(list.get(i), list.get(i + 1)) > 0) {
                    E temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapCounter++;
                    alreadySorted = false;
                }
                compCounter++;
            }
            if(alreadySorted) {
                return list;
            } else {
                bound--;
            }
        }
        return list;
    }

    public int getSwapCounter() {
        return swapCounter;
    }

    public int getCompCounter() {
        return compCounter;
    }
}
