package lab7.Zad1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class KolejkaPriorytetowa<T> {

    private ArrayList<T> list;
    private Comparator<T> comp;

    public KolejkaPriorytetowa(Comparator<T> comp){
        list = new ArrayList<>();
        this.comp = comp;
    }

    public void enqueue(T value){
        list.add(value);
        swim(list.size() - 1);
    }

    public T dequeue() throws NoSuchElementException {

        if(list.isEmpty())
            throw new NoSuchElementException();

        T retValue = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        sink();

        return retValue;
    }

    private void swim(int index){
        int parent = (index - 1) / 2;
        while(index != 0 && comp.compare(list.get(parent), list.get(index)) > 0){
            swap(parent, index);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void sink(){
        int sinkerIndex = 0;
        int child;

        while((child = 2*sinkerIndex + 1) < list.size() - 1){           //size czy size -1 ?? nullpointerexception
            if(comp.compare(list.get(child), list.get(child+1)) > 0){
                child++;
            }
            if(comp.compare(list.get(sinkerIndex), list.get(child)) > 0){
                swap(sinkerIndex, child);
                sinkerIndex = child;
            } else {
                return;
            }
        }
    }

    private void swap(int left, int right){
        T temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }
}
