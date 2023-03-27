package lab4.Zad4;

public class DynamicSizeStack<T> {

    private T[] array;
    private int lastIndex;

    @SuppressWarnings("unchecked")
    public DynamicSizeStack(int size){
        array = (T[]) new Object[size];
        lastIndex = 0;
    }

    @SuppressWarnings("unchecked")
    public void push(T value){
        if(lastIndex + 1 >= array.length*3/4){      //warunek: czy po dodaniu elementu tablica osiagnie 3/4 swojej pojemnosci?
            T[] temp = (T[]) new Object[2* array.length];
            System.arraycopy(array, 0, temp, 0, lastIndex);     //kopiowanie n elementow tablicy oryginalnej
            array = temp;
//            size = array.length;    //aktualizacja pola size
        }
        array[lastIndex] = value;
        lastIndex++;
    }

    @SuppressWarnings("unchecked")
    public T pop(){
        T retValue = array[lastIndex - 1];      //przechowanie wartości zwracanej
        if(lastIndex - 1 <= array.length/4){
            T[] temp = (T[]) new Object[array.length/2];
            System.arraycopy(array, 0, temp, 0, lastIndex - 1);     //usuwanie elementu z wierzchu
            array = temp;       //poprzez kopiowanie n-1 elementow tablicy oryginalnej, length-1 == lastIndex-1
//            size = array.length;
        } else {
            array[lastIndex - 1] = null;
        }
        lastIndex--;
        return retValue;
    }

    public int getSize() {
        return array.length;
    }

    public int getLastIndex() {
        return lastIndex;
    }
}
