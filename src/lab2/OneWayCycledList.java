package lab2;

import java.util.AbstractList;

public class OneWayCycledList<E> extends AbstractList<E> {

    private class Element{
        private E value;
        private Element next;

        public Element(E value) {
            this.value = value;
        }

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
    }

    Element head = null;

    public boolean isEmpty(){
        return head == null;
    }

    @Override
    public void clear(){
        head = null;
    }

    @Override
    public int size(){
        int pos = 0;
        if(head == null)
            return 0;
        if(head.getNext() == head)
            return 1;
        Element elem = head.getNext();
        pos++;
        while(elem != head){
            pos++;
            elem = elem.getNext();
        }
        return pos;
    }

    private Element getElement(int index){
        if(index<0) throw new IndexOutOfBoundsException();
        if(index == 0)
            return head;
        Element elem = head;
        int counter = 0;
        do {
            counter++;
            elem = elem.getNext();
        } while(elem != head && counter < index);
        if(elem == head)
            throw new IndexOutOfBoundsException();
        return elem;
    }

    @Override
    public E get(int index){
        Element actElem = getElement(index);
        return actElem.getValue();
    }

    @Override
    public boolean add(E e){
        Element newElem = new Element(e);
        if(head == null){
            head = newElem;
            newElem.setNext(head);
            return true;
        }
        Element tail = head;
        while(tail.getNext() != head){
            tail = tail.getNext();
        }
        tail.setNext(newElem);
        newElem.setNext(head);
        return true;
    }

    @Override
    public boolean remove(Object data){
        if(head == null)
            return false;
        E value = (E) data;
        if(value.equals(head.getValue())){
//            System.out.println("Osoba z numerem " + head.getValue() + " umiera.");
            getElement(size() - 1).setNext(head.getNext());
            head = head.getNext();
            return true;
        }
        Element actElem = head;
        while(actElem.getNext() != head && !value.equals(actElem.getNext().getValue())){
            actElem = actElem.getNext();
        }
        if(actElem.getNext() == head)
            return false;
//        System.out.println("Osoba z numerem " + head.getValue() + " umiera.");
        actElem.setNext(actElem.getNext().getNext());
        return true;
    }

    public OneWayCycledList<E> avoidDeath(int k){
        if(size() <= 0){
            System.out.println("Brak osob w kole.");
            return this;
        }
        if(size() <= 2) {
            System.out.println("Wszyscy przezyja - liczba osob w kole nie jest wieksza niz 2.");
            return this;
        }
        Element current = head;
        for (int i = 0; i < k-1; i++) {
            current = current.getNext();
        }
        System.out.println("Osoba z numerem " + current.getValue() + " umiera.");
        remove(current.getValue());

        while(size() > 2){
            for (int i = 0; i < k; i++) {
                current = current.getNext();
            }
            System.out.println("Osoba z numerem " + current.getValue() + " umiera.");
            remove(current.getValue());
        }
        return this;
    }

}
