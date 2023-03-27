package lab6.Zad4;

import java.util.ArrayList;
import java.util.Comparator;

public class InsertSort<E> {

    int moveCounter;
    int compCounter;

    public void sort(ArrayList<E> list, Comparator<E> comp){
        compCounter = 0;
        moveCounter = 0;

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
    }

    public int getMoveCounter() {
        return moveCounter;
    }

    public int getCompCounter() {
        return compCounter;
    }
}
