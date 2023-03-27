package lab5.Sort;

import java.util.ArrayList;
import java.util.Comparator;

public interface Sort <E> {

    public ArrayList<E> sort(ArrayList<E> list, Comparator<E> comp);

}
