package lab4.Zad1;

import java.util.EmptyStackException;

public class FixedSizeArrayStack<T> {

    private T [] array;
    private int lastIndex;

    @SuppressWarnings("unchecked")
    public FixedSizeArrayStack(int size){
        this.array = (T[]) new Object[size];
        lastIndex = 0;
    }

    public boolean push(T value) throws FullStackException {
        if(lastIndex < array.length){
            array[lastIndex] = value;
            lastIndex++;
            return true;
        } else {
            System.out.println("Stack is full!");
            throw new FullStackException();
        }
    }

    public T pop() throws EmptyStackException{
        if(lastIndex != 0){
            T retValue = array[lastIndex - 1];
            array[lastIndex - 1] = null;
            lastIndex--;
            return retValue;
        } else {
            System.out.println("Stack is empty!");
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty(){
        return lastIndex == 0;
    }

    public boolean isFull(){
        return lastIndex == array.length;
    }

}
