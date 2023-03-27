package lab1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorTablicowy<Student> implements Iterator<Student> {

    private int pos = 0;
    private Student[] tab;

    public IteratorTablicowy(Student[] tablica){
        this.tab = tablica;
    }

    public Student first() throws NoSuchElementException{
        if(tab[0] != null){
            pos = 1;
            return tab[0];
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean hasNext() {
        return pos < tab.length;
    }

    @Override
    public Student next() throws NoSuchElementException {
        if(hasNext()) {
            pos++;
            return tab[pos - 1];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }
}
