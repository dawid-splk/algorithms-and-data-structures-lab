package lab5.Sort;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapSort<E> implements Sort<E>{

    Comparator<E> comp;
    int compCounter;
    int swapCounter;

    @Override
    public ArrayList<E> sort(ArrayList<E> list, Comparator<E> comp) {
        this.comp = comp;
        compCounter = 0;
        swapCounter = 0;
        heapSort(list, list.size());
        System.out.println("Number of comparisons: " + compCounter);
        System.out.println("Number of swaps: " + swapCounter);

        return list;
    }

    private void heapSort(ArrayList<E> list, int heapSize) {
        createHeap(list, heapSize);
        for (int i = 0; i < heapSize;) {
            heapSize--;
            swap(list, 0, heapSize);    //index= size - 1
            sink(list, 0, heapSize);  //size= size - 1
        }
    }

    private void createHeap(ArrayList<E> list, int heapSize) {
        for (int i = (heapSize-1)/2; i >= 0; i--) {
            sink(list, i, list.size());
        }
    }

    private void sink(ArrayList<E> list, int index, int heapSize) {
        int childIdx = 2 * index + 1;
        if(childIdx < heapSize){
            compCounter++;
            if(childIdx + 1 < heapSize){
                compCounter++;
                if(comp.compare(list.get(childIdx), list.get(childIdx + 1)) < 0)
                    childIdx++;
            }
            if(comp.compare(list.get(index), list.get(childIdx)) < 0){
                swap(list, index, childIdx);
                sink(list, childIdx, heapSize);
            }
        }
    }

    private void swap(ArrayList<E> list, int left, int right){
        swapCounter++;
        if(left != right) {
            E temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
        }
    }
}
