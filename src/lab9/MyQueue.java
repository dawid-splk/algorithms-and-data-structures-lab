package lab9;

import java.util.NoSuchElementException;

public class MyQueue<E> {

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
    Element tail = head;

    public Element getElement(int index){
        if(index<0) throw new IndexOutOfBoundsException();
        if(index == 0)
            return head;
        Element actElem = head;
        while(index > 0 && actElem != null){
            index--;
            actElem=actElem.getNext();
        }
        if (actElem==null)
            throw new IndexOutOfBoundsException();
        return actElem;
    }

    public E get(int index) {
        Element actElem=getElement(index);
        return actElem.getValue(); }

    public int size() {
        int pos=0;
        Element actElem=head;
        while(actElem!=null)
        {
            pos++;
            actElem=actElem.getNext();
        }
        return pos;}

    public boolean enqueue(E e) {
        Element newElem = new Element(e);
        if(head==null){
            head=newElem;
            newElem.setNext(null);
            tail = head;
            return true;
        }
        if(tail == head){
            tail = newElem;
            newElem.setNext(null);
            head.setNext(newElem);
        } else {
            tail.setNext(newElem);
            tail = newElem;
            tail.setNext(null);
        }
        return true;
    }

    public E dequeue() {
        if(head == null){
            System.out.print("Kolejka jest pusta!");
            throw new NoSuchElementException();
        }
        E retValue = head.getValue();
        head = head.getNext();
        return retValue;
    }
}

