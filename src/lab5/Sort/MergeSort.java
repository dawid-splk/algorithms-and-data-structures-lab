package lab5.Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class MergeSort<E> implements Sort<E>{

    Comparator<E> comp;
    int compCounter;
    int moveCounter;


    public ArrayList<E> sort(ArrayList<E> list, Comparator<E> comp){
        compCounter = 0;
        moveCounter = 0;
        this.comp = comp;
        ArrayList<E> result = mergeSort(list, 0, list.size() - 1);
        System.out.println("Number of comparisons: " + compCounter);
        System.out.println("Number of reallocations: " + moveCounter);

        return result;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<E> mergeSort(ArrayList<E> list, int startIndex, int endIndex){
        if(startIndex == endIndex){
            ArrayList<E> result = (ArrayList<E>) new ArrayList<Object>();
            result.add(list.get(startIndex));
            return result;
        }

        int splitIndex = startIndex + (endIndex - startIndex) / 2;
        return merge(mergeSort(list, startIndex, splitIndex), mergeSort(list, splitIndex + 1, endIndex));
    }

    @SuppressWarnings("unchecked")
    private ArrayList<E> merge(ArrayList<E> list1, ArrayList<E> list2){
        ArrayList<E> result = (ArrayList<E>) new ArrayList<Object>();
        Iterator<E> it1 = list1.iterator();
        Iterator<E> it2 = list2.iterator();

        E elem1 = null, elem2 = null;
        boolean cont1, cont2;

        if(cont1 = it1.hasNext()) {
            elem1 = it1.next();
        }
        if(cont2 = it2.hasNext()) {
            elem2 = it2.next();
        }

        while (cont1 && cont2) {
            compCounter++;
            if(comp.compare(elem1, elem2) <= 0){
                result.add(elem1);
                moveCounter++;
                if(cont1 = it1.hasNext()){
                    elem1 = it1.next();
                } else {
                    result.add(elem2);
                    moveCounter++;
                }
            } else {
                result.add(elem2);
                moveCounter++;
                if (cont2 = it2.hasNext()) {
                    elem2 = it2.next();
                } else {
                    result.add(elem1);
                    moveCounter++;
                }
            }
        }
        while(it1.hasNext()) {
            result.add(it1.next());
            moveCounter++;
        }
        while(it2.hasNext()) {
            result.add(it2.next());
            moveCounter++;
        }
        return result;
    }

    public int getCompCounter() {
        return compCounter;
    }

    public int getMoveCounter() {
        return moveCounter;
    }
}
