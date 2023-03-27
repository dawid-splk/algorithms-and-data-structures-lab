package lab2;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;

public class TwoWayCycledList<E> extends AbstractList<E> {

    private class Element {
        private E value;
        private Element next;
        private Element prev;

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public Element getPrev() {
            return prev;
        }

        public void setPrev(Element prev) {
            this.prev = prev;
        }

        Element(E data) {
            this.value = data;
        }


        public void insertAfter(Element elem) {
            elem.setNext(this.getNext());
            elem.setPrev(this);
            this.getNext().setPrev(elem);
            this.setNext(elem);
        }

        public void insertBefore(Element elem) {
            elem.setNext(this);
            elem.setPrev(this.getPrev());
            this.getPrev().setNext(elem);
            this.setPrev(elem);
        }

        public void remove() {
            this.getNext().setPrev(this.getPrev());
            this.getPrev().setNext(this.getNext());
        }
    }

    Element sentinel = null;

    public TwoWayCycledList() {
        sentinel=new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);}

    private Element getElement(int index){
        if(index<0) throw new IndexOutOfBoundsException();
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel && counter<index){
            counter++;
            elem=elem.getNext();}
        if(elem==sentinel)
            throw new IndexOutOfBoundsException();
        return elem;}

    private Element getElement(E value) {
        Element elem = sentinel.getNext();
        while (elem != sentinel && !value.equals(elem.getValue())) {
            elem = elem.getNext();
        }
        if (elem == sentinel)
            return null;
        return elem;
    }

    public boolean isEmpty() {
        return sentinel.getNext()==sentinel;}

    public void clear() {
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);}

    public boolean contains(Object value) {
        return indexOf(value)!=-1;}

    public E get(int index) {
        Element elem=getElement(index);
        return elem.getValue();}

    public E set(int index, E value) {
        Element elem=getElement(index);
        E retValue=elem.getValue();
        elem.setValue(value);
        return retValue;}

    public boolean add(E value) {
        Element newElem=new Element(value);
        sentinel.insertBefore(newElem);
        return true;}

    public void add(int index, E value) {
        Element newElem=new Element(value);
        if(index==0) sentinel.insertAfter(newElem);
        else{
            Element elem=getElement(index-1);
            elem.insertAfter(newElem);}
        return;
    }

    public int indexOf(Object value) {
        E castedValue = (E) value;
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel && !elem.getValue().equals(castedValue)){
            counter++;
            elem=elem.getNext();}
        if(elem==sentinel)
            return -1;
        return counter;
    }

    public E remove(int index) {
        Element elem=getElement(index);
        elem.remove();
        return elem.getValue();
    }

    public boolean remove(Object value) {
        Element elem = getElement((E) value);
        if(elem == null) return false;
        elem.remove();
        return true;
    }

    public int size() {
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel){
            counter++;
            elem=elem.getNext();}
        return counter;
    }

    public void viewList(){
        Iterator<E> iter = this.iterator();
        while(iter.hasNext()){
            E e = iter.next();
            System.out.println(e);
        }
    }

    public TwoWayCycledList<E> insertListIndex(TwoWayCycledList<E> otherList, int index){
//        System.out.println("Wstawianie drugiej listy przed element o indeksie: " + index);
        Element elem = sentinel.getNext();
        int counter = 0;
        while(elem!=sentinel && counter < index){
            counter++;
            elem=elem.getNext();
        }
        if(elem==sentinel || index < 0) {
            System.out.println("\nLista nie zawiera elementu o takim indeksie! Nie polaczono list.\n");
            throw new IndexOutOfBoundsException();
        }

        otherList.sentinel.getPrev().setNext(elem);
        otherList.sentinel.getNext().setPrev(elem.getPrev());
        elem.getPrev().setNext(otherList.sentinel.getNext());
        elem.setPrev(otherList.sentinel.getPrev());

        return this;
    }

    public TwoWayCycledList<E> insertListEquals(TwoWayCycledList<E> otherList, E value){
//        System.out.println("Wstawianie drugiej listy przed wskazany metoda 'equals' element : ");
        Element elem = sentinel.getNext();
        while(elem != sentinel && !elem.getValue().equals(value)){
            elem = elem.getNext();
        }
        if(elem == sentinel){
            System.out.println("\nNie znaleziono studenta w bazie! Nie polaczono list.\n");
            throw new IndexOutOfBoundsException();
        }

        otherList.sentinel.getPrev().setNext(elem);
        otherList.sentinel.getNext().setPrev(elem.getPrev());
        elem.getPrev().setNext(otherList.sentinel.getNext());
        elem.setPrev(otherList.sentinel.getPrev());

        return this;
    }

    public TwoWayCycledList<E> insertListEnd(TwoWayCycledList<E> otherList){
        System.out.println("Wstawianie drugiej listy na koniec biezacej listy: ");
        otherList.sentinel.getPrev().setNext(sentinel);
        otherList.sentinel.getNext().setPrev(sentinel.getPrev());
        sentinel.getPrev().setNext(otherList.sentinel.getNext());
        sentinel.setPrev(otherList.sentinel.getPrev());

        return this;
    }

    public Iterator<E> iterator() {
        return new TWCIterator();
    }

    private class TWCIterator implements Iterator<E>{

        Element _current=sentinel;
        public boolean hasNext() {
            return _current.getNext()!=sentinel;}

        public E next() {
            _current=_current.getNext();
            return _current.getValue();}

    }
    public ListIterator<E> listIterator() {
        return new TWCListIterator();
    }

    private class TWCListIterator implements ListIterator<E>{
        boolean wasNext=false;
        boolean wasPrevious=false;
        Element _current=sentinel;

        public boolean hasNext() {
            return _current.getNext()!=sentinel;
        }

        public boolean hasPrevious() {
            return _current!=sentinel;
        }

        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        public E next() {
            wasNext=true;
            wasPrevious=false;
            _current=_current.getNext();
            return _current.getValue();
        }

        public E previous() {
            wasNext=false;
            wasPrevious=true;
            E retValue=_current.getValue();
            _current=_current.getPrev();
            return retValue;
        }

        public void remove() {
            if(wasNext){
                Element curr=_current.getPrev();
                _current.remove();
                _current=curr;
                wasNext=false;
            }
            if(wasPrevious){
                _current.getNext().remove();
                wasPrevious=false;
            }
        }

        public void add(E data) {
            Element newElem=new Element(data);
            _current.insertAfter(newElem);
            _current=_current.getNext();
        }

        public void set(E data) {
            if(wasNext){
                _current.setValue(data);
                wasNext=false;}
            if(wasPrevious){
                _current.getNext().setValue(data);
                wasPrevious=false;}
        }
    }


}

//current.getNext().setPrev(sentinel2.getPrev());
//sentinel.getPrev().setNext(current.next);
//current.setNext(sentinel.getNext())
//sentinel.getNext().setPrev(current);

//        elem.getPrev().setNext(otherList.sentinel);
//        elem.setPrev(otherList.getLast());
////        otherList.sentinel.remove();
//        otherList.sentinel.getPrev().setNext(otherList.sentinel.getNext());
//        otherList.sentinel.getNext().setPrev(otherList.sentinel.getPrev());