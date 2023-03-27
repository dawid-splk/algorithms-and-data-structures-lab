package lab1;

import java.util.Iterator;

public class FilterIterator <Student> implements Iterator<Student> {

    private Iterator<Student> iterator;
    private Predicate<Student> predykat;
    private boolean bHasNext = true;
    private Student currentValid;

    public FilterIterator(Iterator<Student> iterator, Predicate<Student> predykat){
        super();
        this.iterator = iterator;
        this.predykat = predykat;
        findNextValid();
    }

    private void findNextValid(){
        while(iterator.hasNext()){
            currentValid = iterator.next();
            if(predykat.accept(currentValid)){
                return;
            }
        }
        bHasNext = false;
        currentValid = null;
    }

    @Override
    public boolean hasNext(){
        return bHasNext;
    }

    @Override
    public Student next(){
        Student st = currentValid;
        findNextValid();
        return st;
    }

}
