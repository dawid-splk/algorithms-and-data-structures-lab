package lab6.Zad4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class Shellsort<E> {

    Gap gapMethod;
    Comparator<E> comp;


    public Shellsort(Gap gapMethod, Comparator<E> comp){
        this.gapMethod = gapMethod;
        this.comp = comp;
    }

    public void sort(ArrayList<E> list){
        Stack<Integer> gaps = gapMethod.createGaps(list.size());
        InsertSort<E> tempSorter = new InsertSort<>();
        int gap;
        ArrayList<E> temp = new ArrayList<>();
        int reruns = gaps.size();
        int totalCompCounter = 0;
        int totalMoveCounter = 0;

        for (int i = 0; i < reruns; i++) {     //dla każdego wyznaczonego gapa
            gap = gaps.pop();
            for (int j = 0; j < gap; j++) {     //dla każdego elementu pomiedzy 0 a jego nastepnikiem odleglym o gap
                for (int k = j; k < list.size(); k += gap) {    //zabieramy elementy odległe od siebie o gap
                    temp.add(list.get(k));
                }

                tempSorter.sort(temp,comp);    //sortowanie powybieranych elementow
                totalCompCounter += tempSorter.getCompCounter();
                totalMoveCounter += tempSorter.getMoveCounter();

                int tempIdx = 0;
                for (int k = j; k < list.size(); k += gap) {
                    list.set(k, temp.get(tempIdx++));   //wklejenie ich z powrotem do tablicy wejsciowej
                }
                temp.clear();
            }
        }
        System.out.println("Number of comparisons: " + totalCompCounter);
        System.out.println("Number of reallocations: " + totalMoveCounter);
    }
}
