package lab5.Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class QuickSort<E> implements Sort<E>{

    Comparator<E> comp;
    int compCounter;
    int swapCounter;
    Random rand = new Random();

    public ArrayList<E> sort(ArrayList<E> list, Comparator<E> comp){
        compCounter = 0;
        swapCounter = 0;
        this.comp = comp;
        quickSort(list, 0, list.size());
        System.out.println("Number of comparisons: " + compCounter);
        System.out.println("Number of swaps: " + swapCounter);

        return list;
    }

    private void quickSort(ArrayList<E> list, int begin, int end){
        if(end - begin > 1){
            int part = partition(list, begin, end);
            quickSort(list, begin, part);
            quickSort(list, part + 1, end);
        }
    }

    private int partition(ArrayList<E> list, int left, int right){
        int pivot = left + rand.nextInt(right - left);
        E pivotVal = list.get(pivot);
        swap(list, left, pivot);
        swapCounter++;
        int searchB = left + 1;
        int searchS = right - 1;

        do{
            compCounter += 2;   //dla obu petli while - warunek niespelniony
            while(searchB <= searchS && comp.compare(list.get(searchB), pivotVal) <= 0) {
                searchB++;
                compCounter++;
            }
            while(comp.compare(list.get(searchS), pivotVal) > 0) {
                searchS--;
                compCounter++;
            }
            if(searchB < searchS) {
                swap(list, searchB, searchS);
                swapCounter++;
            }
        } while(searchB < searchS);
        swap(list, searchS, left);
        swapCounter++;

        return searchS;
    }

    private void swap(ArrayList<E> list, int left, int right){
        if(left != right) {
            E temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
        }
    }
}
